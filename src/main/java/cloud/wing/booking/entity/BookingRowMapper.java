package cloud.wing.booking.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cloud.wing.passenger.entity.Passenger;

public  class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setPassengerId(rs.getInt("passenger_id"));
        booking.setFlightId(rs.getInt("flight_id"));
        booking.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());
        booking.setNumberOfSeats(rs.getInt("number_of_seats"));
        booking.setSeatClass(rs.getString("seat_class"));
        booking.setTotalAmount(rs.getBigDecimal("total_amount"));
        booking.setStatus(rs.getString("status"));
        booking.setBookingRef(rs.getString("booking_ref"));
        
        Passenger passenger=new Passenger();
//        passenger.setEmail(rs.getString("email"));
        passenger.setPassengerId(rs.getInt("passenger_id"));
        passenger.setName(rs.getString("name"));
        passenger.setEmail(rs.getString("email"));
        
        booking.setPassenger(passenger);
        
        return booking;
    }
}