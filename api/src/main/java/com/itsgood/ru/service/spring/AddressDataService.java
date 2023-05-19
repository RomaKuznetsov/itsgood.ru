package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.address.AddressRequestCreate;
import com.itsgood.ru.controller.request.address.AddressRequestSearch;
import com.itsgood.ru.controller.request.address.AddressRequestUpdate;
import com.itsgood.ru.converters.AddressConverterRequestCreate;
import com.itsgood.ru.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.AddressDTO;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.DeliveryDTO;
import com.itsgood.ru.repository.spring.AddressDataRepository;
import com.itsgood.ru.codes.CodeAddress;
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
    public AddressDTO createHibernateAddress(AddressRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        AddressDTO addressDTO = addressConverterRequestCreate.convert(request);
        if (!customerDTO.getAddress().contains(addressDTO)) {
            addressDTO.setCustomer(customerDTO);
            addressDTO = addressDataRepository.save(addressDTO);
        } else throw new EntityExistsException();
        return addressDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public AddressDTO updateHibernateAddress(AddressRequestUpdate request) {
        return addressDataRepository.save(addressConverterRequestUpdate.convert(request));
    }

    public AddressDTO findHibernateAddressById(Integer id) {
        Optional<AddressDTO> searchResult = addressDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public Set<DeliveryDTO> findSetHibernateDelivery(AddressRequestSearch request) {
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

    public List<AddressDTO> findListHibernateAddressRegistration() {
        return addressDataRepository.findAllHibernateAddressByCode(CodeAddress.CODE_ADDRESS_REGISTRATION.getCode());
    }

    @Cacheable("delivery")
    public Set<DeliveryDTO> findListHibernateDeliveryOneAddress(AddressRequestSearch request) {
        return findHibernateAddressById(request.getId()).getDeliveries();
    }

    public List<AddressDTO> findAllHibernateAddressByAuthenticateAndCode(AddressRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<AddressDTO>> searchResult = addressDataRepository.
                findHibernateAddressByCustomerAndCode(customerDTO, request.getCode());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public AddressDTO findHibernateAddressByAuthenticateAndRegistration() {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<AddressDTO>> searchResult = addressDataRepository.
                findHibernateAddressByCustomerAndCode(customerDTO, CodeAddress.CODE_ADDRESS_REGISTRATION.getCode());
        return searchResult.orElseThrow(EntityNotFoundException::new).get(0);
    }

    public List<AddressDTO> findAllHibernateAddress() {
        return addressDataRepository.findAll();
    }

}
