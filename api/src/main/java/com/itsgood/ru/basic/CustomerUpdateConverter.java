package com.itsgood.ru.basic;

import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.repository.CustomerDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerUpdateConverter extends CustomerBaseConverter<CustomerUpdateRequest, CustomerDTO> {

    private final CustomerDataRepository dataRepository;

    @Override
    public CustomerDTO convert(CustomerUpdateRequest request) {
        Optional<CustomerDTO> hibernateCustomer = dataRepository.findById(Math.toIntExact(request.getId()));
        return doConvert(hibernateCustomer.orElseThrow(EntityNotFoundException::new), request);
    }

}
