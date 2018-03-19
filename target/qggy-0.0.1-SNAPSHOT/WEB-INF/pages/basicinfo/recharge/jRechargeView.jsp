<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<link rel="stylesheet" rev="stylesheet" type="text/css"
      href="${ctx}/css/viewdetail.css"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">浏览充值信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">充值人名称：</td>
                        <td class="tableContent">${obj.payUserName}</td>
                        <td class="columnTitle">充值金额：</td>
                        <td class="tableContent">${obj.totalFee}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">充值时间：</td>
                        <td class="tableContent"><fmt:formatDate
                                value="${obj.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td class="columnTitle">微信交易订单号：</td>
                        <td class="tableContent">${obj.transactionId}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>

