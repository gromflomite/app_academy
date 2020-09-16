<!-- Including head -->
<jsp:include page="../includes/head.jsp">
	<jsp:param name="title" value="Login " />
</jsp:include>
<!-- -------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container w-25">

	<h2 class="mt-5">Log in</h2>

	<form action="login" method="post">

		<div class="form-group my-3">
			<label for="username">Username:</label>
			<input type="text" name="userName" class="form-control" id="username" placeholder="Enter your username" required autofocus>
		</div>

		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" name="userPassword" class="form-control" id="password" placeholder="Enter your password" required>
		</div>
		
		<button type="submit" class="btn btn-info mt-3">Log in</button>

	</form>

	<br>

</div>

<!-- Including foot -->
<%@include file="../includes/foot.jsp"%>
<!-- -------------- -->