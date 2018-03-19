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
    <title>转账成功</title>


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
    <script src="https://one.teegon.com/jslib/t-charging.min.js"></script>
</head>
<body>
<div class="weui-msg">
    <div class="weui-msg__icon-area"><img src="${pageContext.request.contextPath}/images/gzh.jpg"></div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">内部转账成功</h2>
        <p class="weui-msg__desc">您可以在我的转账记录中查看该订单</p>

    </div>

</div>

<div class="weui-form-preview">
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">内部转账编号</label>
            <span class="weui-form-preview__value">${transfer.id }</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">转账前课程账户余额</label>
            <span class="weui-form-preview__value">${availableAssets}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">转账前现金账户余额</label>
            <span class="weui-form-preview__value">${xianjin}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">转账金额</label>
            <span class="weui-form-preview__value">${transfer.transferMoney }</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">手续费</label>
            <span class="weui-form-preview__value">${transfer.transferCommission }</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">实际到账金额</label>
            <span class="weui-form-preview__value">${transfer.transferCash }</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">转账后课程账户余额</label>
            <span class="weui-form-preview__value">${sessionScope.user.availableAssets}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">转账后现金账户余额</label>
            <span class="weui-form-preview__value">${sessionScope.user.xianjin}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">转账时间</label>
            <span class="weui-form-preview__value"><fmt:formatDate value="${transfer.transferTime}"
                                                                   pattern="yyyy-MM-dd HH:mm:ss"/></span>
        </div>
    </div>
</div>

<div class="weui-footer">

</div>
</body>
</html>
