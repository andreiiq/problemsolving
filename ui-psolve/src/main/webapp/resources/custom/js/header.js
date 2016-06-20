$(document).ready(function () {
	checkForNewNotifications();
	window.setInterval(function(){
		checkForNewNotifications();
		}, 10000);
	
	
	$('.accept-invitation').on("click", function() {
		   var form = $('#filter-search-form');
	        $('#filter-search-results').empty();
	        
	        var action = form.attr('action');
	        var method = form.attr('method');
	        
	        $.ajax({
	            contentType: 'application/json; charset=utf-8',
	            type: method,
	            url: action,
	            dataType: 'json',
	            data: JSON.stringify(form.serializeObject()),
	            success: function (data) {
	            	console.log(data);
	                for (var key in data.tasks) {
	                    appendSearchResults(data.tasks[key]);
	                }
	                buildPageNavigation(data.numberOfPages, data.currentPage);
	            },
	            error: function () {
	            }
	        });
	});
	
	
	$('.reject-invitation').on("click", function() {
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
	               	 $('.new-notifications-number').html("");
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
                console.log(data);
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
               	 $('.new-notifications-number').html("");
                            },
            error: function () {
            }
        });
    });
    function appendNotifications(notification, menu) {
    	menu.append('<li class="divider"></li><li><a class="dropdown-element">'+ notification.message +
    	'<form:form class="accept-invitation" method="POST" action="/ui-psolve/search/filter">' +
      	'<button type="button"class="add-new-skill btn btn-info btn-circle-sm">'+
								'<i class="glyphicon glyphicon-ok-circle"></i>'+
							'</button>'+
							'<button type="button" class="btn btn-danger btn-circle-sm">'+
								'<i class="glyphicon glyphicon-remove-circle"></i>'+
							'</button></form:form></a></li>');
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