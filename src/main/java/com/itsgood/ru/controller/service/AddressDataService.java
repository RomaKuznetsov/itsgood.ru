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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        } else throw new EntityNotFoundException("Такой адрес уже есть");
        return hibernateAddress;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernateAddress updateHibernateAddress(AddressRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateAddress hibernateAddress = addressConverterRequestUpdate.convert(request);
        if (hibernateCustomer.getAddress().contains(hibernateAddress)) {
            hibernateAddress.setCustomer(hibernateCustomer);
            hibernateAddress = addressDataRepository.save(hibernateAddress);
        } else throw new EntityNotFoundException("Уточните данные");
        return hibernateAddress;
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
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernateAddress hibernateAddress = addressConverterRequestUpdate.convert(request);
        if (hibernateCustomer.getAddress().contains(hibernateAddress)) {
            hibernateAddress.setCustomer(hibernateCustomer);
            addressDataRepository.delete(hibernateAddress);
        } else throw new EntityNotFoundException("Такого адреса уже нет");
    }

    public List<HibernateAddress> findListHibernateAddressRegistration() {
        return addressDataRepository.findAllByCode(CodeAddress.CODE_ADDRESS_REGISTRATION.getCode());
    }

    public HibernateAddress findHibernateAddressByCustomerAuthenticateAndRegistration() {
        HibernateAddress hibernateAddress = new HibernateAddress();
        for (HibernateAddress address : customerDataService.findAllAddressHibernateCustomerByAuthenticate()) {
            if (address.getCode().equals(CodeAddress.CODE_ADDRESS_REGISTRATION.getCode())) {
                hibernateAddress = address;
            } else if (hibernateAddress.getId() == 0) throw new EntityNotFoundException("Нет адреса регистрации");
        }
        return hibernateAddress;
    }

    public List<HibernateAddress> findAllHibernateAddress() {
        return addressDataRepository.findAll();
    }

}
