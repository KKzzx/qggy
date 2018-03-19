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

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="back"><a href="list.action">返回</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">浏览管理员信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">名称：</td>
                        <td class="tableContent">${obj.name}</td>
                        <td class="columnTitle">微信号：</td>
                        <td class="tableContent">${obj.weixin}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">邮箱：</td>
                        <td class="tableContent">${obj.email}</td>
                        <td class="columnTitle">手机：</td>
                        <td class="tableContent">${obj.phonenumber}</td>
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
                        <td class="columnTitle">状态：</td>
                        <td class="tableContent"><c:if test="${obj.state==1}">
                            <font color="green">已启用</font>
                        </c:if> <c:if test="${obj.state==0}">
                            <font color="red">已停用</font>
                        </c:if>
                        </td>
                    </tr>
                </table>

                <div class="textbox" id="centerTextbox">
                    <div class="textbox-header">
                        <div class="textbox-inner-header">
                            <div class="textbox-title">角色列表</div>
                        </div>
                    </div>

                    <div>

                        <div class="eXtremeTable">
                            <table id="ec_table" class="tableRegion" width="98%">
                                <thead>
                                <tr>
                                    <td class="tableHeader">序号</td>
                                    <td class="tableHeader">角色</td>
                                </tr>
                                </thead>
                                <tbody class="tableBody">

                                <c:forEach items="${obj.roles}" var="td" varStatus="status">
                                    <tr class="odd" onmouseover="this.className='highlight'"
                                        onmouseout="this.className='odd'">
                                        <td>${status.index+1}</td>
                                        <td>${td.sroleName}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>

