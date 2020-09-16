<!-- Including head -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Professor area " />
</jsp:include>
<!-- -------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="my-5 container">

	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Welcome, ${userLoginDetails.name}!!</h1>
			<p class="mt-4">Name: ${userLoginDetails.name} ${userLoginDetails.surname}</p>
		</div>
	</div>

	<h2 class="my-3">Your courses</h2>

	<table id="table" class="table table-striped">
		<!-- id used for Datatables plugin -->

		<thead>

			<tr>
				<td hidden="">Course ID</td>
				<td>Name</td>
				<td>Identifier</td>
				<td>Duration (hours)</td>
				<td>Options</td>
			</tr>

		</thead>

		<tbody>

			<c:forEach items="${coursesByProfessorId}" var="course">

				<tr>
					<td hidden="">${course.id}</td>
					<td>${course.name}</td>
					<td>${course.identifier}</td>
					<td>${course.hours}</td>
					<td>
						<a href="deleteCourse?idCourseToDelete=${course.id}" onclick="confirmDelete('${course.name}')">
							<i class="fas fa-trash fa-1x" title="Delete this course"></i>
						</a>
					</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

	<hr class="my-3">

	<a class="btn btn-link ml-0 pl-0 my-1" data-toggle="collapse" href="#edit-user-details" role="button">Create new course</a>

	<div class="collapse" id="edit-user-details">

		<h2>Create a new course</h2>

		<form id="newCourseForm" action="createCourse" method="post">

			<div class="form-group">
				<label for="courseName">Course name:</label>
				<input type="text" name="courseName" class="form-control" id="courseName" placeholder="Course name" required>
			</div>

			<div class="form-group">
				<label for="courseIdentifier">Identifier:</label>
				<input type="text" name="courseIdentifier" class="form-control" id="courseIdentifier" placeholder="Identifier" required>
			</div>

			<div class="form-group">
				<label for="courseHours">Duration:</label>
				<input type="text" name="courseHours" class="form-control" id="courseHours" placeholder="Hours" required>
			</div>

			<button type="submit" class="btn btn-info">Add course</button>

		</form>

	</div>

	<hr class="my-3">

	<a href="logout" class="my-3 btn btn-warning">Log out</a>

</div>

<!-- Including foot -->
<%@include file="../../includes/foot.jsp"%>
<!-- -------------- -->