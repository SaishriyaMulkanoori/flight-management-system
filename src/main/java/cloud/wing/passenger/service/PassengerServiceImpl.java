package cloud.wing.passenger.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cloud.wing.booking.entity.Booking;
import cloud.wing.loyaltypoints.repository.LoyaltyPointsDAO;
import cloud.wing.passenger.entity.Passenger;
import cloud.wing.passenger.repositry.PassengerDao;
import cloud.wing.utility.Utils;
import jakarta.transaction.Transactional;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	PassengerDao passengerDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	LoyaltyPointsDAO loyaltyPointsDao;

	@Override
	public String registerPassenger(Passenger passenger) {

		Optional<Passenger> existingPaseenger = passengerDao.findByEmail(passenger.getEmail());

		String pwdSalt = Utils.generateSalt();

		String modifiedPassword = passenger.getPassword() + pwdSalt;

		String pwdHash = Utils.generateHash(modifiedPassword);

		passenger.setPassword_hash(pwdHash);
		passenger.setPassport_salt(pwdSalt);

		if (existingPaseenger.isPresent()) {
			return "User Already exists";
		}

		passengerDao.createPassenger(passenger);

		int passengerId = jdbcTemplate.queryForObject("Select LAST_INSERT_ID()", Integer.class);
		System.out.println("\nprint: " + passengerId);

		loyaltyPointsDao.initializeLoyaltyPointsForPassenger(passengerId);

		return "User Registered Successfully";
	}

	@Override
	public Optional<Passenger> isUserExists(String email, String password) {

		Optional<Passenger> existingP = passengerDao.findByEmail(email);

		if (existingP.isPresent()) {

			Passenger passenger = existingP.get();
			String pwdSalt = passenger.getPassport_salt();

			String modifiedPassword = password + pwdSalt;

			String pwdHash = passenger.getPassword_hash();

			String newPwdHash = Utils.generateHash(modifiedPassword);
			if (newPwdHash.equals(pwdHash)) {

				return Optional.of(passenger);
			}

		}

		return Optional.empty();
	}

	@Override
	public String updatePassenger(Passenger passenger) {
		passengerDao.updatePassenger(passenger);
		return "Data Updated";
	}

	@Transactional
	public Booking createBooking(Booking booking) {

		booking.setBookingRef(Utils.generateBookingReference());
		booking.setBookingDate(LocalDateTime.now());
		booking.setStatus("pending");
		passengerDao.save(booking);
		passengerDao.savePassengers(booking);
		return booking;
	}

	@Override
	public Optional<Passenger> findUserByEmail(String email) {
		Optional<Passenger> existingPassenger = passengerDao.findByEmail(email);

		if (existingPassenger.isPresent()) {

			return existingPassenger;
		}
		return Optional.empty();
	}

	@Override
	public boolean updatePassword(String email, String password) {

		String pwdSalt = Utils.generateSalt();

		String modifiedPassword = password + pwdSalt;

		String pwdHash = Utils.generateHash(modifiedPassword);

		return passengerDao.updatepassword(email, pwdSalt, pwdHash);
	}

}
