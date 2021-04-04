$(document).ready(
    function(){
        $('#login-btn').click(
            function(){
                $('#warn').css("display","none");
                if(
                    $('#username').val() == "" ||
                    $('#username').val() == null ||
                    $('#username').val() == undefined 
                    
                ){
                    $('#warn').html("用户名不能为空!!!");
                    $('#warn').css("display","block");
                    return;
                }
                else if(
                    $('#password').val() == "" ||
                    $('#password').val() == null ||
                    $('#password').val() == undefined
                ){
                    $('#warn').html("密码不能为空!!!");
                    $('#warn').css("display","block");
                    return;
                }
                $.ajax({
                    "url":"/user/login",
                    "data":{
                        username:$('#username').val(),
                        password:$('#password').val()
                    },
                    "type":"POST",
                    "dataType":"JSON",
                    "success":function(json){
                        if(json.state!=1000){
                            $('#warn').html(json.message);
                            $('#warn').css("display","block");
                        }
                        else{
                            $('#warn').html("登陆成功！");
                            $('#warn').css("display","block");
                            // location.href="";
                        }
                    },
                    "fail":function(json){
                        ('#warn').html(json.message);
                        $('#warn').css("display","block");
                    }
                })
            }
        )
    }
)