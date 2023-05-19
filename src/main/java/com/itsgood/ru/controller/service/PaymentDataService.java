package com.itsgood.ru.controller.service;

import com.itsgood.ru.controller.dto.converters.PaymentConverterRequestCreate;
import com.itsgood.ru.controller.dto.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestCreate;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestSearch;
import com.itsgood.ru.controller.dto.request.paymentDTO.PaymentRequestUpdate;
import com.itsgood.ru.controller.springDataRepository.PaymentDataRepository;
import com.itsgood.ru.enums.StatusPayment;
import com.itsgood.ru.hibernate.domain.HibernateCustomer;
import com.itsgood.ru.hibernate.domain.HibernatePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class PaymentDataService {
    private final CustomerDataService customerDataService;
    private final PaymentDataRepository paymentDataRepository;
    private final PaymentConverterRequestUpdate paymentConverterRequestUpdate;
    private final PaymentConverterRequestCreate paymentCardConverterRequestCreate;

    public HibernatePayment findHibernatePaymentById(Integer id) {
        Optional<HibernatePayment> searchResult = paymentDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<HibernatePayment> findAllHibernatePayments() {
        return paymentDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernatePayment createHibernatePayment(PaymentRequestCreate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernatePayment hibernatePayment = paymentCardConverterRequestCreate.convert(request);
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        if (!payments.contains(hibernatePayment)) {
            hibernatePayment.setCustomer(hibernateCustomer);
            hibernatePayment = paymentDataRepository.save(hibernatePayment);
        }
        return hibernatePayment;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernatePayment(PaymentRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernatePayment hibernatePayment = paymentConverterRequestUpdate.convert(request);
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        if (payments.contains(hibernatePayment)) {
            hibernatePayment.setCustomer(hibernateCustomer);
            paymentDataRepository.delete(hibernatePayment);
        }
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernatePayment updateHibernatePayment(PaymentRequestUpdate request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        HibernatePayment hibernatePayment = paymentConverterRequestUpdate.convert(request);
        Set<HibernatePayment> payments = hibernateCustomer.getPayments();
        if (payments.contains(hibernatePayment)) {
            hibernatePayment.setCustomer(hibernateCustomer);
            hibernatePayment = paymentDataRepository.save(hibernatePayment);
        }
        return hibernatePayment;
    }

    public List<HibernatePayment> findHibernatePaymentByCustomerAndStatus(PaymentRequestSearch request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        List<HibernatePayment> payments = new ArrayList<>();
        for (HibernatePayment payment : hibernateCustomer.getPayments()) {
            if (payment.getStatus().contains(request.getStatus())) {
                payments.add(payment);
            }
        }
        if (payments.size() == 0) new EntityNotFoundException("Такой оплаты не существует");
        return payments;
    }

    public HibernatePayment findHibernatePaymentByAuthenticateAndActive() {
        HibernatePayment hibernatePayment = new HibernatePayment();
        for (HibernatePayment payment : customerDataService.findHibernateCustomerByAuthenticationInfo().getPayments()) {
            if (payment.getStatus().contains(StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus())) {
                hibernatePayment = payment;
            }
        }
        if (hibernatePayment.getId() == 0) new EntityNotFoundException("Такой оплаты не существует");
        return hibernatePayment;
    }

    public List<HibernatePayment> findHibernatePaymentByCustomerAndValidity(PaymentRequestSearch request) {
        List<HibernatePayment> payments = new ArrayList<>();
        for (HibernatePayment payment : customerDataService.findHibernateCustomerByAuthenticationInfo().getPayments()) {
            if (payment.getValidity().after(request.getValidation())) {
                payments.add(payment);
            }
        }
        if (payments.size() == 0) new EntityNotFoundException("Таких оплат не существует");
        return payments;
    }

}
