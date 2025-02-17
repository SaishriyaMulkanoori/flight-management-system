package cloud.wing.admin.repository;

import java.util.Optional;

import cloud.wing.admin.entity.FlightManager;

public interface FlightManagerDao {

	Optional<FlightManager> findByEmail(String email);

	int createFlightManager(FlightManager flightManager);

	void changeAuthority(int adminId);

	boolean getAuthority(int adminId);

	FlightManager findFlightManagerById(int flightManagerId);

	double totalRevenue();

	int activeFlightCount();

	int getFlightCount();

	int getPassengerCount();

	int updateFlightManager(FlightManager manager);

	boolean updatepassword(String email, String salt, String hash);

}
