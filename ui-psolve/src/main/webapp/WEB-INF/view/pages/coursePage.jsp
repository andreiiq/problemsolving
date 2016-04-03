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

<link href="<c:url value="/resources/custom/css/courseModel.css"/>"
	rel="stylesheet">
</head>
<body>
	<badger:header />
	<div class="container-fluid">
		<div class="row text-center">
			<div class="col-lg-12 ">
				<h3>${courseModel.name}</h3>
			</div>
		</div>
		<div>
			<div class="col-md-6 col-md-offset-3 text-left well">${courseModel.description}</div>
		</div>
		<div class="col-md-6 col-md-offset-3">
			<button class="btn btn-primary btn-sm" type="submit">Download
				Course Material</button>
			<div>
				<br>
				<h4>Take the quiz to complete the courseModel and get some experience</h4>
				<h4><a href="/badger/quiz/${courseModel.quiz.id}">${courseModel.quiz.name}</a></h4>
			</div>
			&nbsp;
		</div>
	</div>
	<badger:footer />
</body>
</html>
