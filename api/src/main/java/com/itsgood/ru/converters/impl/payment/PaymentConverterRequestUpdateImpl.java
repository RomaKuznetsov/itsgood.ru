package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
import com.itsgood.ru.repository.spring.PaymentDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentConverterRequestUpdateImpl implements PaymentConverterRequestUpdate {
    private final PaymentDataRepository paymentDataRepository;
    @Override
    public PaymentDTO convert(PaymentRequestUpdate request) {
        Optional<PaymentDTO> searchResult = paymentDataRepository.findById(request.getId());
        PaymentDTO paymentDTO = searchResult.orElseThrow(EntityExistsException::new);
        paymentDTO.setStatus(request.getStatus());
        paymentDTO.setPhone(request.getPhone());
        return paymentDTO;
    }
}
