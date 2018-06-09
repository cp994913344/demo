var loginFun = function(){
    $.ajax({
        url: '/returnLogin',
        type: 'post',
        success: function (_res) {
            console.log(_res);
            if (_res.code===200) {
                $("#login_user_name").text(_res.message);
                $("#topUserImage").attr("src","123");
                $("#pl-login-info").removeClass("displayNone");
                userName=_res.message;
            } else if (_res.code===500) {
                $("#loginDiv").removeClass("displayNone");
            }
        }
    });
};
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

function fmtDate(obj){
    var date =  new Date(obj);
    var y = 1900+date.getYear();
    var m = "0"+(date.getMonth()+1);
    var d = "0"+date.getDate();
    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
}