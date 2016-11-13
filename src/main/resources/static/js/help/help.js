;
$(function () {

    var $save = $('#save');
    $save.on('click', function () {
        addHelp();
    });

});

var addHelp = function () {
    $form = $(".add-help-form");
    $form.attr("action", "/help/add")
        .attr("method", "post")
        .submit();
};

