<%--
  Created by IntelliJ IDEA.
  User: ljs
  Date: 2018/5/26
  Time: 下午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit"/>
    <title>随心游忘记密码</title>
    <link rel="shortcut icon" href="../../../images/logo.png"/>
    <link rel="stylesheet" type="text/css" href="http://static.liantu.cn/style/dist/my/common/common.css">
    <link rel="stylesheet" type="text/css" href="http://static.liantu.cn/style/dist/my/member/passwd/phone.css">
</head>
<body>

<div class="content">
    <div class="main">
        <div class="hd">
            <a href="http://www.liantu.cn/" class="logo">
                <img src="../../../images/logo.png" alt="联途旅游"/>
            </a>
            <p>同行之路，有你才有意义</p>
        </div>
        <div class="formbox panel">
            <ul>
                <li class="title">验证手机</li>
                <li class="num">
                    <label>请输入手机号码</label>
                    <input type="text" placeholder="请输入手机号码" id="user" autocomplete="off" maxlength="13" name="phonenumber"/>
                    <span class="hint"></span>
                </li>
                <li class="captcha">
                    <label>验证码</label>
                    <input type="text" placeholder="验证码" id="captcha" autocomplete="off" maxlength="4" name="captcha"/>
                    <a href="javascript:"><img src="/i/captcha" alt="更换验证码" title="更换验证码"/></a>
                    <span class="hint"></span>
                </li>
                <li class="code">
                    <label>短信验证码</label>
                    <input type="text" placeholder="短信验证码" id="code" autocomplete="off" maxlength="4" name="code"/>
                    <a href="javascript:" class="getcode">发送验证码</a>
                    <span class="hint"></span>
                </li>
                <li class="btn">
                    <a href="javascript:" class="next">下一步</a>
                </li>
            </ul>
        </div>

        <div class="nextbox panel">
            <ul>
                <li class="title">找回密码</li>
                <li class="newPass">
                    <label>新密码</label>
                    <input type="password" class="newPass" placeholder="新密码" id="newPass" autocomplete="off" name="password">
                    <span class="hint"></span>
                </li>
                <li clas="checkPass">
                    <label>确认新密码</label>
                    <input type="password" class="checkPass" placeholder="确认新密码" id="checkPass" autocomplete="off" name="checkPass">
                    <span class="hint"></span>
                </li>
                <li class="btn"><a href="javascript:" class="submit">确认</a></li>
            </ul>
        </div>

        <div class="other"></div>
    </div>
</div>
<div class="flclear"></div>

<div class="footer">

    <div class="mod-back">
        <span class="qrcode">
            <span class="ico"></span>
			<img src="../../../qrcode.jpg" alt="联途微信二维码" width="200" height="200"/>官方微信服务号
        </span>
        <a href="javascript:" class="code" id="code"></a>
        <a href="#" class="goback"></a>
    </div>

    <div class="mod-foot">
        <div class="info">
            <div class="contact">
                <p class="logo">
                    <a href="/"><img src="../../../images/logo.png" alt="联途" width="136" height="50"/></a>
                    <span>同行之路，有你才有意义</span>
                </p>

                <p class="tell">4006 666863</p>

                <p>24小时服务热线</p>
            </div>
            <dl>
                <dt>公司信息</dt>
                <dd><a href="http://www.liantu.cn/about/aboutus/" target="_blank"  rel="nofollow">关于我们</a></dd>
                <dd><a href="http://www.liantu.cn/about/copyrightstatement/" target="_blank"  rel="nofollow">免责声明</a></dd>
                <dd><a href="http://www.liantu.cn/about/contactus/" target="_blank"  rel="nofollow">联系我们</a></dd>
                <dd><a href="http://www.liantu.cn/about/joinus/" target="_blank" rel="nofollow">加入我们</a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="http://www.liantu.cn/about/userprotocol/" target="_blank"  rel="nofollow">用户协议</a></dd>
                <dd><a href="http://www.liantu.cn/about/safetyassurance/" target="_blank"  rel="nofollow">安全保障</a></dd>
                <dd><a href="http://www.liantu.cn/about/bookingprocess/" target="_blank"  rel="nofollow">预定流程</a></dd>
            </dl>
            <dl class="app">
                <dt>发现</dt>
                <dd>
                    <a href="http://www.liantu.cn/app/" target="_blank">Android客户端</a><br/>
                    <a href="http://www.liantu.cn/app/" target="_blank">iOS客户端</a>
                </dd>
            </dl>
        </div>
        <div class="copyright">
            <a href="http://www.miitbeian.gov.cn/" target="_blank" rel="nofollow">闽ICP备15007411号-1</a>
            Copyright &copy; 2018 liantu.cn, All Rights Reserved stat
        </div>
    </div>
</div>

<script type="text/javascript" src="http://static.liantu.cn/script/dist/lib/jquery.seajs.js" id="seajsnode"></script>
<script type="text/javascript">
    seajs.config({
        base: 'http://static.liantu.cn/script/dist',
        paths: {
            'modules': 'http://static.liantu.cn/script/dist'
        },
        map: [
            [/^(.*?\/script\/(?:src|dist)\/.*?\/.*?\.js$)(?:.*)$/i, '$1?version=v1']
        ],
        charset: 'utf-8'
    });
    seajs.use('modules/my/member/passwd/phone.js');
</script>
<div class="hide">
    <script>var _hmt = _hmt || [];(function() {var hm = document.createElement("script");hm.src = "//hm.baidu.com/hm.js?7fb2d3d6f0b9e008b1e5a7fee7b07e3b";var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(hm, s);})();</script></div>
</body>
</html>

