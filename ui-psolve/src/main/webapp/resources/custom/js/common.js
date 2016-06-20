$(document).ready(
    function () {
        initProgressBars();
        onReady();

        function onReady() {
        	$('#view-selected-project').hide();
        }
        
        function initProgressBars() {
            $(".progress-bar").each(function () {
                var bar_width = $(this).attr('aria-valuenow');
                $(this).width(bar_width + '%');
            });
        }
    });

