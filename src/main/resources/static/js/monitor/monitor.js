;
$(function () {
    setTimeout(ping, 20000);
    var $save = $('#save');
    $save.on('click', function () {
        addIp();
    });
});

var ping = function () {
    $ipAddresses = $('.ip-address');
    $ipAddresses.each(function () {
        var $td = $(this);
        var $tr = $td.parent();
        var ipAddress = $td.attr('data-address');
        $.ajax({
            type: "GET",
            url: "monitor/ping",
            data: {'ipAddress': ipAddress},
            dataType: "json",
            success: function (data) {
                $tr.attr('class', data['color']);
                $tr.children().each(function () {
                    $statusTd = $(this);
                    if ($statusTd.attr('class') == 'status') {
                        $statusTd.html(data['statusName']);
                    }
                });
            }
        });
    });
    setTimeout(ping, 20000);
};

var addIp = function () {

    $form = $(".add-ip-form");
    $form.attr("action", "/monitor/add")
        .attr("method", "post")
        .submit();
    // var ipAddr = $("#ipAddr").val();
    // var name = $("#name").val();
    // var commit = $("#commit").val();
    // var $modal = $('#addModal');
    //
    // $.post("monitor/add", {"ipAddr": ipAddr, "name": name, "commit": commit}, function (data) {
    //
    //     var status = data["status"];
    //     var message = data["message"];
    //     if (status == "success") {
    //         $modal.modal('hide');
    //         jQuery.message.success(message);
    //     } else if (status == "error") {
    //         jQuery.message.error(message);
    //     }
    // });
}
