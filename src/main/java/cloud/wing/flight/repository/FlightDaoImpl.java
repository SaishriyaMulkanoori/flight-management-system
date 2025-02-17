package cloud.wing.flight.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cloud.wing.flight.entity.Flight;
import jakarta.transaction.Transactional;

@Repository

public class FlightDaoImpl implements FlightDao {

	@Autowired

	private JdbcTemplate jdbcTemplate;

	@Override

	public void addFlight(Flight flight) {

		String sql = "INSERT INTO flight (flight_number, airline_id, departure_airport, arrival_airport, departure_date, arrival_date, available_seats_economy, available_seats_business, total_seats_economy, total_seats_business, price_economy, price_business, flight_status, flight_manager_id, is_deleted) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getAirline().getAirlineId(),
				flight.getDepartureAirport(), flight.getArrivalAirport(),

				flight.getDepartureDate(), flight.getArrivalDate(), flight.getAvailableSeatsEconomy(),
				flight.getAvailableSeatsBusiness(),

				flight.getTotalSeatsEconomy(), flight.getTotalSeatsBusiness(), flight.getPriceEconomy(),
				flight.getPriceBusiness(),

				flight.getFlightStatus().toString(), flight.getFlightManager().getId(), flight.isDelete());

	}

	@Override
	@Transactional

	public void updateFlight(Flight flight) {

		String sql = "UPDATE flight SET flight_number = ?, airline_id = ?, departure_airport = ?, arrival_airport = ?, departure_date = ?, arrival_date = ?, available_seats_economy = ?, available_seats_business = ?, total_seats_economy = ?, total_seats_business = ?, price_economy = ?, price_business = ?, flight_status = ?, flight_manager_id = ? WHERE flight_id = ?";

		jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getAirline().getAirlineId(),
				flight.getDepartureAirport(), flight.getArrivalAirport(),

				flight.getDepartureDate(), flight.getArrivalDate(), flight.getAvailableSeatsEconomy(),
				flight.getAvailableSeatsBusiness(),

				flight.getTotalSeatsEconomy(), flight.getTotalSeatsBusiness(), flight.getPriceEconomy(),
				flight.getPriceBusiness(),

				flight.getFlightStatus().toString(), flight.getFlightManager().getId(), flight.getFlightId());

	}

	@Override

	public void safedeleteFlight(int flightId) {

		String sql = "UPDATE flight SET is_deleted=1 WHERE flight_id=?";

		jdbcTemplate.update(sql, flightId);

	}

	@SuppressWarnings("deprecation")
	@Override

	public Flight getFlightById(int flightId) {

		String sql = "SELECT * FROM flight WHERE flight_id = ? ";

		return jdbcTemplate.queryForObject(sql, new Object[] { flightId }, new BeanPropertyRowMapper<>(Flight.class));

	}

	@Override

	public List<Flight> getAllFlights() {

		String sql = "SELECT * FROM flight WHERE is_deleted = 0 ";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Flight.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Flight> getSearchFlight(String departureAirport, String arrivalAirport, Date departureDate) {
		String sql = "SELECT * FROM flight WHERE departure_airport LIKE ? AND arrival_airport LIKE ? AND DATE(departure_date) = ? AND is_deleted = 0  AND flight_status !='Cancelled' ";
		return jdbcTemplate.query(sql, new Object[] { departureAirport, arrivalAirport, departureDate },
				new BeanPropertyRowMapper<>(Flight.class));
	};

	@Override
	public List<Flight> findAllWithAirlines() {

		String sql = "SELECT f.flight_id, " + "f.flight_number, " + "f.departure_airport, " + "f.arrival_airport,"
				+ "f.departure_date," + "f.arrival_date," + "f.available_seats_economy," + "f.available_seats_business,"
				+ "f.total_seats_economy," + "f.total_seats_business," + "f.price_economy," + "f.price_business, "
				+ "f.flight_status, " + "a.airline_id," + "a.name AS airline_name," + "fm.flight_manager_id,"
				+ "fm.name AS manager_name FROM flight f " + "JOIN airline a ON f.airline_id = a.airline_id "
				+ "JOIN flight_manager fm ON f.flight_manager_id=fm.flight_manager_id where f.is_deleted=0";

		return jdbcTemplate.query(sql, new FlightRowMapper());
	}

	@Override
	public boolean existsByFlightNumber(String flightNumber) {

		String sql = "select count(*) from flight where flight_number=?";

		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, flightNumber);
		return count != null && count > 0;
	}

	@Override
	public void cancelBookingByFlightID(int flightId) {
		String sql = "UPDATE booking SET status = 'Cancelled' WHERE flight_id = ?";
		jdbcTemplate.update(sql, flightId);

	}

	@Override
	public List<Flight> getAllByAirlineId(int airlineId) {
		String sql = "SELECT * FROM flight WHERE is_deleted = 0 AND airline_id=? ";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Flight.class), airlineId);
	}

}

//@Override
//public List<Flight> getSearchFlight(String departureAirport, String arrivalAirport, Date departureDate) {
//	final String GET_ALL_FLIGHTS = "SELECT * FROM flight WHERE departure_airport LIKE ? AND arrival_airport LIKE ? AND DATE(departure_date) = ? AND WHERE is_deleted = 0"; 
//
//	return jdbcTemplate.query(GET_ALL_FLIGHTS,new BeanPropertyRowMapper<>(Flight.class) ,
//			new Object[] { "%" + departureAirport + "%", "%" + arrivalAirport + "%", departureDate });
//};
