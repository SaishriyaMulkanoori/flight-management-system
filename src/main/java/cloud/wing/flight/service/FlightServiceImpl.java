package cloud.wing.flight.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.admin.service.FlightManagerService;
import cloud.wing.flight.entity.Flight;
import cloud.wing.flight.repository.FlightDao;
import exception.DuplicateFlightNumberException;
import jakarta.transaction.Transactional;

@Service

public class FlightServiceImpl implements FlightService {

	@Autowired

	private FlightDao flightDao;
	@SuppressWarnings("unused")
	private FlightManagerService flightManagerService;

	@Override

	public void addFlight(Flight flight) {

		if (existsByFlightNumber(flight.getFlightNumber())) {
			throw new DuplicateFlightNumberException("Flight number already exists: " + flight.getFlightNumber());
		}
		flightDao.addFlight(flight);

	}

	@Override
	@Transactional
	public void updateFlight(Flight flight) {
		flightDao.updateFlight(flight);

		if (flight.getFlightStatus().toString() == "Cancelled") {
			flightDao.cancelBookingByFlightID(flight.getFlightId());
		}
	}

	@Override

	public void deleteFlight(int flightId) {
		flightDao.safedeleteFlight(flightId);
	}

	@Override

	public Flight getFlightById(int flightId) {
		return flightDao.getFlightById(flightId);
	}

	@Override

	public List<Flight> getAllFlights() {
		return flightDao.findAllWithAirlines();
	}

	@Override
	public boolean existsByFlightNumber(String flightNumber) {
		return flightDao.existsByFlightNumber(flightNumber);
	}

	@Override
	public List<Flight> getSearchFlight(String departureAirport, String arrivalAirport, Date departureDate) {

		return flightDao.getSearchFlight(departureAirport, arrivalAirport, departureDate);
	}

	@Override
	public List<Flight> getAllFlightByAirlineId(int airlineId) {
		return flightDao.getAllByAirlineId(airlineId);
	}

}
