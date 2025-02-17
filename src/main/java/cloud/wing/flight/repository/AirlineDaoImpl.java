package cloud.wing.flight.repository;

import cloud.wing.flight.entity.Airline;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.List;

@Repository

public class AirlineDaoImpl implements AirlineDao {

	private final JdbcTemplate jdbcTemplate;

	public AirlineDaoImpl(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;

	}

	@Override

	public void saveAirline(Airline airline) {

		String sql = "INSERT INTO airline (name, iata_code, country, contact_email) VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, airline.getName(), airline.getIataCode(), airline.getCountry(),
				airline.getContactEmail());

	}

	@Override

	public Airline findAirlineById(int airlineId) {

		String sql = "SELECT * FROM airline WHERE airline_id = ?";

		return jdbcTemplate.queryForObject(sql, new AirlineRowMapper(), airlineId);

	}

	@Override

	public List<Airline> findAllAirlines() {

		String sql = "SELECT * FROM airline WHERE is_deleted=0";

		return jdbcTemplate.query(sql, new AirlineRowMapper());

	}

	// find all flights should implement later

	@Override

	public void safeDeleteAirlineAndFlights(int airlineId) {

		String sql = "UPDATE airline SET is_deleted=1 WHERE airline_id=?";

		jdbcTemplate.update(sql, airlineId);

		sql = "UPDATE flight SET is_deleted=1 WHERE airline_id=?";

		jdbcTemplate.update(sql, airlineId);

	}

	static class AirlineRowMapper implements RowMapper<Airline> {

		@Override

		public Airline mapRow(ResultSet rs, int rowNum) throws SQLException {

			Airline airline = new Airline();

			airline.setAirlineId(rs.getInt("airline_id"));

			airline.setName(rs.getString("name"));

			airline.setIataCode(rs.getString("iata_code"));

			airline.setCountry(rs.getString("country"));

			airline.setContactEmail(rs.getString("contact_email"));
			// System.out.println(airline);
			return airline;

		}

	}

	@Override
	public void updateAirline(Airline airline) {
		String sql = "UPDATE airline SET name=?,iata_code=?,country=?,contact_email=? WHERE airline_id=?";
		jdbcTemplate.update(sql, airline.getName(), airline.getIataCode(), airline.getCountry(),
				airline.getContactEmail(), airline.getAirlineId());

	}
	
	@Override
	public boolean existsByAirlineName(String airlineName) {
        String sql="select count(*) from airline where name=?";
        Integer count=jdbcTemplate.queryForObject(sql,Integer.class,airlineName);
        return count!=null && count>0;
	}

}
