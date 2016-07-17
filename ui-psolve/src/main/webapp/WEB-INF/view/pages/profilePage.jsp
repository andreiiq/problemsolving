<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="pbs" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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


 <script src="<c:url value="/resources/web/js/jquery-1.11.2.min.js"/>"></script>
    <script src="<c:url value="/resources/web/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/web/css/bootstrap.min.css"/>">
<script
	src="<c:url value="/resources/web/js/bootstrap-typeahead.min.js"/>"></script>

<script src="<c:url value="/resources/custom/js/teacherPage.js"/>"></script>
<script src="<c:url value="/resources/custom/js/common.js"/>"></script>
<script src="<c:url value="/resources/custom/js/header.js"/>"></script>
<script src="<c:url value="/resources/custom/js/solution.js"/>"></script>

<script
	src="<c:url value="/resources/web/js/jquery.autocomplete.min.js"/>"></script>



<link href="<c:url value="/resources/custom/css/teacherPage.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/custom/css/common.css"/>"
	rel="stylesheet">
<title>Teacher Admin</title>
</head>
<body>
	<pbs:header />

	<div class="container-fluid pbs-profile">
		<div class="row">
			<div class="profile-picture col-lg-2 col-lg-offset-3">
				<c:url value="/profile-image" var="profileImageURL" />
				<img id="profile-img"
					class="img-responsive center-block img-thumbnail"
					src="<c:url value="/profile-image"/>"> <br>
			</div>
			<div class="user-information col-lg-4">
				<br>
				<h3>
					<strong>${user.firstname} ${user.lastname} </strong> <br>
				</h3>
				<br>
				<c:forEach var="skill" items="${skills}" varStatus="loop">
					<div class="row">
						<div class="col-lg-6">
							<div class="progress">
								<fmt:formatNumber var="skillValue"
									value="${(skill.experience / skill.levelModel.xpNeeded) * 100}"
									type="number" pattern="#" />

								<c:choose>
									<c:when test="${loop.index == 2}">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="${skillValue}"
											aria-valuemin="0" aria-valuemax="100"
											style="width: 40%; color: black;">${skill.name}
											${skill.levelModel.value}</div>
									</c:when>
									<c:when test="${loop.index == 1}">
										<div class="progress-bar progress-bar-info" role="progressbar"
											aria-valuenow="${skillValue}" aria-valuemin="0"
											aria-valuemax="100" style="width: 40%; color: black;">${skill.name}
											${skill.levelModel.value}</div>
									</c:when>
									<c:otherwise>
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="${skillValue}"
											aria-valuemin="0" aria-valuemax="100"
											style="width: 40%; color: black;">${skill.name}
											${skill.levelModel.value}</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<hr>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-offset-3 col-lg-2">
				<div id="activity-list" class="list-group">
					<button id="view-selected-project" type="button"
						class="list-group-item">Selected Project</button>
					<button id="view-basic-info" type="button" class="list-group-item">Basic
						Info</button>
					<c:url value="/search/findUserTasks" var="findUserTasks" />
					<form:form class="view-projects-form" action="${findUserTasks}"
						method="POST">
						<button id="view-my-projects" type="button"
							class="list-group-item">My Projects</button>
						    <input id="current-page-mynumber" type="hidden" name="page" value="0" />

					</form:form>
					<c:url value="/search/findUserMentorTasks" var="findMentoringTasks" />
					<form:form class="view-projects-form"
						action="${findMentoringTasks}" method="POST">
						<button id="view-my-mentorships-projects" type="button"
							class="list-group-item">My Mentorships</button>
						<input id="current-page-mentnumber" type="hidden" name="page"
							value="0" />
					</form:form>
					<button id="search-projects" type="button" class="list-group-item">Search
						Projects</button>
				</div>

			</div>

			<div class="activity-content-wraper ">
				<div id="vbasic-content" class="col-lg-4">
					<div id="vbasic" class="accordion row">
						<pbs:basicInfo />
					</div>
				</div>

				<div id="vproject-content" class="hidden col-lg-4">
					<div id="vproject" class="accordion row">
						<pbs:selectedProject />
					</div>
				</div>

				<div id="aproject-content" class="hidden col-lg-4">
					<div id="aproject" class="accordion row">
						<pbs:projectsList />
					</div>
				</div>

				<div id="mproject-content" class="hidden col-lg-4">
					<div id="mproject" class="accordion row">
						<pbs:myProjects />
					</div>
				</div>

				<div id="mentproject-content" class="hidden col-lg-4">
					<div id="mentproject" class="accordion row">
						<pbs:myMentorings />
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>