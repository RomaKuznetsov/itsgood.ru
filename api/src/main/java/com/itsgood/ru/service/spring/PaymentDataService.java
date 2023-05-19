package com.itsgood.ru.service.spring;

import com.itsgood.ru.controller.request.payment.PaymentRequestCreate;
import com.itsgood.ru.controller.request.payment.PaymentRequestSearch;
import com.itsgood.ru.controller.request.payment.PaymentRequestUpdate;
import com.itsgood.ru.converters.PaymentConverterRequestCreate;
import com.itsgood.ru.converters.PaymentConverterRequestUpdate;
import com.itsgood.ru.domain.hibernate.CustomerDTO;
import com.itsgood.ru.domain.hibernate.PaymentDTO;
import com.itsgood.ru.repository.spring.PaymentDataRepository;
import com.itsgood.ru.codes.StatusPayment;
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


    public PaymentDTO findHibernatePaymentById(Integer id) {
        Optional<PaymentDTO> searchResult = paymentDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<PaymentDTO> findAllHibernatePayments() {
        return paymentDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public PaymentDTO createHibernatePayment(PaymentRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        PaymentDTO paymentDTO = paymentCardConverterRequestCreate.convert(request);
        Set<PaymentDTO> payments = customerDTO.getPayments();
        if (!payments.contains(paymentDTO)) {
            paymentDTO.setCustomer(customerDTO);
            paymentDTO = paymentDataRepository.save(paymentDTO);
        } else throw new EntityExistsException();
        return paymentDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deleteHibernatePayment(PaymentRequestUpdate request) {
        PaymentDTO paymentDTO = paymentConverterRequestUpdate.convert(request);
            paymentDataRepository.delete(paymentDTO);
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public PaymentDTO updateHibernatePayment(PaymentRequestUpdate request) {
        PaymentDTO paymentDTO = paymentConverterRequestUpdate.convert(request);
            return paymentDataRepository.save(paymentDTO);
    }

    public List<PaymentDTO> findHibernatePaymentByAuthenticateAndStatus(PaymentRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<PaymentDTO>> searchResult = paymentDataRepository.
                findHibernatePaymentByCustomerAndStatus(customerDTO, request.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public PaymentDTO findHibernatePaymentByAuthenticateAndStatusActive() {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<PaymentDTO>> searchResult = paymentDataRepository.
                findHibernatePaymentByCustomerAndStatus(customerDTO,
                        StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new).get(0);
    }

    public List<PaymentDTO> findHibernatePaymentByAuthenticateAndValidity(PaymentRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findHibernateCustomerByAuthenticationInfo();
        Optional<List<PaymentDTO>> searchResult = paymentDataRepository.
                findHibernatePaymentByCustomerAndValidity(customerDTO, request.getValidation());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
}
