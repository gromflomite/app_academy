<!-- Including head -->
<jsp:include page="../includes/head.jsp">
	<jsp:param name="title" value="All courses" />
</jsp:include>
<!-- -------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	
	<h2 class="my-5">All courses</h2>

	<table id="table" class="table table-striped"> <!-- id used for Datatables plugin -->

		<thead>

			<tr>
				<td>Course ID</td>
				<td>Name</td>
				<td>Identifier</td>
				<td>Duration (hours)</td>
				<td>Professor</td>
			</tr>

		</thead>

		<tbody>

			<c:forEach items="${dbCourses}" var="course">

				<tr>
					<td>${course.id}</td>
					<td>${course.name}</td>
					<td>${course.identifier}</td>
					<td>${course.hours}</td>
					<td>${course.professor.name} ${course.professor.surname}</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>

<!-- Including foot -->
<%@include file="../includes/foot.jsp"%>
<!-- -------------- -->