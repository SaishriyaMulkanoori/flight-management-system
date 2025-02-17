<%@page import="java.util.List"%>
<%@page import="cloud.wing.flight.entity.Flight"%>
<%@page import="cloud.wing.passenger.entity.Passenger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Available Flights</title>
   <link href="../css/passenger/availableFlight.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	Passenger passenger = (Passenger) session.getAttribute("loggedInUser");

List<Flight> flights=  (List<Flight>) request.getAttribute("flights");

String bookingHistory="";
if(passenger!=null){
	
	bookingHistory= "/booking/allBookings/"+passenger.PassengerId();
}
else{
	bookingHistory= "/bussinessOwner/";
}

	%>

    <header>
        
        <div class="logo">
        <img src="<%= request.getContextPath() %>/images/logo.png" alt="Logo" class="logo-img">
            CloudWing
        </div>
        <nav>
           <ul>
				<li><a href="/">Home</a></li>
				<li><a href="/passenger/profile"><%=(passenger==null) ? "" : "Profile"%></a></li>
				<li><a href="<%=(passenger==null) ? "/bussinessOwner/":bookingHistory %>">  <%=(passenger==null) ? "Admin Login" : "Booking History"%></a></li>
				<li><a href="<%=(passenger==null) ? "/flightManager/":"/passenger/upcomingFlight" %>"><%=(passenger==null) ? "Manager Login" : "UpcomingFlight"%></a></li>
				<li><a href="<%=(passenger==null) ? "/passenger/openLoginPage":"/passenger/logout" %>" ><%=(passenger==null) ? "UserLogin" : "Logout"%></a></li>
			</ul>
        </nav>
    </header>
    <h2>Available Flights</h2>
   <% if(flights.size()==0) { %> <div class="no-flights">No flights available</div> <%} %>
   
   
   <% if(flights.size()!=0) { %>
    <table>
        <thead>
            <tr>
                <th>Flight Number</th>
                <th>Departure Airport</th>
                <th>Arrival Airport</th>
                <th>Departure Time</th>
                <th>Arrival Time</th>
                <th>Economy Seat Price</th>
                <th>Business Seat Price</th>
                <th>Available Economy Seats</th>
                <th>Available Business Seats</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        
        <% for(Flight flight :flights)  { %>
            <tr>
                <td><%=flight.getFlightNumber() %></td>
                <td><%=flight.getDepartureAirport()%></td>
                <td><%=flight.getArrivalAirport()%></td>
                <td><%=flight.getDepartureDate()%></td>
                <td><%=flight.getArrivalDate()%></td>
                <td><%=flight.getPriceEconomy()%></td>
                <td><%=flight.getPriceBusiness() %></td>
                <td><%=flight.getAvailableSeatsEconomy()%></td>
                <td><%=flight.getAvailableSeatsBusiness()%></td>
                <td>
                    <a href="/booking/<%=flight.getFlightId() %>" class="book-button">Book</a>
                </td>
            </tr>
            <%} %>
            <!-- Additional rows can be added here -->
        </tbody>
    </table>
    
    <%} %>
</body>
</html>