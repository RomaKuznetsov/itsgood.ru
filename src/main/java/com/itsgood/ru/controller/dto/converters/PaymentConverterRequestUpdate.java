package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import org.springframework.core.convert.converter.Converter;

public interface PaymentConverterRequestUpdate extends Converter<PaymentRequestUpdate, HibernatePayment> {
}
