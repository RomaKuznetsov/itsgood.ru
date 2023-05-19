package com.itsgood.ru.converters.impl.contract;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.converters.ContractConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.codes.ContractRelevance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ContractConverterRequestCreateImpl implements ContractConverterRequestCreate {
    private final AddressDataService addressDataService;

    @Override
    public ContractDTO convert(ContractRequestCreate request) {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setSum_order(request.getSum_order());
        contractDTO.setPayment_types("cash");
        contractDTO.setRelevance(ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        contractDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        contractDTO.setAddress(addressDataService.findHibernateAddressByAuthenticateAndRegistration());
        contractDTO.setPayment(null);
        return contractDTO;
    }

}
