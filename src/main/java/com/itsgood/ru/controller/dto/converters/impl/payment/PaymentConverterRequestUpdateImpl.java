package com.itsgood.ru.controller.dto.converters.impl.payment;

import com.itsgood.ru.controller.dto.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernatePayment;

public class PaymentConverterRequestUpdateImpl implements PaymentConverterRequestUpdate {

    @Override
    public HibernatePayment convert(PaymentRequestUpdate request) {
        HibernatePayment hibernatePayment = new HibernatePayment();
        hibernatePayment.setId(request.getId());
        hibernatePayment.setStatus(request.getStatus());
        hibernatePayment.setPhone(request.getPhone());
        return hibernatePayment;
    }
}
