package cloud.wing.payment.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.repository.BookingDao;
import cloud.wing.loyaltypoints.repository.LoyaltyPointsDAO;
import cloud.wing.payment.entity.Payment;
import cloud.wing.payment.repository.PaymentDao;
import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao paymentDao;

	@Autowired
	private LoyaltyPointsDAO loyaltyPointsDao;

	@Autowired
	private BookingDao bookingDao;

	@Override
	public Booking fetchbookingByRef(String ref) {
		return paymentDao.getBookingByRef(ref);
	}

	@Override
	@Transactional
	public boolean updateBookingAndSeats(Booking booking, Payment payment) {

		if (booking != null) {
			boolean bookingUpdated = paymentDao.decreaseAvailableSeats(booking.getFlightId(),
					booking.getNumberOfSeats(), booking.getSeatClass());
			boolean statusUpdated = paymentDao.updateStatus(booking.getBookingId());

			String status = "";
			if (!bookingUpdated || !statusUpdated) {
				status = "Failed";
			} else {
				status = "Successful";
			}

			payment.setBookingId(booking.getBookingId());
			payment.setPaymentDate(LocalDateTime.now());
			payment.setPaymentMethod("CreditCard");
			payment.setPaymentStatus(status);

			boolean paymentUpdated = paymentDao.savePayment(payment);

			return bookingUpdated && statusUpdated && paymentUpdated;
		}
		return false;
	}

	@Override
	@Transactional

	public boolean processPaymentWithLoyaltyPoints(Booking booking, Payment payment, int pointsRedeemed) {

		int passengerId = booking.getPassengerId();

		// int totalPoints = loyaltyPointsDao.getTotalPointsByPassengerId(passengerId);

		// Example: 1 point = $0.01

		System.out.println(pointsRedeemed);

		boolean savePayment = paymentDao.savePayment(payment);

		boolean isUpdated = bookingDao.updateBooking(booking, payment);

		if (isUpdated && savePayment) {

			boolean seatsDecreased = paymentDao.decreaseAvailableSeats(booking.getFlightId(),
					booking.getNumberOfSeats(), booking.getSeatClass());

			if (!seatsDecreased) {
				throw new RuntimeException("Failed to update seat count. Booking canclled");
			}

			loyaltyPointsDao.updateLoyaltyPoints(passengerId, 50, pointsRedeemed);

		}

		return isUpdated && savePayment;

	}

}
