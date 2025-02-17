package cloud.wing.passenger.service;

import java.util.Optional;

import cloud.wing.booking.entity.Booking;
import cloud.wing.passenger.entity.Passenger;

public interface PassengerService {

	String registerPassenger(Passenger passenger);

	Optional<Passenger> isUserExists(String email, String password);

	String updatePassenger(Passenger passenger);

	public Booking createBooking(Booking booking);

	Optional<Passenger> findUserByEmail(String email);

	boolean updatePassword(String email, String password);

}