
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
        var username = $("#username").val();
        var password = $("#password").val();
      //  var formData = $("form").serializeArray();
        $.ajax({
            'type': 'POST',
            'url': "/sign_in",
            'contentType': 'application/json',
            'data': JSON.stringify({
                username: $("#username").val(),
                password: $("#password").val()
            }),
            'success': function (data, status) {
                window.location.href = '/swagger-ui.html';
            },
            'error': function () {
                console.log("error")
            }});

    });

});