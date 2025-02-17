package cloud.wing.booking.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public  class BookingDetailsRowMapper implements RowMapper<BookingDetails> {
	@Override
	public BookingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setBookingId(rs.getInt("booking_id"));
		bookingDetails.setAirlineName(rs.getString("aname"));
		bookingDetails.setFlightNumber(rs.getString("flight_number"));
		bookingDetails.setDepartureAirport(rs.getString("departure_airport"));
		bookingDetails.setArrivalAirport(rs.getString("arrival_airport"));
		bookingDetails.setDepartureDate(rs.getTimestamp("departure_date").toLocalDateTime());
		bookingDetails.setArrivalDate(rs.getTimestamp("arrival_date").toLocalDateTime());
		bookingDetails.setNumberOfSeats(rs.getInt("number_of_seats"));
		bookingDetails.setSeatClass(rs.getString("seat_class"));
		bookingDetails.setPassengerNames(rs.getString("passenger_names"));
		bookingDetails.setBookingStatus(rs.getString("status"));
		bookingDetails.setTotalAmount(rs.getBigDecimal("total_amount"));
		bookingDetails.setFlightStatus(rs.getString("flight_status"));
		
		return bookingDetails;
	}
}
