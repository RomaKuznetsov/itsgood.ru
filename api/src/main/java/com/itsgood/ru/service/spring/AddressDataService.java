package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.address.AddressRequestCreate;
import com.itsgood.ru.controller.request.address.AddressRequestSearch;
import com.itsgood.ru.controller.request.address.AddressRequestUpdate;
import com.itsgood.ru.converters.AddressConverterRequestCreate;
import com.itsgood.ru.converters.AddressConverterRequestUpdate;
import com.itsgood.ru.domain.AddressDTO;
import com.itsgood.ru.domain.ContractDTO;
import com.itsgood.ru.domain.CustomerDTO;
import com.itsgood.ru.domain.EquipmentDTO;
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

    @Cacheable("address")
    public List<AddressDTO> findAllAddress() {
        return addressDataRepository.findAll();
    }
    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public AddressDTO createAddress(AddressRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        AddressDTO addressDTO = addressConverterRequestCreate.convert(request);
        Set<AddressDTO> searchAddress = customerDTO.getAddress();
        if (!searchAddress.contains(addressDTO)) {
            addressDTO.setCustomer(customerDTO);
            AddressDTO createAddress = addressDataRepository.save(addressDTO);
            if (addressDTO.getCode().contains("REG")) {
                ContractDTO contractDTO = customerDTO.getContract();
                contractDTO.setAddress(createAddress);
            } return createAddress;
        } else throw new EntityExistsException("This address already exists");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public AddressDTO updateHibernateAddress(AddressRequestUpdate request) {
        AddressDTO updateAddress = addressConverterRequestUpdate.convert(request);
        return addressDataRepository.save(updateAddress);
    }

    public AddressDTO findAddressById(Integer id) {
        Optional<AddressDTO> searchResult = addressDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public Set<EquipmentDTO> findSetEquipment(AddressRequestSearch request) {
        return findAddressById(request.getId()).getEquipments();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteAddressById(Integer id) {
        addressDataRepository.deleteById(id);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteAddress(AddressRequestUpdate request) {
        AddressDTO deleteAddress = addressConverterRequestUpdate.convert(request);
        Set<AddressDTO> searchResult = customerDataService.findAllAddressCustomerByAuthenticate();
        if (searchResult.contains(deleteAddress)) {
            addressDataRepository.delete(deleteAddress);
        } else throw new EntityNotFoundException("No such address");
    }

    public List<AddressDTO> findListAddressRegistration() {
        return addressDataRepository.findAllHibernateAddressByCode(CodeAddress.CODE_ADDRESS_REGISTRATION.getCode());
    }

    public Set<EquipmentDTO> findListEquipmentOneAddress(AddressRequestSearch request) {
        return findAddressById(request.getId()).getEquipments();
    }

    public List<AddressDTO> findAllAddressByAuthenticateAndCode(AddressRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        return addressDataRepository.findAddressByCustomerAndCode(customerDTO, request.getCode());
    }

    public AddressDTO findAddressByAuthenticateAndRegistration() {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        return addressDataRepository.findAddressByCustomerAndCode(customerDTO, CodeAddress.CODE_ADDRESS_REGISTRATION.getCode()).get(0);
    }
}
