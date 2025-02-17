package cloud.wing.passenger.repositry;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cloud.wing.booking.entity.Booking;
import cloud.wing.passenger.entity.Passenger;

@Repository
public class PassengerDaoImpl implements PassengerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int createPassenger(Passenger passenger) {

		final String INSERT_QUERY = "INSERT INTO passenger (name,email,username,gender, date_of_birth, mobile_number,password_salt,password_hash) VALUES (?,?,?,?,?,?,?,?)";

		return jdbcTemplate.update(INSERT_QUERY, passenger.getName(), passenger.getEmail(), passenger.getUsername(),
				passenger.getGender(), passenger.getDateOfBirth(), passenger.getMobileNumber(),
				passenger.getPassport_salt(), passenger.getPassword_hash());

	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<Passenger> findByEmail(String email) {

		final String GET_ALL_USER = "SELECT * FROM passenger WHERE email=?";
		return jdbcTemplate.query(GET_ALL_USER, new Object[] { email }, rs -> {

			if (rs.next()) {
				Passenger passenger = new Passenger(rs.getInt("passenger_id"), rs.getString("name"),
						rs.getString("email"), rs.getString("username"), rs.getString("gender"),
						rs.getString("date_of_birth"), rs.getString("mobile_number"), rs.getString("passport_number"),
						rs.getString("password_hash"), rs.getString("password_salt"));
				return Optional.of(passenger);
			}

			return Optional.empty();
		});

	}

	@Override

	public void save(Booking booking) {
		String sql = "INSERT INTO booking (passenger_id, flight_id, booking_date, number_of_seats, seat_class, total_amount, status,booking_ref) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
		jdbcTemplate.update(sql, booking.getPassengerId(), booking.getFlightId(), LocalDateTime.now(),
				booking.getNumberOfSeats(), booking.getSeatClass(), booking.getTotalAmount(), "pending",
				booking.getBookingRef());
	}

	@Override
	public int updatePassenger(Passenger passenger) {

		String UPDATE_QUERY = "";
		if (passenger.getPassportNumber() == null) {
			UPDATE_QUERY = "UPDATE  passenger  SET name=?,username=?,gender=?,date_of_birth=?,mobile_number=?  WHERE passenger_id=?";
			return jdbcTemplate.update(UPDATE_QUERY, passenger.getName(), passenger.getUsername(),
					passenger.getGender(), passenger.getDateOfBirth(), passenger.getMobileNumber(),
					passenger.PassengerId());
		} else {
			UPDATE_QUERY = "UPDATE  passenger  SET name=?,username=?,gender=?,date_of_birth=?,mobile_number=?,passport_number=?  WHERE passenger_id=?";
			return jdbcTemplate.update(UPDATE_QUERY, passenger.getName(), passenger.getUsername(),
					passenger.getGender(), passenger.getDateOfBirth(), passenger.getMobileNumber(),
					passenger.getPassportNumber(), passenger.PassengerId());

		}

	}

	@Override
	public void savePassengers(Booking booking) {
		for (String passengerName : booking.getPassengerNames()) {
			String sql = "INSERT INTO passengernames (booking_ref, passenger_name) VALUES (?, ?)";
			jdbcTemplate.update(sql, booking.getBookingRef(), passengerName);

		}

	}

	@Override
	public boolean updatepassword(String email, String salt, String hash) {
		String sql = "UPDATE passenger SET password_salt = ? , password_hash=? WHERE email = ?";
		int rowsAffected = jdbcTemplate.update(sql, salt, hash, email);
		if (rowsAffected > 0) {
			return true;
		}
		return false;
	}
}
