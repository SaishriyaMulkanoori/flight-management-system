package cloud.wing.payment.repository;

import cloud.wing.booking.entity.Booking;
import cloud.wing.payment.entity.Payment;

public interface PaymentDao {

	Booking getBookingByRef(String ref);

	boolean updateStatus(int bookingId);

	public boolean decreaseAvailableSeats(int flightId, int numberOfSeats, String seatClasss);

	public boolean savePayment(Payment payment);
}
