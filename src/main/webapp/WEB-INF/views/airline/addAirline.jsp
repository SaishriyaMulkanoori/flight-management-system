<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Airline</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/airline/addAirline.css">
<script>
	function addAirlineValidateForm() {
		var name = document.getElementById("name").value;
		var namePattern=/^[A-Z][a-zA-Z ]*$/;
		var iataCode = document.getElementById("iataCode").value;
		var iataCodePattern = /^[A-Z]*$/;
		var country = document.getElementById("country").value;
		var countryPattern = /^[A-Z][a-zA-Z ]*$/;
		var contactEmail = document.getElementById("contactEmail").value;
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

		if (name == "") {
			alert("Airline Name must be filled out");
			return false;
		}else if (name.length > 50 || !namePattern.test(name)) {
			alert("Please check the length of airline name and no special charecters are allowed and should start with Capital");
			return false;
		}
		if (iataCode == "" || iataCode.length != 2) {
			alert("IATA Code must be exactly 2 characters long");
			return false;
		} else if (!iataCodePattern.test(iataCode)) {
			alert("IATA code must be in capital");
			return false;
		}
		if (country == "") {
			alert("Please fill out the country");
			return false;
		} else if (country.length > 40 || !countryPattern.test(country)) {
			alert("Please check the length of country and no special charecters are allowed and should start with Capital");
			return false;
		}
		if (contactEmail != "" && !emailPattern.test(contactEmail)) {
			alert("Invalid email format");
			return false;
		} else if (contactEmail.length > 50) {
			alert("Please check the length of the mail");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
 <%@ include file="header.jsp" %>
<main>
	<div class="container">
		<h2>Add New Airline</h2>
		<%
          String error=(String) request.getAttribute("error");
          if(error!=null && !error.isEmpty()){
        %>	
          <div class="error" style="color:red; margin-bottom:10px;">
            <%= error %>
          </div>
        <%  
          }
        
        %>
		<form action="${pageContext.request.contextPath}/airline/add"
			method="POST" onsubmit="return addAirlineValidateForm()">
			<label for="name">Airline Name:</label> <input type="text" id="name"  pattern="[A-Za-z\s]+" title="enter valid value"
				name="name" required> <label for="iataCode">IATA
				Code:</label> <input type="text" id="iataCode" name="iataCode"   pattern="[A-Za-z\s]+" title="enter valid value" required>
			<label for="country">Country:</label> <input type="text" id="country"  pattern="[A-Za-z\s]+" title="enter valid value"
				name="country" required> <label for="contactEmail">Contact
				Email:</label> <input type="email" id="contactEmail" name="contactEmail">
			<button type="submit">Add Airline</button>
		</form>
		<a href="${pageContext.request.contextPath}/airline/list">Back to
			List</a>
	</div>
	</main>
</body>
</html>
















