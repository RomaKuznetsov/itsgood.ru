package com.itsgood.ru.controller.rest.spring.converters;

import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestUpdate;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import org.springframework.core.convert.converter.Converter;

public interface ContractConverterRequestUpdate extends Converter<ContractRequestUpdate, HibernateContract> {
}
