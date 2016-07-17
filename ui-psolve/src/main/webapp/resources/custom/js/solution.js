$(document).ready(function() {
	$(document).on("change", ".upload-solution", function() {
		var form = $(this).parent();
		var action = form.attr('action');
		var method = form.attr('method');

		var formData = new FormData();
		formData.append("file", this.files[0]);
		formData.append("taskId", $('#solution-id').val());

		$.ajax({
			dataType : 'json',
			url : action,
			data : formData,
			type : method,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			success : function(data) {
				var downloadSolution = '<a href="'+ ctx +'/downloadSolution/'+$('#solution-id').val()+'" download>'+
				'<span class="hover-color text-center">'+ $('#selected-project-container').attr("taskName") +'</span></a>';
				
				var panelBody = $('#collapse-upload-solution').find('.panel-body:first');
				var label = $(panelBody).find('.form-control-label:first');
				$(downloadSolution).insertAfter(label);
				
			},
			error : function() {
			}
		});

	});

});
