package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.domain.hibernate.HibernateContract;
import org.springframework.core.convert.converter.Converter;

public interface ContractConverterRequestCreate extends Converter<ContractRequestCreate, HibernateContract> {
}
