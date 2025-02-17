<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cloud.wing.flight.entity.Airline"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Airline List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/airline/airlineList.css">
<script>
    function confirmDelete(event) {
        if (!confirm("Are you sure you want to delete this airline?")) {
            event.preventDefault();
        }
    }
</script>
</head>
<body>
     <%@ include file="header.jsp" %>
<main>
    <div class="container">
        <h1>List of all Airlines</h1>
        <a class="add-airline" href="${pageContext.request.contextPath}/airline/add">Add Airline</a>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>IATA Code</th>
                    <th>Country</th>
                    <th>Contact Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");
                   for (Airline airline : airlines) { %>
                <tr>
                    <td><%=airline.getName()%></td>
                    <td><%=airline.getIataCode()%></td>
                    <td><%=airline.getCountry()%></td>
                    <td><%=airline.getContactEmail()%></td>
                    <td>
                        
                        <div class="tooltip">
                            <a href="/airline/remove/<%=airline.getAirlineId() %>" onclick="confirmDelete(event)">Remove</a>
                            <span class="tooltiptext">Remove this airline</span>
                        </div>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    </main>
</body>
</html>
