<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<script type="text/javascript">
    //设置冗余的生产厂家名称
    function setAreaName(val) {
        var ele = document.getElementById("areaName");
        ele.value = val;
    }
    //密码校验
    function passwordCheck() {
        value = document.getElementById("password").value;
        if (value == null || value == "") {
            //alert("微信不能为空！！！");
            $("#password").focus();
            return;
        }
        if (value.length < 6) {
            alert("密码长度不能少于6位");
            document.getElementById("password").value = "";
            return;
        }
    }
</script>
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
                <div class="textbox-title">修改系统管理员信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">名称：</td>
                        <td class="tableContent"><input type="text" name="name"
                                                        value="${obj.name}"/></td>
                    </tr>
                    <tr>
                        <td cl ass="columnTitle_mustbe">微信：</td>
                        <td class="tableContent"><input type="text" name="weixin"
                                                        value="${obj.weixin}"/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">邮箱：</td>
                        <td class="tableContent"><input type="text" name="email"
                                                        value="${obj.email}"/></td>
                    </tr>
                    <tr>
                        <td cl ass="columnTitle_mustbe">手机：</td>
                        <td class="tableContent"><input type="text"
                                                        name="phonenumber" value="${obj.phonenumber}"/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">密码：</td>
                        <td class="tableContent"><input type="text" name="password"
                                                        id="password" onchange="passwordCheck()" value="${obj.password}"
                                                        maxlength="20"/>
                        </td>
                    </tr>
                </table>
                <p>
                    <label for="">选择性别</label> <br/> <input type="radio" name="sex"
                                                            id="" value="0"
                                                            <c:if test="${obj.sex==0}">checked</c:if> />男 <input
                        type="radio" name="sex" id="" value="1"
                        <c:if test="${obj.sex==1}">checked</c:if> />女 <label for="">区域</label>

                </p>
                <p>
                    <select id="areaId" name="areaId"
                            onchange="setAreaName(this.options[this.selectedIndex].text);">
                        <option value="">--请选择--</option>
                        <c:forEach items="${arealist}" var="f">
                            <option value="${f.id}"
                                    <c:if test="${obj.areaId==f.id}">selected</c:if>>${f.areaName}</option>
                        </c:forEach>
                    </select> <input type="hidden" id="areaName" name="areaName" value=""/>
                </p>
            </div>
        </div>
    </div>
</form>
</body>
</html>

