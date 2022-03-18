
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

    var ucheck= input_check("username","Please enter your username");
    var pcheck = input_check("password","Please enter your password");
    ucheck;
    pcheck;
    if(ucheck && pcheck) {
        $.get("login", {
                "username":$("#username").val(),
                "password":$("#password").val(),
            }, function (java_response){
                if(java_response==='1'){
                    location.href ="match.html"
                }
                if (user_name.length!==0 && pass_word.length!==0 ){
                    if(java_response!=='1'){
                        alert("Incorrect username/email or password")

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
    var p2check = input_check("password2","Please enter your password2");
    var psamecheck =password_same_check("password","password2");

    ucheck;
    echeck;
    pcheck;
    p2check;
    psamecheck;
    if(ucheck && echeck && pcheck && p2check && psamecheck){
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
                if(java_response==="0"){
                    location.href ="SignUP_verification.html"+ "?user="+user_name ;
                }
                else {
                    console.log(java_response);
                    alert(java_response);
                }

            }
        })
    }



}

function register_verify(){
    var num = $('#verifyNum').val();
    var username = getURLVariable("user");

    var vcheck = input_check("verifyNum","Please enter your verify code");
    vcheck;
    if (vcheck){
        $.get("register_verify", {
                "num":$('#verifyNum').val(),
                "username": username,
                "resend" : "0"
            }, function (java_response){
                if(java_response==="1"){
                    location.href ="info.html" +"?user="+username
                }
                else{
                    alert("incorrect verify number")
                    console.log(java_response);
                    //$('#resend_code').css("visibility","visible");
                }
            }
        )
    }

}

function resend_code(){
    var username = getURLVariable("user");

    $.get("register_verify", {
            "num":"0",
            "username": username,
            "resend" : "1"
        }, function (java_response){
            if(java_response==='1'){
                alert("Resend code successful")
            }
            else{
                alert("Resend code fail")
            }
        }
    )

}

function forgot_password(){

    var email = $('#forgot_email').val();
    var echeck = input_check("email","Please enter your email");
    echeck;
    if(echeck){
        $.get("forgot_password", {
                "email":$('#forgot_email').val(),
            }, function (java_response){
                if(java_response==="1"){
                    location.href ="ForgotPass_verification.html"+"?email="+email
                }
                else{
                    alert("Emails does not exist ")
                }
            }
        )
    }

}


function forgot_verity(){
    var email = getURLVariable("email")
    var num = $('#forgot_verityNum').val();
    var pass_word =$('#Fpassword1').val();
    var pass_word2 =$('#Fpassword2').val();

    var same_check = password_same_check("Fpassword1","Fpassword2");
    var vcheck = input_check("forgot_verityNum","Please enter your verify code");
    var pcheck = input_check("Fpassword1","Please enter your password");
    var pcheck2 = input_check("Fpassword2","Please enter your password");

    vcheck;
    pcheck;
    pcheck2;
    same_check;

    if(vcheck && pcheck && pcheck2 && same_check){
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
            }
        )
    }

}
function forgot_resend_code() {
    var email = getURLVariable("email")

    $.get("forgot_verify", {
            "email":email,
            "num": "0",
            "password" : "0",
            "resend":"1"
        }, function (java_response){
            if(java_response==='1'){
                alert("Resend code successful")
            }
            else{
                alert("Resend code fail")
            }
        }
    )
}
