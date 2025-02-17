
<%@page import="java.time.LocalDate"%>
<%@page import="cloud.wing.passenger.entity.Passenger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Home</title>


<link href="../css/passenger/dashboard.css" rel="stylesheet" type="text/css">

</head>
<body>

	<%
	Passenger passenger = (Passenger) session.getAttribute("loggedInUser");
	
	LocalDate date = LocalDate.now();
	String mindate= date.toString();
	String bookingHistory="";
	String upcomingBookings="";
	if(passenger!=null){
		
		bookingHistory= "booking/allBookings/"+passenger.PassengerId();
		upcomingBookings= "booking/upcomingFlight/"+passenger.PassengerId();
		
	}
	else{
		bookingHistory= "/bussinessOwner/";
		upcomingBookings="/flightManager/";
	}
	
	%>


	<header>
          
	

		<div class="logo">
			<img src="../images/logo.png" alt="Logo" class="logo-img">
			CloudWing
		</div>
		<p></p>
		
		<nav>
			<ul>
				<li><a href="/">Home</a></li>
				<li><a href="passenger/profile"><%=(passenger==null) ? "" : "Profile"%></a></li>
				<li><a href="<%=(passenger==null) ? "/bussinessOwner/":bookingHistory %>"><%=(passenger==null) ? "Admin Login" : "Booking History"%></a></li>
				<li><a href="<%=(passenger==null) ? "/flightManager/":upcomingBookings %>"><%=(passenger==null) ? "Manager Login" : "Upcoming Flight"%></a></li>
				<li><a href="<%=(passenger==null) ? "/passenger/openLoginPage":"/passenger/logout" %>" ><%=(passenger==null) ? "User Login" : "Logout"%></a></li>
			</ul>
		</nav>
	</header>


	<div class="body">
		<h1>Book your tickets Now.</h1>
		<p>Find the best flights for your next journey</p>
		<form class="search-form" action="/flight/searchFlights" >
			<label for="departure">Departure Place</label>
			 <input type="text"   pattern="[A-Za-z\s]+" id="departure" name="departureAirport" required="required" placeholder="Enter departure place"> 
			 <label  for="arrival">Arrival Place</label> 
			 <input type="text"  pattern="[A-Za-z\s]+" name="arrivalAirport" id="arrival" required="required" placeholder="Enter arrival place"> 
			 <label for="date">Travel Date</label>
			  <input type="date" name="departureDate" required="required" min=<%=mindate %> id="date">

			<button type="submit">Search Flights</button>
		</form>


	</div>
	

	<footer>
		<p>&copy; 2024 Flight Reservation System. All rights reserved.</p>
	</footer>
</body>
</html>