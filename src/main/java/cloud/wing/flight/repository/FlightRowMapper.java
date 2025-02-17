package cloud.wing.flight.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cloud.wing.admin.entity.FlightManager;
import cloud.wing.flight.entity.Airline;
import cloud.wing.flight.entity.Flight;
import cloud.wing.flight.entity.FlightStatus;

public class FlightRowMapper implements RowMapper<Flight> {

	@Override
	public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
		Flight flight = new Flight();

		flight.setFlightId(rs.getInt("flight_id"));

		flight.setFlightNumber(rs.getString("flight_number"));

		flight.setDepartureAirport(rs.getString("departure_airport"));

		flight.setArrivalAirport(rs.getString("arrival_airport"));

		java.sql.Timestamp timestamp = rs.getTimestamp("departure_date");
		// LocalDateTime dateTime=LocalDateTime.parse("departure_date");
		flight.setDepartureDate(timestamp);

		timestamp = rs.getTimestamp("arrival_date");
		flight.setArrivalDate(timestamp);

		flight.setAvailableSeatsEconomy(rs.getInt("available_seats_economy"));

		flight.setAvailableSeatsBusiness(rs.getInt("available_seats_business"));

		flight.setTotalSeatsEconomy(rs.getInt("total_seats_economy"));

		flight.setTotalSeatsBusiness(rs.getInt("total_seats_business"));

		flight.setPriceEconomy(rs.getBigDecimal("price_economy"));

		flight.setPriceBusiness(rs.getBigDecimal("price_business"));

		flight.setFlightStatus(FlightStatus.valueOf(rs.getString("flight_status")));

		Airline airline = new Airline();
		airline.setAirlineId(rs.getInt("airline_id"));

		airline.setName(rs.getString("airline_name"));

		FlightManager flightManager = new FlightManager();

		flightManager.setId(rs.getInt("flight_manager_id"));

		flightManager.setName(rs.getString("manager_name"));

		flight.setFlightManager(flightManager);
		flight.setAirline(airline); 

		return flight;
	}

}
