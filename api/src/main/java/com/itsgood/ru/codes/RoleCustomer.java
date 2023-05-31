package com.itsgood.ru.codes;

public enum RoleCustomer {

    ROLE_PERSON_ADMINISTRATOR("ROLE_administrator"), ROLE_PERSON_MODERATOR("ROLE_moderator"),
    ROLE_PERSON_USER("ROLE_user"), ROLE_PERSON_GUEST("ROLE_guest");

    private String role;

    RoleCustomer(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;

    }


}
