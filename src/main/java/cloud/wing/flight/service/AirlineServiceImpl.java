package cloud.wing.flight.service;

import java.util.List;
//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.flight.entity.Airline;
//import cloud.wing.flight.repository.AirlineDao;
import cloud.wing.flight.repository.AirlineDao;
import exception.DuplicateAirlineException;

@Service

public class AirlineServiceImpl implements AirlineService {

	private final AirlineDao airlineDao;

	public AirlineServiceImpl(AirlineDao airlineDao) {

		this.airlineDao = airlineDao;

	}

	@Override

	public void addAirline(Airline airline) {

		if(existsByAirlineName(airline.getName())) {
			throw new DuplicateAirlineException("Airline with name '" +airline.getName() + "' already exists.");
		}
		airlineDao.saveAirline(airline);

	}
	
	@Override
	public void updateAirline(Airline airline) {
		
		if(existsByAirlineName(airline.getName())) {
			throw new DuplicateAirlineException("Airline with name '" +airline.getName() + "' already exists.");
		}
		airlineDao.updateAirline(airline);
				
	}

	@Override

	public Airline getAirlineById(int airlineId) {

		return airlineDao.findAirlineById(airlineId);

	}

	@Override

	public List<Airline> getAllAirlines() {

		return airlineDao.findAllAirlines();

	}

	@Override

	public void removeAirline(int airlineId) {

		airlineDao.safeDeleteAirlineAndFlights(airlineId);

	}

	

	@Override
	public boolean existsByAirlineName(String airlineName) {
		return airlineDao.existsByAirlineName(airlineName);
	}

}
