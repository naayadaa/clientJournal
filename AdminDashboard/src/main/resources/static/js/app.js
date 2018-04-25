
function test() {
    $.ajax({
        'type': 'GET',
        'url': "http://localhost:8082/client-journal-resource/test",
        'contentType': 'application/json',
        'success': function (data, status) {
            $( "div#test-response" )
                .html( "<H1>" + data +"</H1>" );

        }});

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $( "#signIn" ).click(function() {

        $.ajax({
            'type': 'POST',
            'url': "/login",
            'contentType': 'multipart/form-data',
            'data': JSON.stringify({
                username: $("#username").val(),
                password: $("#password").val()
            }),
            'dataType': 'json',
            'error': function () {
                console.log("error")
            },
            'success': function (data, status) {
                window.location.href = '/test.html';
            }});

    });

});