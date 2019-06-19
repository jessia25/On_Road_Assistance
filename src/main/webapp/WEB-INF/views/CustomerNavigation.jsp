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
<title>On Road Assistance - Navigation</title>
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
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

.obj {
	z-index: 10;
	position: relative;
}

.form-group {
	text-align: center !important;
}

h5 {
	color: white;
}

footer {
	left: 0;
	right: 0;
	bottom: 0;
	position: fixed;
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

.outer {
	display: flex;
	flex-direction: column;
	justify-content: center;
	min-height: 15vh;
	align-items: center;
}

.inner {
	background-color: #eee;
	padding: 1rem;
	display: flex;
}

.star {
	font-size: 2rem;
	padding-right: 0.5rem;
	padding-left: 0.5rem;
	color: gray;
	transition: all 0.3s ease;
}

.star:hover {
	text-decoration: none;
	color: #d6c902;
}

.star.hover {
	color: #d6c902;
}

.star.hovered {
	color: #d6c902;
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
					<li class="nav-item"><a class="nav-link"
						href="/On_Road_Assistance/customer/request">request</a></li>
					<li class="nav-item active"><a class="nav-link"
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
		<div class="p-5 mt-5 text-center container">
			<div class="row">
				<div class="col-lg-6">
					<img src="<c:url value='/Images/location.png'/>" width="400px"
						height="400px" class="mb-3" />
					<c:if test="${!empty time }">
						<h5>estimated time for arrival : ${time}</h5>
					</c:if>
					<c:if test="${empty time }">
						<h5>thanking you for using our service. Please feel free to
							rate the mechanic's service</h5>
					</c:if>
				</div>
				<div class="col-lg-6">
					<div class="col">
						<div class="p-5 text-center">
							<h5 class="text-danger trans pb-2">Mechanic details</h5>
							<h5>${mechanic.mechanicName }</h5>
							<h5>${mechanic.mechanicId }</h5>
							<h5>${mechanic.email }</h5>
							<h5>${mechanic.contactNumber }</h5>
							<c:if test="${empty time }">
								<button class="btn btn-primary" data-toggle="modal"
									data-target=".exampleModal">Rating</button>
							</c:if>
						</div>
					</div>
					<c:if test="${empty time }">
						<div class="modal fade exampleModal" tabindex="-1" role="dialog"
							aria-labelledby="mySmallModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="outer">
										<h1 id="rating"></h1>
										<div class="inner">
											<a href="#" class="fas fa-star star"></a> <a href="#"
												class="fas fa-star star"></a> <a href="#"
												class="fas fa-star star"></a> <a href="#"
												class="fas fa-star star"></a> <a href="#"
												class="fas fa-star star"></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
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
		$('.star')
				.each(
						function(index) {

							const prevAll = $(this).prevAll();
							const currItem = $(this);
							$(this).on('mouseover', function() {

								if (currItem.hasClass('hovered')) {
									$(this).prevAll().removeClass('hover');
									currItem.removeClass('hover');
								} else {
									$(this).prevAll().addClass('hover');
								}
							});

							currItem.on('mouseout', function() {

								$(this).removeClass('hover')
								$(this).prevAll().removeClass('hover');

							});

							currItem
									.click(function(index) {
										var curIndex = $(this).index() + 1;
										if (curIndex < 2) {
											$('#rating').text(
													curIndex + ' Star')
										} else {
											$('#rating').text(
													curIndex + ' Stars')
										}
										prevAll.addClass('hovered');
										$(this).addClass('hovered');
										$(this).nextAll()
												.removeClass('hovered');
										alert("Thanks for your feedback");
										window.location.href = "/On_Road_Assistance/customer/rating/${requestId}/${mechanic.mechanicId}/"
												+ curIndex;
									});

						});
	</script>
	<script type="text/javascript" >
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	    }; 
</script>
</body>
</html>
