$(document).ready(function () {
	checkForNewNotifications();
	window.setInterval(function(){
		checkForNewNotifications();
		}, 10000);
	
	 $(document).on('click', '.accept-invitation', function() {
		    event.preventDefault();	
			   var href = $(this).attr("href");
		        
		        var menu = $(this).next(".scrollable-menu");
		        
		        if(menu.children().length > 0) {
		        	menu.empty();
		        }
		        
		        $.ajax({
		            type: "GET",
		            url: href,
		            success: function (data) {
		           	 $('.new-notifications-number').html("(0)");
	               	 $('.notification-dropdown').click();
		                            },
		            error: function () {
		            }
		        });
	});
	
	
	$(document).on("click",'.reject-invitation', function(event) {
		    event.preventDefault();	
		   var href = $(this).attr("href");
	        
	        var menu = $(this).next(".scrollable-menu");
	        
	        if(menu.children().length > 0) {
	        	menu.empty();
	        }
	        
	        $.ajax({
	            type: "GET",
	            url: href,
	            success: function (data) {
	               	 $('.new-notifications-number').html("(0)");
	               	 $('.notification-dropdown').click();
	                            },
	            error: function () {
	            }
	        });
	});
	
	
    $('#header-search-input').keyup(function (e) {
        var form = $('#header-search-form');
        var data = {}

        $.each(form, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: form.attr('method'),
            url: form.attr('action'),
            dataType: 'json',
            data: {name: $(this).val()},
            success: function (data) {
                var searchResults = $("#search-results");

                $.each(data, function(index, result) {
                    searchResults.append('<option value="'+ result.firstname +'">');
                });
            },
            error: function () {
            }
        });
    });
    
    $('.notification-dropdown').click(function () {
        var action = $(this).attr('href');
        var menu = $(this).next(".scrollable-menu");
        
        if(menu.children().length > 0) {
        	menu.empty();
        }
        
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: "GET",
            url: action,
            dataType: 'json',
            data: " ",
            success: function (data) {
            	   for (var key in data.notifications) {
            		   appendNotifications(data.notifications[key], menu);
                   }
               	 $('.new-notifications-number').html("(0)");
                            },
            error: function () {
            }
        });
    });
    
    
    
    function appendNotifications(notification, menu) {
    	var notificationQueryHTML = "";
    	if(notification.status == "PENDING") {
    	notificationQueryHTML = '<button href="'+ctx +'/acceptNotifiation?notificationID='+notification.notificationID+'" type="button"class="accept-invitation btn btn-info btn-circle-sm">'+
		'<i class="glyphicon glyphicon-ok-circle"></i>'+
		'</button>'+
		'<button href="'+ctx +'/rejectNotification?notificationID='+notification.notificationID+'" type="button" class="reject-invitation btn btn-danger btn-circle-sm">'+
			'<i class="glyphicon glyphicon-remove-circle"></i>'+
		'</button>';
    	}

    	var message = notification.message.split(" ");
    	
    	message[message.length-1] ='<a class="project-button">'+
    	''+ message[message.length-1] +'</a>';
    	menu.append('  <li class="divider"></li><li><form role="form" class="form-inline" method="GET" action="'+ ctx +'/getTask"><input type="hidden" name="taskId" value="'+ notification.taskId +'">'+ message.join(" ") + notificationQueryHTML + '</form>');
    	
    }
    function checkForNewNotifications() {
    	var url = ctx + "/countNotifications"
    	 $.ajax({
             contentType: 'application/json; charset=utf-8',
             type: "GET",
             url: url,
             dataType: 'json',
             data: " ",
             success: function (data) {
             	 $('.new-notifications-number').html('(' + data +')');
                             },
             error: function () {
             }
         });
    }
});