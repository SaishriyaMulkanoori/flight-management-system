<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Flight Manager Dashboard</title>
<link href="../css/flightManager/dashboard.css" rel="stylesheet" />



</head>
<%
Map<String, Integer> map = (Map) request.getAttribute("data");
%>
<body>
	<%@include file="header.jsp"%>
	<main>
		<section class="dashboard-overview">
			<h2>Welcome, Manager</h2>
			<div class="cards">
				<div class="card">
					<h3>Total Flights</h3>
					<p><%=map.get("flightCount")%></p>
				</div>
				<div class="card">
					<h3>Active Flights</h3>
					<p><%=map.get("activeFlight")%></p>
				</div>
				<div class="card">
					<h3>Total Passengers</h3>
					<p><%=map.get("passsengerCount")%></p>
				</div>
				<div class="card">
					<h3>Total Revenue</h3>
					<p><%=map.get("revenue")%></p>
				</div>
			</div>
		</section>
		<section class="quick-actions">
			<h2>Quick Actions</h2>
			<div class="actions">
				<a href="/flight/add" class="action-button">Add Flights</a> <a
					href="/flight/list" class="action-button">Manage Flights</a> <a
					href="/booking/bookingReport" class="action-button">View
					Booking Details</a>

			</div>
		</section>
	</main>
	<footer>
		<p>&copy; 2024 Flight Reservation System. All rights reserved.</p>
	</footer>
</body>
</html>
