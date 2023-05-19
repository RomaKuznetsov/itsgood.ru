package com.itsgood.ru.old.service.impl;

import com.itsgood.ru.domain.Address;
import com.itsgood.ru.domain.Customer;
import com.itsgood.ru.old.repository.AddressRepository;
import com.itsgood.ru.old.service.AddressAggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressAggregationServiceImpl implements AddressAggregationService {
    private static Map<Integer, Address> mapAllAddress;

    private final AddressRepository addressRepository;


    @Override
    public Map<Integer, Address> createHashMapAllAddress() {
        Address address;
        try {
            if (mapAllAddress.size() == 0) {
                for (Address add : addressRepository.findAll().stream().collect(Collectors.toList())) {
                    mapAllAddress.put(add.hashCode(), add);
                }
            }
            int countPKey = addressRepository.findMinIdAddress().getId() + mapAllAddress.size();
            int maxId = addressRepository.findMaxIdAddress().getId();
            if (maxId > countPKey) {
                while (maxId > countPKey) {
                    address = addressRepository.findOne(countPKey++);
                    if (address != null) {
                        mapAllAddress.put(address.hashCode(), address);
                    } else break;
                }
            }
            return mapAllAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findListAllAddress() {
        List<Address> listAllAddress;
        try {
            listAllAddress = addressRepository.findAll();
            if (listAllAddress.size() == 0) {
                System.out.println("address list is empty");
            }
            return listAllAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findListCustomerDelivery(Customer customer) {
        List<Address> listOneCustomerDelivery;
        try {
                listOneCustomerDelivery = addressRepository.findListCustomerDelivery(customer);
                if (listOneCustomerDelivery.size() == 0) {
                    System.out.println("list address delivery is empty");
                }
            return listOneCustomerDelivery;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findListAddressOneCustomer(Customer customer) {
        List<Address> listOneCustomerAddress;
        try {
                listOneCustomerAddress = addressRepository.findListAddressOneCustomer(customer);
                if (listOneCustomerAddress.size() == 0) {
                    System.out.println("list address is empty");
                }
            return listOneCustomerAddress;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
