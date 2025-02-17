package cloud.wing.loyaltypoints.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.loyaltypoints.repository.LoyaltyPointsDAO;

@Service

public class LoyaltyServiceImpl implements LoyaltyService {

	@Autowired

	private LoyaltyPointsDAO loyaltyPointsDAO;

	@Override

	public int getTotalPoints(int passengerId) {

		return loyaltyPointsDAO.getTotalPointsByPassengerId(passengerId);

	}

	@Override

	public void updateLoyaltyPoints(int passengerId, int pointsEarned, int pointsRedeemed) {

		loyaltyPointsDAO.updateLoyaltyPoints(passengerId, pointsEarned, pointsRedeemed);

	}

	@Override

	public void initializeLoyaltyPoints(int passengerId) {

		loyaltyPointsDAO.initializeLoyaltyPointsForPassenger(passengerId);

	}

}
