package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.converters.PaymentConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverterRequestCreateImpl implements PaymentConverterRequestCreate {

    @Override
    public PaymentDTO convert(PaymentRequestCreate request) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setStatus(request.getStatus());
        paymentDTO.setPhone(request.getPhone());
        paymentDTO.setCard(request.getCard());
        paymentDTO.setValidity(request.getValidity());
        return paymentDTO;
    }
}
