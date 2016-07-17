<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container-fluid">
	<div class="row">
		<h3 class="content-subtitle">
			<strong>Java Project </strong><a id="edit-seleted-project" href="#"
				class="glyphicon glyphicon-edit"></a>
		</h3>
	</div>
	<hr>

	<form id="selected-project-form">
		<div class="form-group row">
			<label for="selected-name-pr" class="col-sm-2 form-control-label">Name</label>
			<div class="col-sm-10">
				<input type="text" class="name-pr form-control" placeholder="Name"
					readonly="readonly">
			</div>
		</div>
		<div class="form-group row">
			<label for="description-pr" class="col-sm-2 form-control-label">Description</label>
			<div class="col-sm-10">
				<input type="text" class="description-pr form-control"
					placeholder="Description" readonly="readonly">
			</div>
		</div>
		<div class="form-group row">
			<label for="points-pr" class="col-sm-2 form-control-label">Points</label>
			<div class="col-sm-10">
				<input class=" points-pr range-input form-control" type="range"
					data-toggle="popover" data-content="12" name="rangeInput" min="0"
					max="999" readonly="readonly" />
			</div>
		</div>


		<div class="panel-group" id="selected-subtask-acordion">
			<div class="panel panel-default" number="4">
				<div class="panel-heading">
					<h4 class="panel-name">
						<a data-toggle="collapse" data-parent="#selected-subtask-acordion"
							href="#collapse-subtask-4"> Subtask 1</a>
					</h4>
					<div class="subtask-btn">
						<button type="button"
							class="add-new-subtask btn btn-info btn-circle-sm">
							<i class="glyphicon glyphicon-plus"></i>
						</button>
						<button type="button"
							class="remove-current-subtask btn btn-danger btn-circle-sm">
							<i class="glyphicon glyphicon-minus"></i>
						</button>
					</div>
				</div>
				<div id="collapse-subtask-4" class="panel-collapse collapse in">
					<div class="panel-body">
						<div class="form-group row">
							<label for="subtask-pr" class="col-sm-2 form-control-label">Subtask</label>
							<div class="col-sm-10">
								<input type="text" class="form-control subtask-pr"
									placeholder="Name" readonly="readonly">
							</div>
						</div>
						<div class="form-group row">
							<label for="subtask-desc-pr" class="col-sm-2 form-control-label">Info</label>
							<div class="col-sm-10">
								<input type="text" class="form-control subtask-desc-pr"
									placeholder="Info" readonly="readonly">
							</div>
						</div>
						<div class="form-group row">
							<label for="points-pr" class="col-sm-2 form-control-label">Points</label>
							<div class="col-sm-10">
								<input class=" points-pr range-input form-control" type="range"
									data-toggle="popover" data-content="12" name="rangeInput"
									min="0" max="999" readonly="readonly" />
							</div>
						</div>
						<div class="form-group row">
							<label for="select-skill" class="col-sm-2 form-control-label">Skill</label>
							<div class="col-sm-4">
								<select class="form-control select-skill" disabled="disabled">
									<option>Java</option>
									<option>PHP</option>
									<option>Javascript</option>
								</select>
							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control select-levelModel"
									placeholder="Level" readonly="readonly">
							</div>
							<div class="col-sm-1">
								<button type="button"
									class="add-new-skill btn btn-info btn-circle-sm">
									<i class="glyphicon glyphicon-plus"></i>
								</button>
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
		</div>

	</form>
</div>