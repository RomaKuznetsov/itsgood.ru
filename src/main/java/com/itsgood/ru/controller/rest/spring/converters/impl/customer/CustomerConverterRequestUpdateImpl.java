package com.itsgood.ru.controller.rest.spring.converters.impl.customer;

import com.itsgood.ru.controller.rest.spring.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.customerDTO.CustomerRequestUpdate;
import com.itsgood.ru.controller.rest.spring.data.repository.CustomerDataRepository;
import com.itsgood.ru.hibernate.domain.AuthenticationInfo;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestUpdateImpl implements CustomerConverterRequestUpdate {

    private final AuthenticationInfo authenticationInfo;
    private final JWTConfiguration jwtConfiguration;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDataRepository customerDataRepository;

    @Override
    public HibernateCustomer convert(CustomerRequestUpdate request) {
        Optional<HibernateCustomer> searchResult = customerDataRepository.findById(request.getId());
        HibernateCustomer hibernateCustomer = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateCustomer.setFirstname(request.getFirstname());
        hibernateCustomer.setLastname(request.getLastname());
        hibernateCustomer.setMail(request.getMail());
        hibernateCustomer.setPhone(request.getPhone());
        hibernateCustomer.setBirthday(request.getBirthday());
        hibernateCustomer.setGender(request.getGender());
        hibernateCustomer.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        String password = jwtConfiguration.getServerPasswordSalt() + request.getPassword();
        authenticationInfo.setPassword(passwordEncoder.encode(password));
        authenticationInfo.setPassword(request.getUsername());
        hibernateCustomer.setAuthenticationInfo(authenticationInfo);
        return hibernateCustomer;
    }
}
