package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernatePayment;
import org.springframework.core.convert.converter.Converter;

public interface PaymentConverterRequestCreate extends Converter<PaymentRequestCreate, HibernatePayment> {
}
