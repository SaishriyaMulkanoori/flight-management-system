package cloud.wing.booking.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.entity.BookingDetails;
import cloud.wing.booking.entity.BookingDetailsRowMapper;
import cloud.wing.booking.entity.BookingRowMapper;
import cloud.wing.payment.entity.Payment;
import jakarta.transaction.Transactional;

@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Booking findById(Integer bookingId) {
		String sql = "SELECT * FROM booking WHERE booking_id = ?";
		return jdbcTemplate.queryForObject(sql, new BookingRowMapper(), bookingId);
	}

	@Override
	public List<Booking> findAll() {
		String sql = "SELECT * FROM booking";
		return jdbcTemplate.query(sql, new BookingRowMapper());
	}

//	@Override
//	public Optional<Flight> getFlightById(int id) {
//		Flight flight = null;
//		final String sql = "SELECT * FROM flight WHERE flight_id=?";
//
//		try {
//
//			flight = jdbcTemplate.queryForObject(sql, new FlightRowMapper(), id);
//
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//
//		// return an Optional of customer OR Optional of nothing
//		// Now there will no exception or null
//		return Optional.ofNullable(flight);
//
//	}
	// changes
//	@Override
//	public List<BookingDetails> findBookingDetailsByPassengerId(int passengerId) {
//        String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number,flight.flight_status, flight.departure_airport, " +
//                "flight.arrival_airport, flight.departure_date, flight.arrival_date, booking.number_of_seats, " +
//                "booking.seat_class, passenger.name, booking.status, booking.total_amount " +
//                "FROM booking " +
//                "JOIN flight ON booking.flight_id = flight.flight_id " +
//                "JOIN airline ON flight.airline_id = airline.airline_id " +
//                "JOIN passenger ON booking.passenger_id = passenger.passenger_id " +
//                "WHERE booking.passenger_id = ? AND booking.status != 'pending'";
//
//        return jdbcTemplate.query(sql, new BookingDetailsRowMapper(),passengerId);
//    }

	@Override
	public List<BookingDetails> findBookingDetailsByPassengerId(int passengerId) {
		String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number, flight.flight_status, "
				+ "flight.departure_airport, flight.arrival_airport, flight.departure_date, flight.arrival_date, "
				+ "booking.number_of_seats, booking.seat_class, booking.status, booking.total_amount, "
				+ "GROUP_CONCAT(passengernames.passenger_name SEPARATOR ', ') as passenger_names " + "FROM booking "
				+ "JOIN flight ON booking.flight_id = flight.flight_id "
				+ "JOIN airline ON flight.airline_id = airline.airline_id "
				+ "JOIN passenger ON booking.passenger_id = passenger.passenger_id "
				+ "LEFT JOIN passengernames ON booking.booking_ref = passengernames.booking_ref "
				+ "WHERE booking.passenger_id = ? AND booking.status != 'pending' " + "GROUP BY booking.booking_id";

		return jdbcTemplate.query(sql, new BookingDetailsRowMapper(), passengerId);
	}

	@Override
	@Transactional
	public boolean cancelBooking(int bookingId) {

		String updateBookingSql = "UPDATE booking SET status = 'Cancelled' WHERE booking_id = ?";
		int rowsAffected = jdbcTemplate.update(updateBookingSql, bookingId);

		if (rowsAffected > 0) {
			System.out.println(rowsAffected);
			String selectBookingSql = "SELECT flight_id, number_of_seats,seat_class FROM booking WHERE booking_id = ?";
			Map<String, Object> bookingData = jdbcTemplate.queryForMap(selectBookingSql, bookingId);

			int flightId = (Integer) bookingData.get("flight_id");
			int bookedSeats = (Integer) bookingData.get("number_of_seats");
			String seatClass = (String) bookingData.get("seat_class");

			String updateFlightSql = "";
			if (seatClass.equals("Business")) {
				updateFlightSql = "UPDATE flight SET available_seats_business = available_seats_business + ? WHERE flight_id  = ?";
			} else {
				updateFlightSql = "UPDATE flight SET available_seats_economy = available_seats_economy + ? WHERE flight_id  = ?";

			}

			jdbcTemplate.update(updateFlightSql, bookedSeats, flightId);

			return true;
		}
		return false;

	}

	@Override
	public BookingDetails findBookingByID(int bookingId) {
//		String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number,flight.flight_status, flight.departure_airport, " +
//                "flight.arrival_airport, flight.departure_date, flight.arrival_date, booking.number_of_seats, " +
//                "booking.seat_class, passenger.name, booking.status, booking.total_amount " +
//                "FROM booking " +
//                "JOIN flight ON booking.flight_id = flight.flight_id " +
//                "JOIN airline ON flight.airline_id = airline.airline_id " +
//                "JOIN passenger ON booking.passenger_id = passenger.passenger_id " +
//                "WHERE booking.booking_id = ? AND booking.status != 'pending' ";
		String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number, flight.flight_status, "
				+ "flight.departure_airport, flight.arrival_airport, flight.departure_date, flight.arrival_date, "
				+ "booking.number_of_seats, booking.seat_class, booking.status, booking.total_amount, "
				+ "GROUP_CONCAT(passengernames.passenger_name SEPARATOR ', ') as passenger_names " + "FROM booking "
				+ "JOIN flight ON booking.flight_id = flight.flight_id "
				+ "JOIN airline ON flight.airline_id = airline.airline_id "
				+ "JOIN passenger ON booking.passenger_id = passenger.passenger_id "
				+ "LEFT JOIN passengernames ON booking.booking_ref = passengernames.booking_ref "
				+ "WHERE booking.booking_id = ? AND booking.status != 'pending' " + "GROUP BY booking.booking_id";

		return jdbcTemplate.queryForObject(sql, new BookingDetailsRowMapper(), bookingId);
	}

	@Override
	public List<BookingDetails> findBookingDetailsByFlightNumber(String flightNumber) {
//		String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number,flight.flight_status, flight.departure_airport, " +
//                "flight.arrival_airport, flight.departure_date, flight.arrival_date, booking.number_of_seats, " +
//                "booking.seat_class, passenger.name, booking.status, booking.total_amount " +
//                "FROM booking " +
//                "JOIN flight ON booking.flight_id = flight.flight_id " +
//                "JOIN airline ON flight.airline_id = airline.airline_id " +
//                "JOIN passenger ON booking.passenger_id = passenger.passenger_id " +
//                "WHERE flight.flight_number = ? AND booking.status != 'pending' ";
		String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number, flight.flight_status, "
				+ "flight.departure_airport, flight.arrival_airport, flight.departure_date, flight.arrival_date, "
				+ "booking.number_of_seats, booking.seat_class, booking.status, booking.total_amount, "
				+ "GROUP_CONCAT(passengernames.passenger_name SEPARATOR ', ') as passenger_names " + "FROM booking "
				+ "JOIN flight ON booking.flight_id = flight.flight_id "
				+ "JOIN airline ON flight.airline_id = airline.airline_id "
				+ "JOIN passenger ON booking.passenger_id = passenger.passenger_id "
				+ "LEFT JOIN passengernames ON booking.booking_ref = passengernames.booking_ref "
				+ "WHERE flight.flight_number  = ? AND booking.status != 'pending' " + "GROUP BY booking.booking_id";

		return jdbcTemplate.query(sql, new BookingDetailsRowMapper(), flightNumber);
	}

	@Override
	public List<BookingDetails> getAllBookingDetail() {
		String sql = "SELECT booking.booking_id, airline.name as aname, flight.flight_number, flight.flight_status, "
				+ "flight.departure_airport, flight.arrival_airport, flight.departure_date, flight.arrival_date, "
				+ "booking.number_of_seats, booking.seat_class, booking.status, booking.total_amount, "
				+ "GROUP_CONCAT(passengernames.passenger_name SEPARATOR ', ') as passenger_names " + "FROM booking "
				+ "JOIN flight ON booking.flight_id = flight.flight_id "
				+ "JOIN airline ON flight.airline_id = airline.airline_id "
				+ "JOIN passenger ON booking.passenger_id = passenger.passenger_id "
				+ "LEFT JOIN passengernames ON booking.booking_ref = passengernames.booking_ref " +

				"GROUP BY booking.booking_id";

		return jdbcTemplate.query(sql, new BookingDetailsRowMapper());
	}

	@Override

	public boolean updateBooking(Booking booking, Payment payment) {

		String sql = """

				  UPDATE booking

				  SET status = ?,

				    total_amount = ?

				  WHERE booking_id = ?

				""";

		int rowsUpdated = jdbcTemplate.update(

				sql,

				"Confirmed", // Assuming payment status is updated to "PAID"

				payment.getTotalAmount(),

				booking.getBookingId()

		);

		return rowsUpdated > 0;

	}

	@SuppressWarnings("deprecation")
	@Override

	public Booking fetchBookingByRef(String bookingRef) {

		String sql = "SELECT * FROM booking WHERE booking_ref = ?";

		try {

			return jdbcTemplate.queryForObject(sql, new Object[] { bookingRef }, (rs, rowNum) -> {

				Booking booking = new Booking();

				booking.setBookingId(rs.getInt("booking_id"));

				booking.setBookingRef(rs.getString("booking_ref"));

				booking.setStatus(rs.getString("status"));

				booking.setTotalAmount(rs.getBigDecimal("total_amount"));

				// Map other fields as necessary

				return booking;

			});

		} catch (EmptyResultDataAccessException e) {

			return null; // Return null if no booking is found

		}

	}

}
