package com.itsgood.ru.controller.dto.converters.impl.payment;

import com.itsgood.ru.controller.dto.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestUpdate;
import com.itsgood.ru.controller.service.PaymentDataService;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConverterRequestUpdateImpl implements PaymentConverterRequestUpdate {

    private final PaymentDataService paymentDataService;

    @Override
    public HibernatePayment convert(PaymentRequestUpdate request) {
        HibernatePayment hibernatePayment = paymentDataService.findHibernatePaymentById(request.getId());
        hibernatePayment.setStatus(request.getStatus());
        hibernatePayment.setPhone(request.getPhone());
        return hibernatePayment;
    }
}
