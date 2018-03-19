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
    <title>充值成功</title>
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
<div class="weui-msg">
    <div class="weui-msg__icon-area"><img src="${pageContext.request.contextPath}/images/gzh.jpg"></div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">充值成功</h2>
        <p class="weui-msg__desc">您可以在我的充值记录中查看该订单</p>

    </div>

</div>

<div class="weui-form-preview">
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">充值订单编号</label>
            <span class="weui-form-preview__value">${trade.id}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">充值前现金账户余额</label>
            <span class="weui-form-preview__value">${xianjin}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">充值金额</label>
            <span class="weui-form-preview__value">${trade.weixinmoney}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">充值后现金账户余额</label>
            <span class="weui-form-preview__value">${sessionScope.user.xianjin}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">充值时间</label>
            <span class="weui-form-preview__value"><fmt:formatDate value="${trade.payTime}"
                                                                   pattern="yyyy-MM-dd HH:mm:ss"/></span>
        </div>


    </div>
</div>

<div class="weui-footer">

</div>


</body>

</html>

