package com.itsgood.ru.controller.dto.converters.impl.payment;

import com.itsgood.ru.controller.dto.converters.PaymentConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestCreate;
import com.itsgood.ru.controller.service.CustomerDataService;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverterRequestCreateImpl implements PaymentConverterRequestCreate {

    @Override
    public HibernatePayment convert(PaymentRequestCreate request) {
        HibernatePayment hibernatePayment = new HibernatePayment();
        hibernatePayment.setStatus(request.getStatus());
        hibernatePayment.setPhone(request.getPhone());
        hibernatePayment.setCard(request.getCard());
        hibernatePayment.setValidity(request.getValidity());
        return hibernatePayment;
    }
}
