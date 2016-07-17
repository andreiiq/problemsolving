$(document).ready(function () {

	  $('#view-my-projects').click(function () {
	        var form = $(this).parent();
	        var action = form.attr('action');
	        var method = form.attr('method');
	        
	        $.ajax({
	            contentType: 'application/json; charset=utf-8',
	            type: method,
	            url: action,
	            dataType: 'json',
	            data: JSON.stringify(form.serializeObject()),
	            success: function (data) {
	                $('#my-projects-search-results').empty();
	                for (var key in data.tasks) {
	                	  var html = '<div class="row" style="margin-top: 0.5em"><form class="form-horizontal" role="form" method="GET" action="'+ ctx +'/getTask"> <div type="button" class="project-button text-justify well img-rounded taskId="'+data.tasks[key].id +'" "> <h6 class="content-subtitle"><input type="hidden" name="taskId" value="'+ data.tasks[key].id +'"><span class="hover-color"><strong>' + data.tasks[key].title + '</strong></span> </h6> <div class="projectDescription"><b>Course: </b>'+data.tasks[key].course +' <br> <b>Points: </b>'+ data.tasks[key].points +'<br>' + data.tasks[key].description + '</div></div></form></div';
	                      $('#my-projects-search-results').append(html);
	                }
	                buildPageNavigationMyProjects(data.numberOfPages, data.currentPage);
	            },
	            error: function () {
	            }
	        });
	    });
	  
	  $('#view-my-mentorships-projects').click(function () {
	        var form = $(this).parent();
	        var action = form.attr('action');
	        var method = form.attr('method');
	        
	        $.ajax({
	            contentType: 'application/json; charset=utf-8',
	            type: method,
	            url: action,
	            dataType: 'json',
	            data: JSON.stringify(form.serializeObject()),
	            success: function (data) {
	                $('#my-mentorings-search-results').empty();
	                for (var key in data.tasks) {
	                	  var html = '<div class="row" style="margin-top: 0.5em"><form class="form-horizontal" role="form" method="GET" action="'+ ctx +'/getTask"> <div type="button" class="project-button text-justify well img-rounded taskId="'+data.tasks[key].id +'" "> <h6 class="content-subtitle"><input type="hidden" name="taskId" value="'+ data.tasks[key].id +'"><span class="hover-color"><strong>' + data.tasks[key].title + '</strong></span> </h6> <div class="projectDescription"><b>Course: </b>'+data.course +' <br> <b>Points: </b>'+ data.tasks[key].points +'<br>' + data.tasks[key].description + '</div></div></form></div';
	                      $('#my-mentorings-search-results').append(html);
	                }
	                buildPageNavigationMyMentorings(data.numberOfPages, data.currentPage);
	            },
	            error: function () {
	            }
	        });
	    });
	  

      $("#my-projects-search-results").on({
          mouseenter: function () {
              $(this).find('.hover-color').css("color", "white");
          },
          mouseleave: function () {
              $(this).find('.hover-color').css("color", "#337ab7");
          }
      },'.project-button');

	    
	
    $('#filter-btn-submit').click(function (e) {
    	 if (e.originalEvent !== undefined)
    	  {
    	    var currentPage = $('#current-page-number-ftasks');
    	    currentPage.val(0);
    		 
    	  }
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
                for (var key in data.tasks) {
                    appendSearchResults(data.tasks[key]);
                }
                buildPageNavigation(data.numberOfPages, data.currentPage);
            },
            error: function () {
            }
        });
    });
    $(document).on('click', '.nav-item-page-my', function () {
    	var currentPage = $('#current-page-mynumber');
    	currentPage.val($(this).text());
        $('#view-my-projects').trigger( "click" );
    });
    
    $(document).on('click', '.next-page-my', function () {
    	var currentPage = $('#current-page-mynumber');
    	currentPage.val(parseInt(currentPage.val()) + 1);
        $('#view-my-projects').trigger( "click" );
    });
    
    $(document).on('click', '.previous-page-my', function () {
    	var currentPage = $('#current-page-mynumber');
    	currentPage.val(parseInt(currentPage.val()) - 1);
        $('#view-my-projects').trigger( "click" );
    });
        
    $(document).on('click', '.nav-item-page-ment', function () {
    	var currentPage = $('#current-page-mentnumber');
    	currentPage.val($(this).text());
        $('#my-mentorings-search-results').trigger( "click" );
    });
    
    $(document).on('click', '.next-page-ment', function () {
    	var currentPage = $('#current-page-mentnumber');
    	currentPage.val(parseInt(currentPage.val()) + 1);
        $('#my-mentorings-search-results').trigger( "click" );
    });
    
    $(document).on('click', '.previous-page-ment', function () {
    	var currentPage = $('#current-page-mentnumber');
    	currentPage.val(parseInt(currentPage.val()) - 1);
        $('#my-mentorings-search-results').trigger( "click" );
    });
    
    
    
    $('#filter-search-results').on('click', '.nav-item-page-filter', function () {
    	var currentPage = $('#current-page-number-ftasks');
    	currentPage.val($(this).text());
        $('#filter-btn-submit').trigger( "click" );
    });
    
    $('#filter-search-results').on('click', '.next-page-filter', function () {
    	var currentPage = $('#current-page-number-ftasks');
    	currentPage.val(parseInt(currentPage.val()) + 1);
        $('#filter-btn-submit').trigger( "click" );
    });
    
    $('#filter-search-results').on('click', '.previous-page-filter', function () {
    	var currentPage = $('#current-page-number-ftasks');
    	currentPage.val(parseInt(currentPage.val()) - 1);
        $('#filter-btn-submit').trigger( "click" );
    });
    function appendSearchResults(data) {
  	  var html = '<div class="row" style="margin-top: 0.5em"><form class="form-horizontal" role="form" method="GET" action="'+ ctx +'/getTask"> <div type="button" class="project-button text-justify well img-rounded  taskId="'+data.id +'""> <h6 class="content-subtitle"><input type="hidden" name="taskId" value="'+ data.id +'"><span class="hover-color"><strong>' + data.title + '</strong></span> </h6> <div class="projectDescription"><b>Course: </b>'+data.course +' <br> <b>Points: </b>'+ data.points +'<br>' + data.description + '</div></div></form></div';
        $('#filter-search-results').append(html);
    }

    function buildPageNavigation(numberOfPages, currentPage) {
    	var html="<nav><ul class=\"pagination\">";
    	if(numberOfPages == 1 || numberOfPages == 0) {
    		return;
    	}
    	if(currentPage != 0) {
    		html = html.concat('<li> <a class="previous-page-filter" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a> </li>');
    	} 
        for (page = 0; page < numberOfPages; page++) {
            if (page == currentPage) {
                html = html.concat('<li class="active"><a class="nav-item-page-filter" href="#">' + page + '</a></li>');
            } else {
                html = html.concat('<li><a class="nav-item-page-filter" href="#">' + page + '</a></li>');
            }
        }
        if(currentPage != numberOfPages-1) {
        html = html.concat('<li> <a class="next-page-filter" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a> </li>');
        }
        html = html.concat('</ul> </nav>');
        $('#filter-search-results').append(html);
    }
    

    function buildPageNavigationMyProjects(numberOfPages, currentPage) {
    	var html="<nav><ul class=\"pagination\">";
    	if(numberOfPages == 1 || numberOfPages == 0) {
    		return;
    	}
    	if(currentPage != 0) {
    		html = html.concat('<li> <a class="previous-page-my" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a> </li>');
    	} 
        for (page = 0; page < numberOfPages; page++) {
            if (page == currentPage) {
                html = html.concat('<li class="active"><a class="nav-item-page-my" href="#">' + page + '</a></li>');
            } else {
                html = html.concat('<li><a class="nav-item-page-my" href="#">' + page + '</a></li>');
            }
        }
        if(currentPage != numberOfPages-1) {
        html = html.concat('<li> <a class="next-page-my" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a> </li>');
        }
        html = html.concat('</ul> </nav>');
        $('#my-projects-search-results').append(html);
    }
    

    function buildPageNavigationMyMentorings(numberOfPages, currentPage) {
    	var html="<nav><ul class=\"pagination\">";
    	if(numberOfPages == 1 || numberOfPages == 0) {
    		return;
    	}
    	if(currentPage != 0) {
    		html = html.concat('<li> <a class="previous-page-ment" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a> </li>');
    	} 
        for (page = 0; page < numberOfPages; page++) {
            if (page == currentPage) {
                html = html.concat('<li class="active"><a class="nav-item-page-ment" href="#">' + page + '</a></li>');
            } else {
                html = html.concat('<li><a class="nav-item-page-ment" href="#">' + page + '</a></li>');
            }
        }
        if(currentPage != numberOfPages-1) {
        html = html.concat('<li> <a class="next-page-ment" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a> </li>');
        }
        html = html.concat('</ul> </nav>');
        $('#my-mentorings-search-results').append(html);
    }
    
   
    
    $(document).on('change', '.select-skill', function() {
    	var parentContainer = $(this).parent();
    	var level = parentContainer.next().find(".select-level");
    	level.attr('name', 'skills[' + $(this).val() + ']');
    });
    
    var allowClose = false;
    $('.dropdown').on('shown.bs.dropdown', function () {
        allowClose = false;
    });
    $('.dropdown-toggle').on('click', function () {
        allowClose = true;
    });
    $('.dropdown').on('hide.bs.dropdown', function () {
        if (!allowClose) return false;
    });
    
    
    $(document).on('click', '.project-button', function(){
    	var form = $(this).parent();
    	 var action = form.attr('action');
         var method = form.attr('method');
         
         $.ajax({
             contentType: 'application/json; charset=utf-8',
             type: method,
             url: action,
             dataType: 'json',
             data: form.serializeObject(),
             success: function (data) {
            	 
            	 $('#view-selected-project').show();
            	 $('#view-selected-project').css('background-color', '#337ab7');
            	 $('#view-selected-project').css('color', 'white');

            	 $('#selected-project-container').empty();
            	 $('#selected-project-container').attr("taskId", data.id);
            	 $('#selected-project-container').attr("taskName", data.title);
             	 $('#view-selected-project').trigger( "click" );
             	var basicHTML = buildSelectedProjectBasic(data);
             	$('#selected-project-container').prepend(basicHTML);
             	
             	var solutionHtml = buildSelectedProjectSolution(data);
             	$('#selected-project-container').append(solutionHtml);
             	
             	var evaluationHtml =  buildSelectedProjectEvaluationModal(data);
             	$('#selected-project-container').append(evaluationHtml);
             	
             	
             },
             error: function () {
             }
         });
    	
    });	
    
    function buildSelectedProjectBasic(data) {
    	console.log(data.ownerEmail);
    	var htmlAssignationButton = "";
    	htmlAssignationButton = buildAssignButton(data);
    	
    	
    	
    	var htmlEvaluationButton = "" ;
    	if(data.teacherEmail == userEmail) {
    		htmlEvaluationButton= '<button type="button" class="btn btn-info btn-sm pull-right" data-toggle="modal"'+
     		'data-target="#evaluationModal">Evaluate Task</button>';
    	} 
    	
    	var htmlTaskInfo='<div class="form-group row">'+
    		'<label for="name-pr" class="col-sm-2 form-control-label">Name</label>'+
    		'<div class="col-sm-10">'+ data.title +'</div>'+ htmlAssignationButton + htmlEvaluationButton +
    	'</div>'+
    	'<div class="form-group row">'+
    		'<label for="description-pr" class="col-sm-2 form-control-label">Description</label>'+
    		'<div class="col-sm-10">'+data.description+ '</div>'+
    	'</div>'+
    	'<div class="form-group row">'+
    		'<label for="points-pr" class="col-sm-2 form-control-label">Points</label>'+
    		'<div class="col-sm-10">'+ data.points+ '</div>'+
    	'</div>';
    	
    	var htmlSubtaskInfo = "";
 	
        for (var key in data.subtasks) {
        	var assignHTML ="";
        	if(data.subtasks[key].owner == null && data.ownerEmail == userEmail) {
            	assignHTML = '<button subtask="' + data.subtasks[key].id +'" type="button" class="pull-right btn btn-info btn-sm assign-subtask-modal-btn" data-toggle="modal"'+
        		'data-target="#search-students-modal">Assign To Student</button>';
        	}else if(data.subtasks[key].owner == userEmail && data.subtasks[key].mentor == null) {
              	assignHTML = '<button subtask="' + data.subtasks[key].id +'" type="button" class="pull-right btn btn-info btn-sm assign-mentor-modal-btn" data-toggle="modal"'+
        		'data-target="#search-mentors-modal">Get Mentor</button>';
        	} else if(data.teacherEmail == userEmail){
        		assignHTML="";
        	}
        	
        	htmlSubtaskInfo = htmlSubtaskInfo + '<div class="panel-group">'+
    		'<div class="panel panel-default">'+
    			'<div class="panel-heading">'+
    				'<h4 class="panel-name">'+
    					'<a data-toggle="collapse" href="#collapse-subtask-selected-project-'+ key +'"> Subtask: '+ data.subtasks[key].title +'</a>'+ assignHTML +
    				'</h4>'+
    			'</div>'+
    			'<div id="collapse-subtask-selected-project-'+ key +'" class="panel-collapse collapse in">'+
    				'<div class="panel-body">'+
    					'<div class="form-group row">'+
    						'<label class="col-sm-2 form-control-label">Subtask</label>'+
    						'<div class="col-sm-10">'+data.subtasks[key].title+'</div>'+
    					'</div>'+
    					'<div class="form-group row">'+
						'<label class="col-sm-2 form-control-label">Owner</label>'+
						'<div class="col-sm-10">'+data.subtasks[key].owner+'</div>'+
						'</div>'+
						'<div class="form-group row">'+
						'<label class="col-sm-2 form-control-label">Mentor</label>'+
						'<div class="col-sm-10">'+data.subtasks[key].mentor+'</div>'+
						'</div>'+
    					'<div class="form-group row">'+
    						'<label class="col-sm-2 form-control-label">Description</label>'+
    						'<div class="col-sm-10">'+data.subtasks[key].description+'</div>'+
    					'</div>'+
    					'<div class="form-group row">'+
    						'<label class="col-sm-2 form-control-label">Points</label>'+
    						'<div class="col-sm-10">'+data.subtasks[key].points+'</div>'+
    					'</div>';
        	
    					var htmlSkillInfo = "";
    			        for (var key2 in data.subtasks[key].skills) {
    			        	htmlSkillInfo = htmlSkillInfo + '<div class="form-group row">'+
    						'<label class="col-sm-2 form-control-label">Skill</label>'+
    						'<div class="col-sm-4">'+data.subtasks[key].skills[key2].name + ' ' + data.subtasks[key].skills[key2].level+'</div>'+
    					'</div>';
    			        }
    			        htmlSubtaskInfo = htmlSubtaskInfo + htmlSkillInfo +'</div></div></div></div>';
    }
 		var assignmentModal = buildSelectedProjectAssignmentModule(data);
 		var mentorModal = buildSelectedProjectMentoringModal(data);

        htmlTaskInfo = htmlTaskInfo + htmlSubtaskInfo + assignmentModal + mentorModal;
        return htmlTaskInfo;
    }
    
    function buildSelectedProjectSolution(data) {
    	if(data.ownerEmail != userEmail) {
    		return;
    	}
    	
    	var downloadSolution = "";
    	if (data.solutionDTO != null) {
    		downloadSolution = '<a href="'+ ctx +'/downloadSolution/'+data.solutionDTO.id+'" download>'+
			'<span class="hover-color text-center">'+ data.solutionDTO.projectName +'</span></a>';
    	}
    	
    	if(data.teacherEmail == userEmail) {
    		var solutionPanel = '<div class="panel-group">'+
		'<div class="panel panel-default">'+
			'<div class="panel-heading">'+
				'<h4 class="panel-name">'+
					'<a data-toggle="collapse" href="#collapse-upload-solution">'+
						'Solution</a>'+
				'</h4>'+
			'</div>'+
			'<div id="collapse-upload-solution" class="panel-collapse collapse in">'+
				'<div class="panel-body">'+
						'<label  class="col-sm-2 form-control-label">Solution </label>'+ downloadSolution +
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>';
    		return solutionPanel;
    	}
    	
    	var solutionPanel = '<div class="panel-group">'+
		'<div class="panel panel-default">'+
			'<div class="panel-heading">'+
				'<h4 class="panel-name">'+
					'<a data-toggle="collapse" href="#collapse-upload-solution">'+
						'Solution</a>'+
				'</h4>'+
			'</div>'+
			'<div id="collapse-upload-solution" class="panel-collapse collapse in">'+
				'<div class="panel-body">'+
					'<form enctype="multipart/form-data" class="form-register"'+
						'action="' + ctx + '/uploadSolution" method="POST">'+
						'<label for="upload-solution" class="col-sm-2 form-control-label">Solution </label>'+ downloadSolution +
						'<input class="upload-solution" type="file" class="file"'+
							'name="file" />'+
						'<input id="solution-id" type="hidden" value="'+data.id+'" name="taskId" />'+
					'</form>'+
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>';
    	return solutionPanel;
    }
    
    $(document).on('click', '#assign-task-to-me', function () {
    	  var form =$(this).parent();
          var action = form.attr('action');
          var method = form.attr('method');
          
          $.ajax({
              contentType: 'application/json; charset=utf-8',
              type: method,
              url: action,
              dataType: 'json',
              data: JSON.stringify(form.serializeObject()),
              success: function (data) {
            	 var owner =  '<div class="form-group row"><label for="owner-pr" class="col-sm-2 form-control-label">Owner</label>'+
          		'<div class="col-sm-10">'+ userEmail +'</div>';
            	 var container = form.parent().parent();
            	 container.prepend(owner);
            	 form.empty();
            	 
            	 var solutionPanel = '<div class="panel-group">'+
         		'<div class="panel panel-default">'+
         			'<div class="panel-heading">'+
         				'<h4 class="panel-name">'+
         					'<a data-toggle="collapse" href="#collapse-upload-solution">'+
         						'Solution</a>'+
         				'</h4>'+
         			'</div>'+
         			'<div id="collapse-upload-solution" class="panel-collapse collapse in">'+
         				'<div class="panel-body">'+
         					'<form enctype="multipart/form-data" class="form-register"'+
         						'action="' + ctx + '/uploadSolution" method="POST">'+
         						'<label for="upload-solution" class="col-sm-2 form-control-label">Solution </label>'+
         						'<input class="upload-solution" type="file" class="file"'+
         							'name="file" />'+
         						'<input id="solution-id" type="hidden" value="'+$('#selected-project-container').attr('taskId')+'" name="taskId" />'+
         					'</form>'+
         				'</div>'+
         			'</div>'+
         		'</div>'+
         	'</div>';
            	 $('#selected-project-container').append(solutionPanel);
            	 
              },
              error: function () {
              }
          });
    });	
    
    $(document).on('click', '#submit-evaluation', function () {
        var form = $('#evaluate-task-form');
        var action = form.attr('action');
        var method = form.attr('method');
        
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: method,
            url: action,
            dataType: 'json',
            data: JSON.stringify(form.serializeObject()),
            success: function (data) {
            	   $('#evaluationModal').modal('hide');
            },
            error: function () {
            }
        });
    });

    function buildSelectedProjectMentoringModal(data) {
    	var htmlToAdd = 
    	'<div id="search-mentors-modal" class="modal fade" role="dialog">'+
    		'<div class="modal-dialog">'+
    			'<div class="modal-content">'+
    				'<div class="modal-header">'+
    					'<h4 class="modal-title">'+
    						'<form class="form-horizontal" role="form" method="GET"'+
    							'action="'+ ctx+'/search/findAllEligibleMentors">'+
    							'<div class="input-group">'+
    							'<input id="search-mentor-subtask" name="subtaskId" type="hidden"'+
    							'value="" />'+
    								'<input name="name" type="text"'+
    									'class="on-key-search"'+
    									'placeholder="Search for students" value=""> <span'+
    									'class="input-group-btn"> <input'+
    									'class="current-page-number" type="hidden" name="page" value="0" />'+
    									'<button style="background-color: white; border-color: #5bc0de"'+
    										'class="btn btn-secondary" type="button">'+
    										'<span class="pbs-serach-glyph glyphicon glyphicon-search"></span>'+
    									'</button>'+
    								'</span>'+
    							'</div>'+
    						'</form>'+
    					'</h4>'+
    				'</div>'+
    				'<div class="modal-body">'+
    					'<div class="list-group"></div>'+
    				'</div>'+
    				'<div class="modal-footer">'+
    					'<form class="assign-subtask form-horizontal" role="form" method="POST"'+
    						'action="'+ctx+'/assign/assignMentor">'+
    						'<input id="assign-mentor-email" name="receiverEmail" type="hidden"'+
    							'value="" />'+
    						'<input id="assign-mentor-subtask" name="subtaskID" type="hidden"'+
    							'value="" />'+
    					'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
    					'<button type="submit" class="assign-subtask-btn btn btn-primary">Submit</button>'+
    					'</form>'+
    				'</div>'+
    			'</div>'+
    		'</div>'+
    	'</div>';
    	return htmlToAdd;
    }
    
    
    
    function buildSelectedProjectAssignmentModule(data) {
    	var htmlToAdd = 
    	'<div id="search-students-modal" class="modal fade" role="dialog">'+
    		'<div class="modal-dialog">'+
    			'<div class="modal-content">'+
    				'<div class="modal-header">'+
    					'<h4 class="modal-title">'+
    						'<form class="form-horizontal" role="form" method="GET"'+
    							'action="'+ ctx+'/search/findAllEligibleUsers">'+
    							'<div class="input-group">'+
    							'<input id="search-students-subtask" type="hidden" name="taskId" value="" />'+
    								'<input name="name" type="text"'+
    									'class="on-key-search"'+
    									'placeholder="Search for students" value=""> <span'+
    									'class="input-group-btn"> <input'+
    									'class="current-page-number" type="hidden" name="page" value="0" />'+
    									'<button style="background-color: white; border-color: #5bc0de"'+
    										'class="btn btn-secondary" type="button">'+
    										'<span class="pbs-serach-glyph glyphicon glyphicon-search"></span>'+
    									'</button>'+
    								'</span>'+
    							'</div>'+
    						'</form>'+
    					'</h4>'+
    				'</div>'+
    				'<div class="modal-body">'+
    					'<div class="list-group"></div>'+
    				'</div>'+
    				'<div class="modal-footer">'+
    					'<form class="assign-subtask form-horizontal" role="form" method="POST"'+
    						'action="'+ctx+'/assign/assignTask">'+
    						'<input id="assign-user-email" name="receiverEmail" type="hidden"'+
    							'value="" />'+
    						'<input id="assign-user-subtask" name="subtaskID" type="hidden"'+
    							'value="" />'+
    					'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
    					'<button type="submit" class="assign-subtask-btn btn btn-primary">Submit</button>'+
    					'</form>'+
    				'</div>'+
    			'</div>'+
    		'</div>'+
    	'</div>';
    	return htmlToAdd;
    }
    
    
    function buildSelectedProjectEvaluationModal(data) {
    	
    	var projectEvaluationHtml =
    		'<div id="evaluationModal" class="modal fade" role="dialog">'+
    		'<div class="modal-dialog">'+
    			'<div class="modal-content">'+
    				'<div class="modal-header">'+
    					'<button type="button" class="close" data-dismiss="modal">&times;</button>'+
    					'<h4 class="modal-title">Evaluate Task</h4>'+
    				'</div>'+
    				'<form id="evaluate-task-form" class="form-horizontal" role="form" method="POST"'+
					'action="'+ctx+'/evaluateTask">'+
    				'<div class="modal-body">'+
    					'<div class="form-group row">'+
    						'<label for="task-eval-title " class="col-sm-2 form-control-label">Task</label>'+
    						'<div class="task-eval-title col-sm-10">'+ data.title +'</div>'+
    						' <input type="text" class="selected-pr-task-grade form-control" name="'+data.id +'">'+
    					'</div>';
    					var projectEvaluationSubtaskHtml = " ";
    					for (var key in data.subtasks) {
    						projectEvaluationSubtaskHtml= projectEvaluationSubtaskHtml +
    					'<div class="form-group row">'+
    						'<label for="subtask-eval-title" class="col-sm-2 form-control-label">Subtask</label>'+
    						'<div class="subtask-eval-title col-sm-10">'+ data.subtasks[key].title +'</div>'+
    						'<input type="text" class="form-control" name="'+  data.subtasks[key].id +'">'+
    					'</div>';
    					}
    					projectEvaluationHtml = projectEvaluationHtml + projectEvaluationSubtaskHtml;
    					projectEvaluationHtml = projectEvaluationHtml + '</div>'+
    					'<div class="modal-footer">'+
    						'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
    						'<button id="submit-evaluation" type="button" class="assign-submit-btn btn btn-primary">Submit</button>'+
    					'</div>'+
						'</form>'+
    				'</div>'+
    			'</div>'+
    		'</div>'+
    	'</div>';
    	return projectEvaluationHtml;				
    }
    
    function buildAssignButton(data) {
    	console.log(data);
    	if((data.ownerEmail == null && data.teacherEmail != userEmail)) {
    		var assignationForm = '<form class="form-horizontal" role="form" method="POST" action="'+ ctx +'/assignTask">'+
			  ' <input type="hidden" name="id" value="'+data.id+'"/>'+
			  '<button id="assign-task-to-me" type="button" class="btn btn-info btn-sm pull-right">Assign Task to Me</button>'+
			  '</form>';

    		return assignationForm;
    	}
    	return 	'</div><div class="form-group row"><label for="owner-pr" class="col-sm-2 form-control-label">Owner</label>'+
		'<div class="col-sm-10">'+ data.ownerEmail +'</div></div>';
    }
    

});