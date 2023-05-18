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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
        } else throw new EntityExistsException();
        return hibernatePayment;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernatePayment(PaymentRequestUpdate request) {
        HibernatePayment hibernatePayment = paymentConverterRequestUpdate.convert(request);
            paymentDataRepository.delete(hibernatePayment);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public HibernatePayment updateHibernatePayment(PaymentRequestUpdate request) {
        HibernatePayment hibernatePayment = paymentConverterRequestUpdate.convert(request);
            return paymentDataRepository.save(hibernatePayment);
    }

    public List<HibernatePayment> findHibernatePaymentByAuthenticateAndStatus(PaymentRequestSearch request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernatePayment>> searchResult = paymentDataRepository.
                findHibernatePaymentByCustomerAndStatus(hibernateCustomer, request.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public HibernatePayment findHibernatePaymentByAuthenticateAndStatusActive() {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernatePayment>> searchResult = paymentDataRepository.
                findHibernatePaymentByCustomerAndStatus(hibernateCustomer,
                        StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new).get(0);
    }

    public List<HibernatePayment> findHibernatePaymentByAuthenticateAndValidity(PaymentRequestSearch request) {
        HibernateCustomer hibernateCustomer = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<HibernatePayment>> searchResult = paymentDataRepository.
                findHibernatePaymentByCustomerAndValidity(hibernateCustomer, request.getValidation());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
}
