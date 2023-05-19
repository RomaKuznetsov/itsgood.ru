package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.converters.PaymentConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernatePayment;
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
