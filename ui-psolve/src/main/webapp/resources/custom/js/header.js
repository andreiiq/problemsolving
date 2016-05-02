$(document).ready(function () {
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

})
;