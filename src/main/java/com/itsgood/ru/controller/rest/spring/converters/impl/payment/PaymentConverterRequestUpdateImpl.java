package com.itsgood.ru.controller.rest.spring.converters.impl.payment;

import com.itsgood.ru.controller.rest.spring.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestUpdate;
import com.itsgood.ru.controller.rest.spring.data.repository.PaymentDataRepository;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentConverterRequestUpdateImpl implements PaymentConverterRequestUpdate {
    private final PaymentDataRepository paymentDataRepository;
    @Override
    public HibernatePayment convert(PaymentRequestUpdate request) {
        Optional<HibernatePayment> searchResult = paymentDataRepository.findById(request.getId());
        HibernatePayment hibernatePayment = searchResult.orElseThrow(EntityExistsException::new);
        hibernatePayment.setStatus(request.getStatus());
        hibernatePayment.setPhone(request.getPhone());
        return hibernatePayment;
    }
}
