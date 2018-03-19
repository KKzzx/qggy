<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>

<script src="../../weui/lib/jquery-2.1.4.js"></script>
<script src="../../weui/js/jquery-weui.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/weui/lib/weui.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/weui/css/jquery-weui.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/weui/css/jquery-weui.css">
<meta http-equiv="Content-Type" content="innerHTML/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
      content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,viewport-fit=cover">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>我的账户</title>
</head>
<style type="text/css">
    .weui-media-box__desc {
        color: #999;
        font-size: 13px;
        line-height: 1.2;
        overflow: hidden;
        innerHTML-overflow: ellipsis;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2
    }
</style>
<script type="text/javascript">
    window.onload = function () {
        $
            .ajax({
                url: "/qggy/phone/user/basicinfo/student/findbyOID.action", //请求地址
                type: "GET", //请求方式
                data: "{timestamp=" + new Date().getTime() + "}",
                dataType: "json",
                contentType: 'application/json;charset=utf-8', //指定为json类型
                success: function (response) {//拼标签
                    var content = document.getElementById("preview");
                    //http://jqweui.com/components#preview
                    var hddiv = document.getElementById("previewhd");
                    var label = document.createElement("lable");
                    label.setAttribute("class", "weui-form-preview__label");
                    label.setAttribute("style", "color: black;");
                    label.innerHTML = "用户名称";
                    var em = document.createElement("em");
                    em.setAttribute("class", "weui-form-preview__value");
                    em.innerHTML = response[0].userName;
                    hddiv.appendChild(label);
                    hddiv.appendChild(em);

                    tenplate("previewbd", "手机", response[0].phoneNumber);
                    if (response[0].state == 2) {
                        tenplate("previewbd", "微信", response[0].weiXin);
                        tenplate("previewbd", "邮箱", response[0].email);
                        if (response[0].sex == 0) {
                            tenplate("previewbd", "性别", "男");
                        } else if (response[0].sex == 1) {
                            tenplate("previewbd", "性别", "女");
                        }
                        tenplate("previewbd", "公司", response[0].company);
                        if (response[0].marryd == 0) {
                            tenplate("previewbd", "婚姻", "已婚");
                        } else if (response[0].marryd == 1) {
                            tenplate("previewbd", "婚姻", "未婚");
                        }
                    }
                    /*tenplate("previewbd", "冻结押金", response[0].frozenAssets
                     + ' 元');
                     if (response[0].frozenAssets > 0) {
                     tenplate2("previewbd", "冻结资产提现", "tixian("
                     + response[0].frozenAssets + ")");
                     }*/

                    tenplate("previewbd", "课程余额",
                        response[0].availableAssets + ' 元');

                    if (response[0].availableAssets > 0) {
                        tenplate2("previewbd", "提取至充值余额",
                            "tiqutoxianjin("
                            + response[0].availableAssets
                            + ")");
                    }

                    tenplate("previewbd", "充值余额", response[0].xianjin
                        + ' 元');

                    var ftdiv = document.getElementById("previewft");
                    var a = document.createElement("a");
                    a
                        .setAttribute("class",
                            "weui-form-preview__btn weui-form-preview__btn_default");
                    a.setAttribute("onclick", "chongzhi()");
                    a.innerHTML = "充值";
                    ftdiv.appendChild(a);

                    var button = document.createElement("a");
                    button
                        .setAttribute("class",
                            "weui-form-preview__btn weui-form-preview__btn_primary");
                    button.setAttribute("onclick", "tixian("
                        + response[0].xianjin + ")");
                    button.innerHTML = "提现";

                    ftdiv.appendChild(button);

                },
                fail: function (status) {
                    alert("接口调用异常" + status);
                }
            });
    };
    function tenplate(id, name, value) {


        var bddiv = document.getElementById(id);
        var div = document.createElement("div");
        div.setAttribute("class", "weui-form-preview__item");
        var label = document.createElement("lable");
        label.setAttribute("class", "weui-form-preview__label");
        label.setAttribute("style", "color: black;");
        label.innerHTML = name;
        var span = document.createElement("span");
        span.setAttribute("class", "weui-form-preview__value");
        span.setAttribute("style", "color: black;");
        span.innerHTML = value;
        div.appendChild(label);
        div.appendChild(span);
        bddiv.appendChild(div);
    }
    function tenplate2(id, name, value) {
        var bddiv = document.getElementById(id);
        var div = document.createElement("div");
        div.setAttribute("class", "weui-form-preview__ft");
        var button = document.createElement("a");
        button.setAttribute("class",
            "weui-form-preview__btn weui-form-preview__btn_primary");
        button.setAttribute("onclick", value);
        button.innerHTML = name;
        div.appendChild(button);
        bddiv.appendChild(div);
    }
    function tixian(money) {
        //http://jqweui.com/components#dialog
        if (money > 0) {
            //如果参数过多，建议通过 object 方式传入
            $.prompt({
                title: '提现',
                text: '请输入提现金额 (可提金额 <font color="red">' + money
                + '</font> 元,提现金额不得小于1元)',
                input: money,
                empty: false, // 是否允许为空
                onOK: function (input) {
                    if (input <= money & input >= 1) {
                        var a = document.createElement("a");
                        //a.setAttribute("href", "javascript:alert(10);");
                        a.setAttribute("href",
                            "/qggy/phone/user/basicinfo/withdraw/getmoneyapply.action?money="
                            + input);
                        //$.alert("请打开注释");
                        a.click();
                    } else {
                        $.alert("输入提现金额大于可提金额");
                    }
                },
                onCancel: function () {
                    //点击取消
                }
            });
        }
    }
    function tiqutoxianjin(money) {
        //http://jqweui.com/components#dialog
        var real = (money * 0.97).toFixed(2);
        if (money > 0) {
            //如果参数过多，建议通过 object 方式传入
            $
                .prompt({
                    title: '提取至充值余额,请输入金额',
                    text: '(课程余额 <font color="red">'
                    + money
                    + '</font> 元,手续费 <font color="red">3%</font> ,实际到账金额 <font color="red">'
                    + real + '</font> 元，如果使用课程余额购买课程，则不收取手续费)',
                    input: money,
                    empty: false, // 是否允许为空
                    onOK: function (input) {
                        if (input <= money) {
                            suretiqutoxianjin(input);
                        } else {
                            $.alert("输入提现金额大于可提金额");
                        }
                    },
                    onCancel: function () {
                        //点击取消
                    }
                });
        }
    }
    function chongzhi() {
        //http://jqweui.com/components#dialog
        //如果参数过多，建议通过 object 方式传入
        $.prompt({
            title: '充值',
            text: '请输入充值金额',
            input: '0.01',
            empty: false, // 是否允许为空
            onOK: function (input) {
                if (input > 0) {
                    window.location.href = "${pageContext.request.contextPath}/phone/user/basicinfo/trade/czprepay.action?money=" + input;


                    //var a = document.createElement("a");
                    //a.setAttribute("href", "javascript:alert(10);");
                    //a
                    //		.setAttribute("href",
                    //				"/qggy/phone/user/basicinfo/withdraw/getmoney.action?money=");
                    //$.alert("请打开注释");
                    //a.click();
                    //"/qggy/phone/user/basicinfo/withdraw/getmoney.action");
                } else {
                    $.alert("重置金额必须大于0元");
                }
            },
            onCancel: function () {
                //点击取消
            }
        });
    }
    function suretiqutoxianjin(money) {
        //http://jqweui.com/components#dialog
        var real = (money * 0.97).toFixed(2);
        $.confirm({
            title: '提取至充值余额',
            text: '提取余额 <font color="red">' + money
            + '</font> 元,手续费 <font color="red">'
            + (money * 0.03).toFixed(2)
            + '</font> 元,实际到账金额 <font color="red">' + real
            + '</font> 元',
            onOK: function () {
                var a = document.createElement("a");
                a.setAttribute("href",
                    "/qggy/phone/user/basicinfo/transfer/insert.action?money="
                    + money);
                a.click();
            },
            onCancel: function () {
            }
        });
    }

</script>

<body>

<div style="width: 95%;padding-left: 0.2rem;">
    <div class="weui-form-preview" id="preview">
        <div class="weui-form-preview__hd" id="previewhd"
             style="color: black;"></div>
        <div class="weui-form-preview__bd" id="previewbd"></div>
        <div class="weui-form-preview__ft" id="previewft"></div>
    </div>
</div>
<font color="red"></font>
</body>
</html>