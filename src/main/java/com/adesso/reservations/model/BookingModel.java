package com.adesso.reservations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingModel {
    int startDay;
    int endDay;

    public BookingModel(@JsonProperty("startDay") int startDay, @JsonProperty("endDay") int endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }
}
