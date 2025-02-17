package cloud.wing.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cloud.wing.admin.entity.BussinessOwner;
import cloud.wing.admin.entity.FlightManager;

@Repository
public class BussinessOwnerDaoImpl implements BussinessOwnerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public Optional<BussinessOwner> findByEmail(String email) {

		final String FIND_BY_EMAIL = "SELECT * FROM business_owner WHERE email=?";

		return jdbcTemplate.query(FIND_BY_EMAIL, new Object[] { email }, rs -> {

			if (rs.next()) {
				BussinessOwner bussinessOwner = new BussinessOwner(rs.getInt("business_owner_id"), rs.getString("name"),
						rs.getString("email"), rs.getString("username"), rs.getString("gender"), rs.getString("mobile"),
						rs.getString("password_salt"), rs.getString("password_hash"));

				return Optional.of(bussinessOwner);
			}
			return Optional.empty();
		});

	}

	@Override
	public List<FlightManager> getAllFlightManager() {
		final String GET_ALL_USER = "SELECT * FROM  flight_manager";
		return jdbcTemplate.query(GET_ALL_USER, new FlightManagerRowMapper());

	}

	public int getFlightCount() {
		String sql = "SELECT COUNT(*) FROM flight";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getManagerCount() {
		String sql = "SELECT COUNT(*) FROM flight_manager";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getPassengerCount() {
		String sql = "SELECT COUNT(*) FROM passenger";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
