<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Verify OTP</title>
<style>
body {
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 10px;
	background-image: url("/images/signup-bg.jpg");
	background-position: center;
	background-size: cover;
}

.container {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h2 {
	margin-bottom: 20px;
}

.container input {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.container button {
	width: 100%;
	padding: 10px;
	background-color: #28a745;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.container button:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Verify OTP</h2>
		<form action="/flightManager/verify_otp" method="post">
			<input type="text" name="otp" placeholder="Enter OTP" required>
			<button type="submit">Verify OTP</button>
		</form>
		<%@include file="../error.jsp"%>
	</div>
</body>
</html>