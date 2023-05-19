package com.itsgood.ru.enums;

public enum ContractRelevance {

    RELEVANCE_CONTRACT_RELEVANT("relevant"), STATUS_PAYMENT_INACTIVE("irrelevance");

    private String relevance;

    ContractRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getStatus() {
        return relevance;

    }
}
