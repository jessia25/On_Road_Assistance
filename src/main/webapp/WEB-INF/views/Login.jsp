<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>On Road Assistance - Login</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/Images/road.png' />" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/Style.css" type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
.bgimg {
	background-image: url(<c:url value='/Images/pic.jpeg'/>);
	background-position: center;
	background-size: cover;
	height: 100vh;
	width: 100%;
	background-position: center;
	background-position: center;
	background-position: center;
	background-position: center;
}

.form-container {
	/* border: 1px solid; */
	padding: 60px 50px;
	margin-top: 150px;
	box-shadow: 18px 15px 26px 3px rgba(0, 0, 0, 0.72);
	border-radius: 11px;
	border: 0px solid #000000;
}

* {
	transition: all 1s !important;
}

.but {
	display: flex;
	justify-content: space-around;
}

.btn-success {
	color: white !important;
	background: black !important;
	border: none;
}

.btn {
	padding: 10px 45px !important;
	border-radius: 100px !important;
}

.custom {
	margin-top: 45px;
	padding: 20px;
}

.grid {
	display: grid !important;
	grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
	grid-gap: 10px !important;
}
.ex {
	color: red;
}
</style>
</head>

<body>
	<div class="container-fluid ">
		<div class="row">
			<div class="col-lg-8 col-md-4 d-md-block d-none bgimg"></div>
			<div class="col-lg-4 col-md-8 p-5">
				<form class="form-container " action="/On_Road_Assistance/login"
					method="post" onsubmit="return validateFunction()">
					<div class="form-group">
						<label for="exampleInputEmail1"><b>User ID</b></label> <input
							type="text" class="form-control" name="userid" id="userid"
							placeholder="User ID" pattern="[0-9]{1-7}" required />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1"><b>Password</b></label> <input
							type="password" class="form-control" name="password"
							id="password" placeholder="Password" required />
					</div>

					<span><label class="ex">${message }</label></span>


					<div class="text-center">
						<button type="submit" class="btn btn-success shadow-lg">
							Login</button>
							<label id="error"></label>
					</div>
				</form>

				<div class="custom grid">
				<div>
					<h5>Don't have an account yet?</h5>
					<button type="submit" name="CustRegBtn" class="btn btn-secondary"
						onclick="window.location.href='/On_Road_Assistance/registerCustomer';">
						Register Now</button>
				<!--  	<button type="submit" name="MechRegBtn" class="btn btn-secondary"
						onclick="window.location.href='/On_Road_Assistance/registerMechanic';">
						Register as Mechanic</button> -->
				</div>
			</div>
		</div>
	</div>
	</div>
		<script type="text/javascript" >
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	    }; 
</script>
<script>
function validateFunction() {
  var x, text;

  x = document.getElementById("userid").value;

  if (isNaN(x)) {
    alert("User ID not valid. Please Enter Correct User ID")
    return false;
  }
}
</script>
</body>
</html>