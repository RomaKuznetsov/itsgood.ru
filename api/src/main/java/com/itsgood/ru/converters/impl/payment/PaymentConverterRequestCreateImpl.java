package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.codes.StatusPayment;
import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.converters.PaymentConverterRequestCreate;
import com.itsgood.ru.domain.PaymentDTO;
import com.itsgood.ru.repository.spring.PaymentDataRepository;
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
        paymentDTO.setPhone(request.getPhone());
        Optional<PaymentDTO> searchPayment = paymentDataRepository.findPaymentDTOByCard(request.getCard());
        if (searchPayment.isEmpty()) {
            paymentDTO.setCard(request.getCard());
        } else throw new EntityExistsException("card with this number already exists");
        paymentDTO.setValidity(request.getValidity());
        return paymentDTO;
    }
}
