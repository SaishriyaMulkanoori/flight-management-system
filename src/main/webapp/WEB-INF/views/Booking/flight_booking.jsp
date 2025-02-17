<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Flight Booking</title>


<style>
/* General Styles */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f3f4f7;
	color: #333;
}

/* Header Styles */
.header {
	background-color: #01a9db;
	color: white;
	text-align: center;
	padding: 10px;
}

/* Booking Form Section */
.booking-form {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	max-width: 600px;
	margin: 20px auto;
	padding: 20px;
}

.booking-form h2 {
	text-align: center;
	color: #01a9db;
}

.booking-form label {
	font-weight: bold;
	display: block;
	margin: 10px 0 5px;
}

.booking-form input, .booking-form select, .booking-form button {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.booking-form button {
	background-color: #01a9db;
	color: white;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
}

.booking-form button:hover {
	background-color: #0056b3;
}

/* Confirmation Section */
.confirmation {
	max-width: 600px;
	margin: 20px auto;
	text-align: center;
	padding: 10px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.confirmation h2 {
	color: #01a9db;
}

/* Footer */
footer {
	text-align: center;
	padding: 10px;
	background-color: #f1f1f1;
	position: fixed;
	bottom: 0;
	width: 100%;
}
</style>

</head>
<body>

	<header class="header">
		<h1>CloudWing</h1>
		<p>Find the best flights for your next journey!</p>
	</header>

	<main>
	<p> <a href="/flightBooking">Book Flight</a>
	</p>
		<section class="booking-form">

			<h2>Book Your Flight</h2>
			<form id="booking/book" method="post">
				<label for="passengerName">Passenger Name:</label> <input
					type="text" id="passengerName" name="passengerName"
					placeholder="Enter your name" required> <label
					for="fromPlace">From:</label> <input type="text" id="fromPlace"
					name="fromPlace" placeholder="Enter City/Airport Name" required>

				<label for="toPlace">To:</label> <input type="text" id="toPlace"
					name="toPlace" placeholder="Enter City/Airport Name" required>

				<label for="date">Travel Date:</label> <input type="date" id="date"
					name="date" required> <label for="seatType">Seat
					Type:</label> <select id="seatType" name="seatType">
					<option value="select">Select</option>
					<option value="economy">Economy</option>
					<option value="business">Business</option>
					<option value="firstClass">First Class</option>
				</select> <label for="numSeats">Number of Seats:</label> <input type="number"
					id="numSeats" name="numSeats" min="1"
					placeholder="Enter number of seats" required>

				<button type="submit">Book Now</button>
			</form>
		</section>
		<section class="confirmation">
			<h2>Booking Confirmation</h2>
			<p id="confirmationMessage">Your booking details will appear here
				after submission.</p>
		</section>
		</form>
	</main>

	<footer>
		<p>© 2024 CloudWing. All rights reserved.</p>
	</footer>
</body>
</html>