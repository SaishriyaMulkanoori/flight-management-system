package cloud.wing.loyaltypoints.entity;

public class LoyaltyPoints {

	  private int loyaltyId;

	  private int passengerId;

	  private int pointsEarned;

	  private int pointsRedeemed;

	  private int totalPoints;



	  // Getters and Setters

	  public int getLoyaltyId() {

	    return loyaltyId;

	  }



	  public void setLoyaltyId(int loyaltyId) {

	    this.loyaltyId = loyaltyId;

	  }



	  public int getPassengerId() {

	    return passengerId;

	  }



	  public void setPassengerId(int passengerId) {

	    this.passengerId = passengerId;

	  }



	  public int getPointsEarned() {

	    return pointsEarned;

	  }



	  public void setPointsEarned(int pointsEarned) {

	    this.pointsEarned = pointsEarned;

	  }



	  public int getPointsRedeemed() {

	    return pointsRedeemed;

	  }



	  public void setPointsRedeemed(int pointsRedeemed) {

	    this.pointsRedeemed = pointsRedeemed;

	  }



	  public int getTotalPoints() {

	    return totalPoints;

	  }



	  public void setTotalPoints(int totalPoints) {

	    this.totalPoints = totalPoints;

	  }



	  @Override

	  public String toString() {

	    return "LoyaltyPoints{" +

	        "loyaltyId=" + loyaltyId +

	        ", passengerId=" + passengerId +

	        ", pointsEarned=" + pointsEarned +

	        ", pointsRedeemed=" + pointsRedeemed +

	        ", totalPoints=" + totalPoints +

	        '}';

	  }

	}
