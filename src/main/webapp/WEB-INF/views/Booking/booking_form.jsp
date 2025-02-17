
<%@page import="cloud.wing.flight.entity.Flight"%>
<%@page import="cloud.wing.passenger.entity.Passenger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Home</title>
<link href="../css/passenger/bookingPage.css" rel="stylesheet" type="text/css">
<% Flight flight = (Flight) request.getAttribute("flight"); 
Passenger passenger = (Passenger) session.getAttribute("loggedInUser");
int id = passenger.PassengerId();
%>
<style>
    .passenger-name {
        margin-bottom: 10px;
    }
    .button-container {
        margin-top: 20px;
    }
</style>
</head>

<script>
function handleClick(){
    document.getElementById('bookingForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const seatClass = document.getElementById('seatClass').value;
        const numSeats = parseInt(document.getElementById('numberOfSeats').value);
        const availableSeats = {
            economy: <%= flight.getAvailableSeatsEconomy()%>,
            business: <%= flight.getAvailableSeatsBusiness()%>,
        };

        if (numSeats > availableSeats[seatClass]) {
            alert('The number of seats requested exceeds the available seats in ' + seatClass + ' class.');
        } else {
            // Submit the form using POST method and handle the response
            const form = event.target;
            const formData = new FormData(form);

            fetch(form.action, {
                method: form.method,
                body: formData
            }).then(response => response.text())
              .then(redirectUrl => {
                  window.location.href = redirectUrl;
              }).catch(error => {
                  console.error('Error:', error);
                  alert('An error occurred while submitting the form.');
              });
        }
    });
}

function updateTotalAmount() {
    var seatClass = document.getElementById("seatClass").value;
    var numberOfSeats = document.getElementById("numberOfSeats").value;
    var totalAmount = document.getElementById("totalAmount");

    var pricePerSeat = 0;
    if (seatClass === "business") {
        pricePerSeat = <%= flight.getPriceBusiness() %>;
    } else if (seatClass === "economy") {
        pricePerSeat = <%= flight.getPriceEconomy() %>;
    }

    var total = (pricePerSeat * numberOfSeats);
    totalAmount.value = total.toFixed(2);

    updatePassengerNames(numberOfSeats);
}

function updatePassengerNames(numSeats) {
    if (numSeats > 0 && numSeats <= 5) {
        var passengerNamesContainer = document.getElementById("passengerNamesContainer");
        passengerNamesContainer.innerHTML = "";

        for (let i = 1; i <= numSeats; i++) {
            var label = document.createElement("label");
            label.setAttribute("for", "passengerName" + i);
            label.textContent = "Passenger Name " + i + ":";

            var input = document.createElement("input");
            input.type = "text";
            input.id = "passengerName" + i;
            input.name = "passengerNames[]";
            input.required = true;
            input.classList.add("passenger-name");

            passengerNamesContainer.appendChild(label);
            passengerNamesContainer.appendChild(input);
            passengerNamesContainer.appendChild(document.createElement("br"));
        }
    }
}
</script>

<body>
<header>
    <div class="logo">
        <img src="../images/logo.png" alt="Logo" class="logo-img">
        CloudWing
    </div>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/passenger/profile">Profile</a></li>
            <li><a href="/booking/allBookings/<%=id%>">Booking History</a></li>
            <li><a href="/booking/upcomingFlight/<%=id%>">Upcoming Flight</a></li>
            <li><a href="/passenger/logout">Logout</a></li>
        </ul>
    </nav>
</header>
<div class="body">
    <h2>Flight Booking Form</h2>
    <form action="/passenger/book" id="bookingForm" method="post">
    <input type="hidden" id="airline" name="flightId" value="<%=flight.getFlightId() %>">
    
    <label for="flightNumber">Flight Number:</label>
    <input type="text" id="flightNumber" value="<%=flight.getFlightNumber() %>" readonly name="flightNumber" required><br><br>
    
    <label for="departureAirport">Departure Airport:</label>
    <input type="text" id="departureAirport" value="<%= flight.getDepartureAirport() %>" readonly name="departureAirport" required><br><br>
    
    <label for="arrivalAirport">Arrival Airport:</label>
    <input type="text" id="arrivalAirport" value="<%= flight.getArrivalAirport() %>" name="arrivalAirport" required><br><br>
    
    <label for="journeyDate">Journey Date:</label>
    <input type="text" id="journeyDate" value="<%=flight.getDepartureDate().toString()%>" readonly name="departureDate" required><br><br>
    
    <label for="seatClass">Seat Class:</label>
    <select id="seatClass" name="seatClass" onchange="updateTotalAmount()" required>
        <option value="business">Business</option>
        <option value="economy">Economy</option>
    </select><br><br>
    
    <label for="numberOfSeats">Number of Seats:</label>
    <input type="number" id="numberOfSeats" name="numberOfSeats" max="5" min="1" value="0" onchange="updateTotalAmount()" required><br><br>
    
    <label for="totalAmount">Total Amount:</label>
    <input type="text" id="totalAmount" value="<%=flight.getPriceBusiness() %>" name="totalAmount" readonly><br><br>
    
    <div id="passengerNamesContainer"></div>
    
    <div class="button-container">
        <button type="submit" onClick="handleClick()">Book Now</button>
    </div>
</form>
</div>
<footer>
    <p>&copy; 2024 Flight Reservation System. All rights reserved.</p>
</footer>
</body>
</html>