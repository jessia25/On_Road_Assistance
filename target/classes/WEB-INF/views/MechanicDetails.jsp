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
<title>On Road Assistance - Mechanic Details</title>
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
	color: white !important;
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

a {
	color: white !important;
}

.ipt {
	color: white !important;
}

.obj {
	z-index: 10;
	position: relative;
}

.form-group {
	text-align: center !important;
}
label{
	color:white;
}
footer {
	left: 0;
	right: 0;
	bottom: 0;
	position: fixed;
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
			<c:if test="${!empty mechanic }">
				<label>Mechanic Name : ${mechanic.mechanicName } </label>
				<br>
				<label>Mechanic ID : ${mechanic.mechanicId } </label>
				<br>
				<label>Mechanic Number : ${mechanic.contactNumber } </label>
				<br>
				<label>Latitude : ${mechanic.latitude } </label>
				<br>
				<label>Longitude : ${mechanic.longitude } </label>
				<br>
				<button class="btn btn-primary"
					onclick="window.location.href='/On_Road_Assistance/sendRequest/${requestId}/${mechanic.mechanicId }';">
					<strong>Send Request</strong>
				</button>
				
				<button class="btn btn-primary"
					onclick="window.location.href='/On_Road_Assistance/customer/relistMechanic/${requestId}';">
					<strong>Cancel</strong>
				</button>
				<br>
			</c:if>
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
	<script>
		$(function() {
			$('[data-toggle="tooltip"]').tooltip()
		})
	</script>
	<script type="text/javascript" >
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	    }; 
</script>
</body>
</html>