package cloud.wing.booking.repository;

import java.util.List;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.entity.BookingDetails;
import cloud.wing.payment.entity.Payment;

public interface BookingDao {

	Booking findById(Integer bookingId);

	List<Booking> findAll();

	public List<BookingDetails> findBookingDetailsByPassengerId(int passengerId);

	public BookingDetails findBookingByID(int bookingId);

	boolean cancelBooking(int bookingId);

	public List<BookingDetails> findBookingDetailsByFlightNumber(String flightNumber);

	List<BookingDetails> getAllBookingDetail();

	boolean updateBooking(Booking booking, Payment payment);

	Booking fetchBookingByRef(String bookingRef);

}
