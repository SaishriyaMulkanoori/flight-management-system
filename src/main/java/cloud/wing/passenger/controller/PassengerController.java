package cloud.wing.passenger.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cloud.wing.booking.entity.Booking;
import cloud.wing.passenger.entity.Passenger;
import cloud.wing.passenger.service.PassengerService;
import cloud.wing.payment.service.EmailService;
import cloud.wing.utility.Utils;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

	@Autowired
	PassengerService passengerService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/signup")
	public String openHomePage() {

		return "passenger/signup";
	}

	@PostMapping("/registration")
	public String customerRegistration(@RequestParam String name, @RequestParam String email,
			@RequestParam String username, @RequestParam String gender, @RequestParam String date_of_birth,
			@RequestParam String mobile_number, @RequestParam String password, @RequestParam String cpassword,
			Model model

	) {

		if (password.equals(cpassword)) {

			Passenger passenger = new Passenger(name, email, username, gender, date_of_birth, mobile_number, password);

			String message = passengerService.registerPassenger(passenger);
			model.addAttribute("message", message);

		} else {
			model.addAttribute("error", "Password and confirm not matched");
		}

		String destination = "passenger/signup";

		return destination;

	}

	@GetMapping("/openLoginPage")
	public ModelAndView loginPage() {

		ModelAndView modelAndView = new ModelAndView("/passenger/login");
		return modelAndView;
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String email, @RequestParam String password, ModelAndView mView,
			HttpSession session) {

		Optional<Passenger> passenger = passengerService.isUserExists(email, password);

		if (passenger.isPresent()) {
			session.setAttribute("loggedInUser", passenger.get());

			return new ModelAndView("redirect:/");

		} else {
			mView.setViewName("passenger/login");
			mView.addObject("error", "Invalid credentials");

		}

		return mView;

	}

	@GetMapping("/profile")
	public String openProfilePage(HttpSession session, Model model) {

		Passenger passenger = (Passenger) session.getAttribute("loggedInUser");
		if (passenger == null) {

			return "redirect:/passenger/openLoginPage";
		}

		Optional<Passenger> passengerdata = passengerService.findUserByEmail(passenger.getEmail());
		Passenger passengerLogin = passengerdata.get();
		model.addAttribute("passengerLogin", passengerLogin);

		return "passenger/profile";
	}

	@PostMapping("/update")
	public String opensavePage(@RequestParam String name, @RequestParam String username, @RequestParam String gender,
			@RequestParam String date_of_birth, @RequestParam String mobile_number,
			@RequestParam String passport_number, Model model, HttpSession session) {
		;
		Passenger passengerLogIn = (Passenger) session.getAttribute("loggedInUser");

		if (passengerLogIn == null) {
			return "redirect:/passenger/openLoginPage";
		}

		int id = passengerLogIn.PassengerId();
		Passenger passenger = null;

		if (passport_number != null) {
			passenger = new Passenger(id, name, username, gender, date_of_birth, mobile_number, passport_number);
		} else {
			passenger = new Passenger(id, name, username, gender, date_of_birth, mobile_number);
		}

		@SuppressWarnings("unused")
		String message = passengerService.updatePassenger(passenger);

		return "redirect:/passenger/profile";
	}

	@PostMapping("/book")
	public ResponseEntity<String> submitBooking(@ModelAttribute Booking booking, Model model, HttpSession session) {
		Passenger passenger = (Passenger) session.getAttribute("loggedInUser");

		booking.setPassengerId(passenger.PassengerId());
		String amount = booking.getTotalAmount().toString();
		booking.setTotalAmount(new BigDecimal(amount));

		// Assuming passengerService.createBooking handles saving the list of passenger
		// names
		Booking savedBooking = passengerService.createBooking(booking);

		String redirectUrl = "/payment?bookingRef=" + savedBooking.getBookingRef();
		return ResponseEntity.ok(redirectUrl);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelAndView mView) {
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "passenger/forgotPassword";
	}

	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam String email, Model model, HttpSession session) {

		Optional<Passenger> user = passengerService.findUserByEmail(email);
		if (user.isPresent()) {
			String otp = Utils.generateOTP();
			session.setAttribute("email", email);
			session.setAttribute("otp", otp);
			try {

				emailService.sendForgotPasswordOtp(email, otp);

			} catch (Exception e) {

				model.addAttribute("error", "Failed to send Otp " + e.getMessage());
			}
			return "passenger/verifyOtp";
		} else {

			model.addAttribute("error", "user not found! ");
			return "passenger/forgotPassword";
		}

	}

	@PostMapping("/verify_otp")
	public String Verifyotp(@RequestParam String otp, HttpSession session, Model model) {

		String sendOtp = (String) session.getAttribute("otp");

		if (otp.equals(sendOtp)) {
			return "passenger/changePassword";
		} else {
			model.addAttribute("error", "invalid otp! Please enter a valid otp");
			return "passenger/verifyOtp";
		}
	}

	@PostMapping("/update-password")
	public String updatePassword(@RequestParam String password, @RequestParam String cpassword, HttpSession session,
			Model model) {
		String email = (String) session.getAttribute("email");
		if (!password.equals(cpassword)) {
			model.addAttribute("error", "Password and confirm password not match");
			return "passenger/changePassword";
		}
		System.out.println(email);
		boolean status = passengerService.updatePassword(email, password);

		if (status) {
			model.addAttribute("message", "Password changed successfully");
		} else {
			model.addAttribute("error", "Failed to change password");
		}

		return "passenger/login";
	}

}
