<%@page import="java.math.BigDecimal"%>
<%@page import="cloud.wing.flight.entity.Airline"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="cloud.wing.booking.entity.BookingDetails"%>
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
	href="${pageContext.request.contextPath}/css/flightManager/bookingReport.css">
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<script>
function filterTable(){
	var selectedAirline = document.getElementById('airlineId');
	var airlineId = +selectedAirline.value;
	const url = airlineId ? "/flight/flightReport?airlineId=" + airlineId : "/flight/flightReport";
	window.location.href = url;
}

function downloadPDF() {
	const button = document.querySelector('#download-button');
	button.style.display = 'none';
	html2canvas(document.querySelector('#flightTable')).then(canvas => {
		const imgData = canvas.toDataURL('image/png');
		const { jsPDF } = window.jspdf;
		const pdf = new jsPDF('landscape');
		const imgWidth = canvas.width;
		const imgHeight = canvas.height;
		const aspectRatio = imgWidth / imgHeight;
		const pdfWidth = pdf.internal.pageSize.getWidth();
		const pdfHeight = pdfWidth / aspectRatio;
		pdf.addImage(imgData, 'PNG', 10, 10, pdfWidth - 20, pdfHeight - 20);
		pdf.save('flightReport.pdf');
		button.style.display = 'block';
	});
}

function sortTable(order) {
	const table = document.getElementById('flightTable');
	const rows = Array.from(table.rows).slice(1);
	rows.sort((a, b) => {
		const flightNumberA = a.cells[0].innerText.toUpperCase();
		const flightNumberB = b.cells[0].innerText.toUpperCase();
		if (order === 'asc') {
			return flightNumberA < flightNumberB ? -1 : flightNumberA > flightNumberB ? 1 : 0;
		} else {
			return flightNumberA > flightNumberB ? -1 : flightNumberA < flightNumberB ? 1 : 0;
		}
	});
	rows.forEach(row => table.appendChild(row));
}

function handleSortChange() {
	const sortOrder = document.getElementById('sortOrder').value;
	sortTable(sortOrder);
}
</script>

<body>
<%@ include file="header.jsp" %>

<% List<Flight> flights = (List<Flight>)request.getAttribute("flights");
    List<Airline> airlines = (List<Airline>)request.getAttribute("airlines");
%>
<h1>Flight Details</h1>
<div class="container">
    <label for="airlineName">Select Airline Name:</label>
    <select id="airlineId">
        <option value="">All</option>
        <% if (!airlines.isEmpty()) {
            for (Airline airline : airlines) { %>	
            <option value ="<%=airline.getAirlineId() %>"><%=airline.getName() %></option>
        <% } } %>
    </select>
    
    <button onclick="filterTable()" class="search-button">Search Flight</button>
    <div>
        <button onclick="downloadPDF()" class="search-button" id="download-button">Download Report</button>
    </div>
    <div style="float: right;">
        <label for="sortOrder">Sort by Flight Number:</label>
        <select id="sortOrder" onchange="handleSortChange()">
            <option value="asc">A-Z</option>
            <option value="desc">Z-A</option>
        </select>
    </div>

    <% if (!flights.isEmpty()) { %>
    <table id="flightTable">
        <thead>
            <tr>
                <th>Flight Number</th>
                <th>Departure Airport</th>
                <th>Arrival Airport</th>
                <th>Departure Date</th>
                <th>Arrival Date</th>
                <th>Total Economy Seats</th>
                <th>Total Business Seats</th>
                <th>Available Economy Seats</th>
                <th>Available Business Seats</th>
                <th>Economy Class Price</th>
                <th>Business Class Price</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <% for (Flight flight : flights) { %>
            <tr>
                <td><%=flight.getFlightNumber() %></td>
                <td><%=flight.getDepartureAirport() %></td>
                <td><%=flight.getArrivalAirport() %></td>
                <td><%=flight.getDepartureDate() %></td>
                <td><%=flight.getArrivalDate() %></td>
                <td><%=flight.getTotalSeatsEconomy() %></td>
                <td><%=flight.getTotalSeatsBusiness() %></td>
                <td><%=flight.getAvailableSeatsEconomy() %></td>
                <td><%=flight.getAvailableSeatsBusiness() %></td>
                <td><%=flight.getPriceEconomy() %></td>
                <td><%=flight.getPriceBusiness() %></td>
                <td class="<%= flight.getFlightStatus().toString() == "Cancelled" ? "status-cancelled" : "status-confirmed" %>"><%=flight.getFlightStatus() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>No Flights Available</p>
    <% } %>
</div>
</body>
</html>

