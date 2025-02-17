<%@page import="java.time.Instant"%>
<%@page import="java.security.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cloud.wing.flight.entity.Flight"%>

<%@ page import="cloud.wing.flight.entity.Airline"%>

<%@ page import="java.util.List"%>
<%@page import="cloud.wing.admin.entity.FlightManager"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Flight</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/flight/editFlight.css">

</head>

<script>
    function validateForm() {
    	
      
        var departureDate = new Date(document.getElementById("departureDate").value);
        var arrivalDate = new Date(document.getElementById("arrivalDate").value);
        if (departureDate >= arrivalDate) {
            alert("Arrival date & time must be after departure date & time");
            return false;
        }
        return true;
    }
</script>

<body>

	<div class="container">
		<form action="${pageContext.request.contextPath}/flight/update"
			method="POST"     onsubmit="return validateForm()">
			<h2>Edit Flight</h2>
			<!-- Hidden field for flight ID -->
			<input type="hidden" name="flightId" value="${flight.flightId}" /> <label
				for="flightNumber">Flight Number:</label> <input type="text"
				id="flightNumber" name="flightNumber" readonly
				value="${flight.flightNumber}" required /> <label for="airline">Airline:</label>

			<select id="airline" name="airline.airlineId" required>
				<option value="">Select an Airline</option>
				<%
				Flight flight = (Flight) request.getAttribute("flight");
				FlightManager logInflightManager = (FlightManager) session.getAttribute("loggedInFm");
				List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");
				Instant current = Instant.now();
				Instant timeStamp = flight.getDepartureDate().toInstant();
				int result = current.compareTo(timeStamp);

				for (Airline airline : airlines) {
				%>
				<option value="<%=airline.getAirlineId()%>"
					<%=(flight.getAirline() != null && airline.getAirlineId() == flight.getAirline().getAirlineId()) ? "selected"
		: ""%>>
					<%=airline.getName()%>
				</option>
				<%
				}
				%>
			</select> <label for="departureAirport">Departure Airport:</label> <input
				type="text" id="departureAirport" name="departureAirport"
				value="${flight.departureAirport}" required /> <label
				for="arrivalAirport">Arrival Airport:</label> <input type="text"
				id="arrivalAirport" name="arrivalAirport"
				value="${flight.arrivalAirport}" required /> <label
				for="departureDate">Departure Date:</label> <input
				type="datetime-local" <%= result>0 ?"readonly":"" %>
				min=<%= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date()) %>
				id="departureDate" name="departureDate"
				value="${flight.departureDate != null ? flight.departureDate.toString().replace(' ', 'T') : ''}"
				required /> <label for="arrivalDate">Arrival Date:</label>
				 <input
				type="datetime-local" <%= result>0 ?"readonly":"" %>
				min=<%= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date()) %>
				id="arrivalDate" name="arrivalDate"
				value="${flight.arrivalDate != null ? flight.arrivalDate.toString().replace(' ', 'T') : ''}"
				required />
				
				
				 <label for="availableSeatsEconomy">Available
				Seats (Economy):</label> <input type="number" id="availableSeatsEconomy"
				readonly name="availableSeatsEconomy"
				value="${flight.availableSeatsEconomy}" required /> <label
				for="availableSeatsBusiness">Available Seats (Business):</label> <input
				type="number" id="availableSeatsBusiness" readonly
				name="availableSeatsBusiness"
				value="${flight.availableSeatsBusiness}" required /> <label
				for="totalSeatsEconomy">Total Seats (Economy):</label> <input
				type="number" id="totalSeatsEconomy" readonly
				name="totalSeatsEconomy" value="${flight.totalSeatsEconomy}"
				required /> <label for="totalSeatsBusiness">Total Seats
				(Business):</label> <input type="number" id="totalSeatsBusiness" readonly
				name="totalSeatsBusiness" value="${flight.totalSeatsBusiness}"
				required /> <label for="priceEconomy">Price (Economy):</label> <input
				type="number" step="0.01" id="priceEconomy" min="1000"
				name="priceEconomy" value="${flight.priceEconomy}" required /> <label
				for="priceBusiness">Price (Business):</label> <input type="number"
				step="0.01" id="priceBusiness" min="2000" name="priceBusiness"
				value="${flight.priceBusiness}" required /> <label
				for="flightStatus">Flight Status:</label> <select id="flightStatus"
				name="flightStatus" required>
				<option value="Active"
					<%="Active".equals(flight.getFlightStatus()) ? "selected" : ""%>>Active</option>
				<%
				if (result > 0) {
				%>
				<option value="Completed"
					<%="Completed".equals(flight.getFlightStatus()) ? "selected" : ""%>>Completed</option>
				<%
				}
				%>
				<%
				if (result < 0) {
				%>
				<option value="Cancelled"
					<%="Cancelled".equals(flight.getFlightStatus()) ? "selected" : ""%>>Cancelled</option>
				<%
				}
				%>
			</select>

			<%
			if (logInflightManager != null) {
			%>
			<input type="hidden" value=<%=logInflightManager.getId()%>
				name="flightManager.id">

			<%
			} else {
			%>

			<label for="flightManager">Flight Manager:</label> <select
				id="flightManager" name="flightManager.id" required>
				<option value="">select</option>
				<%
				List<FlightManager> flightManagers = (List<FlightManager>) request.getAttribute("flightManager");

				for (FlightManager flightManager : flightManagers) {
				%>
				<option value="<%=flightManager.getId()%>"><%=flightManager.getName()%></option>
				<%
				}
				%>
			</select>
			<%
			}
			%>

			<button type="submit">Update Flight</button>
		</form>
	</div>
</body>
</html>