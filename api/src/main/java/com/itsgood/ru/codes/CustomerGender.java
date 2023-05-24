package com.itsgood.ru.codes;

public enum CustomerGender {
    GENDER_MALE("male"), GENDER_FEMALE("female"), TRANS_GENDER("transgender");
    private String gender;

    CustomerGender (String gender) {
        this.gender = gender;
    }

    public String getCode() {
        return gender;
    }
}
