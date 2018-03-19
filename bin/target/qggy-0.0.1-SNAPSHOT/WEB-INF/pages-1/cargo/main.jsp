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
                <td colspan="2" class="modelTitle">财务管理模块介绍</td>
            </tr>
            <tr>
                <td class="subModelTitle">交易记录</td>
                <td class="model_intro_right">会员购买课程以后，管理员可以对交易记录进行查看。</br>
                </td>
            </tr>
            <tr>
                <td class="subModelTitle">退款记录</td>
                <td class="model_intro_right">当会员提出退款申请的时候，后台管理员对申请进行审核，审核通过把钱退到会员的青果账户中去。<br></td>
            </tr>
            <tr>
                <td class="subModelTitle">提现记录</td>
                <td class="model_intro_right">对会员的提现记录进行查看。
                </td>
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