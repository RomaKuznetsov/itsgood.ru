package com.itsgood.ru.controller.dto.converters;

import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import org.springframework.core.convert.converter.Converter;

public interface ContractConverterRequestCreate extends Converter<ContractRequestCreate, HibernateContract> {
}
