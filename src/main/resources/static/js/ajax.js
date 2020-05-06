function ajax(url, method, requestMap, successHandler, errorHandler) {
    $.ajax({
        url: url,
        method: method,
        data: requestMap,
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true
    }).done(successHandler).fail(errorHandler).always(function () {
        console.log('Completed call ' + url + ' method ' + method);
    });
}