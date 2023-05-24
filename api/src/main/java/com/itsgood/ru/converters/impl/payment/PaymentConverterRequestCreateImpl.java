package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.codes.StatusPayment;
import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.converters.PaymentConverterRequestCreate;
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
public class PaymentConverterRequestCreateImpl implements PaymentConverterRequestCreate {
    private final PaymentDataRepository paymentDataRepository;

    @Override
    public PaymentDTO convert(PaymentRequestCreate request) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setStatus(StatusPayment.STATUS_PAYMENT_INACTIVE.getStatus());
        List<PaymentDTO> searchPaymentsByPhone = paymentDataRepository.findPaymentDTOByPhone(request.getPhone());
        if (searchPaymentsByPhone.isEmpty()) {
            paymentDTO.setPhone(request.getPhone());
        } else throw new EntityExistsException("there is already a payment with this number phone");
        List<PaymentDTO> searchPaymentByCard = paymentDataRepository.findPaymentDTOByCard(Long.valueOf(request.getCard()));
        if (searchPaymentByCard.isEmpty()) {
            paymentDTO.setCard(request.getCard());
        } else throw new EntityExistsException("there is already a payment with this number card");
        paymentDTO.setValidity(request.getValidity());
        return paymentDTO;
    }
}
