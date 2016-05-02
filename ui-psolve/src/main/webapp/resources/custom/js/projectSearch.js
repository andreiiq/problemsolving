$(document).ready(function () {

    $('#filter-btn-submit').click(function () {
        var form = $('#filter-search-form');
        $('#filter-search-results').empty();

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: form.attr('method'),
            url: form.attr('action'),
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

    $('#filter-search-results').on('click', '.nav-item-page', function () {
        $('#current-page-number').val($(this).text());
        $('#filter-btn-submit').trigger( "click" );
    });


    function appendSearchResults(data) {
        var html = '<div class="row" style="margin-top: 0.5em"> <div type="button" class="project-button text-justify well img-rounded "> <h6 class="content-subtitle"> <span class="hover-color"><strong>' + data.title + '</strong></span> </h6> <div class="projectDescription">Lorem Ipsum is simply dummy text of the printing</div> <div id="projectDescriptionBody2" class="accordion-body collapse out">' + data.description + '</div> <a class="show-classroom-content" data-toggle="collapse" href="#projectDescriptionBody2"><span class="hover-color"> <strong>(Show More)</strong></span> </a> </div> </div>';

        $('#filter-search-results').append(html);
    }

    function buildPageNavigation(numberOfPages, currentPage) {
        var html = ' <nav> <ul class="pagination"> <li> <a id="previous-page" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a> </li>';
        for (page = 0; page < numberOfPages; page++) {
            if (page == currentPage) {
                html = html.concat('<li class="active"><a class="nav-item-page" href="#">' + page + '</a></li>');
            } else {
                html = html.concat('<li><a class="nav-item-page" href="#">' + page + '</a></li>');
            }
        }

        html = html.concat('<li> <a id="next-page" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a> </li> </ul> </nav>');
        $('#filter-search-results').append(html);
    }
});