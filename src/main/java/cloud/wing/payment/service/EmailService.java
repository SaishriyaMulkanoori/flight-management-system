package cloud.wing.payment.service;

import cloud.wing.booking.entity.Booking;
import cloud.wing.loyaltypoints.service.LoyaltyService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@SuppressWarnings("unused")
	private LoyaltyService loyaltyService;

	@Autowired

	private JavaMailSender mailSender;

	public void sendEmailWithLoyaltyDetails(String recipientEmail, Booking booking, int pointsRedeemed,
			int totalPoints) {

		String subject = "Flight Ticket Confirmation with CloudWing";

		String passengerName = booking.getPassenger().getName();

		// String bookingRef= booking.getBookingRef()+"\n";

		String departureDate = "Departure Date: " + booking.getBookingDate() + "\n";

		String seatClass = "Seat Class: " + booking.getSeatClass() + "\n";

		String numberOfSeats = "Number of Seats: " + booking.getNumberOfSeats() + "\n";

		String totalAmount = "Total Amount: " + booking.getTotalAmount().toString() + "\n";

		String text = "Dear " + passengerName + ",\n\n" +

				"Thank you for your payment! Your booking is confirmed. Here are your travel details:\n\n" +

				departureDate +

				seatClass +

				numberOfSeats +

				totalAmount +

				"\n\n" +

				"You can download your ticket by visiting the CloudWing official website, " +

				"you have earned additional \"50\" points for your next travel " +

				"We wish you a pleasant journey!\n\n" +

				"Best regards,\nFlight Management Team\n\"Fly Smater, Not Harder with CloudWing\"";

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(recipientEmail);

		message.setSubject(subject);

		message.setText(text);

		mailSender.send(message);

	}

	public void sendForgotPasswordOtp(String email, String otp) {
		String text = "Your  one-time-password  is " + otp;
		String subject = "Forgot Password OTP for CloudWing";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

	}

}