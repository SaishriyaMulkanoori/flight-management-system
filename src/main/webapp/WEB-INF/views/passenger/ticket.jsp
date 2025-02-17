<%@page import="cloud.wing.booking.entity.BookingDetails"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Flight Ticket</title>
<link href="../css/passenger/ticket.css" rel="stylesheet"
	type="text/css">

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #eaf2f8;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.ticket {
	width: 650px;
	background: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	overflow: hidden;
	margin-bottom: 20px;
}

.ticket-header {
	background: #1e90ff;
	color: white;
	padding: 20px;
	text-align: center;
}

.ticket-header h1 {
	margin: 0;
	font-size: 26px;
}

.ticket-header p {
	margin: 5px 0 0;
	font-size: 16px;
}

.ticket-body {
	padding: 20px;
	line-height: 1.8;
}

.ticket-row {
	display: flex;
	justify-content: space-between;
	margin-bottom: 15px;
}

.ticket-row div {
	width: 45%;
}

.ticket-row label {
	font-weight: bold;
	color: #333;
}

.ticket-row span {
	color: #555;
}

.ticket-footer {
	background: #f8f9fa;
	padding: 15px;
	text-align: center;
	font-size: 14px;
	color: #666;
}

.button-container {
	text-align: center;
}

#download-button {
	background-color: #1e90ff;
	color: white;
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
}

#download-button:hover {
	background-color: #1c86e2;
}
</style>

</head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<script>
   

   function downloadPDF() {
       const button = document.querySelector('#download-button');
       button.style.display = 'none';
       html2canvas(document.querySelector('.ticket')).then(canvas => {
           const imgData = canvas.toDataURL('image/png');
           const { jsPDF } = window.jspdf;
           const pdf = new jsPDF('landscape');
           pdf.addImage(imgData, 'PNG', 10, 10, 280, 200);
           pdf.save('flightTicket.pdf');
           button.style.display = 'block';
       });
   }
 
   
   
   
  
  </script>

<%
BookingDetails booking = (BookingDetails) request.getAttribute("ticket");
String[] array = booking.getPassengerNames().split(",");
%>
<body>
	<div id="ticket" class="ticket">
		<div class="ticket-header">
			<h1>✈️ Flight Ticket</h1>
			<p>
				Airline:
				<%=booking.getAirlineName()%>
			</p>
		</div>
		<div class="ticket-body">
			<div class="ticket-row">
				<div>
					<%
					for (int i = 0; i < array.length; i++) {
					%>
					<label>Passenger<%=i + 1%> Name:
					</label> <span><%=array[i]%></span>
					<%
					}
					%>
				</div>
				<div>
					<label>Seat Class:</label> <span><%=booking.getSeatClass()%></span>
				</div>
			</div>
			<div class="ticket-row">
				<div>
					<label>Departure Airport:</label> <span><%=booking.getDepartureAirport()%></span>
				</div>
				<div>
					<label>Arrival Airport:</label> <span><%=booking.getArrivalAirport()%></span>
				</div>
			</div>
			<div class="ticket-row">
				<div>
					<label>Departure Date:</label> <span><%=booking.getDepartureDate()%></span>
				</div>
				<div>
					<label>Arrival Date:</label> <span><%=booking.getArrivalDate()%></span>
				</div>
			</div>
			<div class="ticket-row">
				<div>
					<label>Number of Seats:</label> <span><%=booking.getNumberOfSeats()%></span>
				</div>
				<div>
					<label>Booking Status:</label> <span><%=booking.getBookingStatus()%></span>
				</div>
			</div>
		</div>
		<div class="ticket-footer">
			<p>
				Flight Code:
				<%=booking.getFlightNumber()%>
				| Gate: A12 | Terminal: 1
			</p>
			<p>Have a wonderful journey! 🌏</p>
		</div>
	</div>

	<div class="button-container">
		<button onclick="downloadPDF()" id="download-button">Download
			Ticket</button>
	</div>
	<br>
	<div class="button-container">
		<a href="/" id="download-button">Back To Homepage</a>
	</div>

</body>


</html>