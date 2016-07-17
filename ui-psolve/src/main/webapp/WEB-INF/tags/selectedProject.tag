<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div id="selected-project-container" taskId="" taskName="">

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

	
	$(document).on('click', '.assign-mentor-modal-btn', function() {
		$('#search-mentor-subtask').val($(this).attr("subtask"));
		$('#assign-mentor-subtask').val($(this).attr("subtask"));
	});
	
	
	
	$(document).on('click', '.assign-subtask-modal-btn', function() {
		$('#assign-user-subtask').val($(this).attr("subtask"));
		$('#search-students-subtask').val($(this).attr("subtask"));

	});
	$(document).on('click', '.assign-subtask-btn', function() {
	        var form = $(this).parent();
	        var action = form.attr('action');
	        var method = form.attr('method');
	        

	        $.ajax({
	            type: method,
	            url: action,
	            data: form.serializeObject(),
	            success: function (data) {
	            	   $('#search-students-modal').modal('hide');
	            	   $('#search-mentors-modal').modal('hide');
	            },
	            error: function () {
	            }
	        });
	        });


	 $(document).on('keyup','.on-key-search', function(){
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

	$(document).on('click', '.user-result-item', function() {
		$('.list-group a').removeClass('active');
		$(this).addClass('active');
		$('#assign-user-email').val($(this).attr("email"));
		$('#assign-mentor-email').val($(this).attr("email"));
	});
</script>