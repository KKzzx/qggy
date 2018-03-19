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
    <title>班级详情</title>


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
<body class="weui-pull-to-refresh">
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>


<div class="weui-panel__bd">

    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>班级名称</p>
            </div>
            <div class="weui-cell__ft" id="refundNum" style="font-size: 14px">${obj.className}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>上课时间</p>
            </div>
            <div class="weui-cell__ft" id="orderNum" style="font-size: 14px">${obj.classTime}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>上课地点</p>
            </div>
            <div class="weui-cell__ft" id="orderDetailNum"
                 style="font-size: 14px">${obj.classAddress}</div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>课程名</p>
            </div>
            <div class="weui-cell__ft" id="money">${obj.tradeDetails[0].courseName}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>班长</p>
            </div>
            <div class="weui-cell__ft" id="date">${obj.classMonitor}</div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>状态</p>
            </div>
            <div class="weui-cell__ft" id="state">
                <c:if test="${obj.classstate==0}">开课中</c:if>

                <c:if test="${obj.classstate==1}">已结课</c:if>

            </div>
        </div>
    </div>
</div>

<script>
    function getState(val) {
        if (val == 0) {
            return "上课中";
        } else if (val == 1) {
            return "已结课";
        }
    }


    $(document.body).pullToRefresh().on("pull-to-refresh", function () {
        setTimeout(function () {
            $(document.body).pullToRefreshDone();
        }, 2000);
    });
</script>
</body>
</html>
