
package cloud.wing.payment.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cloud.wing.booking.entity.Booking;
import cloud.wing.loyaltypoints.service.LoyaltyService;
import cloud.wing.passenger.entity.Passenger;
import cloud.wing.payment.entity.Payment;
import cloud.wing.payment.service.EmailService;
import cloud.wing.payment.service.PaymentService;
import jakarta.servlet.http.HttpSession;

@Controller

public class PaymentController {

	@Autowired

	private PaymentService paymentService;

	@Autowired

	private EmailService emailService;

	@Autowired

	private LoyaltyService loyaltyService;

	@GetMapping("/payment")

	public String openPaymentPage(@RequestParam("bookingRef") String bookingRef, Model model, HttpSession session) {

		Passenger user = (Passenger) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/passenger/openLoginPage";
		}

		Booking booking = paymentService.fetchbookingByRef(bookingRef);

		if (booking != null) {

			int passengerId = booking.getPassenger().getPassengerId();

			int totalPoints = loyaltyService.getTotalPoints(passengerId);

			model.addAttribute("booking", booking);

			model.addAttribute("loyaltyPoints", totalPoints);

			return "passenger/payment_form";

		}

		return "/";

	}

	@PostMapping("/{bookingRef}")

	public String paymentProcess(

			@PathVariable String bookingRef,

			@RequestParam String cardHoldername,

			@RequestParam String cardNumber,

			@RequestParam String expiryDate,

			@RequestParam BigDecimal totalAmount,

			@RequestParam(required = false, defaultValue = "0") int pointsToRedeem,

			Model model, HttpSession session) {

		Passenger user = (Passenger) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/passenger/openLoginPage";
		}

		Booking booking = paymentService.fetchbookingByRef(bookingRef);

		if (booking == null) {

			model.addAttribute("error", "Booking not found. Please try again.");

			return "/passenger/paymentStatus";

		}

		int passengerId = booking.getPassenger().getPassengerId();

		int totalPoints = loyaltyService.getTotalPoints(passengerId);

		if (pointsToRedeem > totalPoints) {

			model.addAttribute("error", "Invalid points to redeem. Available points: " + totalPoints);

			return "/passenger/payment_form";

		}

		BigDecimal discount = BigDecimal.valueOf(pointsToRedeem * 1); // Example: 1 point = 0.1 currency
		BigDecimal finalAmount = totalAmount;

		Payment payment = new Payment();

		payment.setCardHoldername(cardHoldername);

		payment.setCardNumber(cardNumber);

		payment.setExpiryDate(expiryDate);

		// changes
		LocalDateTime dateTime = LocalDateTime.now();
		payment.setTotalAmount(finalAmount);
		payment.setPaymentMethod("CreditCard");
		payment.setPaymentStatus("Successful");
		payment.setBookingId(booking.getBookingId());
		payment.setPaymentDate(dateTime);
		payment.setDiscountAmonut(discount);

		boolean isUpdated = paymentService.processPaymentWithLoyaltyPoints(booking, payment, pointsToRedeem); // pointsToRedeem
		if (isUpdated) {

			model.addAttribute("message", "Payment successful and booking confirmed.");

			String passengerEmail = booking.getPassenger().getEmail();

			try {

				emailService.sendEmailWithLoyaltyDetails(passengerEmail, booking, pointsToRedeem, totalPoints);

			} catch (Exception e) {

				model.addAttribute("error", "Payment successful, but failed to send email: " + e.getMessage());
			}

		} else {

			model.addAttribute("error", "Booking failed. Please try again.");
		}

		return "/passenger/paymentStatus";

	}

}
