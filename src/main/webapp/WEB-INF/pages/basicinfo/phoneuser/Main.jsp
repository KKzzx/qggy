<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no"/>
    <title>个人中心</title>
    <link rel="stylesheet" href="../../weui/lib/weui.min.css">
    <link rel="stylesheet" href="../../weui/css/jquery-weui.min.css">
    <link rel="stylesheet" href="../../css/module.css">
    <script src="../../weui/lib/jquery-2.1.4.js"></script>
    <script src="../../weui/js/jquery-weui.min.js"></script>
    <script type='text/javascript' src='../../js/swiper.js' charset='utf-8'></script>
    <script type='text/javascript'>
        window.onload = function setPersonInfo() {
            var time = {timestamp:new Date().getTime()};
            $.ajax({
                url: "/qggy/phone/user/basicinfo/student/findbyOID.action", //请求地址
                type: "POST", //请求方式
                data: time,
                dataType: "json",
                contentType: 'application/json;charset=utf-8', //指定为json类型
                success: function (response) {
                    var userName = document.getElementById("userName");
                    var phoneNumber = document.getElementById("phoneNumber");
                    var accountMoney = document.getElementById("accountMoney");
                    userName.innerHTML = response[0].userName;
                    phoneNumber.innerHTML = response[0].phoneNumber;
                    accountMoney.innerHTML = "账户余额:" + response[0].availableAssets;
                },
                fail: function (status) {
                    alert("接口调用异常" + status);
                }
            });
        };

        //accountMoney.innerHTML="<font color='red'>this</font>"; 解析标签，修饰真正的输出/输出红色的this
        //accountMoney.text="<font color='red'>this</font>"; 将标签当做输出/输出<font color='red'>this</font>
    </script>
</head>
<body>

<div class="user">
    <div class="userImg" id="img">
        <img src="../../images/Phone/tou.png"/>
    </div>
    <div class="ueseText">
        <h3 id="userName"></h3>
        <p id="phoneNumber"></p>
        <p id="accountMoney">账户余额：</p>
    </div>
</div>
<div>会员</div>
<div class="weui-grids">
    <a href="/qggy/phone/user/myclass.action" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="../../images/Phone/course.png" alt="">
        </div>
        <p class="weui-grid__label">我的课程</p></a> <a
        href="/qggy/phone/user/myinfo.action" class="weui-grid js_grid">
    <div class="weui-grid__icon">
        <img src="../../images/Phone/account.png" alt="">
    </div>
    <p class="weui-grid__label">我的账户</p></a> <a
        href="/qggy/phone/user/myrefund.action" class="weui-grid js_grid">
    <div class="weui-grid__icon">
        <img src="../../images/Phone/shopping.png" alt="">
    </div>
    <p class="weui-grid__label">我的退款记录</p></a> <a
        href="/qggy/phone/user/myorder.action" class="weui-grid js_grid">
    <div class="weui-grid__icon">
        <img src="../../images/Phone/dingdan.png" alt="">
    </div>
    <p class="weui-grid__label">我的订单</p></a> <a
        href="/qggy/phone/user/mywithdraw.action" class="weui-grid js_grid">
    <div class="weui-grid__icon">
        <img src="../../images/Phone/pay.png" alt="">
    </div>
    <p class="weui-grid__label">我的提现记录</p></a> <a href="/qggy/phone/user/myRecharge.action"
                                                  class="weui-grid js_grid">
    <div class="weui-grid__icon">
        <img src="../../images/Phone/remark.png" alt="">
    </div>
    <p class="weui-grid__label">我的充值记录</p></a> <a
        href="/qggy/phone/user/myattendance.action" class="weui-grid js_grid">
    <div class="weui-grid__icon">
        <img src="../../images/Phone/kaoqin.png" alt="">
    </div>
    <p class="weui-grid__label">我的考勤记录</p></a>
    <a
            href="/qggy/phone/user/mytransfer.action" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="../../images/Phone/kaoqin.png" alt="">
        </div>
        <p class="weui-grid__label">我的转账记录</p></a>
    <c:if test="${state==1}">
        <a
                href="/qggy/phone/user/becomedaoshi.action" class="weui-grid js_grid">
            <div class="weui-grid__icon">
                <img src="../../images/Phone/kaoqin.png" alt="">
            </div>
            <p class="weui-grid__label">成为公益导师</p></a></c:if>
</div>
<c:if test="${state==3}">
    <div>公益导师</div>
    <div class="weui-grids">
        <a href="/qggy/phone/user/teacherclasslist.action" class="weui-grid js_grid">
            <div class="weui-grid__icon">
                <img src="../../images/Phone/course.png" alt="">
            </div>
            <p class="weui-grid__label">班级列表</p></a> <a
            href="/qggy/phone/user/teachercourselist.action" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="../../images/Phone/account.png" alt="">
        </div>
        <p class="weui-grid__label">课程列表</p></a>
        <a
                href="/qggy/phone/user/myattendance.action" class="weui-grid js_grid">
            <div class="weui-grid__icon">
                <img src="../../images/Phone/account.png" alt="">
            </div>
            <p class="weui-grid__label">我的薪酬</p></a>

    </div>
</c:if>
</body>
</html>
