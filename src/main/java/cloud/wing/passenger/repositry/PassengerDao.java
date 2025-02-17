package cloud.wing.passenger.repositry;

import java.util.Optional;

import cloud.wing.booking.entity.Booking;
import cloud.wing.passenger.entity.Passenger;

public interface PassengerDao {

	Optional<Passenger> findByEmail(String email);

	int createPassenger(Passenger passenger);

	int updatePassenger(Passenger passenger);

	void save(Booking booking);

	void savePassengers(Booking booking);

	boolean updatepassword(String email, String salt, String hash);
}
