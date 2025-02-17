<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String error = (String) request.getAttribute("error");
	if(error!=null) {
		out.print("<span style='color: red'>"+error+"</span>");
	}

%>