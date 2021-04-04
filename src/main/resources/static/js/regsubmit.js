var time = 30;
var Itname;
$(document).ready(
    function () {
        $('#send-reg').click(
            function () {
                $("#warn").css("display","none");
                if (
                    $('#username').val() == "" ||
                    $('#password').val() == "" ||
                    $('#phone').val() == "" ||
                    $('#email').val() == "" ||
                    $('#username').val() == null ||
                    $('#password').val() == null ||
                    $('#phone').val() == null ||
                    $('#email').val() == null ||
                    $('#username').val() == undefined ||
                    $('#password').val() == undefined ||
                    $('#phone').val() == undefined ||
                    $('#email').val() == undefined
                ) {
                    $("#warn").css("display","block");
                    $("#warn").html("请完整填写信息!");
                    return;
                }
                $.ajax({
                    "url": "/user/Reg/SendVCode",
                    "data": {
                        username: $('#username').val(),
                        password: $('#password').val(),
                        phone: $('#phone').val(),
                        email: $('#email').val()
                    },
                    "type": "POST",
                    "dataType": "JSON",
                    "success": function (json) {
                        if (json.state != 1000) {
                            $("#warn").css("display","block");
                            $("#warn").html("发送验证码失败:" + json.message);
                        }
                        else {
                            $("#warn").css("display","block");
                            $("#warn").html("发送成功！");
                            $("#btn-reg").removeAttr("disabled");
                            $('#send-reg').attr("disabled", "true");
                            Itname = setInterval("TimeC()", 1000);
                        }
                    },
                    "fail": function (json) {
                        $("#warn").css("display","block");
                        $("#warn").html("发送验证码失败:" + json.message)
                    }
                });
            }
        );
        $('#btn-reg').click(
            function () {
                $.ajax({
                    "url": "../user/register",
                    "data": {
                        inputCode: $('#inputCode').val()
                    },
                    "type": "POST",
                    "dataType": "JSON",
                    "success": function (json) {
                        if (json.state != 1000) {
                            $("#warn").css("display","block");
                            $("#warn").html("注册失败:" + json.message);
                        }
                        else {
                            $("#warn").css("display","block");
                            $("#warn").html("注册成功！");
                            location.href = "";
                        }
                    },
                    "fail": function (json) {
                        $("#warn").css("display","block");
                        $("#warn").html("注册失败:" + json.message)
                    }
                });
            }
        );
    }
)
function TimeC() {
    if (time == 0) {
        $("#send-reg").attr("value", "验证");
        $("#send-reg").removeAttr("disabled");
        time = 30;
        clearInterval(Itname);
    }
    else {
        $("#send-reg").attr("disabled", "true");
        $("#send-reg").attr("value", "重新发送(" + time + ")");
    }
    time--;
}