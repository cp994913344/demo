window.onload=function(){
    $.ajax({
        url: '/returnLogin',
        type: 'post',
        success: function (_res) {
            if (_res.code===200) {
                $("#login_user_name").text(_res.message);
                $("#pl-login-info").removeClass("displayNone");
                userName=_res.message;
            } else if (_res.code===500) {
                $("#loginDiv").removeClass("displayNone");
            }
        }
    });
}
//控制头像登录 滑入滑出
$("#pl-login-info").hover(function(){
    $("#loginInfo").toggle();
});

function loginOut(){
    $.ajax({
        url: '/visitor/loginOut',
        type: 'post',
        success: function (_res) {
            if (_res.code===200) {
                if(!$("#pl-login-info").hasClass("displayNone")){
                    $("#pl-login-info").addClass("displayNone");
                }
                if($("#loginDiv").hasClass("displayNone")){
                    $("#loginDiv").removeClass("displayNone");
                }
            } else if (_res.code===500) {
               layer.msg("退出失败");
            }
        }
    });
}