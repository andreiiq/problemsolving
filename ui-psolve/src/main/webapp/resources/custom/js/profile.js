$(document).ready(
		function() {
			$('[data-toggle="popover"]').popover();
			$('.awarded-badge').click(
					function() {
						$('#badge-name').css('display', 'block');
						$('#badge-description').css('display', 'block');

						$('#badge-name>.content-subtitle').html(
								$(this).attr("name"));
						$('#badge-description').html(
								$(this).attr("description"));

						var currentlySelected = $('.activity-content-wraper')
								.find(".selected");
						currentlySelected.css("border", "");
						currentlySelected.removeClass('selected');

						$(this).css("border", "2px solid #337ab7");
						$(this).addClass('selected');
					});
			var charLength = 200;

			$('#view-badge-content').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('badge-content')) {
					openContent.addClass('hidden');
					$('#badge-content').removeClass('hidden');
				}
			});

			$('#view-classroom-content').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('classroom-content')) {
					openContent.addClass('hidden');
					$('#classroom-content').removeClass('hidden');
				}
			});

			$('#view-chronology-content').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('chronology-content')) {
					openContent.addClass('hidden');
					$('#chronology-content').removeClass('hidden');
				}
			});

			$('#view-achievement-content').click(function() {
				var openContent = $('.activity-content-wraper >:not(.hidden)');
				if (!openContent.hasClass('achievements-content')) {
					openContent.addClass('hidden');
					$('#achievements-content').removeClass('hidden');
				}
			});

			$('.classroomDescription').text(
					function() {
						var firstStringPart = $(this).text().substr(0,
								charLength);
						var lastSpace = firstStringPart.lastIndexOf(".");

						var trimmedFirstPart = firstStringPart.substr(0, Math
								.min(firstStringPart.length, lastSpace +1));

						var acordionDescription = $(this).next();
						acordionDescription
								.text($(this).text().substr(
										trimmedFirstPart.length,
										$(this).text().length));

						return trimmedFirstPart;
					});
			$('.')
		});
