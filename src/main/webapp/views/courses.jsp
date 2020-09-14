<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>App Academy - Courses</title>
</head>
<body>

	<h2>All courses</h2>

	<table id="table" class="tabla table table-striped">

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

</body>
</html>