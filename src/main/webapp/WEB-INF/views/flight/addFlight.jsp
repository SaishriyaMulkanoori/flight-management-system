<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cloud.wing.flight.entity.Airline"%>
<%@ page import="cloud.wing.admin.entity.FlightManager"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Flight</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/flight/addFlight.css">
<script>
    function validateForm() {
    	
        var flightNumber = document.getElementById("flightNumber").value;
        var departureDate = new Date(document.getElementById("departureDate").value);
        var arrivalDate = new Date(document.getElementById("arrivalDate").value);
        var totalSeatsEconomy = parseInt(document.getElementById("totalSeatsEconomy").value);
        var totalSeatsBusiness = parseInt(document.getElementById("totalSeatsBusiness").value);
        var priceEconomy = parseFloat(document.getElementById("priceEconomy").value);
        var priceBusiness = parseFloat(document.getElementById("priceBusiness").value);
        var airlineSelect = document.getElementById("airline");
        var selectedAirline = airlineSelect.options[airlineSelect.selectedIndex].text;
        var iataCode = selectedAirline.split(' ')[0]; 

        var flightNumberPattern = new RegExp("^" + iataCode + "\\d{4}$");
        if (!flightNumberPattern.test(flightNumber)) {
            alert("Flight number must start with the IATA code (" + iataCode + ") followed by digits.");
            return false;
        }

        if (flightNumber.length > 7) {
            alert("Please check the length of flight number");
            return false;
        }

        if (departureDate >= arrivalDate) {
            alert("Arrival date & time must be after departure date & time");
            return false;
        }

       
        
        if(totalSeatsEconomy<=0){
        	alert("Number of economy seats must be greater than 0")
        	return false
        }
        
        if(totalSeatsBusiness<=0){
        	alert("Number of bussiness seats must be greater than 0")
        	return false;
        }

        if (isNaN(priceEconomy) || priceEconomy < 0 || priceEconomy > 9999999.99) {
            alert("please enter the valid price for economy class");
            return false;
        }

        if (isNaN(priceBusiness) || priceBusiness < 0 || priceBusiness > 9999999.99) {
            alert("please enter the valid price for business class");
            return false;
        }
               
           

        return true;
    }
</script>
</head>
<body>
<%@ include file="header.jsp" %>
    <div class="container">
        <h2>Add Flight</h2>
        
        
        <%
       
           FlightManager logInflightManager =(FlightManager) session.getAttribute("loggedInFm");
          String error=(String) request.getAttribute("error");
          if(error!=null && !error.isEmpty()){
        %>	
          <div class="error" style="color:red; margin-bottom:10px;">
            <%= error %>
          </div>
        <%  
          }
        
        %>
        <form action="${pageContext.request.contextPath}/flight/add" method="POST" onsubmit="return validateForm()">
            <label for="airline">Airline:</label>
            <select id="airline" name="airline.airlineId" required>
                <option value="">select</option>
                <% List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");                   
                   for (Airline airline : airlines) { %>   
                <option value="<%=airline.getAirlineId()%>"><%=airline.getIataCode()%> <%=airline.getName()%></option>
                <% } %>
            </select>
            <label for="flightNumber">Flight Number:</label>
            <input type="text" id="flightNumber" name="flightNumber" maxlength="10" required>
            
            <label for="departureAirport">Departure Airport:</label>
            <input type="text" id="departureAirport" name="departureAirport" maxlength="50" required>
            
            <label for="arrivalAirport">Arrival Airport:</label>
            <input type="text" id="arrivalAirport" name="arrivalAirport" maxlength="50" required>
            
            <label for="departureDate">Departure Date and Time:</label>
            <input type="datetime-local"  min=<%= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date()) %> id="departureDate" name="departureDate" required>
            
            <label for="arrivalDate">Arrival Date and Time:</label>
            <input type="datetime-local" min=<%= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date()) %> id="arrivalDate" name="arrivalDate" required>
            
           
            
            <label for="totalSeatsEconomy">Total Seats in Economy:</label>
            <input type="number" id="totalSeatsEconomy"name="totalSeatsEconomy" min="0" required>
            
            <label for="totalSeatsBusiness">Total Seats in Business:</label>
            <input type="number" id="totalSeatsBusiness" name="totalSeatsBusiness" min="0" required>
            
            <label for="priceEconomy">Price of Economy:</label>
            <input type="number" min="1000" step="0.01" id="priceEconomy" name="priceEconomy" min="0" required>
            
            <label for="priceBusiness">Price of Business:</label>
            <input type="number" min="2000" step="0.01" id="priceBusiness" name="priceBusiness" min="0" required>
            
            <label for="flightStatus">Flight Status:</label>
            <select id="flightStatus" name="flightStatus" required>
                <option value="">select</option>
                <option value="Active">Active</option>
               
            </select>
            
            <%if(logInflightManager !=null) {%>
            <input type="hidden" value=<%=logInflightManager.getId() %> name="flightManager.id">
            
            <%}  else {%>
            
            <label for="flightManager">Flight Manager:</label>
            <select id="flightManager" name="flightManager.id" required>
                <option value="">select</option>
                <% List<FlightManager> flightManagers = (List<FlightManager>) request.getAttribute("flightManagers");
                   for (FlightManager flightManager : flightManagers) { %> 
                <option value="<%=flightManager.getId()%>"><%=flightManager.getName()%></option>
                <% } %>
            </select>
            
            <%} %>
            <button type="submit">Add Flight</button>
        </form>
    </div>
</body>
</html>