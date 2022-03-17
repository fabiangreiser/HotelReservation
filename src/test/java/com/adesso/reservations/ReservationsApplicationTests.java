package com.adesso.reservations;

import com.adesso.reservations.model.BookingModel;
import com.adesso.reservations.model.HotelResetModel;
import com.adesso.reservations.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationsApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() throws Exception {

		// Test Run (1a/1b)
		testHotelReset(1);
		testBooking(-4, 2, ReservationService.Decision.Decline.name());
		testBooking(200, 400, ReservationService.Decision.Decline.name());

		// Test Run (2)
		testHotelReset(3);
		testBooking(0,5, ReservationService.Decision.Accept.name());
		testBooking(7,13,ReservationService.Decision.Accept.name());
		testBooking(3,9,ReservationService.Decision.Accept.name());
		testBooking(5,7,ReservationService.Decision.Accept.name());
		testBooking(6,6,ReservationService.Decision.Accept.name());
		testBooking(0,4,ReservationService.Decision.Accept.name());

		// Test Run (3)
		testHotelReset(3);
		testBooking(1,3,ReservationService.Decision.Accept.name());
		testBooking(2,5,ReservationService.Decision.Accept.name());
		testBooking(1,9,ReservationService.Decision.Accept.name());
		testBooking(0,15,ReservationService.Decision.Decline.name());

		// Test Run (4)
		testHotelReset(3);
		testBooking(1,3,ReservationService.Decision.Accept.name());
		testBooking(0,15,ReservationService.Decision.Accept.name());
		testBooking(1,9,ReservationService.Decision.Accept.name());
		testBooking(2,5,ReservationService.Decision.Decline.name());
		testBooking(4,9,ReservationService.Decision.Accept.name());

		// Test Run (5)
		testHotelReset(2);
		testBooking(1,3,ReservationService.Decision.Accept.name());
		testBooking(0,4,ReservationService.Decision.Accept.name());
		testBooking(2,3,ReservationService.Decision.Decline.name());
		testBooking(5,5,ReservationService.Decision.Accept.name());
		testBooking(4,10,ReservationService.Decision.Decline.name());
		testBooking(10,10,ReservationService.Decision.Accept.name());
		testBooking(6,7,ReservationService.Decision.Accept.name());
		testBooking(8,10,ReservationService.Decision.Accept.name());
		testBooking(8,9,ReservationService.Decision.Accept.name());
	}

	void testBooking(int startDay, int endDay, String result) throws Exception{
		BookingModel booking = new BookingModel(startDay, endDay);
		mvc.perform(MockMvcRequestBuilders.post("/booking")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(booking)))
				.andExpect(status().isOk())
				.andExpect(content().string(result));
	}

	void testHotelReset(int size)throws Exception {

		HotelResetModel reset = new HotelResetModel(size);
		mvc.perform(MockMvcRequestBuilders.post("/reset")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(reset)))
				.andExpect(status().isOk())
				.andExpect(content().string("hotel reset with size: " + size));
	}

}
