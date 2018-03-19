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
    <title>课程账户余额不足</title>


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
        <h2 class="weui-msg__title">内部转账失败</h2>
        <p class="weui-msg__desc">课程账户余额不足，无法进行内部转账</p>

    </div>

</div>


<div class="weui-footer">

</div>
</body>
</html>
