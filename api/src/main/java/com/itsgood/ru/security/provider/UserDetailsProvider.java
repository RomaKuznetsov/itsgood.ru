package com.itsgood.ru.security.provider;

import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.RoleDTO;
import com.itsgood.ru.repository.spring.CustomerDataRepository;
import lombok.RequiredArgsConstructor;
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
    private final CustomerDataRepository customerDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<CustomerDTO> searchResult = customerDataRepository.findByAuthenticationInfoUsername(username);
            if (searchResult.isPresent()) {
                CustomerDTO customerDTO = searchResult.get();
                return new org.springframework.security.core.userdetails.User(
                        customerDTO.getMail(),
                        customerDTO.getAuthenticationInfo().getPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                customerDTO.getRoles()
                                        .stream()
                                        .map(RoleDTO::getRole)
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
