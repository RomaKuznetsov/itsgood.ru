package com.itsgood.ru.repository.spring;

import com.itsgood.ru.domain.hibernate.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerDataRepository extends JpaRepository<CustomerDTO, Integer>,
        PagingAndSortingRepository<CustomerDTO, Integer>,
        CrudRepository<CustomerDTO, Integer> {

    Optional<CustomerDTO> findByMail(String mail);

    Optional<CustomerDTO> findByAuthenticationInfoUsername(String username);

}
