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
    <title>分享页面</title>


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
    <div class="weui-msg__icon-area"><img src="${pageContext.request.contextPath}/images/gzh.jpg"></i></div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">购买成功</h2>
        <p class="weui-msg__desc">请将此页面分享给此课程的报名者，提醒报名者严格按照姓名和电话注册</p>
    </div>

</div>

<div class="weui-form-preview">
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">课程名称</label>
            <span class="weui-form-preview__value"><a
                    href="/qggy/phone/public/basicinfo/course/phonecoursedetail1.action?id=${obj.tradedetails[0].courseId}">${obj.name }</a></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">所属区域</label>
            <span class="weui-form-preview__value">${obj.areaName }</span>
        </div>
    </div>
</div>

<c:forEach items="${obj.tradedetails}" var="o" varStatus="status">
    <div class="weui-form-preview">
        <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">报名人</label>
                <span class="weui-form-preview__value">${o.userName }</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">电话号码</label>
                <span class="weui-form-preview__value">${o.userPhone}</span>
            </div>
        </div>
    </div>
</c:forEach>
<div class="weui-footer">

</div>
</body>
</html>
