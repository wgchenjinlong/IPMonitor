;
$(function () {
    setTimeout(ping, 20000);
    var $save = $('#save');
    var $modal = $('#addModal');
    $save.on('click', function () {
        addIp();
    });
    $modal.on('hidden.bs.modal', function (e) {
        window.location.href = 'monitor';
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
    var ipAddr = $("#ipAddr").val();
    var name = $("#name").val();
    var commit = $("#commit").val();
    var $modal = $('#addModal');

    $.post("monitor/add", {"ipAddr": ipAddr, "name": name, "commit": commit}, function (data) {

        var status = data["status"];
        if (status == "success") {
            $modal.modal('hide');
        } else if (status == "error") {

        }
    });
}
