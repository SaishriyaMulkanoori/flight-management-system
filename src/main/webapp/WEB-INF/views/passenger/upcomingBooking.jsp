<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.time.LocalDate"%>
<%@page import="cloud.wing.booking.entity.Booking"%>
<%@page import="cloud.wing.booking.entity.BookingDetails"%>
<%@page import="java.util.List"%>
<%@page import="cloud.wing.flight.entity.Flight"%>
<%@page import="cloud.wing.passenger.entity.Passenger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>All Booking </title>
   
   
</head>
<style>
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

.download-button {
	background-color: #4CAF50; /* Green background */
	color: white; /* White text */
	border: none; /* No border */
	padding: 7px 10px; /* Padding */
	font-size: 16px; /* Font size */
	cursor: pointer; /* Pointer cursor on hover */
	border-radius: 5px; /* Rounded corners */
	transition: background-color 0.3s, transform 0.3s;
	/* Smooth transition for background color and transform */
	text-decoration: none; /* No underline for links */
	display: inline-block; /* Inline block for proper padding */
}

.download-button:hover {
	background-color: #45a049; /* Darker green on hover */
	transform: scale(1.05); /* Slightly larger on hover */
}

.download-button:active {
	background-color: #3e8e41; /* Even darker green when clicked */
	transform: scale(1); /* Reset scale when clicked */
}

.cancel-button {
	background-color: #ff4d4d; /* Red background */
	color: white; /* White text */
	border: none; /* No border */
	padding: 7px 10px; /* Padding */
	font-size: 16px; /* Font size */
	cursor: pointer; /* Pointer cursor on hover */
	border-radius: 3px; /* Rounded corners */
	transition: background-color 0.3s, transform 0.3s;
	/* Smooth transition for background color and transform */
}

.cancel-button:hover {
	background-color: #e60000; /* Darker red on hover */
	transform: scale(1.05); /* Slightly larger on hover */
}

.cancel-button:active {
	background-color: #cc0000; /* Even darker red when clicked */
	transform: scale(1); /* Reset scale when clicked */
}

.cancelled-text {
	color: red; /* Red color to indicate cancellation */
	font-weight: bold; /* Bold text */
	font-size: 16px; /* Font size */
	text-transform: uppercase; /* Uppercase letters */
	background-color: #ffe6e6; /* Light red background */
}
</style>
<body>
<%
	
List<BookingDetails> bookings=  (List<BookingDetails>) request.getAttribute("bookings");
int id =(int) request.getAttribute("user");


LocalDateTime today = LocalDateTime.now();
bookings=bookings.stream() .filter(booking -> !booking.getDepartureDate().isBefore(today)) .collect(Collectors.toList());



	%>

    <header>
        
       
            
      <div class="logo">
			 <img src="<%= request.getContextPath() %>/images/logo.png" alt="Logo" class="logo-img">
			CloudWing
		</div>
		
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
    <h2>Upcoming Flights</h2>
   <% if(bookings.size()==0) { %> <div class="no-flights">No Upcoming flight available</div> <%} %>
   
   
   <% if(bookings.size()!=0) { %>
    <table>
        <thead>
            <tr>
                <th>Airline Name</th>
                <th>Flight Number</th>
                <th>Departure Airport</th>
                <th>Arrival Airport</th>
                <th>Departure Date</th>
                <th>Arrival Date</th>
                <th>Seat Class</th>
                <th>NO. OF Seats</th>
                <th>Action</th>
                <th>DownloadTicket</th>
              
            </tr>
        </thead>
        <tbody>
        
        <% for(BookingDetails booking :bookings)  { 
            String status = booking.getBookingStatus();
        %>
            <tr>
                <td><%=booking.getAirlineName() %></td>
                <td><%=booking.getFlightNumber()%></td>
                <td><%=booking.getDepartureAirport()%></td>
                <td><%=booking.getArrivalAirport()%></td>
                <td><%=booking.getDepartureDate()%></td>
                <td><%=booking.getArrivalDate()%></td>
                <td><%=booking.getSeatClass()%></td>
                <td><%=booking.getNumberOfSeats()%></td>
                
               <% if(status.equals("Confirmed")){ %>
               <td> <button  onclick="confirmCancellation(<%=booking.getBookingId() %>)"  class="cancel-button"> Cancel Booking</button></td>
           
              <%  }  else {%>
              <td class="cancelled-text"> Cancelled</td>
              
              <%} %>
                
               <td> <a href="/booking/ticket/<%=booking.getBookingId()%>"  class="download-button" >Download Ticket</a></td>
            </tr>
            <%} %>
            <!-- Additional rows can be added here -->
        </tbody>
    </table>
    
    <% } %>
    
  
</body>

<script>

function confirmCancellation(bookingId) {
    if (confirm("Are you sure you want to cancel this booking?")) {
        fetch('/booking/cancelBooking/'+bookingId , {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        })
        .then(response => response.text())
        .then(result => {
            if (result === 'success') {
                alert('Booking cancelled successfully.');
                location.reload(); // Reload the page to reflect changes
            } else {
                alert('Failed to cancel booking.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while cancelling the booking.');
        });
    }
}
</script>

</html>