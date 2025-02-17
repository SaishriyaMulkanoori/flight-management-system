package cloud.wing.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.admin.entity.FlightManager;
import cloud.wing.admin.repository.FlightManagerDao;
import cloud.wing.utility.Utils;

@Service
public class FlightManagerServiceImpl implements FlightManagerService {

	@Autowired
	FlightManagerDao flightmanagerDao;

	@Override
	public String registerFlightManager(FlightManager flightManager) {

		Optional<FlightManager> existingFm = flightmanagerDao.findByEmail(flightManager.getEmail());

		String pwdSalt = Utils.generateSalt();

		String modifiedPassword = flightManager.getPassword() + pwdSalt;

		String pwdHash = Utils.generateHash(modifiedPassword);

		flightManager.setPassword_hash(pwdHash);
		flightManager.setPassword_salt(pwdSalt);

		if (existingFm.isPresent()) {
			return "User Already exists";
		}

		flightmanagerDao.createFlightManager(flightManager);

		return "User Registered Successfully";
	}

	@Override
	public Optional<FlightManager> isUserExists(String email, String password) {

		Optional<FlightManager> existingFm = flightmanagerDao.findByEmail(email);

		if (existingFm.isPresent()) {

			FlightManager flightManager = existingFm.get();
			String pwdSalt = flightManager.getPassword_salt();

			String modifiedPassword = password + pwdSalt;

			String pwdHash = flightManager.getPassword_hash();

			String newPwdHash = Utils.generateHash(modifiedPassword);
			if (newPwdHash.equals(pwdHash)) {

				return Optional.of(flightManager);
			}

		}

		return Optional.empty();
	}

	@Override
	public void modifyAuthority(int adminId) {
		flightmanagerDao.changeAuthority(adminId);

	}

	@Override
	public boolean getAuthority(int adminId) {
		return flightmanagerDao.getAuthority(adminId);
	}

	@Override
	public double totalRevenue() {
		return flightmanagerDao.totalRevenue();
	}

	@Override
	public int activeFlightCount() {
		return flightmanagerDao.activeFlightCount();
	}

	@Override
	public int getFlightCount() {
		return flightmanagerDao.getFlightCount();
	}

	@Override
	public int getPassengerCount() {
		return flightmanagerDao.getPassengerCount();
	}

	@Override
	public String updateFlightManager(FlightManager manager) {
		flightmanagerDao.updateFlightManager(manager);
		return "Data Updated";
	}

	@Override
	public FlightManager getFlightManagerById(int flightManagerId) {

		return flightmanagerDao.findFlightManagerById(flightManagerId);
	}

	@Override
	public Optional<FlightManager> findManagerByEmail(String email) {
		Optional<FlightManager> manager = flightmanagerDao.findByEmail(email);

		if (manager.isPresent()) {

			return manager;
		}
		return Optional.empty();
	}

	@Override
	public boolean updatePassword(String email, String password) {

		String pwdSalt = Utils.generateSalt();

		String modifiedPassword = password + pwdSalt;

		String pwdHash = Utils.generateHash(modifiedPassword);

		return flightmanagerDao.updatepassword(email, pwdSalt, pwdHash);
	}

}
