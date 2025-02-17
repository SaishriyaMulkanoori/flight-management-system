package cloud.wing.flight.repository;

import java.util.List;

import cloud.wing.flight.entity.Airline;

public interface AirlineDao {

	void saveAirline(Airline airline);

	Airline findAirlineById(int airlineId);

	List<Airline> findAllAirlines();

	void safeDeleteAirlineAndFlights(int airlineId);

	void updateAirline(Airline airline);

	boolean existsByAirlineName(String airlineName);

}