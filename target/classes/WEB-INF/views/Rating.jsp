<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en" dir="ltr">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	background: #f1f1f1;
	font-size: 20px;
}

.container {
	width: 1170px;
	margin: 20px auto;
	text-align: center;
}

h1 {
	font-family: 'Playfair Display', serif;
	font-size: 5em;
	color: #9e9ba4;
	text-transform: capitalize;
	margin-bottom: 50px;
}

input[type="checkbox"] {
	width: 100%;
	height: 100%;
	opacity: 0;
	cursor: pointer;
}

label {
	position: relative;
	width: 50px;
	height: 50px;
	display: inline-block;
}

.check::before, .rated::after {
	content: '\2605';
	font-size: 60px;
	position: absolute;
	color: #777;
	left: 0;
	bottom: 0;
	line-height: 50px;
}

.rated::after {
	color: orange;
}

.check:hover::before {
	color: orange;
}

label i {
	position: absolute;
	font-size: 35px;
}

label i.em {
	display: none;
}
</style>
</head>
<body>
	<form action="/On_Road_Assistance/rating" method="post">
		<div class="container">
			<div class="smileybox">
				<h1>Rate the Mechanic</h1>
				<label for="r1" class="check"><input type="checkbox" id="r1"
					name="rating" value="1" /><i class="em em-weary"></i></label> <label
					for="r2" class="check"><input type="checkbox" id="r2"
					name="rating" value="2" /><i class="em em-worried"></i></label> <label
					for="r3" class="check"><input type="checkbox" id="r3"
					name="rating" value="3" /><i class="em em-blush"></i></label> <label
					for="r4" class="check"><input type="checkbox" id="r4"
					name="rating" value="4" /><i class="em em-smiley"></i></label> <label
					for="r5" class="check"><input type="checkbox" id="r5"
					name="rating" value="5" /><i class="em em-sunglasses"></i></label>
			</div>
			<br>
			<br>
			<br> <input type="submit" class="btn btn-primary" value="submit">
		</div>

	</form>
</body>
</html>
