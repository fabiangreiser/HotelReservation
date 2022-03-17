package com.adesso.reservations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResetModel {

    private int size;

    public HotelResetModel(@JsonProperty("size") int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
