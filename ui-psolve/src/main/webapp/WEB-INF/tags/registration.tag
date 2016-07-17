<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<script src="<c:url value="/resources/custom/js/registration.js"/>"></script>
<link href="<c:url value="/resources/custom/css/registration.css"/>"
	rel="stylesheet" />
</head>

<div class="modal fade" id="reg" tabindex="-1"
	role="dialog" aria-labelledby="registrationModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-name" id="registrationModalLabel">Registration</h4>
			</div>
			<c:url value="/register" var="registerURL" />
			<form:form enctype="multipart/form-data" class="form-register"
				action="${registerURL}" method="POST">
				<div class="modal-body">
					<div class="form-group">
						<div class="input-group col-lg-7">
							<div class="image-upload">
								<label for="profile-picture-input"><img
									id="profile-picture-selection"
									class="img-thumbnail img-responsive"
									src="<c:url value="/resources/images/empty_profile.gif"/>">
								</label> <input name="profileImage" id="profile-picture-input"
									type="file" class="file" required/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-3">Email</label>
						<div class="input-group col-lg-7">
							<input name="email" type="text" class="form-control"
								placeholder="Email" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-3">Password</label>
						<div class="input-group col-lg-7">
							<input name="password" type="password" class="form-control"
								placeholder="Password" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-3">Firstname</label>
						<div class="input-group col-lg-7">
							<input name="firstname" type="text" class="form-control"
								placeholder="Firstname" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-3">Lastname</label>
						<div class="input-group col-lg-7">
							<input name="lastname" type="text" class="form-control"
								placeholder="Lastname" required/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Register</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
