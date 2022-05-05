function input_check(id,text){
    var input = $("#"+id).val()
    if (input==null || input.length===0||input==""){
        $("#"+id). css("box-shadow","0 0px 4px 0 red");
        $("#"+id). css("border-color","white");
        alert(text);
        return false;
    }
    $("#"+id). css("box-shadow","none");
    $("#"+id). css("border","1px solid #D8D8D8");
    return true;
}

function password_same_check(id1,id2){
    var input = $("#"+id1).val()
    var input2 = $("#"+id2).val()
    if (input==null || input.length===0||input==""){
        $("#"+id1). css("box-shadow","0 0px 4px 0 red");
        $("#"+id1). css("border-color","white");
        $("#"+id2). css("box-shadow","0 0px 4px 0 red");
        $("#"+id2). css("border-color","white");
        alert("Password need to be same");
        return false;
    }
    $("#"+id1). css("box-shadow","none");
    $("#"+id1). css("border","1px solid #D8D8D8");
    $("#"+id2). css("box-shadow","none");
    $("#"+id2). css("border","1px solid #D8D8D8");
    return true;
}

function getURLVariable(x) {
    var url_link = window.location.search.substring(1);
    var s = url_link.split("&");
    for (let i=0; i<s.length; i++) {
        var pair = s[i].split("=");
        if(pair[0] === x){
            return pair[1];
        }
    }
}


function login(){

    var user_name = $("#username").val();
    var pass_word = $("#password").val();
    var ucheck= input_check("username","Please enter your username");
    var pcheck = input_check("password","Please enter your password");

    if(ucheck && pcheck) {
        $.get("login", {
                "username":$("#username").val(),
                "password":$("#password").val(),
            }, function (java_response){
                console.log(java_response);
                if (user_name.length!==0 && pass_word.length!==0 ){
                    if(java_response ==='0'){
                        alert("Incorrect username/email or password")
                    }
                    else {
                        location.href ="match.html" + "?u="+window.btoa(java_response);
                    }
                }


            }
        )
    }

}



function register (){
    var user_name = $('#username').val();
    var pass_word = $('#password').val();
    var pass_word2 = $('#password2').val();
    var email = $('#email').val();

    var ucheck = input_check("username","Please enter your username");
    var echeck = input_check("email","Please enter your email");
    var pcheck = input_check("password","Please enter your password");
    var p2check = input_check("password2","Please enter your confirm password");

    if(ucheck && echeck && pcheck && p2check){
        if(pass_word2===pass_word){
            $.ajax({
                url: "register",  // **back-end files name
                method: "get",
                data:{
                    "username":$("#username").val(),
                    "password":$("#password").val(),
                    "email":$("#email").val()
                },
                success:function(java_response){
                    console.log(java_response);
                    if(java_response[0]==="e"){
                        //console.log(java_response);
                        alert(java_response);
                    }
                    else {
                        location.href ="SignUP_verification.html"+ "?u="+window.btoa(user_name)+"&p="+window.btoa(pass_word)+"&e="+window.btoa(email)+"&v="+window.btoa(java_response);
                    }

                }
            })
        }
        else {alert("password need to be same")}

    }



}


function register_verify(){
    //window.atob(str64)
    var num = $('#verifyNum').val();

    var username = getURLVariable("u");  //user
    var vcode = getURLVariable("v"); // vcode
    var password= getURLVariable("p"); // password
    var email= getURLVariable("e");  // emails
    //console.log(window.atob(email))

    var vcheck = input_check("verifyNum","Please enter your verify code");
    vcheck;
    if (vcheck){
        if(window.atob(vcode)===num){
            $.get("register_verify", {
                    "username": window.atob(username),
                    "password":window.atob(password),
                    "email":window.atob(email),
                    "resend" : "0"
                }, function (java_response){
                    if(java_response==="1"){
                        location.href ="info.html" +"?u="+username
                    }
                }
            )
        }
        else {
            alert("incorrect verify number")
        }
    }


}



function verify_fail(){
    var username = getURLVariable("u");  //user
    var vcode = getURLVariable("v"); // vcode
    var password= getURLVariable("p"); // password
    var email= getURLVariable("e");  // emails
    $.get("timeout", {
            "username": username,
        }, function (java_response){
            if(java_response==="1"){
                location.href ="SignIn.html";
                alert("timeout verify Fail :(")
            }

        }
    )
}

function timeout_jump (){
    setTimeout('verify_fail()',3*60*1000); //3 min
}

function resend_code(){
    var username = getURLVariable("u");  //user
    var password= getURLVariable("p"); // password
    var email= getURLVariable("e");  // emails
    $.get("register_verify", {
            "username": "0",
            "password":"0",
            "email": window.atob(email),
            "resend" : "1"
        }, function (java_response){
            alert("Resend code successful")
            history.replaceState("","",location.href ="SignUP_verification.html"+ "?u="+username+"&p="+password+"&e="+email+"&v="+window.btoa(java_response))
        }
    )

}

function forgot_password(){

    var email = $('#forgot_email').val();
    var echeck = input_check("forgot_email","Please enter your email");
    echeck;
    if(echeck){
        $.get("forgot_password", {
                "email":$('#forgot_email').val(),
            }, function (java_response){
                if(java_response==="1"){
                    location.href ="ForgotPass_verification.html"+"?e="+window.btoa(email)
                }
                else{
                    alert("Emails does not exist ")
                }
            }
        )
    }

}


function forgot_verity(){
    var email = window.atob(getURLVariable("e"));
    var num = $('#forgot_verityNum').val();
    var pass_word =$('#Fpassword1').val();
    var pass_word2 =$('#Fpassword2').val();

    var vcheck = input_check("forgot_verityNum","Please enter your verify code");
    var pcheck = input_check("Fpassword1","Please enter your password");
    var pcheck2 = input_check("Fpassword2","Please enter your confirm password");

    if(vcheck && pcheck && pcheck2 ){
        if (pass_word2===pass_word){
            $.get("forgot_verify", {
                    "email":email,
                    "num": num,
                    "password" : pass_word,
                    "resend":"0"
                }, function (java_response){
                    if(java_response==="1"){
                        alert("Your password has been changed successfully")
                        location.href ="SignIn.html"
                    }
                    else {
                        alert("incorrect ")
                    }
                }
            )
        }
        else {
            alert("password need to be same")
        }

    }

}
function forgot_resend_code() {
    var email =  window.atob(getURLVariable("e"));

    $.get("forgot_verify", {
            "email":email,
            "num": "0",
            "password" : "0",
            "resend":"1"
        }, function (java_response){
            if(java_response==="1"){
                alert("Resend code successful")
            }
            else{
                alert("Resend code fail")
            }
        }
    )
}
function matchs(){
    var username=  window.atob(getURLVariable("u"));

    $.get('matchs', {
            "username":username
        }, function (java_response){
            console.log(java_response);
            const data = JSON.parse(java_response);
            //console.log(data);

            if (Object.keys(data).length===1){
                alert("No one can match right now");
            }
            else{
                // var spli = java_response.split('###');
                // console(spli);
                // match_notice(spli[0],spli[1]);
                match_notice(data["user1"],data["user2"]);
            }

        }
    )
}



function get_time(date){
    var date = new Date(date)
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() <10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() <10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y+M+D+h+m+s;
}
function toolnum(s){
    var str = s.toString();
    var a = str.match(/\d+/g)[0];
    return parseInt(a);
}
function send_message(){
    // var from_username=  window.atob(getURLVariable("u"));
    // var to_username=  window.atob(getURLVariable("u2"));
    // var room =  toolnum(getURLVariable("r"));

    var from_username = document.getElementById("current_user").innerHTML;
    var to_username= document.getElementById("chat_friend_username").innerHTML;
    var room =document.getElementById("room_num").value
    var message = $('#message_input').val()
    //console.log("send message = "+message);
    if(message!==''){

        var time = get_time(new Date());
        $.post("send_response", {
                "from":from_username,
                "to": to_username,
                "room": room.toString(),
                "message":message,
                "time" : time
            }

        )
        $('#message_input').val('')
    }

}


//
// function detect_new_message(){
//     const username = window.atob(getURLVariable("u"));
//     const room = toolnum(getURLVariable("r"));
//
//     $.post("post_check",{"user":username,"room":room},function(response){
//         // response > 0, let it get the new messages
//         console.log("res:"+ response);
//         if(response !=="0" ) {
//             // run to get new messages
//             console.log("run show message")
//             show_new_messages();
//         }
//         else {
//             console.log("listening")
//         }
//     },"text");
// }
//
// function chat_detector (){
//     detect_new_message();
//     setTimeout(detect_new_message, 1000);
//
//     $("#send_message_button").keypress(function(event) {
//         //用户按下回车键时，发送聊天消息
//         if (event.keyCode === 13) {
//             //获取聊天消息的内容
//             //发送到服务器端
//             send_message()
//         }
//     })
// }


// function friend_list (){
//     var str = '<ul>'
//     var list = [];
//
//     var username=  window.atob(getURLVariable("u"));
//     $.get("friend_list", {  // get friend list
//             "username":username
//         }, function (java_response){
//             var f = JSON.parse(java_response); // JSON = "friend: []"
//             //list = f.friend;
//             list.forEach(function(list) {
//                 str += '<li>'+ list + '</li>';});
//             str += '</ul>';
//             document.getElementById("friend_list").innerHTML = str;
//         }
//     )
//
// }

function match_chat_create(user2){
    const user1 = window.atob(getURLVariable("u"));
    $.get("create_rooms",{"user1":user1,"user2":user2},function(response){
        //console.log(response);
        if(response!=="0") {
            //this is the room number
            window.location.href= 'chat.html'+"?u="+window.btoa(user1) + "&match=" + window.btoa(user2);


        }
    },);
}

function left_arrow(){
    const current_link = new URL(window.location);
    const user1 = window.atob(getURLVariable("u"));
    if (current_link.searchParams.has("chat")){
        window.location.href= 'chat.html'+"?u="+window.btoa(user1)
    }
    else{
        window.location.href= 'SignIn.html'
    }
}