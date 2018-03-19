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
    <title>提现申请成功页面</title>


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no">

    <meta name="description"
          content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.">

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
    <div class="weui-msg__icon-area">
        <img src="${pageContext.request.contextPath}/images/gzh.jpg">
    </div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">提现申请成功</h2>
        <p class="weui-msg__desc">提现会在两到三个工作日内完成</p>
    </div>

</div>

<div class="weui-form-preview">
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">提现金额</label> <span
                class="weui-form-preview__value">${obj.money} </span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">提现时间</label> <span
                class="weui-form-preview__value"><fmt:formatDate
                value="${obj.withdrawTime}" pattern="yyyy-MM-dd HH:mm"/> </span>
        </div>
    </div>
</div>

<div class="weui-form-preview">
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">提现用户</label> <span
                class="weui-form-preview__value">${obj.userName}</span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">提现状态</label> <span
                class="weui-form-preview__value"><c:if
                test="${obj.state==0}">
            <font color="red">流程中</font>
        </c:if> </span>
        </div>
    </div>
</div>
<div class="weui-footer"></div>
</body>
</html>
