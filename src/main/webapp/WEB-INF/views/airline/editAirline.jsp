<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Airline</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/airline/editAirline.css">
  
</head>
<body>
     <%@ include file="header.jsp" %>
     <main>
  <div class="container">
    <h2>Edit Airline</h2>
    <%
          String error=(String) request.getAttribute("error");
          if(error!=null && !error.isEmpty()){
        %>	
          
        <%  
          }
        
      %>
    <form action="${pageContext.request.contextPath}/airline/update" method="POST">
      <input type="hidden" name="airlineId" value="${airline.airlineId}" />
      <label for="name">Airline Name:</label>
      <input type="text" id="name" pattern="[A-Za-z\s]+" title="enter valid value"  name="name" value="${airline.name}" required>
      <label for="iataCode">IATA Code:</label>
      <input type="text"  pattern="[A-Za-z\s]+" title="enter valid value" id="iataCode" name="iataCode" value="${airline.iataCode}" required>
      <label for="country">Country:</label>
      <input type="text"  pattern="[A-Za-z\s]+" title="enter valid value" id="country" name="country" value="${airline.country}" required>
      <label for="contactEmail">Contact Email:</label>
      <input type="email" id="contactEmail" name="contactEmail" value="${airline.contactEmail}">
      <button type="submit">Save Changes</button>
    </form>
    <a href="${pageContext.request.contextPath}/airline/list">Back to List</a>
  </div>
  </main>
</body>
</html>