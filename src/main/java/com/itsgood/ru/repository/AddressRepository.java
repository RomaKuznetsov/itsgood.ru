package com.itsgood.ru.repository;

import com.itsgood.ru.domain.Address;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.repositoryCRUD.CRUDRepository;

import java.sql.SQLException;
import java.util.List;

public interface AddressRepository extends CRUDRepository<Integer, Address> {
    Address findCustomerRegistration(Customer customer) throws SQLException;
    List<Address> findListCustomerDelivery(Customer customer) throws SQLException;
    List<Address> findListAddressOneCustomer(Customer customer) throws SQLException;
    Address findMaxIdAddress() throws SQLException;
    Address findMinIdAddress() throws SQLException;
}
