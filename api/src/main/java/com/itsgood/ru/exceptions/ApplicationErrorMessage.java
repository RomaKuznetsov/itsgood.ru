package com.itsgood.ru.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    public class ApplicationErrorMessage {

        private String errorId;

        private Integer errorCode;

        private String errorMessage;
    }

