package cloud.wing.flight.service;

import java.util.List;

import cloud.wing.flight.entity.Airline;


public interface AirlineService {

  void addAirline(Airline airline);

  Airline getAirlineById(int airlineId);

  List<Airline> getAllAirlines();

  void removeAirline(int airlineId);

  void updateAirline(Airline airline);
  
  boolean existsByAirlineName(String airlineName);


}


