package com.itsgood.ru.enums;

public enum VolumeItem {
    VOLUME_ITEM_BIG("big"), VOLUME_ITEM_SMALL("small"), VOLUME_ITEM_MEDIUM("medium");

    private String volume;

    VolumeItem(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
}
