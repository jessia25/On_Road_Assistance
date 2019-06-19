<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>On Road Assistance - Request Service</title>
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css" />
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/Images/road.png' />" />
<style>
body {
	width: 100vw;
	height: 100vh;
	overflow: hidden;
}

.object {
	z-index: -10;
	position: absolute;
}

input::placeholder {
	color: #d3d3d3 !important;
	text-align: center;
}

input {
	border: none !important;
	outline: none !important;
	border-bottom: 2px solid white !important;
	color: white !important;
	background: transparent !important;
	width: 500px !important;
	text-align: center;
}

.obj {
	z-index: 10;
	position: relative;
}

.form-group {
	text-align: center !important;
}

footer {
	left: 0;
	right: 0;
	bottom: 0;
	position: fixed;
}

.error {
	color: gold;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="/On_Road_Assistance/customer/home"><img
			src="<c:url value='/Images/icon.png' />" alt="logo"
			class="img-thumbnail" width="40px" height="40px" /> On road
			assistance</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="container">
			<div class="collapse navbar-collapse text-center"
				id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="/On_Road_Assistance/customer/home">Home <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="/On_Road_Assistance/customer/request">request</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/On_Road_Assistance/activeRequests">active request</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/On_Road_Assistance/logout">logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Navbar Ends -->

	<!-- main -->
	<main> <video src="<c:url value='/Images/bgvid.mp4'/>" autoplay
		muted loop class="object"></video>

	<div
		class="d-flex flex-column justify-content-center-align-items-center">
		<div class="shadow   p-5 mt-5 text-center container">
			<c:url var="action" value="/customer/search"></c:url>
			<form:form method="post" action="${action }"
				commandName="customerRequest" cssClass="customerForm p-4 obj" novalidate="novalidate">
				<div class="form-group">
					<center>

						<form:input path="customerId" readonly="true" size="8"
							disabled="true" cssClass="form-control" />
						<form:hidden path="customerId" />
					</center>
				</div>
				<div class="form-group">
					<center>


						<form:input path="customerName" readonly="true"  cssClass="form-control" />
						<form:errors path="customerName" cssClass="error"></form:errors>
					</center>
				</div>
				<div class="form-group">
					<center>


						<form:input path="contactNumber" readonly="true" cssClass="form-control"
							placeholder="Contact Number" />
						<form:errors path="contactNumber" cssClass="error"></form:errors>
					</center>
				</div>
				<c:if test="${!empty customerRequest.email }">
				<div class="form-group">
					<center>

						<form:input type="email" path="email" cssClass="form-control"
							placeholder="Email ID" required="required" readonly="true"/>
						<form:errors path="email" cssClass="error"></form:errors>
					</center>
				</div>
				</c:if>
				<div class="form-group">
					<center>

						<form:input path="location" cssClass="form-control"
							placeholder="Enter the Location" required="required" />
						<form:errors path="location" cssClass="error"></form:errors>
					</center>
				</div>
				<div class="form-group">
					<center>

						<form:input path="latitude" cssClass="form-control"
							placeholder="Enter the Latitude" required="required" />
						<form:errors path="latitude" cssClass="error"></form:errors>
					</center>
				</div>
				<div class="form-group">
					<center>

						<form:input path="longitude" cssClass="form-control"
							placeholder="Enter the Longitude" required="required" />
						<form:errors path="longitude" cssClass="error"></form:errors>
					</center>
				</div>

				<button type="submit" class="btn btn-primary">Search</button>
			</form:form>
		</div>
	</div>
	</main>
	<!-- main ends -->

	<!-- footer -->
	<footer class="bg-primary text-center">
		<p class="h5 text-light p-3">All Rights Reserved &copy;2019</p>
	</footer>
	<!-- footer ends -->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
		<script type="text/javascript" >
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	    }; 
</script>
		
</body>
</html>
