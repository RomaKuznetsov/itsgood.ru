package com.itsgood.ru.codes;

public enum DistanceDelivery {
    DISTANCE_DELIVERY_PICKUP("pickup"), DISTANCE_DELIVERY_CITY("city"),
    DISTANCE_DELIVERY_POSTAL("postal"), DISTANCE_DELIVERY_COUNTRY("country");

    private String distance;

    DistanceDelivery(String distance) {
        this.distance = distance;
    }

    public String getType() {
        return distance;
    }
}
