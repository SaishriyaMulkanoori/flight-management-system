package cloud.wing.admin.service;

import java.util.Optional;

import cloud.wing.admin.entity.FlightManager;

public interface FlightManagerService {

	String registerFlightManager(FlightManager flightManager);

	Optional<FlightManager> isUserExists(String email, String password);

	void modifyAuthority(int adminId);
//	boolean isUserExists (String email,String password);

	boolean getAuthority(int adminId);

	double totalRevenue();

	int activeFlightCount();

	int getFlightCount();

	int getPassengerCount();

	String updateFlightManager(FlightManager flightManager);

	FlightManager getFlightManagerById(int id);

	Optional<FlightManager> findManagerByEmail(String email);

	boolean updatePassword(String email, String password);
}
