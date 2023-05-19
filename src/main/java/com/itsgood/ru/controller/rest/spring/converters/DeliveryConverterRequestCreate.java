package com.itsgood.ru.controller.rest.spring.converters;

import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import org.springframework.core.convert.converter.Converter;

public interface DeliveryConverterRequestCreate extends Converter<DeliveryRequestCreate, HibernateDelivery> {
}
