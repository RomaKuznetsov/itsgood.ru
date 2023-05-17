package com.itsgood.ru.controller.dto.converters.impl.contract;

import com.itsgood.ru.controller.dto.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestUpdate;
import com.itsgood.ru.controller.service.AddressDataService;
import com.itsgood.ru.controller.service.PaymentDataService;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ContractConverterRequestUpdateImpl implements ContractConverterRequestUpdate {
    private final AddressDataService addressDataService;
    private final PaymentDataService paymentDataService;

    @Override
    public HibernateContract convert(ContractRequestUpdate request) {
        HibernateContract hibernateContract = new HibernateContract();
        hibernateContract.setId(request.getId());
        hibernateContract.setPayment_types(request.getPayment_types());
        hibernateContract.setRelevance(request.getRelevance());
        hibernateContract.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateContract.setAddress(addressDataService.findHibernateAddressByAuthenticateAndRegistration());
        if (request.getPayment_types().contains("card")) {
            hibernateContract.setPayment(paymentDataService.findHibernatePaymentByAuthenticateAndStatusActive());
        } else if (request.getPayment_types().contains("cash")) {
            hibernateContract.setPayment(null);
        }
        return hibernateContract;
    }

}


