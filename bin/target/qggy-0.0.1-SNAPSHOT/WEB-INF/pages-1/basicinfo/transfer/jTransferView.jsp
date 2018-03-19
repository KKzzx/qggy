<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
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
                <div class="textbox-title">浏览等级信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">申请人名称：</td>
                        <td class="tableContent">${obj.transferUsername}</td>
                        <td class="columnTitle">转账金额：</td>
                        <td class="tableContent">${obj.transferMoney}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">转账手续费：</td>
                        <td class="tableContent">${obj.transferCommission}</td>
                        <td class="columnTitle">实际到账金额：</td>
                        <td class="tableContent">${obj.transferCash}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">转账时间：</td>
                        <td class="tableContent"><fmt:formatDate
                                value="${obj.transferTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>

