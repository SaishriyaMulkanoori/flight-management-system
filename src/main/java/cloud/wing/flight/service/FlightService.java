package cloud.wing.flight.service;

import java.sql.Date;
import java.util.List;

import cloud.wing.flight.entity.Flight;

public interface FlightService {

	void addFlight(Flight flight);

	void updateFlight(Flight flight);

	void deleteFlight(int flightId);

	Flight getFlightById(int flightId);

	List<Flight> getAllFlights();
	List<Flight> getAllFlightByAirlineId(int airlineId);

	boolean existsByFlightNumber(String flightNumber);

	List<Flight> getSearchFlight(String departureAirport, String arrivalAirport, Date departureDate);

}

//List<Flight> fetchAllFlights(String departureAirport, String arrivalAirport, Date departureDate);
