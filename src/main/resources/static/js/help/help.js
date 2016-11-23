;
$(function () {

    var $save = $('#save');
    $save.on('click', function () {
        if (editFlg) {
            editHelp();
        } else {
            addHelp();
        }
    });

    $('.edit-help').on('click', function () {
        var id = $(this).attr('data-id');
        showEdit(id);
    });

    $('#addModal').on('hide.bs.modal', function (e) {
        editFlg = false;
    })
});
var editFlg = false;
var showEdit = function (id) {
    editFlg = true;
    $.get("/help/edit/" + id, function (data) {
        $('#question').val(data['question']);
        $('#answer').val(data['answer']);

        $(".add-help-form").append('<input type="hidden" id="id" name="id" value="' + id + '" >');
        $('#addModal').modal('show');
    });
};

var editHelp = function () {
    $form = $(".add-help-form");
    $form.attr("action", "/help/edit")
        .attr("method", "post")
        .submit();
};
var addHelp = function () {
    $form = $(".add-help-form");
    $form.attr("action", "/help/add")
        .attr("method", "post")
        .submit();
};

