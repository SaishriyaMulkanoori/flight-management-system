package cloud.wing.flight.repository;

import java.sql.Date;
import java.util.List;

import cloud.wing.flight.entity.Flight;
import exception.DuplicateFlightNumberException;

public interface FlightDao {

	
	void addFlight(Flight flight) throws DuplicateFlightNumberException;

	void updateFlight(Flight flight);

	void safedeleteFlight(int flightId);

	Flight getFlightById(int flightId);

	List<Flight> getAllFlights();
	List<Flight> getAllByAirlineId(int airlineId);
	
	List<Flight> findAllWithAirlines();
	
	 List<Flight> getSearchFlight(String departureAirport, String arrivalAirport, Date departureDate);

	boolean existsByFlightNumber(String flightNumber);
	void cancelBookingByFlightID(int flightId);
}
