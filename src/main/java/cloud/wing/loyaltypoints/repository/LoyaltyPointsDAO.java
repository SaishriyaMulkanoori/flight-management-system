package cloud.wing.loyaltypoints.repository;

public interface LoyaltyPointsDAO {

	int getTotalPointsByPassengerId(int passengerId);

	void updateLoyaltyPoints(int passengerId, int pointsEarned, int pointsRedeemed);

	void initializeLoyaltyPointsForPassenger(int passengerId);

}