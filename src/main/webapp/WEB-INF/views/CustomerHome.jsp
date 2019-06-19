<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/Images/road.png' />" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css" />

<title>On Road Assistance - Home</title>
<style>
body {
	width: 100vw;
	height: 100vh;
}

.trans {
	display: inline-block;
	position: relative;
}

.trans::after {
	content: "";
	position: absolute;
	width: 100%;
	transform: scaleX(0);
	height: 2px;
	bottom: 0;
	left: 0;
	background-color: crimson;
	transform-origin: bottom right;
	transition: transform 0.25s ease-out;
}

.trans:hover::after {
	transform: scaleX(1);
	transform-origin: bottom left;
}

footer {
	left: 0;
	right: 0;
	bottom: 0;
	position: fixed;
}
.im{
	display:block;
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
					<li class="nav-item active"><a class="nav-link"
						href="/On_Road_Assistance/customer/home">Home <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
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

	<!-- Main  -->
	<main class="m-5">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 p-2">
				<div class="shadow p-5">
					<img src="<c:url value='/Images/repair.jpg' />" alt="Car"
						class="img-fluid" />
				</div>
			</div>
			<div class="col-lg-4 p-2">
				<div class="shadow p-5 text-center">
					<h5 class="text-danger trans pb-2">profile</h5>
					
					<c:if test="${customer.gender == 'M' }">
						<img class="im mx-auto mb-3" alt="profpic" src="<c:url value='/Images/boy.png' />"
							width="150px" height="150px">
					</c:if>
					<c:if test="${customer.gender == 'F' }">
						<img class="im mx-auto mb-3" alt="profpic" src="<c:url value='/Images/girl.png' />"
							width="150px" height="150px">
					</c:if>
					<h5>${customer.customerName }</h5>
					<h5>${customer.customerId }</h5>
					<h5>${customer.email }</h5>
					<h5>${customer.contactNumber }</h5>
				</div>
			</div>
		</div>
	</div>
	</main>

	<!-- Main Ends -->

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