package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.domain.ContractDTO;
import org.springframework.core.convert.converter.Converter;

public interface ContractConverterRequestCreate extends Converter<ContractRequestCreate, ContractDTO> {
}
