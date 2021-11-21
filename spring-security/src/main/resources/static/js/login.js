function login() {
    var formData = {"username":$("#username").val(), "password":$("#password").val(), "captcha":$("#captcha").val()};
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/auth/login",
        data: formData,
        success: function (result) {
            if (result.code == 200) {
                location.href = "/auth/success";
            } else if (result.code == 1003001) {
                $("#captcha").val("");
                var oldSrc =  $("#captchaImg").attr("src");
                $("#captchaImg").attr("src", oldSrc+"?");
            } else {
                $("#errorMsg").html(result.message);
            }
        },
        error: function(data) {
            $("#errorMsg").html(data.message);
        }

    });
}