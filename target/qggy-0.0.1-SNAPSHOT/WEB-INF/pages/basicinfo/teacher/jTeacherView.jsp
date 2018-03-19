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
                <div class="textbox-title">浏览教师信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>

                        <td class="columnTitle">手机：</td>
                        <td class="tableContent">${obj.phoneNumber}</td>
                        <td class="columnTitle">名称：</td>
                        <td class="tableContent">${obj.userName}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">邮箱：</td>
                        <td class="tableContent">${obj.email}</td>
                        <td class="columnTitle">地址：</td>
                        <td class="tableContent">${obj.adress}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">性别：</td>
                        <td class="tableContent"><c:if test="${obj.sex==1}">女
                        </c:if> <c:if test="${obj.sex==0}">男
                        </c:if>
                        </td>
                        <td class="columnTitle">所属区域：</td>
                        <td class="tableContent">${obj.areaName}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">身份证：</td>
                        <td class="tableContent">${obj.shenFen}></td>
                    </tr>

                    <tr>
                        <td class="columnTitle">课程账户资产：</td>
                        <td class="tableContent">${obj.availableAssets}</td>
                        <td class="columnTitle">现金账户资产：</td>
                        <td class="tableContent">${obj.xianjin}</td>


                    </tr>
                    <tr>
                        <td class="columnTitle">总资产：</td>
                        <td class="tableContent">${obj.xianjin+obj.availableAssets}</td>
                        <td class="columnTitle">爱好：</td>
                        <td class="tableContent">${obj.likes}</td>

                    </tr>
                    <tr>
                        <td class="columnTitle">申请者自述：</td>
                        <td class="tableContent">${obj.zishu}</td>

                    </tr>
                </table>

            </div>
        </div>
    </div>
</form>
</body>
</html>

