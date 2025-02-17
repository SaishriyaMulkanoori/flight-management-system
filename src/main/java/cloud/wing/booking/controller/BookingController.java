package cloud.wing.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cloud.wing.booking.entity.Booking;
import cloud.wing.booking.entity.BookingDetails;
import cloud.wing.booking.service.BookingService;
import cloud.wing.flight.entity.Flight;
import cloud.wing.flight.service.FlightService;
import cloud.wing.passenger.entity.Passenger;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@Autowired
	private FlightService flightService;

	@GetMapping("/{flightId}")
	public String getFlightById(@PathVariable int flightId, Model model, HttpSession session) {

		Flight flight = flightService.getFlightById(flightId);

		Passenger passenger = (Passenger) session.getAttribute("loggedInUser");

		if (passenger != null) {

			model.addAttribute("flight", flight);
			System.out.println(flight);
			return "Booking/booking_form";

		}
		return "redirect:/passenger/openLoginPage";

	}

	@GetMapping("/allbooking")
	public String getAllFlight(HttpSession session) {
		Passenger user = (Passenger) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/passenger/openLoginPage";
		}

		@SuppressWarnings("unused")
		List<Booking> bookings = bookingService.getAllBooking();

		return "booking/booking_form";

	}

	@GetMapping("/allBookings/{passengerId}")
	public String getBookingDetails(@PathVariable int passengerId, Model model) {
		List<BookingDetails> details = bookingService.fetchBookingDetails(passengerId);

		model.addAttribute("bookings", details);
		model.addAttribute("user", passengerId);
		return "passenger/allBooking";
	}

	@GetMapping("/upcomingFlight/{passengerId}")
	public String getUpcomingFlight(@PathVariable int passengerId, Model model, HttpSession session) {

		Passenger user = (Passenger) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/passenger/openLoginPage";
		}

		List<BookingDetails> details = bookingService.fetchBookingDetails(passengerId);

		model.addAttribute("bookings", details);
		model.addAttribute("user", passengerId);
		return "passenger/upcomingBooking";
	}

	@PutMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<String> updateBookingStatus(@PathVariable int bookingId) {
		System.out.println("aa gaya controller me");
		boolean isUpdated = bookingService.cancelTicket(bookingId);
		return isUpdated ? ResponseEntity.ok("success") : ResponseEntity.status(400).body("failure");
	}

	@GetMapping("/ticket/{bookingId}")
	public String downloadTicket(@PathVariable int bookingId, Model model) {

		BookingDetails booking = bookingService.fetchBookingById(bookingId);
		if (booking != null) {
			model.addAttribute("ticket", booking);
			return "passenger/ticket";
		}
		return "/";
	}

	@GetMapping("/bookingReport")
	public String bookingReport(@RequestParam(required = false) String flightNumber, Model model) {
		List<BookingDetails> bookingDetails = null;
		List<Flight> flight = flightService.getAllFlights();
		if (!flight.isEmpty()) {
			model.addAttribute("flight", flight);
		}
		if (flightNumber != null) {
			bookingDetails = bookingService.fetchBookingByFlightNumber(flightNumber);

		} else {
			bookingDetails = bookingService.fetchAllBookingDetails();
		}

		model.addAttribute("bookingDetails", bookingDetails);

		return "flightManager/bookingReport";

	}

}