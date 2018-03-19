<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${obj.id}"/>

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('update.action','_self');">确定</a></li>
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">修改角色信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">角色名称：</td>
                        <td class="tableContent"><input type="text" name="roleName"
                                                        value="${obj.roleName}"/></td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">备注：</td>
                        <td class="tableContent"><textarea name="roleRemark"
                                                           style="height:120px;">${obj.roleRemark}</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>

