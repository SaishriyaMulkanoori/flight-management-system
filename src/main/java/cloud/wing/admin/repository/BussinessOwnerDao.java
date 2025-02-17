package cloud.wing.admin.repository;

import java.util.List;
import java.util.Optional;

import cloud.wing.admin.entity.BussinessOwner;
import cloud.wing.admin.entity.FlightManager;

public interface BussinessOwnerDao {

	Optional<BussinessOwner> findByEmail(String email);

	List<FlightManager> getAllFlightManager();

	int getFlightCount();

	int getPassengerCount();

	int getManagerCount();
}
