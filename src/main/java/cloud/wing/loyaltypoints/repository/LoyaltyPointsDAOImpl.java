package cloud.wing.loyaltypoints.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository

public class LoyaltyPointsDAOImpl implements LoyaltyPointsDAO {



  @Autowired

  private JdbcTemplate jdbcTemplate;



  @SuppressWarnings("deprecation")
  @Override

  public int getTotalPointsByPassengerId(int passengerId) {

    String sql = "SELECT total_points FROM loyalty_points WHERE passenger_id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[]{passengerId}, Integer.class);

  }



  @Override

  public void updateLoyaltyPoints(int passengerId, int pointsEarned, int pointsRedeemed) {

	  System.out.println(pointsRedeemed);
	  String addEarnedPointsQuery = "UPDATE loyalty_points " +

                 "SET points_earned = points_earned + ?, " +

                 " total_points = total_points + ? " +

                 "WHERE passenger_id = ?";

  jdbcTemplate.update(addEarnedPointsQuery, pointsEarned, pointsEarned, passengerId);



  // Deduct redeemed points if applicable

  if (pointsRedeemed > 0) {

    String redeemPointsQuery = "UPDATE loyalty_points " +

                  "SET points_redeemed = points_redeemed + ?, " +

                  " total_points = total_points - ? " +

                  "WHERE passenger_id = ?";

    jdbcTemplate.update(redeemPointsQuery, pointsRedeemed, pointsRedeemed, passengerId);

  }
  }



  @Override

  public void initializeLoyaltyPointsForPassenger(int passengerId) {

	  System.out.println(passengerId);
	    String sql = """

	      INSERT INTO loyalty_points (passenger_id, points_earned, points_redeemed, total_points)

	      VALUES (?, 50, 0, 50) 

	    """;

	    jdbcTemplate.update(sql, passengerId);

  }

}
