<%@page import="java.util.Comparator"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cloud.wing.flight.entity.Flight"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/flight/flightList.css">
</head>


<body>
<%@ include file="header.jsp" %>
	<h1>List of all flights</h1>
	<div class="container">
		<a href="${pageContext.request.contextPath}/flight/add"
			class="add-button">Add New Flight</a>
		<div class="flight-list">

			<%
			List<Flight> flights = (List<Flight>) request.getAttribute("flights");
			

			for (Flight flight :flights ) {
			%>


			<div class="flight-card">
				<div class="details">
					<h3>
						Flight
						<%=flight.getFlightNumber()%>
						-
						<%=flight.getAirline().getName()%></h3>
					<p>
						<strong>Flight Manager:</strong>
						<%=flight.getFlightManager().getName()%></p>
					<p>
						<strong>Departure:</strong>
						<%=flight.getDepartureAirport()%>
						on
						<%=flight.getDepartureDate()%></p>
					<p>
						<strong>Arrival:</strong>
						<%=flight.getArrivalAirport()%>
						on
						<%=flight.getArrivalDate()%></p>
					<p>
						<strong>Economy Seats:</strong>
						<%=flight.getAvailableSeatsEconomy()%>/<%=flight.getTotalSeatsEconomy()%></p>
					<p>
						<strong>Business Seats:</strong>
						<%=flight.getAvailableSeatsBusiness()%>/<%=flight.getTotalSeatsBusiness()%></p>
					<p>
						<strong>Price:</strong> Economy - ₹<%=flight.getPriceEconomy()%>,
						Business - ₹<%=flight.getPriceBusiness()%></p>
					<p>
						<strong>Status:</strong>
						<%=flight.getFlightStatus()%></p>
				</div>

				<div class="actions">
				
				<%if(flight.getFlightStatus().toString()=="Cancelled") {%>
				       <span class="cancel-button">  Cancelled</span>
				<%}
				
				else if(flight.getFlightStatus().toString()=="Completed"){ %>
				
				<span class ="complete-button">Completed</span>
				<% 
				} else { %>
					<a href="/flight/edit/<%=flight.getFlightId()%>" class="edit">Edit</a><%} %>
					
					<%if (!flight.getFlightStatus().toString().equals("Active")) { %>
					<a
						href="${pageContext.request.contextPath}/flight/delete/<%=flight.getFlightId()%>"
						class="delete"
						onclick="return confirm('Are you sure you want to delete this flight?')">Delete</a>
						<%} %>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>

