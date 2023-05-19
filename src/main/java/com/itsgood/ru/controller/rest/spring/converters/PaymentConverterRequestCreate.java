package com.itsgood.ru.controller.rest.spring.converters;

import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import org.springframework.core.convert.converter.Converter;

public interface PaymentConverterRequestCreate extends Converter<PaymentRequestCreate, HibernatePayment> {
}
