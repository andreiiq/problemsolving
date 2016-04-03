<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="pbs" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<name>Teacher Admin</name>
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
<script src="<c:url value="/resources/web/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/web/js/bootstrap-typeahead.min.js"/>"></script>

<script src="<c:url value="/resources/custom/js/teacherPage.js"/>"></script>

<link href="<c:url value="/resources/custom/css/teacherPage.css"/>"
	rel="stylesheet">
</head>
<body>
	<pbs:header />
	<div class="container-fluid pbs-profile">
		<div class="row">
			<div class="profile-picture col-lg-2 col-lg-offset-3">
				<c:url value="/profile-image" var="profileImageURL" />
				<img id="profile-img"
					class="img-responsive center-block img-thumbnail"
					src="<c:url value="/resources/images/empty_profile.gif"/>"> <br>
			</div>
			<div class="user-information col-lg-4">
					<br>
					<h3>
							<strong>Andrei Georgescu</strong>
						</h3>
					<br>
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
					<button id="view-create-projects" type="button"
						class="list-group-item">Create Project</button>
					<button id="view-active-projects" type="button"
						class="list-group-item">View Projects</button>
					<button id="view-sent-projects" type="button"
						class="list-group-item">View Solutions</button>
				</div>
				
			</div>

			<div class="activity-content-wraper ">
				<div id="cproject-content" class="col-lg-4">
					<div id="cproject" class="accordion row">
					<c:import  url="/WEB-INF/view/pages/createProject.jsp"/>
					</div>
				</div>
				
			   <div id="vproject-content" class="hidden col-lg-4">
					<div id="vproject" class="accordion row">
					 <pbs:projectDetails/>
					</div>
				</div>

				<div id="aproject-content" class="hidden col-lg-4">
					<div id="aproject" class="accordion row">
					<pbs:projectsList/>
					</div>
				</div>

				<div id="sproject-content" class="hidden col-lg-4">
					<div id="sproject" class="accordion row">
						<pbs:solutions/>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>