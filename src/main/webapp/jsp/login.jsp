<%--
  Created by IntelliJ IDEA.
  User: ljs
  Date: 2018/5/27
  Time: 上午12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit"/>
    <title>随心游账号登陆</title>
    <link rel="shortcut icon" href="../../../images/logo.png"/>
    <link rel="stylesheet" type="text/css" href="http://static.liantu.cn/style/dist/my/common/common.css">
    <link rel="stylesheet" type="text/css" href="http://static.liantu.cn/style/dist/my/member/passwd/phone.css">
</head>
<body>
<center>
<div class="mod-login mod-login-show" style="display: block;">
    <div class="mask"></div><div class="dialog"><div class="panel login">
    <div class="hd"><span>登录</span><a class="close" href="javascript:;"></a></div>
    <div class="bd">
        <form method="post" action="" autocomplete="off">
            <p class="error"></p>
            <p><span class="icon icon-phone"><input class="input-text" name="username" placeholder="手机号码" autocomplete="off" maxlength="11" type="number"></span></p>
            <p><span class="icon icon-key"><input class="input-text" name="password" placeholder="密码" autocomplete="off" type="password"></span></p>
            <p class="auto">
                <a class="forget" href="http://my.liantu.cn/member/passwd/phone/" target="_blank">忘记密码</a>
                <label class="checked"><input name="remember" value="1" checked="checked" type="checkbox">记住密码</label></p>
            <p class="btn"><input class="input-submit" value="登 录" type="submit"></p>
            <p class="separate"><span class="line"></span><span class="or">或</span></p>
            <p class="link"><a class="switch" href="#reg">立即注册</a></p>
        </form>            </div>        </div><div class="panel reg" style="display: none;">
    <div class="hd"><span>快速注册</span><a class="close" href="javascript:;"></a></div>
    <div class="bd">
        <form method="post" action="" autocomplete="off">
            <p class="error"></p>
            <p><span class="icon icon-phone"><input class="input-text" name="username" placeholder="常用的手机号码" autocomplete="off" maxlength="11" type="number"></span></p>
            <p><span class="icon icon-captcha"><input class="input-text" name="captcha" placeholder="验证码" autocomplete="off" maxlength="4" type="text"></span>
                <a class="captcha" href="javascript:;">
                    <img src="http://my.liantu.cn/i/captcha?1527351585455" alt="更换验证码" title="更换验证码" width="100" height="40"></a></p>
            <p><span class="icon icon-captcha"><input class="input-text" name="code" placeholder="输入接收到的验证码" autocomplete="off" maxlength="4" type="number"></span>
                <a class="getcode" href="javascript:;">发送验证码</a></p>
            <p><span class="icon icon-key"><input class="input-text" name="password" placeholder="设置密码" autocomplete="off" type="password"></span></p>
            <p class="btn"><input class="input-submit" value="立即注册" type="submit"></p>
            <p class="separate"><span class="line"></span><span class="or">或</span></p>
            <p class="link"><a class="switch" href="#login">登录</a></p>
        </form>
    </div>
</div></div></div>
</center>
</body>
</html>
