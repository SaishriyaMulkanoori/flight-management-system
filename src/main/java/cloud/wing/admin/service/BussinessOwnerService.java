package cloud.wing.admin.service;

import java.util.List;
import java.util.Optional;

import cloud.wing.admin.entity.BussinessOwner;
import cloud.wing.admin.entity.FlightManager;

public interface BussinessOwnerService {

	
	Optional<BussinessOwner> isUserExists(String email,String password);
	
	List<FlightManager> getAllFlightManager();
	int getFlightCount();
	
	int getPassengerCount();
	int getManagerCount();
}
