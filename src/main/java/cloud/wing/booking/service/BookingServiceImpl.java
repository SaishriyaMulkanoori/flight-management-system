package cloud.wing.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.entity.BookingDetails;
import cloud.wing.booking.repository.BookingDao;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	public BookingDao bookingDao;

	@Override
	public List<Booking> getAllBooking() {
		return bookingDao.findAll();
	}

//	@Override
//	public Optional<Flight> fetchFlightById(int id) {
//		return bookingDao.getFlightById(id);
//	}

	@Override
	public List<BookingDetails> fetchBookingDetails(int passenger_id) {
		return bookingDao.findBookingDetailsByPassengerId(passenger_id);
	}

	@Override
	public boolean cancelTicket(int booking_id) {
		return bookingDao.cancelBooking(booking_id);
	}

	@Override
	public BookingDetails fetchBookingById(int bookingId) {
		return bookingDao.findBookingByID(bookingId);
	}

	@Override
	public List<BookingDetails> fetchBookingByFlightNumber(String flightNumber) {
		return bookingDao.findBookingDetailsByFlightNumber(flightNumber);
	}

	@Override
	public List<BookingDetails> fetchAllBookingDetails() {
		return bookingDao.getAllBookingDetail();
	}

}
