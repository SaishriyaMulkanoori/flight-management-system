<%@page import="cloud.wing.admin.entity.BussinessOwner"%>
<%@page import="cloud.wing.admin.entity.FlightManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<link href="../css/flightManager/header.css" rel="stylesheet"/>

<% FlightManager manager=(FlightManager) session.getAttribute("loggedInFm");
BussinessOwner owner = (BussinessOwner) session.getAttribute("owner");

String home= "";
String logout="";
if(owner!=null){
	home="/bussinessOwner/home";
	logout="/bussinessOwner/logout";
}
else{
	home="/flightManager/home";
	logout="/flightManager/logout";
}

%>

<header>
    <div class="logo">
        <img src="../images/logo.png" alt="Logo" class="logo-img" /> CloudWing
    </div>
    <nav>
        <ul>
            <li><a href="<%=home %>">Home</a></li>
           <%if(owner==null){ %>    <li><a href="/flightManager/profile">Profile</a></li> <%} %>
          
            <li><a href="<%=logout%>">Logout</a></li>
        </ul>
    </nav>
</header>