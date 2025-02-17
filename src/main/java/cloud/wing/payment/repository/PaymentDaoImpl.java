package cloud.wing.payment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.entity.BookingRowMapper;
import cloud.wing.payment.entity.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Booking getBookingByRef(String ref) {
		String sql = "SELECT b.*, p.* \r\n" + "FROM booking b \r\n"
				+ "LEFT JOIN passenger p ON b.passenger_id = p.passenger_id \r\n" + "WHERE b.booking_ref = ?";

		return jdbcTemplate.queryForObject(sql, new BookingRowMapper(), ref);
	}

	@Override
	public boolean updateStatus(int bookingId) {
		String sql = "UPDATE booking SET status = 'Confirmed' WHERE booking_id = ?";
		int rowsAffected = jdbcTemplate.update(sql, bookingId);
		return rowsAffected > 0;
	}

	@Override
	public boolean decreaseAvailableSeats(int flightId, int numberOfSeats, String seatClasss) {

		String sql = "";
		if (seatClasss.equals("Business")) {
			sql = "UPDATE flight SET available_seats_business = available_seats_business - ? WHERE flight_id = ?";
		} else {

			sql = "UPDATE flight SET available_seats_economy = available_seats_economy - ? WHERE flight_id = ?";
		}

		int rowsAffected = jdbcTemplate.update(sql, numberOfSeats, flightId);
		return rowsAffected > 0;
	}

	@Override
	public boolean savePayment(Payment payment) {
		String sql = "INSERT INTO payment (booking_id, payment_method, card_number, card_holder_name,expiry_date,payment_status,payment_date,total_amount,discount_amount) VALUES ( ?, ?, ?,?,?,?,?,?,?)";
		int rowsAffected = jdbcTemplate.update(sql, payment.getBookingId(), payment.getPaymentMethod(),
				payment.getCardNumber(), payment.getCardHoldername(), payment.getExpiryDate(),
				payment.getPaymentStatus(), payment.getPaymentDate(), payment.getTotalAmount(),
				payment.getDiscountAmonut());
		return rowsAffected > 0;
	}

}
