package com.adesso.reservations.service;

import com.adesso.reservations.model.ReservationModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {

    private ReservationModel model;
    public enum Decision {Decline, Accept};
    private static int DEFAULT_ROOM_AMOUNT = 3; // Assumpt that default room amount is 3


    public ReservationService() {
        model = new ReservationModel();
        this.resetHotel(DEFAULT_ROOM_AMOUNT);
    }

    public void resetHotel(int size) {
        this.resetRooms(size);
    }


    public String bookRoom(int startDay, int endDay) {

        if (startDay < 0 || endDay > ReservationModel.YEARINDAYS)
            return Decision.Decline.name();

        for (Map.Entry<Integer, Boolean[]> room : this.model.getRooms().entrySet()) {
            Boolean[] days = room.getValue();

            boolean reservationAccepted = true;
            for (int i = startDay; i <= endDay; i++) {

                if (days[i] != null && days[i]) {
                    reservationAccepted = false;
                    break;
                }
            }

            if (reservationAccepted) {
                for (int j = startDay; j <= endDay; j++) {
                    days[j] = true;
                }
                this.model.getRooms().replace(room.getKey(), days);
                return Decision.Accept.name();
            }
        }
        return Decision.Decline.name();
    }

    private void resetRooms(int size) {
        this.model.setRooms(new HashMap<Integer, Boolean[]>());

        for (int i = 1; i <= size; i++) {
            this.model.getRooms().put(i, new Boolean[ReservationModel.YEARINDAYS]);
        }
    }

    public int getSize() {
        return this.model.getSize();
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Map.Entry<Integer, Boolean[]> entry : this.model.getRooms().entrySet()) {
            String roomName = "Room " + entry.getKey().toString();
            returnString.append(roomName);
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
