
function login(){

    var user_name = $("#username").val();
    var pass_word = $("#password").val();
    if (user_name ==null|| user_name===''||user_name.length===0){
        $("#username"). css("box-shadow","0 0px 4px 0 red");
        $("#username"). css("border-color","white");
        alert('please enter your username or email')
    }
    else {
        $("#username"). css("box-shadow","none");
        $("#username"). css("border","1px solid #D8D8D8");

    }
    if (pass_word==null||pass_word===''|| pass_word.length===0){
        $("#password"). css("box-shadow","0 0px 4px 0 red");
        $("#password"). css("border-color","white");
        alert('please enter your password')
    }
    else{
        $("#password"). css("box-shadow","none");
        $("#password"). css("border","1px solid #D8D8D8");

    }
    $.get("./register", {
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

function register (){
    var user_name = $('#username').val();
    var pass_word = $('#password').val();
    var pass_word2 = $('#password2').val();
    var email = $('#email').val();

    if (user_name ==null|| user_name==''||user_name.length==0){
        $("#username"). css("box-shadow","0 0px 4px 0 red");
        $("#username"). css("border-color","white");
        alert('please enter your username or email')
    }
    else {
        $("#username"). css("box-shadow","none");
        $("#username"). css("border","1px solid #D8D8D8");
    }
    if(email==null|| email==''||email.length==0){
        $("#email"). css("box-shadow","0 0px 4px 0 red");
        $("#email"). css("border-color","white");
        alert('please enter your email')
    }
    else if (!email.includes('@')){
        $("#email"). css("box-shadow","0 0px 4px 0 red");
        $("#email"). css("border-color","white");
        alert('Please enter valid email address')
    }
    else{
        $("#email"). css("box-shadow","none");
        $("#email"). css("border","1px solid #D8D8D8");
    }
    if (pass_word==null||pass_word==''|| pass_word.length==0){
        $("#password"). css("box-shadow","0 0px 4px 0 red");
        $("#password"). css("border-color","white");
        alert('please enter your password')
    }
    else{
        $("#password"). css("box-shadow","none");
        $("#password"). css("border","1px solid #D8D8D8");
    }

    if (pass_word2==null||pass_word2==''|| pass_word2.length==0){   //confirm password
        $("#password2"). css("box-shadow","0 0px 4px 0 red");
        $("#password2"). css("border-color","white");
    }
    else if(pass_word2!==pass_word){
        $("#password2"). css("box-shadow","0 0px 4px 0 red");
        $("#password2"). css("border-color","white");
        alert('The password and confirmation password do not match please enter again')
    }
    else{
        $("#password2"). css("box-shadow","none");
        $("#password2"). css("border","1px solid #D8D8D8");
    }


    $.ajax({
        url: "register",  // **back-end files name
        method: "get",
        data:{
            "username":$("#username").val(),
            "password":$("#password").val(),
            "email":$("#email").val()
        },
        success:function(java_response){
            if(java_response ==='2'){
                location.href ="SignUP_verification.html"+ "?user="+user_name
            }
            if(java_response==='1'){
                alert(" exist username")
            }
            if(java_response==='0'){
                alert(" exist email ")
            }

        }
    })
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

function register_verify(){
    var num = $('#verifyNum').val();
    var username = getURLVariable("user");

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

