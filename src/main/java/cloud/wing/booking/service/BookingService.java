package cloud.wing.booking.service;

import java.util.List;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.entity.BookingDetails;

public interface BookingService {

	public List<Booking> getAllBooking();

//	 Optional<Flight> fetchFlightById(int id) ;
	List<BookingDetails> fetchBookingDetails(int passenger_id);

	boolean cancelTicket(int booking_id);

	BookingDetails fetchBookingById(int bookingId);

	List<BookingDetails> fetchBookingByFlightNumber(String flightNumber);

	List<BookingDetails> fetchAllBookingDetails();
}
