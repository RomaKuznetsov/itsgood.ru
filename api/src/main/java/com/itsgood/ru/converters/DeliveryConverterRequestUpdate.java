package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.delivery.DeliveryRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernateDelivery;
import org.springframework.core.convert.converter.Converter;

public interface DeliveryConverterRequestUpdate extends Converter<DeliveryRequestUpdate, HibernateDelivery> {
}
