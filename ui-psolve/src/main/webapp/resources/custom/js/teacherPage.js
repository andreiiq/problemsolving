$(document).ready(
    function () {

        $('#view-create-projects').click(function () {
            var openContent = $('.activity-content-wraper >:not(.hidden)');
            if (!openContent.hasClass('cproject-content')) {
                openContent.addClass('hidden');
                $('#cproject-content').removeClass('hidden');
            }
        });

        $('#search-projects').click(function () {
            var openContent = $('.activity-content-wraper >:not(.hidden)');
            if (!openContent.hasClass('aproject-content')) {
                openContent.addClass('hidden');
                $('#aproject-content').removeClass('hidden');
            }
        });
        
        $('#view-my-projects').click(function () {
            var openContent = $('.activity-content-wraper >:not(.hidden)');
            if (!openContent.hasClass('mproject-content')) {
                openContent.addClass('hidden');
                $('#mproject-content').removeClass('hidden');
            }
        });

        $('#view-solutions-button').click(function () {
            var openContent = $('.activity-content-wraper >:not(.hidden)');
            if (!openContent.hasClass('sproject-content')) {
                openContent.addClass('hidden');
                $('#sproject-content').removeClass('hidden');
            }
        });


        $('#view-selected-project').click(function () {
            var openContent = $('.activity-content-wraper >:not(.hidden)');
            if (!openContent.hasClass('vproject-content')) {
                openContent.addClass('hidden');
                $('#vproject-content').removeClass('hidden');
            }
        });


        $(document).on('click', '.add-new-subtask', function () {
            var lastEntry = $("#subtask-acordion");
            var currentLastCollapsable = $('.panel.panel-default:last');
            var number = parseFloat(currentLastCollapsable.attr('number')) + 1;
            var htmlToAdd =
                '<div class="panel panel-default" number="' + number + '">' +
                '<div class="panel-heading">' +
                '<h4 class="panel-title">' +
                '<a data-toggle="collapse" data-parent="#subtask-acordion" href="#collapse-subtask-' + number + '">' +
                'Subtask' + number + '</a>' +
                '</h4>' +
                '<div class="subtask-btn">' +
                '<button type="button" class="remove-current-subtask btn btn-danger btn-circle-sm"><i class="glyphicon glyphicon-minus"></i></button>  ' +
                '</div>' +
                '</div>' +
                '<div id="collapse-subtask-' + number + '" class="panel-collapse collapse in">' +
                '<div class="panel-body">' +
                '<div class="form-group row">' +
                '<label for="subtask-pr" class="col-sm-2 form-control-label">Subtask</label>' +
                '<div class="col-sm-10">' +
                '<input type="text" class="form-control subtask-pr" placeholder="Name">' +
                '</div>' +
                '</div>' +
                '<div class="form-group row">' +
                '<label for="subtask-desc-pr" class="col-sm-2 form-control-label">Info</label>' +
                '<div class="col-sm-10">' +
                '<input type="text" class="form-control subtask-desc-pr" placeholder="Info">' +
                '</div></div>' +
                '<div class="form-group row">' +
                '<label for="points-pr" class="col-sm-2 form-control-label">Points</label>' +
                '<div class="col-sm-10">' +
                '<input class=" points-pr range-input form-control" type="range" data-toggle="popover" data-content="12" name="rangeInput" min="0" max="999"/>' +
                '</div>' +
                '</div>' +
                '<div class="form-group row">' +
                '<label for="select-skill" class="col-sm-2 form-control-label">Skill</label>' +
                '<div class="col-sm-4">' +
                '<select class="form-control select-skill" >' +
                '<option>Java</option>' +
                '<option>PHP</option>' +
                '<option>Javascript</option>' +
                '</select>' +
                '</div>' +
                '<div class="col-sm-4">' +
                '<input type="text" class="form-control select-level" placeholder="Level">' +
                '</div>' +
                '<div class="col-sm-1">' +
                '<button type="button" class="add-new-skill btn btn-info btn-circle-sm"><i class="glyphicon glyphicon-plus"></i></button>' +
                '</div>' +
                '<div>' +
                '<button type="button" class="remove-skill btn btn-danger btn-circle-sm"><i class="glyphicon glyphicon-minus"></i></button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';

            lastEntry.append(htmlToAdd);
//			 positionAddRemoveButtons();

        });
        $(document).on('click', '.remove-current-subtask', function () {
            var deleteEntry = this.closest('.panel.panel-default');
            deleteEntry.remove();
//			   positionAddRemoveButtons();

        });
        $(document).on('click', '.add-new-skill', function () {
            var container = $(this).closest('.form-group.row');
            container.parents(':eq(0)').append(container.get(0).outerHTML);
        });

        $(document).on('click', '.remove-skill', function () {
            var skillInput = $(this).closest('.form-group.row');
            skillInput.remove();
        });


        $('.range-input').change(function () {
            $(this).attr("data-content", this.value);

        });

        $('#header-search').click(function () {
            var typeaheadSource = [{id: 1, name: 'John'}, {id: 2, name: 'Alex'}, {id: 3, name: 'Terry'}];

            $(this).typeahead({
                source: typeaheadSource
            });
        });

        $('#accept-reset-project-form').click(function () {
            $('#create-project-form').load("/badger/teacher/getCreateProjectForm");
            $('.modal-backdrop.fade.in').remove();
        });


        $("#filter-search-results").on({
            mouseenter: function () {
                $(this).find('.hover-color').css("color", "white");
            },
            mouseleave: function () {
                $(this).find('.hover-color').css("color", "#337ab7");
            }
        },'.project-button');
        
        
        $('#view-solutions-button').click(function(){
        	var form = $(this).parent();
        	 var action = form.attr('action');
             var method = form.attr('method');
             
             var resultContainer = $('#solutions-container');
             resultContainer.empty();
             var html="";
             $.ajax({
                 contentType: 'application/json; charset=utf-8',
                 type: method,
                 url: action,
                 dataType: 'json',	
                 data: form.serializeObject(),
                 success: function (data) {
                 	console.log(data);
                 	$.each(data, function(key, value) { 
                 		html = html.concat('<div class="solution-wraper row">' +
                		'<div class="panel panel-default">' +
             			'<div class="blue-header panel-heading">' +
             		     '<h3 class="panel-name">'+ key +'</h3>' +
             			'</div><div class="panel-body">');
    	                 $.each( value, function( index, solution){
    	                	console.log(solution);
    	                	html = html.concat('<div class="solution col-lg-3">' +
    	                			'<a href="'+ ctx +'/teacher/downloadSolution/'+solution.id+'" download>'+		
                 					'<img class="solution-img img-responsive img-thumbnail"' +
                 					'src="'+ ctx +'/resources/images/archive_icon.png"/></a> <span class="hover-color text-center">'+ solution.projectName +'</span>' +
                 				     '</div>');
    	                });
    	                html = html.concat('</div></div></div>');
                 	});
	                resultContainer.append(html);

                 	}
                 	});
             });
        	

        $('#edit-seleted-project').click(function () {
            $('.form-control').removeAttr("readonly");
            $('.form-control').removeAttr("disabled");
        });
        
        

    });
