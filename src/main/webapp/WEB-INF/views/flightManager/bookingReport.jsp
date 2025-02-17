<%@page import="cloud.wing.admin.entity.FlightManager"%>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<script>
function filterTable(){
	var selectedFlight = document.getElementById('flightNumber');
	var flightNumber = selectedFlight.value;
	const url = flightNumber ? "/booking/bookingReport?flightNumber=" + flightNumber : "/booking/bookingReport";
	window.location.href = url;
}

function downloadPDF() {
	const button = document.querySelector('#download-button');
	button.style.display = 'none';
	html2canvas(document.querySelector('#bookingTable')).then(canvas => {
		const imgData = canvas.toDataURL('image/png');
		const { jsPDF } = window.jspdf;
		const pdf = new jsPDF('landscape');
		const imgWidth = canvas.width;
		const imgHeight = canvas.height;
		const aspectRatio = imgWidth / imgHeight;
		const pdfWidth = pdf.internal.pageSize.getWidth();
		const pdfHeight = pdfWidth / aspectRatio;
		pdf.addImage(imgData, 'PNG', 10, 10, pdfWidth - 20, pdfHeight - 20);
		pdf.save('bookingReport.pdf');
		button.style.display = 'block';
	});
}

function sortTable(order) {
	const table = document.getElementById('bookingTable');
	const rows = Array.from(table.rows).slice(1);
	rows.sort((a, b) => {
		const nameA = a.cells[0].innerText.toUpperCase();
		const nameB = b.cells[0].innerText.toUpperCase();
		if (order === 'asc') {
			return nameA < nameB ? -1 : nameA > nameB ? 1 : 0;
		} else {
			return nameA > nameB ? -1 : nameA < nameB ? 1 : 0;
		}
	});
	rows.forEach(row => table.appendChild(row));
}

function handleSortChange() {
	const sortOrder = document.getElementById('sortOrder').value;
	sortTable(sortOrder);
}
</script>

<%
List<Flight> flights = (List<Flight>) request.getAttribute("flight");
List<BookingDetails> bookingDetails = (List<BookingDetails>) request.getAttribute("bookingDetails");

FlightManager manager = (FlightManager) session.getAttribute("loggedInFm");
String home = "";
String logout = "";
if (manager == null) {
	home = "/bussinessOwner/home";
	logout = "/bussinessOwner/logout";
} else {
	home = "/flightManager/home";
	logout = "/flightManager/logout";
}
%>
<body>
	<header>
		<div class="logo">
			<img src="../images/logo.png" alt="Logo" class="logo-img" />
			CloudWing
		</div>
		<nav>
			<ul>
				<li><a href="<%=home%>">Home</a></li>
				<%
				if (manager != null) {
				%>
				<li><a href="/flightManager/profile">Profile</a></li>
				<%
				}
				%>
				<li><a href="<%=logout%>">Logout</a></li>
			</ul>
		</nav>
	</header>

	<h1>Booking Details</h1>
	<div class="container">
		<label for="flightNumber">Select Flight Number:</label> <select
			id="flightNumber">
			<option value="">All</option>
			<%
			if (!flights.isEmpty()) {
				for (Flight flight : flights) {
			%>
			<option value="<%=flight.getFlightNumber()%>"><%=flight.getFlightNumber()%></option>
			<%
			}
			}
			%>
		</select>

		<button onclick="filterTable()" class="search-button">Search
			Passenger</button>
		<div>
			<button onclick="downloadPDF()" class="search-button"
				id="download-button">Download Report</button>
		</div>
		<div style="float: right;">
			<label for="sortOrder">Sort by Name:</label> <select id="sortOrder"
				onchange="handleSortChange()">
				<option value="asc">A-Z</option>
				<option value="desc">Z-A</option>
			</select>
		</div>

		<%
		if (!bookingDetails.isEmpty()) {
		%>
		<table id="bookingTable">
			<thead>
				<tr>
					<th>Passengers Name</th>
					<th>Airline Name</th>
					<th>Flight Number</th>
					<th>Departure Airport</th>
					<th>Arrival Airport</th>
					<th>Journey Date</th>
					<th>No. of Seats</th>
					<th>Seat Class</th>
					<th>Amount</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (BookingDetails bookingDetail : bookingDetails) {
				%>
				<tr>
					<td><%=bookingDetail.getPassengerNames()%></td>
					<td><%=bookingDetail.getAirlineName()%></td>
					<td><%=bookingDetail.getFlightNumber()%></td>
					<td><%=bookingDetail.getDepartureAirport()%></td>
					<td><%=bookingDetail.getArrivalAirport()%></td>
					<td><%=bookingDetail.getDepartureDate()%></td>
					<td><%=bookingDetail.getNumberOfSeats()%></td>
					<td><%=bookingDetail.getSeatClass()%></td>
					<td><%=bookingDetail.getTotalAmount()%></td>
					<%
					if (bookingDetail.getBookingStatus().equals("Cancelled")) {
					%>
					<td class="status-cancelled">Cancelled</td>
					<%
					} else {
					%>
					<td class="status-confirmed">Confirmed</td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<p>No Booking Available</p>
		<%
		}
		%>
	</div>
</body>
</html>