package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.equipment.EquipmentRequestCreate;
import com.itsgood.ru.domain.EquipmentDTO;
import org.springframework.core.convert.converter.Converter;

public interface EquipmentConverterRequestCreate extends Converter<EquipmentRequestCreate, EquipmentDTO> {
}
