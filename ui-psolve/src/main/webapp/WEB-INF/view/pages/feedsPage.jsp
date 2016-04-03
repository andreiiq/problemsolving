<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="badger"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<name>Badger</name>
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

<link href="<c:url value="/resources/custom/css/feeds.css"/>"
	rel="stylesheet">
</head>
<body>
	<badger:header />
	<div class="container-fluid col-lg-2 col-lg-offset-3">
		<div class="row text-center">
			<div class="col-lg-12 ">
				<h3>Classrooms</h3>
			</div>
			<c:forEach items="${userClassrooms}" var="classroom">
				<div class="col-lg-12">
					<a href="/badger/classroom/${classroom.id}">${classroom.name}</a>
				</div>
			</c:forEach>
			<div class="col-lg-12">
				<button class="btn btn-primary btn-sm" type="submit">Create
					Classroom</button>
			</div>
			<hr>
			<div class="col-lg-12 ">
				<h3>
					<hr>
					Top Courses
				</h3>
			</div>
			<div class="col-lg-12">
				<a href="#">Java Developer Tutorials</a>
			</div>
			<div class="col-lg-12">
				<a href="#">Bootstrap Tomorrow</a>
			</div>
			<div class="col-lg-12">
				<a href="#">Hybris Training</a>
			</div>
		</div>
	</div>
	<div class="container-fluid col-lg-4">
		<badger:chronology userEvents="${userEvents}" />
	</div>
	<badger:footer />
	<script type="text/javascript">
		$(function() {
			$('[data-toggle="popover"]').popover()
		});
	</script>
</body>
</html>
