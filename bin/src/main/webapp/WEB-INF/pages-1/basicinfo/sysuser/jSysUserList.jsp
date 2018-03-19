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
                        <li id="view"><a href="#"
                                         onclick="formSubmit('toview.action','_self');this.blur();">查看</a>
                        </li>

                        <li id="update"><a href="#"
                                           onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a>
                        </li>
                        <li id="delete"><a href="#"
                                           onclick="formSubmit('deleteById.action','_self');this.blur();">删除</a>
                        </li>
                        <li id="delete"><a href="#"
                                           onclick="formSubmit('delete.action','_self');this.blur();">删除N</a>
                        </li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('start.action','_self');this.blur();">启用</a>
                        </li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('stop.action','_self');this.blur();">停用</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">管理员列表</div>
            </div>
        </div>

        <div>

            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid"
                                                       onclick="checkAll('id',this)">
                        </td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">名称</td>
                        <td class="tableHeader">微信号</td>
                        <td class="tableHeader">电话</td>
                        <td class="tableHeader">邮箱</td>
                        <td class="tableHeader">性别</td>
                        <td class="tableHeader">所属区域</td>
                        <td class="tableHeader">状态</td>
                        <td class="tableHeader">添加角色</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/>
                            </td>
                            <td>${status.index+1}</td>
                            <td>${o.name}</td>
                            <td>${o.weixin}</td>
                            <td>${o.phonenumber}</td>
                            <td>${o.email}</td>
                            <td><c:if test="${o.sex==1}">女
                            </c:if> <c:if test="${o.sex==0}">男
                            </c:if>
                            </td>
                            <td>${o.areaName}</td>
                            <td><c:if test="${o.state==1}">
                                <a href="stop.action?id=${o.id}"><font color="green">启用</font>
                                </a>
                            </c:if> <c:if test="${o.state==0}">
                                <a href="start.action?id=${o.id}">停用</a>
                            </c:if></td>
                            <td><a href="toUrRoupdate.action?id=${o.id}">添加</a></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

</form>
</body>
</html>

