<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<link href="<c:url value="/resources/custom/css/header.css"/>"
	rel="stylesheet">
</head>

<sec:authentication var="principal" property="principal" />
<script>
	var ctx = "${pageContext.request.contextPath}";
	var userEmail ="${principal}";
</script>

<nav class="navbar navbar-default col-lg-6 col-lg-offset-3">
<div class="container-fluid">
	<div class="navbar-header">
		<img id="logo-img"
			class="img-responsive center-block img-thumbnail"
			src="<c:url value="/resources/images/logo.png"/>">
	</div>
	<div class="collapse navbar-collapse" id="defaultNavbar1">
		<ul class="nav navbar-nav navbar-right">
			<c:url value="/getNotifications" var="notificationsURL" />
			<li class="dropdown"><a href="${notificationsURL}"
				class="notification-dropdown dropdown-toggle" data-toggle="dropdown"
				role="button" aria-expanded="false">Notifications<span
					class="new-notifications-number">(0)</span><span class="caret"></span></a>
				<ul class="dropdown-menu scrollable-menu" role="menu">
				</ul></li>
			<c:url value="/logout" var="logoutURL" />
			<li><a><form:form method="POST" action="${logoutURL}">
						<button class="link-button" type="submit">Logout</button>
					</form:form></a></li>
		</ul>
	</div>
</div>
</nav>
</html>