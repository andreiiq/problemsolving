<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="badger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/web/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/bootstrap-social.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/font-awesome.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/docs.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/custom/css/range.css"/>"
	rel="stylesheet">


<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script
	src="<c:url value="/resources/web/js/bootstrap-typeahead.min.js"/>"></script>

<script src="<c:url value="/resources/custom/js/common.js"/>"></script>

<script
	src="<c:url value="/resources/web/js/jquery.autocomplete.min.js"/>"></script>



<link href="<c:url value="/resources/custom/css/teacherPage.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/custom/css/common.css"/>"
	rel="stylesheet">
	<link href="<c:url value="/resources/custom/css/login.css"/>"
	rel="stylesheet">
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-name">Collaborative Problem Solving</h1>
				<div class="account-wall">
					<img id="logo-login-img"
						class="img-responsive center-block img-thumbnail"
						src="<c:url value="/resources/images/logo2.png"/>">
					<c:url value="/j_spring_security_check" var="loginURL" />
					<form:form class="form-signin" action="${loginURL}" method="POST">
						<input name="username" type="text" class="form-control"
							placeholder="Email" required autofocus>
														<br>
						<input name="password" type="password" class="form-control"
							placeholder="Password" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
						<span class="clearfix"></span>
					</form:form>
				</div>
				<a href="#" class="text-center new-account" data-toggle="modal"
					data-target="#reg">Create an account </a>
				<badger:registration />
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
