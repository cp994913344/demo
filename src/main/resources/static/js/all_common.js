/*<div class="mod-head">
    <div class="box">
    <div class="logo">
    <a href="/main"><img src="../images/logo2.png" alt="联途" /></a>
    </div>
    <div class="link"><a href="apply.html">申请成为向导</a></div>

<div class="login pl-login-info displayNone" id="pl-login-info" >
    <div class="user" >
    <img src="http://static.liantu.cn/image/public/man_100x100.jpg" alt="cp" width="40" height="40">
    <span class="name" id="login_user_name"></span>
    <span class="ico"></span>
    </div>
    <div class="list" id="loginInfo" style="display: none;height: 138px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px; right: -26px;">
    <div class="ico">
    </div>
    <ul>
    <li class="d-pc">
    <a href="/toMemberInfo">个人信息</a>
    </li>
    <li>
    <a href="http://my.liantu.cn/member/order/">我的订单</a>
    </li>
    <li>
    <a href="http://my.liantu.cn/member/travel/createlist/">我的同游</a>
    </li>
    <li class="d-m">
    <a href="http://my.liantu.cn/member/account/">我的钱包</a>
    </li>
    <li class="d-m">
    <a href="/banyou/">伴游</a>
    </li>
    <li class="d-m">
    <a href="/daoyou/">导游</a>
    </li>
    <li class="d-m">
    <a href="/tongyou/">同游</a>
    </li>
    <li class="d-pc">
    <a href="javascript:" onclick="">退出</a>
    </li>
    </ul>
    </div>
    </div>

    <div class="login pl-login-info displayNone" id="loginDiv">
    <div class="info">
    <a  href="/">登录</a>
    </div>
    </div>
    </div>
    </div>*/

$(function(){
    $.ajax({
        url:'/returnLogin',
        type :'post',
        success:function(_res){
            if(_res.message){
                $("#login_user_name").text(_res.message);
                $("#pl-login-info").removeClass("displayNone");
            }else{
                $("#loginDiv").removeClass("displayNone");
            }
        }
    });
});

//控制头像登录 滑入滑出
$("#pl-login-info").hover(function(){
    $("#loginInfo").toggle();
});