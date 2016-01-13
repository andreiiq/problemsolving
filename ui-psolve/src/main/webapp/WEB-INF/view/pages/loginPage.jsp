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
<title>Badger</title>
<!-- Bootstrap -->
<link href="<c:url value="/resources/web/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/bootstrap-social.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/font-awesome.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/docs.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/web/js/jquery-1.11.2.min.js"/>"></script>
<script src="<c:url value="/resources/web/js/bootstrap.js"/>"></script>
<link href="<c:url value="/resources/custom/css/login.css"/>"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Badger</h1>
				<div class="account-wall">
					<img class="profile-img"
						src="https://s-media-cache-ak0.pinimg.com/736x/81/b1/3e/81b13eb4b2c344db9fcdd206976c3b5c.jpg"
						alt="">
					<c:url value="/j_spring_security_check" var="loginURL" />
					<form:form class="form-signin" action="${loginURL}" method="POST">
						<input name="username" type="text" class="form-control"
							placeholder="Email" required autofocus>
						<input name="password" type="password" class="form-control"
							placeholder="Password" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
						<label class="checkbox pull-left"> <input
							path="rememberMe" type="checkbox" value="remember-me">
							Remember me
						</label>
						<a href="#" class="pull-right need-help">Need help? </a>
						<span class="clearfix"></span>
					</form:form>
				</div>
				<a href="#" class="text-center new-account" data-toggle="modal"
					data-target="#registrationModal">Create an account </a>
				<badger:registration/>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
