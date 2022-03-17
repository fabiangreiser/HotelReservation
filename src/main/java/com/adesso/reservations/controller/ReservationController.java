package com.adesso.reservations.controller;

import com.adesso.reservations.model.BookingModel;
import com.adesso.reservations.model.HotelResetModel;
import com.adesso.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReservationController {

    private ReservationService service;

    @Autowired
    public ReservationController(ReservationService service){
        this.service = service;
    }

    @GetMapping("/")
    public String index(){
        return "Hotel with size: " + service.getSize();
    }

    @PostMapping("/reset")
    public String resetHotel(@RequestBody HotelResetModel model){
        service.resetHotel(model.getSize());
        return "hotel reset with size: " + model.getSize();
    }

    @PostMapping("/booking")
    public String bookRoom(@RequestBody BookingModel model){
        return service.bookRoom(model.getStartDay(), model.getEndDay());
    }
}
