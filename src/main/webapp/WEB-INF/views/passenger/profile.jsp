<%@page import="java.time.LocalDate"%>
<%@page import="cloud.wing.passenger.entity.Passenger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Profile</title>
<!-- FontAwesome for icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- Animate.css for animations -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
<!-- External Stylesheets -->
<link href="../css/passenger/profile.css" rel="stylesheet"
	type="text/css">
</head>

<script type="text/javascript">
	function confirmSubmission(event) {
		event.preventDefault(); // Prevent form submission
		var passportNumber = document.getElementById("passportNumber").value;
		var passportPattern = /^[A-Z][0-9]{7}$/;
		var mobileNumber = document.getElementById("mobileNumber").value;
		var mobilePattern = /^[6-9][0-9]{9}$/;
		var dob = document.getElementById("dob").value;
		var today = new Date();
		var minDob = new Date(today.getFullYear() - 12, today.getMonth(), today
				.getDate());

		if (passportNumber && !passportPattern.test(passportNumber)) {
			alert("Invalid passport number. It must start with an uppercase letter followed by 7 digits.");
			return false;
		}

		if (mobileNumber && !mobilePattern.test(mobileNumber)) {
			alert("Invalid mobile number. It must start with a digit between 6 and 9 and be 10 digits long.");
			return false;
		}

		if (new Date(dob) > minDob) {
			alert("You must be at least 12 years old.");
			return false;
		}

		var result = confirm("Are you sure you want to submit?");
		if (result) {
			// Code to submit the form or perform the action
			alert("Profile Updated Successfully!");
			document.getElementById("profileForm").submit(); // Submit the form
		} else {
			// Code to cancel the submission
			alert("Profile Update Unsuccessful.");
		}
	}
</script>

<body>
	<%
	Passenger passenger = (Passenger) request.getAttribute("passengerLogin");

	LocalDate today = LocalDate.now();
	LocalDate mindate = today.minusDays(2);
	LocalDate date16yearback = today.minusYears(12);
	String minDob = date16yearback.toString();
	String maxdate = today.toString();
	String minExpiry = mindate.toString();
	%>
	<header>
		<div class="logo">
			<img src="../images/logo.png" alt="CloudWing Logo"> <span>CloudWing</span>
		</div>
		<nav>
			<ul>
				<li><a href="/">Home</a></li>
				<li><a href="/passenger/profile">My Profile</a></li>
				<li><a href="/booking/allBookings/<%=passenger.PassengerId()%>">
						Booking History</a></li>
				<li><a
					href="/booking/upcomingFlight/<%=passenger.PassengerId()%>">Upcoming
						Flights</a></li>
				<li><a
					href="<%=(passenger == null) ? "/passenger/openLoginPage" : "/passenger/logout"%>">
						<%=(passenger == null) ? "Login" : "Logout"%>
				</a></li>
			</ul>
		</nav>
	</header>
	<div class="container">
		<div class="profile-card">
			<div class="profile-header">
				<h1>My Profile</h1>
			</div>
			<form id="profileForm" action="update" method="post">
				<div class="profile-info">
					<div class="info-row">
						<label for="username">Username:</label> <input type="text"
							id="username" name="username"
							value="<%=passenger.getUsername()%>" pattern="[A-Za-z0-9]+"
							required> <i class="fas fa-edit"></i>
					</div>
					<div class="info-row">
						<label for="name">Name:</label> <input type="text" id="name"
							name="name" value="<%=passenger.getName()%>" required
							pattern="[A-Za-z\s]+"> <i class="fas fa-edit"></i>
					</div>
					<div class="info-row">
						<label for="email">Email:</label> <input type="email" id="email"
							name="email" value="<%=passenger.getEmail()%>" readonly>
					</div>
					<div class="info-row">
						<label for="mobileNumber">Mobile Number:</label> <input
							type="text" id="mobileNumber" name="mobile_number"
							value="<%=passenger.getMobileNumber()%>" required> <i
							class="fas fa-edit"></i>
					</div>
					<div class="info-row">
						<label for="gender">Gender:</label> <select id="gender"
							name="gender" required>
							<option value="Male"
								<%=passenger.getGender().equals("Male") ? "selected" : ""%>>Male</option>
							<option value="Female"
								<%=passenger.getGender().equals("Female") ? "selected" : ""%>>Female</option>
							<option value="Other"
								<%=passenger.getGender().equals("Other") ? "selected" : ""%>>Other</option>
						</select>
					</div>
					<div class="info-row">
						<label for="dateOfBirth">Date of Birth:</label> <input type="date"
							id="dob" name="date_of_birth" max="<%=minDob%>"
							value="<%=passenger.getDateOfBirth()%>" required>
					</div>
					<div class="info-row">
						<label for="passportNumber">Passport Number:</label> <input
							type="text" id="passportNumber" name="passport_number"
							value="<%=passenger.getPassportNumber()%>"
							pattern="[A-Z][0-9]{8}">
					</div>
				</div>
				<div class="submit-row">
					<button onclick="confirmSubmission(event)" type="submit"
						class="submit-button animate__animated animate__pulse animate__infinite">Save
						Changes</button>
				</div>
				<%@include file="../message.jsp"%>
			</form>
		</div>
	</div>
</body>
</html>