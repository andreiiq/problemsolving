<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div id="selected-project-container">


	<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal">Open Modal</button>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<c:url value="/search/findAllEligibleUsers"
							var="getEligibleUsersURL" />
						<form:form class="form-horizontal" role="form" method="GET"
							action="${getEligibleUsersURL}">
							<div class="input-group">
								<input name="name" type="text"
									class="on-key-search form-control"
									placeholder="Search for students" value=""> <span
									class="input-group-btn"> <input
									class="current-page-number" type="hidden" name="page" value="0" />
									<button style="background-color: white; border-color: #5bc0de"
										class="btn btn-secondary" type="button">
										<span class="pbs-serach-glyph glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</form:form>
					</h4>
				</div>
				<div class="modal-body">
					<div class="list-group"></div>
				</div>

				<div class="modal-footer">
					<c:url value="/assign/assignTask" var="assignTaskURL" />
					<form:form class="form-horizontal" role="form" method="POST"
						action="${assignTaskURL}">
						<input id="assined-user-email" name="receiverEmail" type="hidden"
							value="" />
						<input id="assined-subtask-id" name="subtaskID" type="hidden"
							value="5" />
					</form:form>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="assign-submit-btn btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>

</div>

<script>
	var delay = (function() {
		var timer = 0;
		return function(callback, ms) {
			clearTimeout(timer);
			timer = setTimeout(callback, ms);
		};
	})();

	$('form input').on('keypress', function(e) {
		return e.which !== 13;
	});

	$('.assign-submit-btn').click(
			function(event) {
				var form = $(this).prevAll('.form-horizontal:first');

				var action = form.attr('action');
				var method = form.attr('method');
				console.log(method);

				$.ajax({
					contentType : 'application/json; charset=utf-8',
					type : method,
					url : action,
					dataType : 'json',
					data : JSON.stringify(form.serializeObject()),
					success : function(data) {
						resultsContainer.empty();
						for ( var key in data.userSearchDTOs) {
							appendUserSearchResults(data.userSearchDTOs[key],
									resultsContainer);
						}
					},
				});

			});

	$('.on-key-search').keyup(
			function(event) {
				var form = $(this).closest('.form-horizontal');
				var headerModal = $(this).closest('.modal-header');
				var bodyModal = headerModal.next();
				var resultsContainer = bodyModal.children('.list-group');
				var action = form.attr('action');
				var method = form.attr('method');

				$.ajax({
					contentType : 'application/json; charset=utf-8',
					type : method,
					url : action,
					dataType : 'json',
					data : form.serializeObject(),
					success : function(data) {
						resultsContainer.empty();
						for ( var key in data.userSearchDTOs) {
							appendUserSearchResults(data.userSearchDTOs[key],
									resultsContainer);
						}
					},
				});
			});

	$('.on-key-search').keyup(
			function(event) {
				var form = $(this).closest('.form-horizontal');
				var headerModal = $(this).closest('.modal-header');
				var bodyModal = headerModal.next();
				var resultsContainer = bodyModal.children('.list-group');
				var action = form.attr('action');
				var method = form.attr('method');

				$.ajax({
					contentType : 'application/json; charset=utf-8',
					type : method,
					url : action,
					dataType : 'json',
					data : form.serializeObject(),
					success : function(data) {
						resultsContainer.empty();
						for ( var key in data.userSearchDTOs) {
							appendUserSearchResults(data.userSearchDTOs[key],
									resultsContainer);
						}
					},
				});
			});

	function appendUserSearchResults(userData, container) {
		container
				.append('<a href="#" class="user-result-item list-group-item" email="'+ userData.email +'">'
						+ userData.firstname + " " + userData.lastname + '</a>');

	}

	$('.list-group').on('click', '.user-result-item', function() {
		$('.list-group a').removeClass('active');
		$(this).addClass('active');
		$('#assined-user-email').val($(this).attr("email"));
	});
</script>