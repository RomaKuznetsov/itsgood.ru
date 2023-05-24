package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.domain.PaymentDTO;
import org.springframework.core.convert.converter.Converter;

public interface PaymentConverterRequestCreate extends Converter<PaymentRequestCreate, PaymentDTO> {
}
