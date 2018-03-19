<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<link rel="stylesheet" rev="stylesheet" type="text/css"
      href="${ctx}/css/viewdetail.css"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">浏览学员信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">姓名：</td>
                        <td class="tableContent">${obj.userName}</td>
                        <td class="columnTitle">电话：</td>
                        <td class="tableContent">${obj.phoneNumber}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">邮箱：</td>
                        <td class="tableContent">${obj.email}</td>
                        <td class="columnTitle">公司：</td>
                        <td class="tableContent">${obj.company}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">性别：</td>
                        <td class="tableContent"><c:if test="${obj.sex==1}">
                            女
                        </c:if> <c:if test="${obj.sex==0}">男
                        </c:if></td>
                        <td class="columnTitle">家庭住址：</td>
                        <td class="tableContent">${obj.adress}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">婚姻状态：</td>
                        <td class="tableContent"><c:if test="${obj.marryd==1}">
                            未婚
                        </c:if> <c:if test="${obj.marryd==0}">已婚
                        </c:if></td>
                        <td class="columnTitle">微信：</td>
                        <td class="tableContent">${obj.weiXin}</td>
                    </tr>

                    <tr>
                        <td class="columnTitle">可用资产：</td>
                        <td class="tableContent">${obj.availableAssets}</td>

                        <td class="columnTitle">冻结资产：</td>
                        <td class="tableContent">${obj.frozenAssets}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">总资产：</td>
                        <td class="tableContent">${obj.frozenAssets+obj.availableAssets}</td>
                        <td class="columnTitle">区域：</td>
                        <td class="tableContent">${obj.areaName}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>

