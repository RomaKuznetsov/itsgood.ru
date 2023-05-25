package com.itsgood.ru.converters.impl.customer;

import com.itsgood.ru.controller.request.customer.CustomerRequestUpdate;
import com.itsgood.ru.converters.CustomerConverterRequestUpdate;
import com.itsgood.ru.domain.AuthenticationInfo;
import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import com.itsgood.ru.security.configuration.JWTConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerConverterRequestUpdateImpl implements CustomerConverterRequestUpdate {
    private final JWTConfiguration jwtConfiguration;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDataRepository customerDataRepository;

    @Override
    public CustomerDTO convert(CustomerRequestUpdate request) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        Optional<CustomerDTO> searchResultById = customerDataRepository.findById(request.getId());
        CustomerDTO customerDTO = searchResultById.orElseThrow(EntityNotFoundException::new);
        customerDTO.setFirstname(request.getFirstname());
        customerDTO.setLastname(request.getLastname());
        Optional<CustomerDTO> searchCustomerByMail = customerDataRepository.findByMail(request.getMail());
        if (searchCustomerByMail.isEmpty()) {
            customerDTO.setMail(request.getMail());
        } else if (searchCustomerByMail.get().equals(customerDTO)) {
            customerDTO.setMail(request.getMail());
        } else throw new EntityExistsException("User with this email address already exists");
        Optional<CustomerDTO> searchCustomerByPhone = customerDataRepository.findByPhone(request.getPhone());
        if (searchCustomerByPhone.isEmpty()) {
            customerDTO.setPhone(request.getPhone());
        } else if (searchCustomerByPhone.get().equals(customerDTO)) {
            customerDTO.setPhone(request.getPhone());
        } else throw new EntityExistsException("User with this phone number already exists");
        customerDTO.setBirthday(request.getBirthday());
        customerDTO.setGender(request.getGender());
        customerDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        String password = jwtConfiguration.getServerPasswordSalt() + request.getPassword();
        authenticationInfo.setPassword(passwordEncoder.encode(password));
        Optional<CustomerDTO> searchCustomerByUserName =
                customerDataRepository.findByAuthenticationInfoUsername(request.getUsername());
        if (searchCustomerByUserName.isEmpty()) {
            authenticationInfo.setUsername(request.getUsername());
        } else if (searchCustomerByUserName.get().equals(customerDTO)) {
            authenticationInfo.setUsername(request.getUsername());
        } else throw new EntityExistsException("User with this username already exists");
        authenticationInfo.setUsername(request.getUsername());
        customerDTO.setAuthenticationInfo(authenticationInfo);
        return customerDTO;
    }
}
