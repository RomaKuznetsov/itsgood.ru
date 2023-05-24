package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.codes.StatusPayment;
import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.domain.PaymentDTO;
import com.itsgood.ru.repository.PaymentDataRepository;
import com.itsgood.ru.service.CustomerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentConverterRequestUpdateImpl implements PaymentConverterRequestUpdate {
    private final PaymentDataRepository paymentDataRepository;
    private final CustomerDataService customerDataService;

    @Override
    public PaymentDTO convert(PaymentRequestUpdate request) {
        Optional<PaymentDTO> searchResult = paymentDataRepository.findById(request.getId());
        PaymentDTO paymentDTO = searchResult.orElseThrow(EntityExistsException::new);
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        if (request.getStatus().contains(StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus())) {
            Optional<PaymentDTO> searchPayment = paymentDataRepository.
                    findActivePaymentByCustomerAndStatus(customerDTO, StatusPayment.STATUS_PAYMENT_INACTIVE.getStatus());
            if (searchResult.isPresent()) {
                PaymentDTO paymentDTOAcIn = searchPayment.get();
                paymentDTOAcIn.setStatus(StatusPayment.STATUS_PAYMENT_INACTIVE.getStatus());
                paymentDataRepository.save(paymentDTOAcIn);
            }
        }
        paymentDTO.setStatus(request.getStatus());
        List<PaymentDTO> searchPaymentsByPhone = paymentDataRepository.findPaymentDTOByPhone(request.getPhone());
        if (searchPaymentsByPhone.isEmpty()) {
            paymentDTO.setPhone(request.getPhone());
        } else throw new EntityExistsException("there is already a payment with this number phone");
        return paymentDTO;
    }
}
