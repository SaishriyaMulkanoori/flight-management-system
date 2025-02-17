package cloud.wing.admin.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cloud.wing.admin.entity.FlightManager;

public class FlightManagerRowMapper implements RowMapper<FlightManager> {

	@Override
	public FlightManager mapRow(ResultSet rs, int rowNum) throws SQLException {

		int id = rs.getInt("flight_manager_id");
		int bussiness_owner_id = rs.getInt("business_owner_id");
		String name = rs.getString("name");
		String mobile = rs.getString("mobile");
		String email = rs.getString("email");
		String gender = rs.getString("gender");
		String username = rs.getString("username");
		String pwdSalt = rs.getString("password_salt");
		String pwdHash = rs.getString("password_hash");
		boolean isApproved = rs.getBoolean("is_approved");

		return new FlightManager(id, bussiness_owner_id, name, mobile, email, gender, username, pwdSalt, pwdHash,
				isApproved);

	}

}
