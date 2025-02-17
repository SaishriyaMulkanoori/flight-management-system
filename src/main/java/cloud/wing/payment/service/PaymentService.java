package cloud.wing.payment.service;

import cloud.wing.booking.entity.Booking;
import cloud.wing.payment.entity.Payment;

public interface PaymentService {

	Booking fetchbookingByRef(String ref);
	public boolean updateBookingAndSeats(Booking booking,Payment payment);
	
	boolean processPaymentWithLoyaltyPoints(Booking booking,Payment payment,int pointsRedeemed);
	
}
