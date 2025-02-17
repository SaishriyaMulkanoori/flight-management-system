<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Set New Password</title>
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
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.container button:hover {
	background-color: #0056b3;
}
</style>

</head>
<body>
	<div class="container">
		<h2>Set New Password</h2>
		<form action="/flightManager/update-password" method="post">
			<input type="password" name="password"
				title="Must contain at least one number, one uppercase and lowercase letter, and at least 8 or more characters"
				id="new-password" placeholder="Enter new password" required
				pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"> <input
				type="password" name="cpassword" id="confirm-password"
				placeholder="Confirm new password" required>
			<button type="submit">Set Password</button>
		</form>

		<%@include file="../error.jsp"%>
	</div>
</body>
</html>