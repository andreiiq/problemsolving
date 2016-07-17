<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pbs"%>
<head>
<link href="<c:url value="/resources/custom/css/searchFilter.css"/>"
	rel="stylesheet">

<script
	src="<c:url value="/resources/web/js/jquery.serializeObject.js"/>"></script>
<script src="<c:url value="/resources/custom/js/projectSearch.js"/>"></script>


</head>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<form:form id="filter-search-form" class="form-horizontal"
				role="form" method="POST" action="/ui-psolve/search/filter">
				<div class="input-group" id="adv-search">
					<input type="text" name="title" class="form-control"
						placeholder="Search for projects" />
					<div class="input-group-btn">
						<div class="btn-group" role="group">
							<div class="dropdown dropdown-lg">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span>
								</button>
								<div class="dropdown-menu dropdown-menu-right" role="menu">
									<div class="form-group">
										<label><input type="checkbox" value=""><b>Search
												My Tasks</b></label>
									</div>
									<div class="form-group">
										<label for="contain">Course</label> <input name="course"
											class="form-control" type="text" />
									</div>
									<div class="form-group">
										<label for="contain">Points</label> <input name="points"
											class="form-control" type="text" />
									</div>

									<div class="row">
										<h4 class="pull-left" style="color: #428bca;">
											Skills
											<button type="button"
												class="add-new-search-skill btn btn-info btn-circle-sm">
												<i class="glyphicon glyphicon-plus"></i>
											</button>
										</h4>
									</div>

									<div id="skills-search-container">
									</div>
								</div>
							</div>
							<span class="input-group-btn"> <input
								id="current-page-number-ftasks" type="hidden" name="page"
								value="0" />
								<button style="background-color: white; border-color: #5bc0de"
									id="filter-btn-submit" class="btn btn-secondary" type="button">
									<span class="pbs-serach-glyph glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
			</form:form>

		</div>
	</div>
	<div id="filter-search-results"></div>
</div>


