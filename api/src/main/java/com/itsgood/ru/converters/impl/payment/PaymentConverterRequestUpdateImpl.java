package com.itsgood.ru.converters.impl.payment;

import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.HibernatePayment;
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
    public HibernatePayment convert(PaymentRequestUpdate request) {
        Optional<HibernatePayment> searchResult = paymentDataRepository.findById(request.getId());
        HibernatePayment hibernatePayment = searchResult.orElseThrow(EntityExistsException::new);
        hibernatePayment.setStatus(request.getStatus());
        hibernatePayment.setPhone(request.getPhone());
        return hibernatePayment;
    }
}
