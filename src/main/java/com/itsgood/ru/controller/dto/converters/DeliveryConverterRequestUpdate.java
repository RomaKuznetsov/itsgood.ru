package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestUpdate;
import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import org.springframework.core.convert.converter.Converter;

public interface DeliveryConverterRequestUpdate extends Converter<DeliveryRequestUpdate, HibernateDelivery> {
}
