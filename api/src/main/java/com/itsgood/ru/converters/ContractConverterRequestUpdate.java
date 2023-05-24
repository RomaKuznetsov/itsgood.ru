package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;;
import com.itsgood.ru.domain.ContractDTO;
import org.springframework.core.convert.converter.Converter;

public interface ContractConverterRequestUpdate extends Converter<ContractRequestUpdate, ContractDTO> {
}
