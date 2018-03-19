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
    <title>确认订单信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no">

    <meta name="description"
          content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/weui/lib/weui.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/weui/css/jquery-weui.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/weui/css/jquery-weui.css">

</head>

<body>
<header class="demos-header">
    <h2 class="demos-title" style="text-align: center;color: red;">确认订单信息</h2>
</header>

<div style="padding-left: 10px">课程信息</div>
<div class="weui-panel__bd">
    <a href="javascript:void(0);"
       class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__hd">
            <img class="weui-media-box__thumb" style="width: 100%;height: 80%;margin-top: 20%;"
                 src="${pageContext.request.contextPath}${course.courseCover}">
        </div>
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">${course.courseName}</h4>
            <p class="weui-media-box__desc">课程单价：${course.coursePrice}</p>
            <p class="weui-media-box__desc">课程所属区域：${course.areaName}</p>

        </div>
    </a>


</div>

<div class="weui-cells" style="margin-top: 0px">
    <div class="weui-cell">
        <div style="color: red; font-size: 10px">${course.courseRemark}
        </div>

    </div>

</div>


<div class="weui-cells" style="margin-top: 0px">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>数量</p>
        </div>
        <div class="weui-cell__ft">${jspay.amount}</div>
    </div>
</div>
<div class="weui-cells" style="margin-top: 0px">
    <c:forEach items="${jspay.studentlist}" var="o" varStatus="status">
        <div style="margin-left: 0.5rem;">第${status.index+1}个</div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>姓名</p>
            </div>
            <div class="weui-cell__ft">${o.userName}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>电话</p>
            </div>
            <div class="weui-cell__ft">${o.phoneNumber}</div>
        </div>


    </c:forEach>
</div>


<div class="weui-cells" style="margin-top: 0px">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>订单总价</p>
        </div>
        <div class="weui-cell__ft" id="total">${jspay.amount*course.coursePrice}</div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>课程账户支付金额</p>
        </div>
        <div class="weui-cell__ft" id="nowmoney">
            ${jspay.countmoney}</div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>现金账户支付金额</p>
        </div>


        <div class="weui-cell__ft" id="wepay">${jspay.xianjin}</div>


    </div>
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>微信支付金额</p>
        </div>


        <div class="weui-cell__ft" id="wepay">${jspay.weixinmoney}</div>


    </div>
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>留言</p>
        </div>


        <div class="weui-cell__ft" id="wepay">${jspay.remark}</div>


    </div>

    <div
            style="width: 80%; margin-left: 10%;margin-top: 0.2rem;margin-bottom: 0.2rem;">
        <a href="javascript:;" id="submit-btn" class="weui-btn weui-btn_warn">确认支付</a>
    </div>

</div>

</body>
<script type="text/javascript">
    $(function () {
        $('#submit-btn')
            .click(
                function (event) {
                    var json = ${wxJsApiParam};
                    if (json == "123") {

                        $
                            .ajax({
                                url: "${pageContext.request.contextPath}/phone/user/basicinfo/trade/orderinsert.action",
                                data: {},
                                type: "get",
                                dataType: "json", //很重要!!!.  预期服务器返回的数据类型
                                error: function (XMLHttpRequest,
                                                 textStatus, errorThrown) {
                                    alert("付款成功，存入数据库失败，请联系管理员");
                                    alert(XMLHttpRequest.status);
                                    alert(XMLHttpRequest.readyState);
                                    alert(textStatus);
                                },
                                success: function (data) {
                                    window.location.href = "http://wxtest.iamlj.com/qggy/phone/public/basicinfo/trade/fenxiang.action?id=" + data[0].id;
                                }
                            });

                    } else {
                        callpay();
                    }

                });

    });
</script>
<script type="text/javascript">
    //var json = ${wxJsApiParam};必须在同一行
    var json = ${wxJsApiParam};
    //alert("["+JSON.stringify(json)+"]");
    function jsApiCall() {
        WeixinJSBridge
            .invoke('getBrandWCPayRequest',
                json,//josn串
                function (res) {
                    WeixinJSBridge.log(res.err_msg);
                    if (res.err_msg == "get_brand_wcpay_request:ok") {
                        $
                            .ajax({
                                url: "${pageContext.request.contextPath}/phone/user/basicinfo/trade/orderinsert.action",
                                data: {},
                                type: "get",
                                dataType: "json", //很重要!!!.  预期服务器返回的数据类型
                                error: function (XMLHttpRequest,
                                                 textStatus, errorThrown) {
                                    alert("付款成功，存入数据库失败，请联系管理员");
                                    alert(XMLHttpRequest.status);
                                    alert(XMLHttpRequest.readyState);
                                    alert(textStatus);
                                },
                                success: function (data) {

                                    window.location.href = "http://wxtest.iamlj.com/qggy/phone/public/basicinfo/trade/fenxiang.action?id=" + data[0].id;
                                }
                            });

                    }
                });
    }
    function callpay() {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', jsApiCall,
                    false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', jsApiCall);
                document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
            }
        } else {
            jsApiCall();
        }
    }
</script>

</html>

