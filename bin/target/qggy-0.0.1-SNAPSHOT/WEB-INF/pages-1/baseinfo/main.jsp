<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模块介绍</title>
    <link rel="stylesheet" rev="stylesheet" type="text/css"
          href="${ctx}/skin/default/css/main.css" media="all"/>
</head>

<body>
<form>
    <div class="textbox"></div>

    <div class="modelDiv">

        <table class="modelTable" cellspacing="1">
            <tr>
                <td colspan="2" class="modelTitle">课程管理模块介绍</td>
            </tr>

            <tr>
                <td colspan="2" class="subModelTitle">课程信息</td>
            </tr>
            <tr>
                <td class="model_intro_left" width="169" colspan="2">管理员发布课程、领导审核课程</td>

            </tr>

            <tr>
                <td colspan="2" class="subModelTitle">报名管理</td>
            </tr>
            <tr>
                <td class="model_intro_left" colspan="2">管理员查看报名信息，开设班级</td>
            </tr>
            <tr>
                <td colspan="2" class="subModelTitle">班级管理</td>
            </tr>
            <tr>
                <td class="model_intro_left" colspan="2">管理员查看班级信息</td>
            </tr>
            <tr>
                <td colspan="2" class="subModelTitle">考勤管理</td>
            </tr>
            <tr>
                <td class="model_intro_left" colspan="2">管理员查看会员上课记录</td>
            </tr>

            <tfoot>
            <tr>
                <td colspan="2" class="tableFooter"></td>
            </tr>
            </tfoot>
        </table>

    </div>
</form>
</body>

</html>