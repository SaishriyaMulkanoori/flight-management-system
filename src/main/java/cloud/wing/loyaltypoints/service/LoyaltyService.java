package cloud.wing.loyaltypoints.service;


public interface LoyaltyService {

	  int getTotalPoints(int passengerId);

	  void updateLoyaltyPoints(int passengerId, int pointsEarned, int pointsRedeemed);

	  void initializeLoyaltyPoints(int passengerId);

	}