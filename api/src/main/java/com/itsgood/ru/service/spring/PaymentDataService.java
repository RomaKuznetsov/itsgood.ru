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


    public PaymentDTO findPaymentById(Integer id) {
        Optional<PaymentDTO> searchResult = paymentDataRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public List<PaymentDTO> findAllPayments() {
        return paymentDataRepository.findAll();
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public PaymentDTO createPayment(PaymentRequestCreate request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        PaymentDTO paymentDTO = paymentCardConverterRequestCreate.convert(request);
        Set<PaymentDTO> payments = customerDTO.getPayments();
        if (!payments.contains(paymentDTO)) {
            paymentDTO.setCustomer(customerDTO);
            paymentDTO = paymentDataRepository.save(paymentDTO);
            if (paymentDTO.getStatus().contains(StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus())) {
                customerDTO.getContract().setPayment(paymentDTO);
            }
        } else throw new EntityExistsException("Such payment is already");
        return paymentDTO;
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public void deletePayment(PaymentRequestUpdate request) {
        PaymentDTO paymentDTO = paymentConverterRequestUpdate.convert(request);
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Set<PaymentDTO> payments = customerDTO.getPayments();
        if (!payments.contains(paymentDTO)) {
            paymentDTO.setCustomer(customerDTO);
            paymentDataRepository.delete(paymentDTO);
        } else throw new EntityExistsException("No such payment");
    }

    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    public PaymentDTO updatePayment(PaymentRequestUpdate request) {
        PaymentDTO paymentDTO = paymentConverterRequestUpdate.convert(request);
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Set<PaymentDTO> payments = customerDTO.getPayments();
        if (!payments.contains(paymentDTO)) {
            paymentDTO.setCustomer(customerDTO);
            paymentDataRepository.save(paymentDTO);
        } else throw new EntityExistsException("No such payment");
        return paymentDataRepository.save(paymentDTO);
    }

    public List<PaymentDTO> findPaymentByAuthenticateAndStatus(PaymentRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<List<PaymentDTO>> searchResult = paymentDataRepository.
                findPaymentByCustomerAndStatus(customerDTO, request.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }

    public PaymentDTO findPaymentByAuthenticateAndStatusActive() {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<List<PaymentDTO>> searchResult = paymentDataRepository.
                findPaymentByCustomerAndStatus(customerDTO,
                        StatusPayment.STATUS_PAYMENT_ACTIVE.getStatus());
        return searchResult.orElseThrow(EntityNotFoundException::new).get(0);
    }

    public List<PaymentDTO> findPaymentByAuthenticateAndValidity(PaymentRequestSearch request) {
        CustomerDTO customerDTO = customerDataService.findCustomerByAuthenticationInfo();
        Optional<List<PaymentDTO>> searchResult = paymentDataRepository.
                findPaymentByCustomerAndValidity(customerDTO, request.getValidation());
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
}
