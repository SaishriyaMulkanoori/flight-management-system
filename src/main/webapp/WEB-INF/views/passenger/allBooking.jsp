<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.time.LocalDate"%>
<%@page import="cloud.wing.booking.entity.Booking"%>
<%@page import="cloud.wing.booking.entity.BookingDetails"%>
<%@page import="java.util.List"%>
<%@page import="cloud.wing.flight.entity.Flight"%>
<%@page import="cloud.wing.passenger.entity.Passenger"%>

	<!DOCTYPE html>
<html>
<head>
    <title>All Booking </title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	box-sizing: border-box;
}

h2 {
	text-align: center;
	color: #333;
}

table {
	width: 95%;
	border-collapse: collapse;
	margin: 20px auto;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	background-color: #fff;
	border-radius: 8px;
	overflow: hidden;
}

table thead {
	background-color: #01a9db;
	color: #fff;
}

table th, table td {
	padding: 13px;
	text-align: left;
}

table th {
	font-size: 16px;
}

tr:hover {
	background-color: #f1f1f1;
}

.no-flights {
	text-align: center;
	margin-top: 20px;
	font-size: 18px;
	color: red;
}

.book-button {
	background-color: #01a9db;
	color: white;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.book-button:hover {
	background-color: #007bb5;
}

header {
	background-color: #01a9db;
	color: #fff;
	padding: 10px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

header .logo {
	display: flex;
	align-items: center;
	font-size: 1.5rem;
	font-weight: bold;
}

header .logo .logo-img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin-right: 10px;
}

header nav ul {
	list-style: none;
	display: flex;
}

header nav ul li {
	margin: 0 10px;
	display: flex;
	align-items: center;
}

header nav ul li a {
	color: #fff;
	text-decoration: none;
	font-size: 1rem;
	display: flex;
	align-items: center;
}

header nav ul li a:hover {
	text-decoration: underline;
}

header nav ul li a .profile-img {
	width: 30px;
	height: 30px;
	border-radius: 50%;
	margin-right: 8px;
}



</style>
<%
	
List<BookingDetails> bookings=  (List<BookingDetails>) request.getAttribute("bookings");
int id =(int) request.getAttribute("user");


LocalDateTime today = LocalDateTime.now();

	%>

</head>
<body>


   <header>
          
	

		<div class="logo">
		 <img src="<%= request.getContextPath() %>/images/logo.png" alt="Logo" class="logo-img">
			
			CloudWing
		</div>
		<p></p>
		
		<nav>
			<ul>
				<li><a href="/">Home</a></li>
				<li><a href="/passenger/profile">Profile</a></li>
				<li><a href="/booking/allBookings/<%=id%>" >Booking History</a></li>
				<li><a href="/booking/upcomingFlight/<%=id%>"> UpcomingFlight</a></li>
				<li><a href="/passenger/logout" >Logout</a></li>
			</ul>
		</nav>
	</header>
   
    <h2>Booking History</h2>
   <% if(bookings.size()==0) { %> <div class="no-flights">No Booking available</div> <%} %>
   
   
   <% if(bookings.size()!=0) { %>
    <table>
        <thead>
            <tr>
                <th>Airline Name</th>
                <th>Flight Number</th>
                <th>Passengers Name</th>
                <th>Departure Airport</th>
                <th>Arrival Airport</th>
                <th>Departure Date</th>
                <th>Arrival Date</th>
                <th>Seat Class</th>
                <th>NO. OF Seats</th>
                
                <th>Status</th>
              
            </tr>
        </thead>
        <tbody>
       
       
     
        
        <% for(BookingDetails booking :bookings)  { %>
            <tr>
                <td><%=booking.getAirlineName() %></td>
                <td><%=booking.getFlightNumber()%></td>
                <td><%=booking.getPassengerNames() %></td>
                <td><%=booking.getDepartureAirport()%></td>
                <td><%=booking.getArrivalAirport()%></td>
                <td><%=booking.getDepartureDate()%></td>
                <td><%=booking.getArrivalDate()%></td>
                <td><%=booking.getSeatClass()%></td>
                <td><%=booking.getNumberOfSeats()%></td>
                <td><%=booking.getBookingStatus()%></td>
                
            </tr>
            <%} %>
            <!-- Additional rows can be added here -->
        </tbody>
    </table>
    
    <% } %>
    
  
</body>
</html>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	










