<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>

<body>
<form name="icform" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('surechangepassword.action','_self');this.blur();">确定</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">更改密码成功后请重新登陆</div>
            </div>
        </div>
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">请输入原始密码：</div>
                <input type="password" name="password" id="password" maxlength="50"
                       required="required" style=""/>
            </div>
        </div>
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">请输入新密码：</div>
                <input type="password" name="password1" id="password1"
                       maxlength="50" required="required" style=""/>
            </div>
        </div>
    </div>
</form>
</body>
</html>

