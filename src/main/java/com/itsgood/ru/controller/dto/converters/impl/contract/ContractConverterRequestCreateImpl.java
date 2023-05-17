package com.itsgood.ru.controller.dto.converters.impl.contract;

import com.itsgood.ru.controller.dto.converters.ContractConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.service.AddressDataService;
import com.itsgood.ru.controller.service.PaymentDataService;
import com.itsgood.ru.enums.ContractRelevance;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ContractConverterRequestCreateImpl implements ContractConverterRequestCreate {
    private final AddressDataService addressDataService;

    @Override
    public HibernateContract convert(ContractRequestCreate request) {
        HibernateContract hibernateContract = new HibernateContract();
        hibernateContract.setSum_order(request.getSum_order());
        hibernateContract.setPayment_types("cash");
        hibernateContract.setRelevance(ContractRelevance.RELEVANCE_CONTRACT_RELEVANT.getStatus());
        hibernateContract.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateContract.setAddress(addressDataService.findHibernateAddressByAuthenticateAndRegistration());
        hibernateContract.setPayment(null);
        return hibernateContract;
    }

}
