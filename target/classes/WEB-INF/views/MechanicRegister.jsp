<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css" />
<link rel="stylesheet" href="../css/Regstyle.css" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/Images/road.png' />" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
.bgimg {
	background-color: #6da9d2;
	background-position: center;
	background-size: cover;
	height: 100%;
	width: 100%;
}

.pad {
	padding: 3rem !important;
	background-image:url(<c:url value='/Images/pic.jpeg' />);
	background-position: center;
	background-size: cover;
}

* {
	transition: all 1s !important;
}
.error{
	color:red;
}
</style>

<title>On Road Assistance - Mechanic Registration</title>
</head>
<body>
	<c:url var="action" value="/mechanic/registerMechanic"></c:url>
	<div class="container-fluid bgimg">
		<div class="container p-5">
			<div class="card  shadow">
				<div class="row ">
					<div class="col-md-6 pad d-md-block d-none"></div>
					<div class="col-md-6 ">
						<div class="card-body m-3 ">
							<div class="card-title">
								<h4 class="text-center">Mechanic Registration</h4>
							</div>
							<form:form method="post" action="${action }"
								commandName="mechanic" cssClass="mechanicForm">

								<div class="form-group">
									<form:label path="mechanicName" cssClass="mechanicLabel">
										<spring:message code="label.mechanicName" />
									</form:label>
									<form:input path="mechanicName" cssClass="form-control" />
									<form:errors path="mechanicName" cssClass="error"></form:errors>
								</div>
								<div class="form-group">

									<form:label path="password" cssClass="mechanicLabel">
										<spring:message code="label.password" />
									</form:label>
									<form:password path="password" cssClass="form-control" />
									<form:errors path="password" cssClass="error"></form:errors>
								</div>
								<div class="form-group">

									<form:label path="contactNumber" cssClass="mechanicLabel">
										<spring:message code="label.contactNumber" />
									</form:label>
									<form:input path="contactNumber" cssClass="form-control" />
									<form:errors path="contactNumber" cssClass="error"></form:errors>
								</div>
								<div class="form-group">

									<form:label path="email" cssClass="mechanicLabel">
										<spring:message code="label.email" />
									</form:label>
									<form:input path="email" cssClass="form-control" />
									<form:errors path="email" cssClass="error"></form:errors>

								</div>
								<div class="radio but">
									<form:label path="gender" cssClass="mechanicLabel">
										<spring:message code="label.gender" />
									</form:label>
									<br>
									<form:radiobutton path="gender" value="M" />
									Male
									<form:errors path="gender" cssClass="error"></form:errors>
									<form:radiobutton path="gender" value="F" />
									Female
									<form:errors path="gender" cssClass="error"></form:errors>
								</div>
								<div class="form-group">
									<br>
									<form:label path="dateOfBirth" cssClass="mechanicLabel">
										<spring:message code="label.dateOfBirth" />
									</form:label>
									<form:input path="dateOfBirth" placeholder="YYYY / MM / DD"
										cssClass="form-control" />
									<form:errors path="dateOfBirth" cssClass="error"></form:errors>
								</div>
								<div class="form-group">

									<form:label path="latitude" cssClass="mechanicLabel">
										<spring:message code="label.latitude" />
									</form:label>
									<form:input path="latitude" cssClass="form-control" />
									<form:errors path="latitude" cssClass="error"></form:errors>

								</div>
								<div class="form-group">
									<form:label path="longitude" cssClass="mechanicLabel">
										<spring:message code="label.longitude" />
									</form:label>
									<form:input path="longitude" cssClass="form-control" />
									<form:errors path="longitude" cssClass="error"></form:errors>
								</div>

								<div class="text-center">
								<button type="button" class="btn btn-success shadow-lg" onclick="window.location.href='/On_Road_Assistance/loginPage';">
										Go Back</button>
									<button type="submit" class="btn btn-success shadow-lg">
										Register</button>
								</div>

							</form:form>
							<br>
							<div class="text-center">
							<h5>Wish to Register as a Customer Instead?</h5>
									<button type="button" class="btn btn-success shadow-lg" onclick="window.location.href='/On_Road_Assistance/registerCustomer';">
										Register as a Customer</button>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>