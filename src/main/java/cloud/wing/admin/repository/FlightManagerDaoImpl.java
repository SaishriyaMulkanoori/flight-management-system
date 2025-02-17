package cloud.wing.admin.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cloud.wing.admin.entity.FlightManager;

@Repository
public class FlightManagerDaoImpl implements FlightManagerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int createFlightManager(FlightManager flightManager) {

		final String INSERT_QUERY = "INSERT INTO flight_manager (name,mobile,email,password_salt,password_hash,gender,username) VALUES (?,?,?,?,?,?,?)";

		return jdbcTemplate.update(INSERT_QUERY, flightManager.getName(), flightManager.getMobile(),
				flightManager.getEmail(), flightManager.getPassword_salt(), flightManager.getPassword_hash(),
				flightManager.getGender(), flightManager.getUsername());

	}

	@Override
	public FlightManager findFlightManagerById(int flightManagerId) {
		String sql = "SELECT * FROM flight_manager WHERE flight_manager_id = ?";
		try {

			return jdbcTemplate.queryForObject(sql, new FlightManagerRowMapper(), flightManagerId);

		} catch (EmptyResultDataAccessException e) {

			System.out.println("No FlightManager found with ID: " + flightManagerId);

			return null; // or throw new CustomException("Flight Manager not found");

		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<FlightManager> findByEmail(String email) {

		final String GET_ALL_USER = "SELECT * FROM flight_manager WHERE email = ?";
		return jdbcTemplate.query(GET_ALL_USER, new Object[] { email }, rs -> {

			if (rs.next()) {
				FlightManager flightManager = new FlightManager(rs.getInt("flight_manager_id"),
						rs.getInt("business_owner_id"), rs.getString("name"), rs.getString("mobile"),
						rs.getString("email"), rs.getString("gender"), rs.getString("username"),
						rs.getString("password_salt"), rs.getString("password_hash"), rs.getBoolean("is_approved"));
				return Optional.of(flightManager);
			}

			return Optional.empty();
		});

	}

	@Override
	public void changeAuthority(int adminId) {

		final String CHANGE_AUTH = "update flight_manager set is_approved = !is_approved where flight_manager_id=?";
		jdbcTemplate.update(CHANGE_AUTH, adminId);

	}

	@Override
	public boolean getAuthority(int adminId) {
		final String GET_AUTH = "SELECT is_approved FROM flight_manager WHERE id = ?";
		return jdbcTemplate.queryForObject(GET_AUTH, Boolean.class, adminId);
	}

	@Override
	public double totalRevenue() {
		final String sql = "SELECT SUM(total_amount) FROM booking WHERE status = 'Confirmed'";
		return jdbcTemplate.queryForObject(sql, Double.class);
	}

	@Override
	public int activeFlightCount() {
		final String query = "SELECT COUNT(*) FROM flight WHERE DATE(departure_date) = CURRENT_DATE AND is_deleted='false'";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public int getPassengerCount() {
		String sql = "SELECT COUNT(*) FROM passenger";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int getFlightCount() {
		String sql = "SELECT COUNT(*) FROM flight";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int updateFlightManager(FlightManager manager) {

		final String UPDATE_QUERY = "UPDATE  flight_manager  SET name=?,username=?,gender=?,mobile=? WHERE flight_manager_id=?";
		return jdbcTemplate.update(UPDATE_QUERY, manager.getName(), manager.getUsername(), manager.getGender(),
				manager.getMobile(), manager.getId());

	}

	@Override
	public boolean updatepassword(String email, String salt, String hash) {
		String sql = "UPDATE flight_manager SET password_salt = ? , password_hash=? WHERE email = ?";
		int rowsAffected = jdbcTemplate.update(sql, salt, hash, email);
		if (rowsAffected > 0) {
			return true;
		}
		return false;
	}
}
