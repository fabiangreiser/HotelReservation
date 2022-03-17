package com.adesso.reservations.model;

import org.springframework.stereotype.Repository;

import java.util.*;


public class ReservationModel {

    public static int YEARINDAYS = 365;
    Map<Integer, Boolean[]> rooms;
    int size;

    public  ReservationModel(){}

    public void setRooms(Map<Integer, Boolean[]> rooms) {
        this.rooms = rooms;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Map<Integer, Boolean[]> getRooms() {
        return rooms;
    }
}
