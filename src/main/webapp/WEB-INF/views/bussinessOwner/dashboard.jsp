<%@page import="java.util.Map"%>
<%@page import="cloud.wing.admin.entity.BussinessOwner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Business Owner Dashboard</title>
<link href="../css/bussinessOwner/dashboard.css" rel="stylesheet" />


</head>
<body>

	<%
	BussinessOwner owner = (BussinessOwner) session.getAttribute("owner");
	%>
	<%
	Map<String, Integer> map = (Map) request.getAttribute("countData");
	%>
	<%@ include file="header.jsp"%>

	<main>
		<section class="overview">
			<h1>Welcome, Business Owner</h1>
			<p>Manage your Flight Managers, Flights, and System Reports
				seamlessly.</p>
		</section>


		<section class="stats">
			<div class="stat-box">
				<h2>Total Flights</h2>
				<p id="total-flights"><%=map.get("flightCount")%></p>
			</div>
			<div class="stat-box">
				<h2>Total Managers</h2>
				<p id="total-managers"><%=map.get("managerCount")%></p>
			</div>
			<div class="stat-box">
				<h2>Total Passengers</h2>
				<p id="total-passengers"><%=map.get("passengerCount")%></p>
			</div>
		</section>


		<section class="actions">
			<div class="card">
				<h3>Manage Flight Managers</h3>
				<p>Approve or revoke access for Flight Managers.</p>
				<a href="/bussinessOwner/viewFlightManager" class="button">Manage
					Flight Managers</a>
			</div>

			<div class="card">
				<h3>Manage Airlines</h3>
				<p>Add or modify Airlines.</p>
				<a href="/airline/list" class="button">Manage Airline</a>
			</div>

			<div class="card">
				<h3>Manage Flights</h3>
				<p>Add, modify, or remove flights from the system.</p>
				<a href="/flight/list" class="button">Manage Flights</a>
			</div>

			<div class="card">
				<h3>View Flight Report</h3>
				<p>View flights report</p>
				<a href="/flight/flightReport" class="button">View details</a>
			</div>

			<div class="card">
				<h3>View Booking Report</h3>
				<p>View bookings report</p>
				<a href="/booking/bookingReport" class="button">View details</a>
			</div>
		</section>
	</main>

	<footer>
		<p>&copy; 2024 Flight Reservation System. All rights reserved.</p>
	</footer>
</body>
</html>
