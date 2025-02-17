<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Passenger Login</title>

<link href="../css/passenger/login.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>
<body>
	<div class="container">
		<!-- Title section -->
		<div class="title">Passenger Login</div>
		<!-- Login form -->
		<form action="login" method="post">
			<div class="input-box">
				<span class="details"><i class="fas fa-envelope"></i> Email</span> <input
					type="text" id="username" name="email"
					placeholder="Enter your email" required>
			</div>
			<div class="input-box">
				<span class="details"><i class="fas fa-lock"></i> Password</span> <input
					type="password" id="password" name="password"
					placeholder="Enter your password" required>
			</div>
			<div class="button">
				<input type="submit" value="Login">
			</div>
			<div class="register-link">
				<p>
					Don't have an account? <a href="/passenger/signup">Register</a>
				</p>
				<p>
				<a href = "/passenger/forgotPassword">Forgot password!</a></p>
			</div>
		</form>
					<%@include file="../message.jsp"%>
			<%@include file="../error.jsp"%>
	</div>
</body>
</html>