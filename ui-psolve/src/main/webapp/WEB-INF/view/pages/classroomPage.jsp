<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="badger"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
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
<link href="<c:url value="/resources/custom/css/classroom.css"/>"
	rel="stylesheet">
</head>
<body>
	<badger:header />
	<div class="container-fluid col-lg-6 col-lg-offset-3">
		<div class="row">
			<div class="col-lg-12 ">
				<h2 class="text-center">${classroom.name}</h2>
				<p class="text-justify">${classroom.description}</p>
			</div>
		</div>
	</div>
	<div class="container-fluid col-lg-2 col-lg-offset-3">
		<div class="row text-center">
			<div class="col-lg-12 ">
				<h3>Courses</h3>
			</div>
			<c:forEach items="${classroomCourses}" var="course">
				<div class="col-lg-12">
					<a href="/badger/classroom/${classroom.id}/${course.id}">${course.name}</a>
				</div>
			</c:forEach>
			<hr>
			<div class="col-lg-12 ">
				<h3>
					<hr>
					Challenges
				</h3>
			</div>
			<div class="col-lg-12">
				<a href="#">Prove your sanity</a>
			</div>
			<div class="col-lg-12">
				<a href="#">Java Threads</a>
			</div>
			<div class="col-lg-12">
				<a href="#">Are you done?</a>
			</div>
		</div>
	</div>

	<div class="container-fluid col-lg-4">
		<div class="row">
			<div class="col-sm-4 col-lg-12">
				<br class="clearfix">
				<div class="btn-group btn-group-justified">
					<a href="#" class="btn btn-default">Create Challenge</a> <a
						href="#" class="btn btn-default">Upload File</a>
				</div>
			</div>
		</div>
		<br class="clearfix">

		<div class="row post-comment">
			<div class="text-justify col-sm-4 col-lg-12">
				<div class="input-group">
					<textarea class="form-control custom-control" rows="4"
						style="resize: none"></textarea>
					<span class="input-group-addon btn btn-primary">Post</span>
				</div>
			</div>
		</div>

		<br class="clearfix">

		<div class=" col-sm-4 text-justify col-lg-12 well">
			<a href="#"><strong>Prof. John Johnson</strong></a> <strong>posted</strong>
			don't forget that the first challenge must be completed before
			22.11.2015!!
		</div>
		<div class="col-sm-4 text-justify col-lg-12 well">
			<a href="#"><strong>Prof. John Johnson</strong></a> <strong>uploaded</strong>
			<a href="#">Tips and Tricks for Java Programming</a>
		</div>
		<div class="col-sm-4 text-justify col-lg-12 well">
			<a href="#"><strong>Stefan Mladin</strong></a> <strong>completed</strong>
			<a href="#">Are you sane?</a>
		</div>

	</div>
	<badger:footer />
</body>
</html>
