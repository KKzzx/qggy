<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模块介绍</title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/main.css" media="all"/>
</head>

<body>
<form>
    <div class="textbox"></div>

    <div class="modelDiv">

        <table class="modelTable" cellspacing="1">
            <tr>
                <td colspan="2" class="modelTitle">统计分析介绍</td>
            </tr>
            <tr>
                <td class="subModelTitle">财务报表</td>
                <td class="model_intro_right">各个地区每个月份的销售情况<br>
                </td>
            </tr>
            <tr>
                <td class="subModelTitle">最受欢迎的课程排行</td>
                <td class="model_intro_right">统计所有或者分部最受欢迎的课程是哪些？</td>
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