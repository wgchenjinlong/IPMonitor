jQuery.message = (function () {
    var success = function (msg) {
        $.growl(
            {message: msg},
            {type: 'success'}
        );
    }, error = function (msg) {
        $.growl(
            {message: msg},
            {type: 'danger'}
        );
    };

    return {
        success: success,
        error: error
    };
})();