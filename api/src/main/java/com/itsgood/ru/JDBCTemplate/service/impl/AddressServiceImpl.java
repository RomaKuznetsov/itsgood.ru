package com.itsgood.ru.JDBCTemplate.service.impl;

import com.itsgood.ru.old.domain.Address;
import com.itsgood.ru.old.domain.Customer;
import com.itsgood.ru.old.repository.AddressRepository;
import com.itsgood.ru.JDBCTemplate.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address findCustomerRegistration(Customer customer) {
        Address address;
        try {
            address = addressRepository.findCustomerRegistration(customer);
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address findOne(Integer id) throws SQLException {
        Address address;
        try {
            address = addressRepository.findOne(id);
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address create(Address address) {
        Address createAddress;
        try {

            createAddress = addressRepository.create(address);
            return createAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address update(Address address) throws SQLException {
        Address updateAddress;
        try {
            updateAddress = addressRepository.update(address);
            return updateAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            addressRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
