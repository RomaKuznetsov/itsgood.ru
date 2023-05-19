package com.itsgood.ru.controller.dto.converters.impl.contract;

import com.itsgood.ru.controller.dto.converters.ContractConverterRequestCreate;
import com.itsgood.ru.controller.dto.request.contractDTO.ContractRequestCreate;
import com.itsgood.ru.controller.service.AddressDataService;
import com.itsgood.ru.controller.service.CustomerDataService;
import com.itsgood.ru.controller.service.PaymentDataService;
import com.itsgood.ru.enums.StatusPayment;
import com.itsgood.ru.hibernate.domain.HibernateContract;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import com.itsgood.ru.utilits.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ContractConverterRequestCreateImpl implements ContractConverterRequestCreate {
    private final AddressDataService addressDataService;
    private final PaymentDataService paymentDataService;

    @Override
    public HibernateContract convert(ContractRequestCreate request) {
        HibernateContract hibernateContract = new HibernateContract();
        hibernateContract.setSum_order(request.getSum_order());
        hibernateContract.setPayment_types(request.getPayment_types());
        hibernateContract.setRelevance(request.getRelevance());
        hibernateContract.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        hibernateContract.setAddress(addressDataService.findHibernateAddressByCustomerAuthenticateAndRegistration());
        if (request.getPayment_types().contains("card")) {
            hibernateContract.setPayment(paymentDataService.findHibernatePaymentByAuthenticateAndActive());
        } else if (request.getPayment_types().contains("cash")) {
            hibernateContract.setPayment(null);
        }
        return hibernateContract;
    }

}
