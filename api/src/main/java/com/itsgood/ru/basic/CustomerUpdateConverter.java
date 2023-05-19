package com.itsgood.ru.basic;

import com.itsgood.ru.domain.hibernate.HibernateCustomer;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerUpdateConverter extends CustomerBaseConverter<CustomerUpdateRequest, HibernateCustomer> {

    private final CustomerDataRepository dataRepository;

    @Override
    public HibernateCustomer convert(CustomerUpdateRequest request) {
        Optional<HibernateCustomer> hibernateCustomer = dataRepository.findById(Math.toIntExact(request.getId()));
        return doConvert(hibernateCustomer.orElseThrow(EntityNotFoundException::new), request);
    }

}
