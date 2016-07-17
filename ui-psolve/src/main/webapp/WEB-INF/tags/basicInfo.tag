<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="form-group row">
	<label class="col-sm-2 form-control-label">Email</label>
	<div class="col-sm-10">${user.email}</div>
</div>
<div class="form-group row">
	<label class="col-sm-2 form-control-label">Firstname</label>
	<div class="col-sm-10">${user.firstname}</div>
</div>
<div class="form-group row">
	<label class="col-sm-2 form-control-label">Lastname</label>
	<div class="col-sm-10">${user.lastname}</div>
</div>


<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-name">
				<a data-toggle="collapse" href="#collapse-basicskills"> Skills</a>
			</h4>
		</div>
		<div id="collapse-basicskills" class="panel-collapse collapse in">
			<div class="panel-body">
				<c:forEach var="skill" items="${user.skills}">
					<fmt:formatNumber var="skillValue"
						value="${(skill.experience / skill.levelModel.xpNeeded) * 100}"
						type="number" pattern="#" />
					<div class="progress">
						<div class="progress-bar progress-bar" role="progressbar"
							aria-valuenow="${skillValue}" aria-valuemin="0"
							aria-valuemax="100" style="width: 40%; color: black;">${skill.name} ${skill.levelModel.value}
							- ${skillValue}%</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<h4 class="panel-name">
	<a data-toggle="collapse" href="#collapse-basic-points"> Courses</a>
</h4>

<div id="collapse-basic-points" class="collapse in">
	<c:forEach var="coursePoint" items="${user.points}">
		<div class="row" style="margin-top: 0.5em">
			<div type="button"
				class="project-button text-justify well img-rounded ">
				<h6 class="content-subtitle">
					<span><strong>${coursePoint.course.title}</strong></span>
				</h6>
				<div class="projectDescription">
					<b>Course: </b>${coursePoint.course.title}<br> <b>Points:
					</b>${coursePoint.points}<br> <br>
					${coursePoint.course.description}

				</div>
			</div>
		</div>
	</c:forEach>

</div>


