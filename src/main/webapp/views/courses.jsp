<!-- Including head -->
<jsp:include page="../includes/head.jsp">
	<jsp:param name="title" value="All courses " />
</jsp:include>
<!-- -------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<h2 class="my-3">
		<i class="fab fa-readme"></i>
		Our courses (${numberOfCourses} currently available)
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

			<c:forEach items="${dbCourses}" var="course">

				<tr>
					<td hidden="">${course.id}</td>
					<td>${course.name}

						<%-- Check if the course have >=3 enrolled students to show a flare --%>
						<c:choose>

							<c:when test="${course.students_enrolled>='3'}">
								<%-- >=3: Show the flare --%>
								<span style="color: #ff0000;">
									<i id="flare" class="fab fa-hotjar" title="Hot course"></i>
								</span>
							</c:when>

							<c:otherwise>

								<span style="color: #ff0000;">
									<%-- <3: No flare --%>

								</span>

							</c:otherwise>

						</c:choose>
						<%-- End check --%>

					</td>
					<td>${course.identifier}</td>
					<td>${course.hours}</td>
					<td>${course.professor.name} ${course.professor.surname}</td>
					<td>${course.students_enrolled}</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

	<hr class="my-3">

	<a href="views/login.jsp" class="mt-3 mb-5 btn btn-info">Log in</a>

</div>

<!-- Including foot -->
<%@include file="../includes/foot.jsp"%>
<!-- -------------- -->