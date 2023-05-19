package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import org.springframework.core.convert.converter.Converter;

public interface DeliveryConverterRequestCreate extends Converter<DeliveryRequestCreate, HibernateDelivery> {
}
