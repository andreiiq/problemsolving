<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/teacher/add-new-task" var="addTaskURL" />
<form:form id="create-project-form" action="${addTaskURL}" method="post">
	<div class="form-group row">
		<label for="name-pr" class="col-sm-2 form-control-label">Name</label>
		<div class="col-sm-10">
			<input name="title" type="text" class="form-control" id="name-pr"
				placeholder="Name">
			<form:errors path="name" cssClass="error">asddads</form:errors>
		</div>
	</div>
	<div class="form-group row">
		<label for="description-pr" class="col-sm-2 form-control-label">Description</label>
		<div class="col-sm-10">
			<input name="description" type="text" class="form-control"
				id="description-pr" placeholder="Description">
		</div>
	</div>
	<div class="form-group row">
		<label for="points-pr" class="col-sm-2 form-control-label">Points</label>
		<div class="col-sm-10">
			<input class=" points-pr range-input form-control" type="range"
				data-toggle="popover" data-content="12" name="pointsRewarded"
				min="0" max="999" />
		</div>
	</div>
	<div class="form-group row">
		<label for="course-pr" class="col-sm-2 form-control-label">Course</label>
		<div class="col-sm-10">
			<select name="course" class="form-control">
				<c:forEach items="${courses}" var="course">
					<option>${course.title}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="row">
		<h3 class="pull-lef" style="color: #428bca;">
			Subtasks
			<button type="button"
				class="add-new-subtask btn btn-info btn-circle-sm">
				<i class="glyphicon glyphicon-plus"></i>
			</button>
		</h3>
	</div>
	<div class="panel-group" id="subtask-acordion">
		<div class="panel panel-default create-subtask" number="1">
			<div class="panel-heading">
				<h4 class="panel-name">
					<a data-toggle="collapse" data-parent="#subtask-acordion"
						href="#collapse-subtask-1"> Subtask 1</a>
				</h4>
				<div class="subtask-btn">
					<button type="button"
						class="remove-current-subtask btn btn-danger btn-circle-sm">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
			</div>
			<div id="collapse-subtask-1" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="form-group row">
						<label for="subtask-pr" class="col-sm-2 form-control-label">Subtask</label>
						<div class="col-sm-10">
							<input name="subtasks[0].title" type="text"
								class="form-control subtask-pr" placeholder="Name">
						</div>
					</div>
					<div class="form-group row">
						<label for="subtask-desc-pr" class="col-sm-2 form-control-label">Info</label>
						<div class="col-sm-10">
							<input name="subtasks[0].description" type="text"
								class="form-control subtask-desc-pr" placeholder="Info">
						</div>
					</div>
					<div class="form-group row">
						<label for="points-pr" class="col-sm-2 form-control-label">Points</label>
						<div class="col-sm-10">
							<input class=" points-pr range-input form-control" type="range"
								data-toggle="popover" data-content="12"
								name="subtasks[0].pointsRewarded" min="0" max="999" />
						</div>
					</div>

					<div class="row">
						<h4 class="pull-lef" style="color: #428bca;">
							Skills
							<button type="button"
								class="add-new-skill btn btn-info btn-circle-sm">
								<i class="glyphicon glyphicon-plus"></i>
							</button>
						</h4>
					</div>

					<div class="form-group row skill-selection" number="[0]">
						<label for="select-skill" class="col-sm-2 form-control-label">Skill</label>
						<div class="col-sm-4">
							<select name="subtasks[0].skills[0].name"
								class="form-control select-skill">
								<option>Java</option>
								<option>PHP</option>
								<option>Javascript</option>
							</select>
						</div>
						<div class="col-sm-4">
							<input name="subtasks[0].skills[0].level" type="text"
								class="form-control select-levelModel" placeholder="Level">
						</div>
						<div>
							<button type="button"
								class="remove-skill btn btn-danger btn-circle-sm">
								<i class="glyphicon glyphicon-minus"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="form-group row">
		<div class="col-sm-2 pull-right">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
		<div class="col-sm-2 pull-right">
			<button type="button" class="btn btn-secondary" data-toggle="modal"
				data-target="#resetFormInquiry">Reset</button>
		</div>
	</div>

	<div id="resetFormInquiry" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p>Are you sure you want to reset the form?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="accept-reset-project-form" type="button"
						class="btn btn-primary">Accept</button>
				</div>
			</div>
		</div>
	</div>
</form:form>