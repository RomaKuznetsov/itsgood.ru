package com.itsgood.ru.controller.rest.springData;

import com.itsgood.ru.controller.dto.request.deliveryDTO.DeliveryRequestSearch;
import com.itsgood.ru.controller.service.DeliveryDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import com.itsgood.ru.hibernate.domain.HibernateDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/springData/delivery")
@RequiredArgsConstructor
public class DeliveryDataController {

    private final DeliveryDataService deliveryDataService;

    @GetMapping(value = "/findHibernateDeliveryById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateDelivery> findHibernateCustomerById(@Validated @RequestBody DeliveryRequestSearch request,
                                                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(deliveryDataService.findHibernateDeliveryById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<HibernateDelivery>> findAll() {
        return new ResponseEntity<>(deliveryDataService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/createHibernateDelivery", consumes = {"application/xml", "application/json"})
    public ResponseEntity<HibernateDelivery> findAll() {
        return new ResponseEntity<>(deliveryDataService.findAll(), HttpStatus.OK);
    }

    //Domain - Car
    //GET + /rest/cars - findAll
    //GET + /rest/cars/{id} - findOne
    //POST + /rest/cars - create object
    //PUT + /rest/cars - update object
    //DELETE + /rest/cars - update object

    //PATCH + /rest/cars  - partial update of object
    //GET + /rest/cars/search
    //GET + /rest/cars/search
    //PUT + /rest/cars/calculate
    //query - поисковой запрос
    //limit/offset = page = ограничение на число выводимых объектов


    //    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    //    public HibernateDelivery createHibernateDelivery(DeliveryRequestCreate request) {
    //        HibernateDelivery hibernateDelivery = new HibernateDelivery();
    //        if (customerDataService.findAllAddressHibernateCustomerByAuthenticate().contains(addressDataService.
    //                findHibernateAddressById(request.getAddress_id()))) {
    //            hibernateDelivery = deliveryDataRepository.save(deliveryConverterRequestCreate.convert(request));
    //        } else throw new EntityNotFoundException("Ошибка адресата");
    //        return hibernateDelivery;
    //    }
    //
    //    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    //    public HibernateDelivery updateHibernateDelivery(DeliveryRequestUpdate request) {
    //        HibernateDelivery hibernateDelivery = new HibernateDelivery();
    //        if (customerDataService.findAllAddressHibernateCustomerByAuthenticate().
    //                contains(addressDataService.findHibernateAddressById(request.getAddress_id()))) {
    //            deliveryDataRepository.save(deliveryConverterRequestUpdate.convert(request));
    //        } else throw new EntityNotFoundException("Ошибка адресата");
    //        return hibernateDelivery;
    //    }
    //
    //    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    //    public void deleteHibernateDelivery(DeliveryRequestUpdate request) {
    //        if (customerDataService.findAllAddressHibernateCustomerByAuthenticate().
    //                contains(addressDataService.findHibernateAddressById(request.getAddress_id()))) {
    //            deliveryDataRepository.delete(deliveryConverterRequestUpdate.convert(request));
    //        } else throw new EntityNotFoundException("Ошибка адресата");
    //    }
    //
    //    @Transactional(isolation = DEFAULT, propagation = REQUIRED, rollbackFor = Exception.class)
    //    public void deleteHibernateDeliveryById(DeliveryRequestUpdate request) {
    //        deliveryDataRepository.deleteById(request.getId());
    //    }

}
