package com.itsgood.ru.basic;

import com.itsgood.ru.domain.hibernate.AuthenticationInfo;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCreateConverter extends CustomerBaseConverter<CustomerCreateRequest, CustomerDTO> {

    @Override
    public CustomerDTO convert(CustomerCreateRequest request) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setPassword(request.getPassword());
        authenticationInfo.setUsername(request.getUsername());
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAuthenticationInfo(authenticationInfo);
        return doConvert(customerDTO, request);

    }

}
