$(document).ready(
		function() {
		    $('[data-toggle="popover"]').popover();

			$('#view-create-projects').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('cproject-content')) {
					openContent.addClass('hidden');
					$('#cproject-content').removeClass('hidden');
				}
			});

			$('#view-active-projects').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('aproject-content')) {
					openContent.addClass('hidden');
					$('#aproject-content').removeClass('hidden');
				}
			});

			$('#view-sent-projects').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('sproject-content')) {
					openContent.addClass('hidden');
					$('#sproject-content').removeClass('hidden');
				}
			});
			
			
			$('#view-selected-project').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('vproject-content')) {
					openContent.addClass('hidden');
					$('#vproject-content').removeClass('hidden');
				}
			});
						
			
		   $(document).on('click','.add-new-subtask', function() {
			  var lastEntry = $("#subtask-acordion");
			  var currentLastCollapsable = $('.panel.panel-default:last');
			  var number = parseFloat(currentLastCollapsable.attr('number')) + 1;
			  var htmlToAdd = 
				  '<div class="panel panel-default" number="'+number+'">'+
			    '<div class="panel-heading">'+
			      '<h4 class="panel-title">'+
			        '<a data-toggle="collapse" data-parent="#subtask-acordion" href="#collapse-subtask-'+number+'">'+
			        'Subtask'+number+'</a>'+
			      '</h4>'+
				'<div class="subtask-btn">'+
				'<button type="button" class="remove-current-subtask btn btn-danger btn-circle-sm"><i class="glyphicon glyphicon-minus"></i></button>  '+  
				'</div>'+
			    '</div>'+
			    '<div id="collapse-subtask-'+number+'" class="panel-collapse collapse in">'+
			      '<div class="panel-body">'+
			        '<div class="form-group row">'+
			      '<label for="subtask-pr" class="col-sm-2 form-control-label">Subtask</label>'+
			    '<div class="col-sm-10">'+
			      '<input type="text" class="form-control subtask-pr" placeholder="Name">'+
			    '</div>'+
			    '</div>'+
			      '<div class="form-group row">'+
			    '<label for="subtask-desc-pr" class="col-sm-2 form-control-label">Info</label>'+
			    '<div class="col-sm-10">'+
			      '<input type="text" class="form-control subtask-desc-pr" placeholder="Info">'+
			    '</div></div>'+
			    '<div class="form-group row">'+
			    '<label for="points-pr" class="col-sm-2 form-control-label">Points</label>'+
			    '<div class="col-sm-10">'+
			      '<input class=" points-pr range-input form-control" type="range" data-toggle="popover" data-content="12" name="rangeInput" min="0" max="999"/>'+
			    '</div>'+
			  '</div>'+
			      '<div class="form-group row">'+
			    '<label for="select-skill" class="col-sm-2 form-control-label">Skill</label>'+
			    '<div class="col-sm-4">'+
			      '<select class="form-control select-skill" >'+
			      '<option>Java</option>'+
			      '<option>PHP</option>'+
			      '<option>Javascript</option>'+
			      '</select>'+
			    '</div>'+
			    '<div class="col-sm-4">'+
			      '<input type="text" class="form-control select-level" placeholder="Level">'+
			    '</div>'+
			    '<div class="col-sm-1">'+
				'<button type="button" class="add-new-skill btn btn-info btn-circle-sm"><i class="glyphicon glyphicon-plus"></i></button>'+  
				'</div>'+
				 '<div>'+
			     '<button type="button" class="remove-skill btn btn-danger btn-circle-sm"><i class="glyphicon glyphicon-minus"></i></button>'+    
				'</div>'+
			    '</div>'+
			  '</div>'+
			'</div>'+
			  '</div>';
			  
			 lastEntry.append(htmlToAdd);
//			 positionAddRemoveButtons();
				
		   });
		   $(document).on('click', '.remove-current-subtask', function() {
			   var deleteEntry = this.closest('.panel.panel-default');
			   deleteEntry.remove();
//			   positionAddRemoveButtons();

		   });
		   $(document).on('click','.add-new-skill', function() {
			   var container = $(this).closest('.form-group.row');
			   container.parents(':eq(0)').append(container.get(0).outerHTML);
		   });
		   
		   $(document).on('click','.remove-skill', function() {
			  var skillInput = $(this).closest('.form-group.row');
			  skillInput.remove();
		   });
		   

		 $('.range-input').change(function() {
			 $(this).attr("data-content", this.value);
		
		 });
		 
		 $('#header-search').click(function() {
			 var typeaheadSource = [{ id: 1, name: 'John'}, { id: 2, name: 'Alex'}, { id: 3, name: 'Terry'}];

			 $(this).typeahead({
			     source: typeaheadSource
			 });
		 });
		 
		 $('#accept-reset-project-form').click(function () {
			 $('#create-project-form').load("/badger/teacher/getCreateProjectForm");
			 $('.modal-backdrop.fade.in').remove();
		 });
		 
		 $('.project-button').hover(function() {
			$(this).find('.hover-color').css("color", "white");
		 },
		 function(){ 
			 $(this).find('.hover-color').css("color", "#337ab7"); 
			 });
		 
		 
		 $('#edit-seleted-project').click(function() {
			 $('.form-control').removeAttr("readonly");
			 $('.form-control').removeAttr("disabled");
		 });
		 
//			$('.projectDescription').text(
//					function() {
//						var firstStringPart = $(this).text().substr(0,
//								charLength);
//						var lastSpace = firstStringPart.lastIndexOf(".");
//
//						var trimmedFirstPart = firstStringPart.substr(0, Math
//								.min(firstStringPart.length, lastSpace +1));
//
//						var acordionDescription = $(this).next();
//						acordionDescription
//								.text($(this).text().substr(
//										trimmedFirstPart.length,
//										$(this).text().length));
//
//						return trimmedFirstPart;
//					});
//		
//		 function positionAddRemoveButtons() {
//			 var addButton = $('.add-new-subtask');
//			 var removeButton = $('.remove-current-subtask');
//			 console.log($('.panel.panel-default').length )
//			 if($('.panel.panel-default').length == 1) {
//				 removeButton.remove();
//			 }
//			 
//			 $('.subtask-btn:last').prepend(addButton);
//			}
		});
