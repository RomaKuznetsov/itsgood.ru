package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.AddressConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestCreate;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestSearch;
import com.itsgood.ru.controller.dto.request.addressDTO.AddressRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.AddressDataRepository;
import com.itsgood.ru.enums.CodeAddress;
import com.itsgood.ru.hibernate.domain.HibernateAddress;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class AddressDataService {

    private final CustomerDataService customerDataService;
    private final AddressDataRepository addressDataRepository;
    private final AddressConverterRequestUpdate addressConverterRequestUpdate;
    private final AddressConverterRequestCreate addressConverterRequestCreate;

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateAddress createHibernateAddress(AddressRequestCreate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateAddress hibernateAddress = addressConverterRequestCreate.convert(request);
        if (!hibernateCustomer.getAddress().contains(hibernateAddress)) {
            hibernateAddress.setCustomer(hibernateCustomer);
            hibernateAddress = addressDataRepository.save(hibernateAddress);
        } else throw new EntityExistsException();
        return hibernateAddress;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateAddress updateHibernateAddress(AddressRequestUpdate request) {
        return addressDataRepository.save(addressConverterRequestUpdate.convert(request));
    }

    public HibernateAddress findHibernateAddressById(Integer id) {
        Optional<HibernateAddress> searchResult = addressDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public Set<HibernateDelivery> findSetHibernateDelivery(AddressRequestSearch request) {
        return findHibernateAddressById(request.getId()).getDeliveries();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateAddressById(Integer id) {
        addressDataRepository.deleteById(id);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernateAddress(AddressRequestUpdate request) {
        addressDataRepository.delete(addressConverterRequestUpdate.convert(request));
    }

    public List<HibernateAddress> findListHibernateAddressRegistration() {
        return addressDataRepository.findAllHibernateAddressByCode(CodeAddress.CODE_ADDRESS_REGISTRATION.getCode());
    }

    @Cacheable("delivery")
    public Set<HibernateDelivery> findListHibernateDeliveryOneAddress(AddressRequestSearch request) {
        return findHibernateAddressById(request.getId()).getDeliveries();
    }

    public List<HibernateAddress> findAllHibernateAddressByAuthenticateAndCode(AddressRequestSearch request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernateAddress>> searchResult = addressDataRepository.
                findHibernateAddressByCustomerAndCode(hibernateCustomer, request.getCode());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernateAddress findHibernateAddressByAuthenticateAndRegistration() {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernateAddress>> searchResult = addressDataRepository.
                findHibernateAddressByCustomerAndCode(hibernateCustomer, CodeAddress.CODE_ADDRESS_REGISTRATION.getCode());
        return searchResult.orElseThrow(EntityNotFoundException::new).get(0);
    }

    public List<HibernateAddress> findAllHibernateAddress() {
        return addressDataRepository.findAll();
    }

}
