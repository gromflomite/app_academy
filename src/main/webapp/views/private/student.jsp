<!-- Including head -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Student area " />
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

	<h2 class="my-3">
		<i class="fas fa-bookmark"></i>
		Enrolled courses
	</h2>

	<table id="table" class="table table-striped">
		<!-- id used for Datatables plugin -->

		<thead>

			<tr>
				<td hidden="">Course ID</td>
				<td>Name</td>
				<td>Identifier</td>
				<td>Hours</td>
				<td>Professor</td>
				<td>Students</td>
			</tr>

		</thead>

		<tbody>

			<c:forEach items="${coursesStudentEnrolled}" var="course">

				<tr>
					<td hidden="">${course.id}</td>
					<td>${course.name}</td>
					<td>${course.identifier}</td>
					<td>${course.hours}</td>
					<td>${course.professor.name}${course.professor.surname}</td>
					<td>${course.students_enrolled}</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

	<hr class="my-5">


	<h2 class="mt-3 mb-3">
		<i class="far fa-bookmark"></i>
		Available courses
	</h2>

	<table id="table" class="table table-striped">
		<!-- id used for Datatables plugin -->

		<thead>

			<tr>
				<td hidden="">Course ID</td>
				<td>Name</td>
				<td>Identifier</td>
				<td>Duration (hours)</td>
				<td>Professor</td>
				<td>Students</td>
				<td>Enroll</td>

			</tr>

		</thead>

		<tbody>

			<c:forEach items="${coursesStudentAvailable}" var="courseAvailable">

				<tr>
					<td hidden="">${course.id}</td>
					<td>${courseAvailable.name}</td>
					<td>${courseAvailable.identifier}</td>
					<td>${courseAvailable.hours}</td>
					<td>${courseAvailable.professor.name}${courseAvailable.professor.surname}</td>
					<td>${courseAvailable.students_enrolled}</td>
					<td>
						<a href="enroll?idCourseToEnroll=${courseAvailable.id}" onclick="confirmEnroll('${courseAvailable.name}')">
							<span style="color: #008000;">
								<i class="fas fa-user-plus" title="Enroll on this course"></i>
							</span>
						</a>
					</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>




	<hr class="my-3">

	<a href="logout" class="my-3 btn btn-warning">Log out</a>


</div>

<!-- Including foot -->
<%@include file="../../includes/foot.jsp"%>
<!-- -------------- -->