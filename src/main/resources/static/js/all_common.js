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