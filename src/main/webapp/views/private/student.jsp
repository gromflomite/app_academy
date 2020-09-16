<!-- Including head -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Student area "/>
</jsp:include>
<!-- -------------- -->

<div class="my-5 container">

	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Welcome, ${userLoginDetails.name}!!</h1>
			<p class="lead">You have assigned a role of student</p>
		</div>
	</div>

</div>

<!-- Including foot -->
<%@include file="../../includes/foot.jsp"%>
<!-- -------------- -->