package com.itsgood.ru.enums;

public enum CodeAddress {
    CODE_ADDRESS_DELIVERY("DEL"), CODE_ADDRESS_REGISTRATION("REG");
    private String code;

    CodeAddress(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;

    }
}
