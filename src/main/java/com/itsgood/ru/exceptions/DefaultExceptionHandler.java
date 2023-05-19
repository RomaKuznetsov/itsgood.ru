package com.itsgood.ru.exceptions;

import com.itsgood.ru.utilits.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static com.itsgood.ru.controller.dto.response.ErrorCodes.*;

@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {
    private final RandomValuesGenerator generator;
    private static final Logger log = Logger.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<ApplicationErrorMessage> handelIllegalRequestException(IllegalRequestException e) {
        String exceptionUniqId = generator.uuidGenerator();

        BindingResult bindingResult = e.getBindingResult();
        String collect = bindingResult.getAllErrors().stream().map(ObjectError::toString).
                collect(Collectors.joining(","));

        log.error(exceptionUniqId + e.getMessage(), e);

        return new ResponseEntity<>(new ApplicationErrorMessage(exceptionUniqId, BAD_REQUEST_USER_CREATE.getCodeId(),
                collect), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ApplicationErrorMessage(
                        exceptionUniqueId,
                        FATAL_ERROR.getCodeId(),
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApplicationErrorMessage> handleRuntimeException(RuntimeException e) {
        /* Handles all other exceptions. Status code 500. */

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ApplicationErrorMessage(
                        exceptionUniqueId,
                        USER_NOT_FOUND.getCodeId(),
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
