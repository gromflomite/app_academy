<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// Redirect to the controller at charge
	response.sendRedirect(request.getContextPath() + "/courses"); // Using getContextPath() to avoid hardcoding the context path
%>

