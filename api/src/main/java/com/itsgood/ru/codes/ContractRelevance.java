package com.itsgood.ru.codes;

public enum ContractRelevance {

    RELEVANCE_CONTRACT_RELEVANT("relevant"), RELEVANCE_CONTRACT_IRRELEVANCE("irrelevance");

    private String relevance;

    ContractRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getStatus() {
        return relevance;

    }
}
