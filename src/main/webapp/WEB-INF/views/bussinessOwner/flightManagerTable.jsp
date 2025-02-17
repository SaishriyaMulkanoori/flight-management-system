<!DOCTYPE html>
<%@page import="cloud.wing.admin.entity.FlightManager"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Manager</title>
    <link href="../css/bussinessOwner/flightManagerTable.css" rel="stylesheet"/>
</head>
<body>
       
       <%@include file= "header.jsp" %>
       
       <% 
		List<FlightManager> fmList= (List<FlightManager>)request.getAttribute("fmList");
      
		
	%>
	
	<script type="text/javascript">
	function changeAuth(event){ 
	 var confirmed = confirm("Are you sure?");
	if (!confirmed) { 
		event.preventDefault();
		} 
	} 
	</script>

    <div class="main">
        <h1>Flight Manager</h1>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Gender</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            
            <% 
			int serialNo = 0;
			for (FlightManager manager :fmList ) { 
				int id = manager.getId();
				boolean status = manager.isApproved;
		%>
                <tr>
                    <td data-label="Name"><%= manager.getName()  %></td>
                    <td data-label="Username"><%= manager.getUsername()  %></td>
                    <td data-label="Email"><%= manager.getEmail()  %></td>
                    <td data-label="Mobile"><%= manager.getMobile()  %></td>
                    <td data-label="Gender"><%= manager.getGender()  %>
                    <td><a  class="button"  onclick="changeAuth(event)" href="/flightManager/changeAuth/<%= id %>"> <%=status ? "Revoke Authority" : "Assign Authority" %> </a></td>
                </tr>
                
                
                <% } %>
                <!-- Add more rows as needed -->
            </tbody>
        </table>
    </div>
    
    
 

</body>
</html>