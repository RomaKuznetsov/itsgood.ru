package com.itsgood.ru.converters.impl.customer;

import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.AuthenticationInfo;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
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
    public CustomerDTO convert(CustomerRequestUpdate request) {
        Optional<CustomerDTO> searchResult = customerDataRepository.findById(request.getId());
        CustomerDTO customerDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        customerDTO.setFirstname(request.getFirstname());
        customerDTO.setLastname(request.getLastname());
        customerDTO.setMail(request.getMail());
        customerDTO.setPhone(request.getPhone());
        customerDTO.setBirthday(request.getBirthday());
        customerDTO.setGender(request.getGender());
        customerDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        String password = jwtConfiguration.getServerPasswordSalt() + request.getPassword();
        authenticationInfo.setPassword(passwordEncoder.encode(password));
        authenticationInfo.setPassword(request.getUsername());
        customerDTO.setAuthenticationInfo(authenticationInfo);
        return customerDTO;
    }
}
