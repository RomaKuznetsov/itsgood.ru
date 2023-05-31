package com.itsgood.ru.controller.response;

public enum ErrorCodes {


    SQL_ERROR(10),
    USER_NOT_FOUND(40),
    BAD_REQUEST_USER_CREATE(76),
    FATAL_ERROR(1);

    public int getCodeId() {
        return codeId;
    }

    private int codeId;

    ErrorCodes(int codeId) {
        this.codeId = codeId;
    }
}
