;
$(function(){

    setTimeout(pingIp, 5000);

});

var pingIp = function(){

    $.ajax({
        type: "GET",
        url: "ping",
        data: {},
        dataType: "json",
        success: function(data){
            $('#monitor-table tbody').empty();
            var html = '';
            $.each(data, function(index, comment){
                html += '<tr class="' + comment["color"] + '">'
                    + '<td>' + (index+1) + '</td>'
                    + '<td>' + comment["ipAddress"] + '</td>'
                    + '<td>' + comment["statusName"] + '</td>'
                    + '</tr>';
            });
            $('#monitor-table tbody').html(html);


            setTimeout(pingIp, 5000);
        }
    });
}