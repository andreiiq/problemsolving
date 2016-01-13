<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="badger"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Badger</title>
<link href="<c:url value="/resources/web/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/bootstrap-social.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/font-awesome.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/docs.css"/>"
	rel="stylesheet">

<script src="<c:url value="/resources/web/js/jquery-1.11.2.min.js"/>"></script>
<script src="<c:url value="/resources/web/js/bootstrap.js"/>"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/custom/css/profile.css"/>">

<script type="text/javascript"
	src="<c:url value="/resources/custom/js/profile.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/custom/js/progressbar.min.js"/>"></script>
</head>
<body>
	<badger:header />
	<div class="container-fluid">
		<div class="row">
			<div class="profile-picture col-lg-2 col-lg-offset-3">
				<c:url value="/profile-image" var="profileImageURL" />
				<img id="profile-img"
					class="img-responsive center-block img-thumbnail"
					src="${profileImageURL}"> <br>
				<div class="progress">
					<div class="progress-bar progress-bar-success" role="progressbar"
						aria-valuenow="${xpPercentage}" aria-valuemin="0"
						aria-valuemax="100" style="width: ${xpPercentage}%">
						<span class="levelContainer"> Level ${buser.level.value}</span>
					</div>
				</div>
			</div>
			<div class="user-information col-lg-4">
				<ul>
					<br>
					<li><h3>
							<strong>${buser.firstname} ${buser.lastname}</strong>
						</h3></li>
					<br>
					<li><h4>${buser.education}</h4></li>
					<li><h4>Courses Finished:
							${fn:length(buser.completedCourses)}</h4></li>
				</ul>
			</div>
		</div>

	</div>
	<hr>
	<div class="container-fluid">
		<div class="row">
			<div class=" col-lg-offset-3 col-lg-2">
				<div id="activity-list" class="list-group">
					<button id="view-chronology-content" type="button"
						class="list-group-item">Chrono</button>
					<button id="view-classroom-content" type="button"
						class="list-group-item">Classrooms</button>
				</div>
			</div>


			<div class="activity-content-wraper ">
				<div id="classroom-content" class="hidden col-lg-4">
					<div id="classroomsAcordion" class="accordion row">
						<c:forEach items="${buser.classrooms}" var="classroom"
							varStatus="status">
							<c:url var="classroomURL" value="/classroom/${classroom.id}" />
							<div type="button"
								class="accordion-group classroom-button classroom-button text-justify col-lg-12 well img-rounded ">
								<div class="accordion-heading">
									<h6 class="content-subtitle">
										<a class="accordion-toggle" data-toggle="collapse"
											data-parent="#classroomsAcordion"
											onclick="location.href='${classroomURL}';"
											href="#classroomDescriptionBody${status.index}"> <strong>${classroom.name}</strong>
										</a>
									</h6>
								</div>
								<div class="classroomDescription">${classroom.description}</div>
								<div id="classroomDescriptionBody${status.index}"
									class="accordion-body collapse out"></div>
								<a class="show-classroom-content" data-toggle="collapse"
									href="#classroomDescriptionBody${status.index}"> <strong>(Show
										More)</strong>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<div id="chronology-content" class="col-lg-4">
					<badger:chronology userEvents="${buser.events}" />
				</div>
			</div>
		</div>
	</div>
	<badger:footer />
</body>
</html>
