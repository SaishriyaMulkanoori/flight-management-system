<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Complete</title>
    <link href="../css/passenger/paymentStatus.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container">
        <h1>Booking Status</h1>
        <p> <%
    String message = (String) request.getAttribute("message");
	if(message!=null) {
		out.print("<span style='color: green'>"+message+"</span>");
	}
	
	
	String error = (String) request.getAttribute("error");
	if(error!=null) {
		out.print("<span style='color: green'>"+error+"</span>");
	}

%></p>
        <a href="/" class="btn-home">Return to Home Page</a>
    </div>
</body>
</html>

















   <link href="../css/passenger/payment_form.css" rel="stylesheet" type="text/css">

