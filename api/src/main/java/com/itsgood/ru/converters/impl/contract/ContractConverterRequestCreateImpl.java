package com.itsgood.ru.converters.impl.contract;

import com.itsgood.ru.controller.request.contract.ContractRequestCreate;
import com.itsgood.ru.converters.ContractConverterRequestCreate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.service.spring.PaymentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ContractConverterRequestCreateImpl implements ContractConverterRequestCreate {
    private final PaymentDataService paymentDataService;

    @Override
    public ContractDTO convert(ContractRequestCreate request) {
        ContractDTO contractDTO = new ContractDTO();
        if (request.getPayment_types().contains("cash")) {
            contractDTO.setPayment_types("cash");
            contractDTO.setPayment(null);
        } else if (request.getPayment_types().contains("card")) {
           PaymentDTO searchPayment = paymentDataService.findPaymentByAuthenticateAndStatusActive();
            contractDTO.setPayment_types("card");
            contractDTO.setPayment(searchPayment);
        }
        contractDTO.setCreate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        return contractDTO;
    }

}
