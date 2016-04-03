<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pbs"%>

<div class="container-fluid">
<div class="row">
	<div class="col-lg-12">
		<div class="input-group">
			<div class="input-group-btn search-panel">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span id="search_concept">Filter by</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#contains">Contains</a></li>
					<li><a href="#its_equal">It's equal</a></li>
					<li><a href="#greather_than">Greather than ></a></li>
					<li><a href="#less_than">Less than < </a></li>
					<li class="divider"></li>
					<li><a href="#all">Anything</a></li>
				</ul>
			</div>
			<input type="text" class="form-control" name="x"
				placeholder="Search term..."> <span class="input-group-btn">
				<button style="background-color: white; border-color: #5bc0de"
					class="btn btn-secondary" type="button">
					<span class="pbs-serach-glyph glyphicon glyphicon-search"></span>
				</button>
			</span>
		</div>
	</div>
</div>

<div class=" row" style="margin-top: 0.5em;">
		<div type="button"
			class="project-button text-justify well img-rounded ">
			<h6 class="content-subtitle">
				<span class="hover-color"><strong> Java Project</strong></span>
			</h6>
			<div class="projectDescription">Lorem Ipsum is simply dummy text of the printing </div>
			<div id="projectDescriptionBody1"
				class="accordion-body collapse out">
				<br>
				<h6>Subtask Make Java Servcerside:</h6><span class="unavailable">UNAVAILABLE</span>(LEVEL too small)
				<br>
				<h6>Subtask Php Web Service:</h6><span class="available">Available</span>(LEVEL 8)
			</div>
			<a class="show-classroom-content" data-toggle="collapse"
				href="#projectDescriptionBody1"><span class="hover-color"> <strong>(Show More)</strong></span>
			</a>
		</div>
		</div>
		<div class=" row">
		<div type="button"
			class="project-button text-justify well img-rounded ">
			<h6 class="content-subtitle">
				<span class="hover-color"><strong>Java Project</strong></span>
			</h6>
			<div class="projectDescription">Lorem Ipsum is simply dummy text of the printing </div>
			<div id="projectDescriptionBody2"
				class="accordion-body collapse out">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
			<a class="show-classroom-content" data-toggle="collapse"
				href="#projectDescriptionBody2"><span class="hover-color"> <strong>(Show More)</strong></span>
			</a>
		</div>
		</div>
		<div class=" row">
		<div type="button"
			class="project-button text-justify well img-rounded ">
			<h6 class="content-subtitle">
				<span class="hover-color"><strong>Java Project</strong></span>
			</h6>
			<div class="projectDescription">Lorem Ipsum is simply dummy text of the printing </div>
			<div id="projectDescriptionBody3"
				class="accordion-body collapse out">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
			<a class="show-classroom-content" data-toggle="collapse"
				href="#projectDescriptionBody3"> <span class="hover-color"><strong>(Show More)</strong></span>
			</a>
		</div>
</div>
</div>


