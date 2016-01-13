<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link href="<c:url value="/resources/custom/css/header.css"/>"
	rel="stylesheet">
</head>
<nav class="navbar navbar-default col-lg-6 col-lg-offset-3">
<div class="container-fluid">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#defaultNavbar1">
			<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
				class="icon-bar"></span><span class="icon-bar"></span>
		</button>
		<a class="navbar-brand badger-brand" href=<c:url value="/"/>>Badger</a>
	</div>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="defaultNavbar1">
		<form class="navbar-form navbar-left form form-vertical" role="search">
			<div class="form-group">
				<input type="text" class="form-control"
					placeholder="Search for everything">
			</div>
			<button type="submit" class="btn btn-default">
				<span class="glyphicon glyphicon-search" style="color: green"
					aria-hidden="true"></span>
			</button>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">About us</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Settings<span
					class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">My classrooms</a></li>
					<li class="divider"></li>
					<li><a href="#">Privacy</a></li>
					<li class="divider"></li>
					<c:url value="/logout" var="logoutURL" />
					<li><form:form method="POST" action="${logoutURL}">
					<button class="link-button" type="submit">Logout</input>
					</form:form></li>
				</ul></li>
			<c:url value="/profile" var="profileURL" />
			<c:url value="/profile-image" var="profileImageURL" />
			<li><a class="navbar-brand" rel="home" href="${profileURL}"> <img
					style="max-width: 35px; margin-top: -7px;" src="${profileImageURL}">
			</a></li>
		</ul>
	</div>
</div>
</nav>
</html>