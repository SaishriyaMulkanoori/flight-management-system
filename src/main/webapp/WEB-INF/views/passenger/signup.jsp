<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Passenger</title>

<link href="../css/passenger/signup.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<%
LocalDate today = LocalDate.now();
LocalDate mindate = today.minusDays(2);
LocalDate date16yearback = today.minusYears(12);
String minDob = date16yearback.toString();
String maxdate = today.toString();
String minExpiry = mindate.toString();
%>

</head>
<body>
	<div class="container">
		<!-- Title section -->
		<div class="title">Passenger Registration</div>
		<div class="content">
			<!-- Registration form -->
			<form action="registration" method="post">
				<div class="user-details">
					<!-- Input fields -->
					<div class="input-box">
						<span class="details"><i class="fas fa-user"></i> Full Name</span>
						<input type="text" id="name" name="name"
							placeholder="Enter your full name" pattern="[A-Za-z\s]+" required>
					</div>
					<div class="input-box">
						<span class="details"><i class="fas fa-user-tag"></i>
							Username</span> <input type="text" id="username" name="username"
							pattern="[A-Za-z0-9]+" minlength="5"
							placeholder="Enter your username" required>
					</div>
					<div class="input-box">
						<span class="details"><i class="fas fa-envelope"></i> Email</span>
						<input type="text" id="email" name="email"
							placeholder="Enter your email" required>
					</div>
					<div class="input-box">
						<span class="details"><i class="fas fa-phone"></i> Phone
							Number</span> <input type="text" id="mobile" maxlength="10"
							minlength="10" name="mobile_number"
							title="please enter a valid phone number" pattern="[6-9][0-9]{9}"
							placeholder="Enter your phone number" required>
					</div>
					<div class="input-box">
						<span class="details"><i class="fas fa-calendar-alt"></i>
							Date of Birth</span> <input type="date" id="dob" name="date_of_birth"
							max="<%=minDob%>" required>
					</div>
					<div class="input-box">
						<span class="details"><i class="fas fa-lock"></i> Password</span>
						<input type="password" id="password" name="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" minlength="8"
							placeholder="Enter your password"
							title="Must contain at least one number, one uppercase and lowercase letter, and at least 8 or more characters"
							required>
					</div>
					<div class="input-box">
						<span class="details"><i class="fas fa-lock"></i> Confirm
							Password</span> <input type="password" id="cpassword" name="cpassword"
							placeholder="Confirm your password" required>
					</div>
					<div class="input-box">
						<input type="radio" id="dot-1" value="male" name="gender" required>
						<input type="radio" id="dot-2" value="female" name="gender"
							required> <span class="gender-title">Gender</span>
						<div class="category">
							<label for="dot-1"><span class="dot one"></span><span
								class="gender">Male</span></label> <label for="dot-2"><span
								class="dot two"></span><span class="gender">Female</span></label>
						</div>
					</div>
					<!-- Submit button -->
					<div class="helper">
						<div class="button">
							<input type="submit" value="Register">
						</div>
						<p>
							Already have an account? <a href="/passenger/openLoginPage">Login</a>
						</p>
					</div>
			</form>
			<%@include file="../message.jsp"%>
			<%@include file="../error.jsp"%>
		</div>
	</div>
</body>
</html>