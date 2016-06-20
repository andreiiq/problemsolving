$(document).ready(function() {
	$(document).on("change", ".upload-solution", function () {
	    var form = $(this).parent();
        var action = form.attr('action');
        var method = form.attr('method');
        
        var formData = new FormData();
        console.log(this.files[0]);
        formData.append("file", this.files[0]);
        formData.append("taskId", 1);

        $.ajax({
        	dataType : 'json',
            url : action,
            data : formData,
            type : method,
            enctype: 'multipart/form-data',
            processData: false, 
            contentType:false,
            success: function (data) {
            },
            error: function () {
            }
        });
		
	});

});
