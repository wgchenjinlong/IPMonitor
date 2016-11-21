;
$(function () {
    // setTimeout(getResult, 10000);
    ping();
    setTimeout(ping, 10000);
    var $save = $('#save');
    $save.on('click', function () {
        validateIpInfo();
    });

    $('.sound-icon').click(function () {
        if (isPlay) {
            pauseSound();
            isPlay = false;
            isManual = true;
            $(this).removeClass('glyphicon-volume-up');
            $(this).addClass('glyphicon-volume-off');
        } else {
            if (hasError) {
                playSound();
                isPlay = true;
            }
            isManual = false;
            $(this).removeClass('glyphicon-volume-off');
            $(this).addClass('glyphicon-volume-up');
        }
    });
});

var isPlay = false;
var isManual = false;
var hasError = false;
var audioElement = null;

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
                    } else if ($statusTd.attr('class') == 'lost') {
                        $statusTd.html(data['lost']);
                    }
                });
                //var $soundIcon = $('.sound-icon');
                hasError = isShowSound();
                if (hasError) {
                    //$soundIcon.css('display', 'block');
                    createAudio();
                    if (!isPlay && !isManual) {
                        playSound();
                        isPlay = true;
                    }

                } else {
                    //$soundIcon.css('display', 'none');
                    if(isPlay) {
                        pauseSound();
                    }
                    isPlay = false;
                }
            }
        });
    });
    setTimeout(ping, 20000);
};

var validateIpInfo = function () {

    var $ipAddr = $("#ipAddr");
    var $name = $("#name");
    var $commit = $("#commit");

    var ipAddr = $ipAddr.val();
    var name = $name.val();
    var commit = $commit.val();

    var result = true;
    $.post("monitor/validate", {"ipAddr": ipAddr, "name": name, "commit": commit}, function (data) {

        var ipAddrResult = data['ipAddr'];
        var nameResult = data['name'];
        var commitResult = data['commit'];

        if (ipAddrResult) {
            $ipAddr.next().remove();
            $ipAddr.after('<ul class="parsley-errors-list filled monitor-errors">' +
                '<li class="parsley-required">' +
                ipAddrResult +
                '</li>' +
                '</ul>');
            result = false;
        }
        if (nameResult) {
            $name.next().remove();
            $name.after('<ul class="parsley-errors-list filled monitor-errors">' +
                '<li class="parsley-required">' +
                nameResult +
                '</li>' +
                '</ul>');
            result = false;
        }
        if (commitResult) {
            $commit.next().remove();
            $commit.after('<ul class="parsley-errors-list filled monitor-errors">' +
                '<li class="parsley-required">' +
                commitResult +
                '</li>' +
                '</ul>');
            result = false;
        }
        if (result) {
            addIp();
        }
    });
    return result;
};

var addIp = function () {
    $form = $(".add-ip-form");
    $form.attr("action", "/monitor/add")
        .attr("method", "post")
        .submit();
};
var createAudio = function () {
    if (audioElement == null) {
        audioElement = document.createElement('audio');
        audioElement.setAttribute('src', 'files/error.mp3');
        audioElement.setAttribute('loop', 'loop');
    }
};
var playSound = function () {
    audioElement.play();
};
var pauseSound = function () {
    audioElement.pause();
};
var isShowSound = function () {
    var isShow = false;
    var $trs = $("#monitor-table tbody tr");
    $trs.each(function () {
        if($(this).hasClass('danger')) {
            isShow = true;
        }
    });

    return isShow;
};
var getResult = function () {

    $.ajax({
        type: "GET",
        url: "monitor/result",
        dataType: "json",
        success: function (data) {

            console.log(data);


            // hasError = isShowSound();
            // if (hasError) {
            //     //$soundIcon.css('display', 'block');
            //     createAudio();
            //     if (!isPlay && !isManual) {
            //         playSound();
            //         isPlay = true;
            //     }
            //
            // } else {
            //     //$soundIcon.css('display', 'none');
            //     if(isPlay) {
            //         pauseSound();
            //     }
            //     isPlay = false;
            // }
        }
    });
    setTimeout(getResult, 10000);
}
