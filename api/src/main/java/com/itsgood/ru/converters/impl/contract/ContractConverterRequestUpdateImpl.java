package com.itsgood.ru.converters.impl.contract;

import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;
import com.itsgood.ru.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.service.spring.PaymentDataService;
import com.itsgood.ru.repository.spring.ContractDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContractConverterRequestUpdateImpl implements ContractConverterRequestUpdate {
    private final AddressDataService addressDataService;
    private final PaymentDataService paymentDataService;
    private final ContractDataRepository contractDataRepository;

    @Override
    public ContractDTO convert(ContractRequestUpdate request) {
        Optional<ContractDTO> searchResult = contractDataRepository.findById(request.getId());
        ContractDTO contractDTO = searchResult.orElseThrow(EntityNotFoundException::new);
        contractDTO.setPayment_types(request.getPayment_types());
        contractDTO.setRelevance(request.getRelevance());
        contractDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        contractDTO.setAddress(addressDataService.findHibernateAddressByAuthenticateAndRegistration());
        if (request.getPayment_types().contains("card")) {
            contractDTO.setPayment(paymentDataService.findHibernatePaymentByAuthenticateAndStatusActive());
            //если такой карты нет то нужен forward на страницу создания активной карты
        } else if (request.getPayment_types().contains("cash")) {
            contractDTO.setPayment(null);
        }
        return contractDTO;
    }
}


