<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty feedback}">

	<div class="alert alert-${feedback.type} alert-dismissible fade show" role="alert">

		${feedback.message}

		<button type="button" class="close" data-dismiss="alert" aria-label="Close">

			<span aria-hidden="true">&times;</span>

		</button>

	</div>

	<!--  As we are using redirections, we have to set Feedback to null after show it in the view -->
	<%
	    session.setAttribute("feedback", null);
	%>

</c:if>
