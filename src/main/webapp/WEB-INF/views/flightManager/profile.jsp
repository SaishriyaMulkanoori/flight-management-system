<%@page import="cloud.wing.admin.entity.FlightManager"%>
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
<link rel="stylesheet" href="styles.css">
<style>

/* Global Styling */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background: url('/images/flight1.jpg') no-repeat center center fixed;
	color: #333;
}

header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 15px 20px;
	background-color: #01a9db;
	color: white;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.logo {
	display: flex;
	align-items: center;
	font-size: 18px;
}

.logo img {
	height: 40px;
	margin-right: 10px;
}

nav ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
}

nav ul li {
	margin: 0 15px;
}

nav ul li a {
	color: White;
	text-decoration: none;
	transition: color 0.3s ease;
}

nav ul li a:hover {
	color: ##01a9db;
}

/* Profile Card Styling */
.container {
	max-width: 800px;
	margin: 50px auto;
	background: LightBlue;
	border-radius: 10px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
	animation: fadeInUp 1s;
	padding: 20px;
}

.profile-header {
	text-align: center;
	margin-bottom: 20px;
	animation: zoomIn 0.5s;
}

.profile-header h1 {
	font-size: 28px;
	color: Black;
}

.profile-info {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 20px;
}

.info-row {
	display: flex;
	align-items: center;
	position: relative;
}

.info-row label {
	flex: 1;
	font-weight: bold;
	color: Black;
}

.info-row input, .info-row select {
	flex: 2;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	transition: all 0.3s ease;
}

.info-row input:focus, .info-row select:focus {
	border-color: #0078D7;
	box-shadow: 0 0 5px rgba(0, 120, 215, 0.5);
	outline: none;
}

.info-row i {
	position: absolute;
	right: 10px;
	color: Grey;
	cursor: pointer;
	transition: transform 0.3s ease;
}

.info-row i:hover {
	transform: scale(1.2);
}

.submit-row {
	text-align: center;
	margin-top: 20px;
}

.submit-button {
	background-color: #0078D7;
	color: white;
	border: none;
	padding: 12px 25px;
	font-size: 15px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.submit-button:hover {
	background-color: #005BB5;
}

@media ( max-width : 600px) {
	.profile-info {
		grid-template-columns: 1fr;
	}
}
</style>
</head>
<script type="text/javascript">
	function confirmSubmission(event) {
		event.preventDefault(); // Prevent form submission
		var mobileNumber = document.getElementById("mobileNumber").value;
		var mobilePattern = /^[6-9][0-9]{9}$/;
		var name = document.getElementById("name").value;
		var namePattern = /^[A-Za-z\s]+$/;
		var username = document.getElementById("username").value;
		var usernamePattern = /^[A-Za-z0-9]+$/;

		if (mobileNumber && !mobilePattern.test(mobileNumber)) {
			alert("Invalid mobile number. It must start with a digit between 6 and 9 and be 10 digits long.");
			return false;
		}

		if (name && !namePattern.test(name)) {
			alert("Invalid name. It must contain only alphabets and spaces.");
			return false;
		}

		if (username && !usernamePattern.test(username)) {
			alert("Invalid username. It must contain only alphabets and digits.");
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
	FlightManager manager = (FlightManager) request.getAttribute("managerLogin");
	%>
	<header>
		<div class="logo">
			<img src="../images/logo.png" alt="CloudWing Logo"> <span>CloudWing</span>
		</div>
		<nav>
			<ul>
				<li><a href="/flightManager/home">Home</a></li>

				<li><a href="flightManager/profile">Profile</a></li>
				<li><a href="/flightManager/logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="container">
		<div class="profile-card">
			<div class="profile-header">
				<h1>My Profile</h1>
			</div>
			<form id="profileForm" action="/flightManager/update" method="post">
				<div class="profile-info">
					<div class="info-row">
						<label for="username">Username:</label> <input type="text"
							id="username" name="username" value="<%=manager.getUsername()%>"
							required> <i class="fas fa-edit"></i>
					</div>
					<div class="info-row">
						<label for="name">Name:</label> <input type="text" id="name"
							name="name" value="<%=manager.getName()%>" required> <i
							class="fas fa-edit"></i>
					</div>
					<div class="info-row">
						<label for="email">Email:</label> <input type="email" id="email"
							name="email" value="<%=manager.getEmail()%>" readonly>
					</div>
					<div class="info-row">
						<label for="mobileNumber">Mobile Number:</label> <input
							type="text" id="mobileNumber" name="mobile_number"
							value="<%=manager.getMobile()%>" required> <i
							class="fas fa-edit"></i>
					</div>
					<div class="info-row">
						<label for="gender">Gender:</label> <select id="gender"
							name="gender" required>
							<option value="Male"
								<%=manager.getGender().equals("Male") ? "selected" : ""%>>Male</option>
							<option value="Female"
								<%=manager.getGender().equals("Female") ? "selected" : ""%>>Female</option>
							<option value="Other"
								<%=manager.getGender().equals("Other") ? "selected" : ""%>>Other</option>
						</select>
					</div>
				</div>
				<div class="submit-row">
					<button type="submit" onclick="confirmSubmission(event)"
						class="submit-button animate__animated animate__pulse animate__infinite">Save
						Changes</button>
				</div>
				<%@include file="../message.jsp"%>
			</form>
		</div>
	</div>
</body>
</html>



