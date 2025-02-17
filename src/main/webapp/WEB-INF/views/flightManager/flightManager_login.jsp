<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Manager Login</title>
<link href="../css/flightManager/login.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<!-- Title section -->
		<div class="title">Flight Manager Login</div>
		<div class="content">
			<!-- Registration form -->
			<form action="login" method="post">
				<div class="user-details">

					<div class="input-box">
						<span class="details">Email</span> <input type="text" id="email"
							name="email" placeholder="Enter your email" required>
					</div>

					<!-- Input for Password -->
					<div class="input-box">
						<span class="details">Password</span> <input type="password"
							id="password" name="password" placeholder="Enter your password"
							required>
					</div>
					<!-- Input for Confirm Password -->

				</div>

				<!-- Submit button -->
				<div class="button">
					<input type="submit" value="Login">
				</div>

				<p>
					Don't have an account? <a href="/flightManager/signup">Register</a>
				</p>
				<p>
					<a href="/flightManager/forgotPassword">Forgot password!</a>
				</p>
			</form>

			<%@include file="../message.jsp"%>
			<%@include file="../error.jsp"%>

		</div>
	</div>






</body>
</html>