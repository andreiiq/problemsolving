$(document).ready(
    function () {
        initProgressBars();

        function initProgressBars() {
            $(".progress-bar").each(function () {
                var bar_width = $(this).attr('aria-valuenow');
                $(this).width(bar_width + '%');
            });
        }
    });