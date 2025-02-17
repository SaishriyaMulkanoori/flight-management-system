package cloud.wing.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.admin.entity.BussinessOwner;
import cloud.wing.admin.entity.FlightManager;
import cloud.wing.admin.repository.BussinessOwnerDao;
import cloud.wing.utility.Utils;

@Service
public class BussinessOwnerServiceImpl implements BussinessOwnerService {

	@Autowired
	BussinessOwnerDao bussinessOwnerDao;

	@Override
	public Optional<BussinessOwner> isUserExists(String email, String password) {

		Optional<BussinessOwner> bussinessOwner = bussinessOwnerDao.findByEmail(email);

		if (bussinessOwner.isPresent()) {
			BussinessOwner bOwner = bussinessOwner.get();

			String salt = bOwner.getPassword_salt();
			String pwdSalt = password + salt;
			String modifiedSlat = Utils.generateHash(pwdSalt);

			if (bOwner.getPassword_hash().equals(modifiedSlat)) {
				return Optional.of(bOwner);
			}

		}

		return Optional.empty();
	}

	@Override
	public List<FlightManager> getAllFlightManager() {

		return bussinessOwnerDao.getAllFlightManager();
	}

	@Override
	public int getFlightCount() {
		return bussinessOwnerDao.getFlightCount();
	}

	@Override
	public int getManagerCount() {
		return bussinessOwnerDao.getManagerCount();
	}

	@Override
	public int getPassengerCount() {
		return bussinessOwnerDao.getPassengerCount();
	}

}
