package com.itsgood.ru.security.provider;

import com.itsgood.ru.controller.springDataRepository.CustomerDataRepository;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.domain.Role;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.service.CustomerService;
import com.itsgood.ru.service.RoleService;
import com.sun.net.httpserver.HttpPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsProvider implements UserDetailsService {
    private final CustomerService customerService;
    private final CustomerDataRepository customerDataRepository;
    private final RoleService roleService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<Customer> searchResult = customerService.findCustomerByUsername(username);
            if (searchResult.isPresent()) {
                Customer customer = searchResult.get();
                //конвертни его через поиск в Hibernate
                HibernateCustomer hibernateCustomer = customerDataRepository.findById(customer.getId()).get();

                return new org.springframework.security.core.userdetails.User(
                        hibernateCustomer.getMail(),
                        hibernateCustomer.getAuthenticationInfo().getPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                roleService.findListRoleOneCustomer(customer.getId())
                                        .stream()
                                        .map(Role::getRole)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }

}
