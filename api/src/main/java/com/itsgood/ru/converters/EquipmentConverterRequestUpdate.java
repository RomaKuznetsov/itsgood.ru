package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.equipment.EquipmentRequestUpdate;
import com.itsgood.ru.domain.hibernate.EquipmentDTO;
import org.springframework.core.convert.converter.Converter;

public interface EquipmentConverterRequestUpdate extends Converter<EquipmentRequestUpdate, EquipmentDTO> {
}
