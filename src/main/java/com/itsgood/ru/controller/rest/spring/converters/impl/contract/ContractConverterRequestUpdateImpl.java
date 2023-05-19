package com.itsgood.ru.controller.rest.spring.converters.impl.contract;

import com.itsgood.ru.controller.rest.spring.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestUpdate;
import com.itsgood.ru.controller.rest.spring.data.service.AddressDataService;
import com.itsgood.ru.controller.rest.spring.data.service.PaymentDataService;
import com.itsgood.ru.controller.rest.spring.data.repository.ContractDataRepository;
import com.itsgood.ru.hibernate.domain.HibernateContract;
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
    public HibernateContract convert(ContractRequestUpdate request) {
        Optional<HibernateContract> searchResult = contractDataRepository.findById(request.getId());
        HibernateContract hibernateContract = searchResult.orElseThrow(EntityNotFoundException::new);
        hibernateContract.setPayment_types(request.getPayment_types());
        hibernateContract.setRelevance(request.getRelevance());
        hibernateContract.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateContract.setAddress(addressDataService.findHibernateAddressByAuthenticateAndRegistration());
        if (request.getPayment_types().contains("card")) {
            hibernateContract.setPayment(paymentDataService.findHibernatePaymentByAuthenticateAndStatusActive());
            //если такой карты нет то нужен forward на страницу создания активной карты
        } else if (request.getPayment_types().contains("cash")) {
            hibernateContract.setPayment(null);
        }
        return hibernateContract;
    }
}


