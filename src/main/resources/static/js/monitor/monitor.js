;
$(function () {
    setTimeout(ping, 10000);
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
    setTimeout(ping, 10000);
}


var pingIp = function () {
    $.ajax({
        type: "GET",
        url: "monitor/ping",
        data: {},
        dataType: "json",
        success: function (data) {
            $('#monitor-table tbody').empty();
            var html = '';
            $.each(data, function (index, comment) {
                html += '<tr class="' + comment["color"] + '">'
                    + '<td>' + (index + 1) + '</td>'
                    + '<td>' + comment["ipAddress"] + '</td>'
                    + '<td>' + comment["statusName"] + '</td>'
                    + '</tr>';
            });
            $('#monitor-table tbody').html(html);


            setTimeout(pingIp, 5000);
        }
    });
}