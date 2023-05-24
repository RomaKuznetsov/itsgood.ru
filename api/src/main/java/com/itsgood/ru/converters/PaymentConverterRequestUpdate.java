package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.domain.PaymentDTO;
import org.springframework.core.convert.converter.Converter;

public interface PaymentConverterRequestUpdate extends Converter<PaymentRequestUpdate, PaymentDTO> {
}
