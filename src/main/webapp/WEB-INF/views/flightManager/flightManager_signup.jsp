<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Manager Registration</title>
<link href="../css/flightManager/signup.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<!-- Title section -->
		<div class="title">Flight Manager Registration</div>
		<div class="content">
			<!-- Registration form -->
			<form action="registration" method="post">
				<div class="user-details">
					<!-- Input for Full Name -->
					<div class="input-box">
						<span class="details">Full Name</span> <input type="text"
							id="name" name="name" pattern="[A-Za-z\s]+"
							placeholder="Enter your name" required>
					</div>
					<!-- Input for Username -->
					<div class="input-box">
						<span class="details">Username</span> <input type="text"
							id="username" min="5" pattern="[A-Za-z0-9]+" name="username"
							placeholder="Enter your username" required>
					</div>
					<!-- Input for Email -->
					<div class="input-box">
						<span class="details">Email</span> <input type="email" id="email"
							name="email" placeholder="Enter your email" required>
					</div>
					<!-- Input for Phone Number -->
					<div class="input-box">
						<span class="details">Phone Number</span> <input type="text"
							id="mobile" minlength="10" maxlength="10" name="mobile"
							title="please enter a valid phone number" pattern="[6-9][0-9]{9}"
							placeholder="Enter your number" required>
					</div>
					<!-- Input for Password -->
					<div class="input-box">
						<span class="details">Password</span> <input type="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Must contain at least one number, one uppercase and lowercase letter, and at least 8 or more characters"
							id="password" name="password" placeholder="Enter your password"
							required>
					</div>
					<!-- Input for Confirm Password -->
					<div class="input-box">
						<span class="details">Confirm Password</span> <input
							type="password" id="cpassword" name="cpassword"
							placeholder="Confirm your password" required>
					</div>
				</div>
				<div class="gender-details">
					<!-- Radio buttons for gender selection -->
					<input type="radio" name="gender" value="male" id="dot-1">
					<input type="radio" name="gender" value="female" id="dot-2">
					<input type="radio" name="gender" value="other" id="dot-3">
					<span class="gender-title">Gender</span>
					<div class="category">
						<!-- Label for Male -->
						<label for="dot-1"> <span class="dot one"></span> <span
							class="gender">Male</span>
						</label>
						<!-- Label for Female -->
						<label for="dot-2"> <span class="dot two"></span> <span
							class="gender">Female</span>
						</label>
						<!-- Label for Prefer not to say -->
						<label for="dot-3"> <span class="dot three"></span> <span
							class="gender">Other</span>
						</label>
					</div>
				</div>
				<!-- Submit button -->
				<div class="button">
					<input type="submit" value="Register">
				</div>

				<p>
					Already have an account! <a href="/flightManager/">Login</a>
				</p>
			</form>

			<%@include file="../message.jsp"%>
			<%@include file="../error.jsp"%>


		</div>
	</div>






</body>
</html>