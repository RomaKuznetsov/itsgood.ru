package com.itsgood.ru.basic;

import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCreateConverter extends CustomerBaseConverter<CustomerCreateRequest, HibernateCustomer> {

    @Override
    public HibernateCustomer convert(CustomerCreateRequest request) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setPassword(request.getPassword());
        authenticationInfo.setUsername(request.getUsername());
        HibernateCustomer hibernateCustomer = new HibernateCustomer();
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return doConvert(hibernateCustomer, request);

    }

}
