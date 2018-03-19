<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<link rel="stylesheet" rev="stylesheet" type="text/css"
      href="${ctx}/css/viewdetail.css"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修改账户信息</title>
</head>
<body>
<form method="post" name="fileinfo">
    <input type="hidden" name="id" value="${obj.id}"/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('update.action','_self');">修改</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">修改账户信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">姓名：</td>
                        <td class="tableContent"><input type="text" name="userName" maxlength="10"
                                                        value="${obj.userName}" readonly="readonly"/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">手机号码：</td>
                        <td class="tableContent"><input type="text"
                                                        name="phoneNumber" value="${obj.phoneNumber}" maxlength="12"
                                                        readonly="readonly"/>
                        </td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">邮箱：</td>
                        <td class="tableContent"><input type="text" name="email" maxlength="100"
                                                        value="${obj.email}"/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">地址：</td>
                        <td class="tableContent"><input type="text"
                                                        name="adress" value="${obj.adress}" maxlength="50"/><br/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">所属区域：</td>
                        <td class="tableContent"><input type="text"
                                                        name="areaName" value="${obj.areaName}" maxlength="10"
                                                        readonly="readonly"/><br/>
                        </td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">身份证：</td>
                        <td class="tableContent"><input type="text"
                                                        name="shenFen" value="${obj.shenFen}" maxlength="10"/><br/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">课程账户资产：</td>
                        <td class="tableContent"><input type="text"
                                                        name="availableAssets" value="${obj.availableAssets}"
                                                        maxlength="10"/><br/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">现金账户资产：</td>
                        <td class="tableContent"><input type="text"
                                                        name="xianjin" value="${obj.xianjin}" maxlength="10"/><br/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">总资产：</td>
                        <td class="tableContent"><input type="text"
                                                        name="allAssets" value="${obj.xianjin+obj.availableAssets}"
                                                        maxlength="10"/><br/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">爱好：</td>
                        <td class="tableContent"><input type="text"
                                                        name="likes" value="${obj.likes}" maxlength="10"/><br/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">申请者自述：</td>
                        <td class="tableContent"><input type="text"
                                                        name="zishu" value="${obj.zishu}" maxlength="10"/><br/></td>
                    </tr>

                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>
