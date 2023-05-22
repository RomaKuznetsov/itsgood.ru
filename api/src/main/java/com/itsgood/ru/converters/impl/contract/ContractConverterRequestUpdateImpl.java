package com.itsgood.ru.converters.impl.contract;

import com.itsgood.ru.controller.request.contract.ContractRequestUpdate;
import com.itsgood.ru.converters.ContractConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.ContractDTO;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
import com.itsgood.ru.repository.spring.ContractDataRepository;
import com.itsgood.ru.service.spring.AddressDataService;
import com.itsgood.ru.service.spring.PaymentDataService;
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
        if (request.getPayment_types().contains("cash")) {
            contractDTO.setPayment_types("cash");
            contractDTO.setPayment(null);
        } else if (request.getPayment_types().contains("card")) {
            PaymentDTO searchPayment = paymentDataService.findPaymentByAuthenticateAndStatusActive();
            contractDTO.setPayment_types("card");
            contractDTO.setPayment(searchPayment);
        }
        contractDTO.setUpdate_time(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));
        contractDTO.setAddress(addressDataService.findAddressByAuthenticateAndRegistration());
        return contractDTO;
    }
}


