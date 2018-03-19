<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript"
            src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
</head>

<body id="left_frame">
<div class="PositionFrame_black" id="PositionFrame"></div>


<!-- begin1  -->
<div id="sidebar" class="sidebar">
    <div class="sidebar_t">
        <div class="sidebar_t_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_t_r"></div>
    </div>
    <div class="panel">
        <div class="panel_icon">
            <img src="${ctx}/skin/default/images/icon/document_into.png"/>
        </div>
        <div class="panel-header">
            <div class="panel-title">课程管理</div>
            <div class="panel-content">
                <ul>


                    <li><a href="${ctx}/basicinfo/course/list.action"
                           onclick="linkHighlighted(this)" target="main" id="aa_1">课程信息</a>
                    </li>

                    <li><a href="${ctx}/basicinfo/adv/list.action"
                           onclick="linkHighlighted(this)" target="main" id="aa_1">广告管理</a>
                    </li>


                    <li><a href="${ctx}/basicinfo/tradedetail/list.action"
                           onclick="linkHighlighted(this)" target="main" id="aa_1">报名管理</a>
                    </li>
                    <li><a href="${ctx}/basicinfo/classs/list.action"
                           onclick="linkHighlighted(this)" target="main" id="aa_1">班级管理</a>
                    </li>
                    <li><a href="${ctx}/basicinfo/attendance/list.action"
                           onclick="linkHighlighted(this)" target="main" id="aa_1">考勤管理</a>
                    </li>

                </ul>
            </div>
        </div>
    </div>
    <div class="sidebar_t">
        <div class="sidebar_b_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_b_r"></div>
    </div>
</div>

</body>
</html>
