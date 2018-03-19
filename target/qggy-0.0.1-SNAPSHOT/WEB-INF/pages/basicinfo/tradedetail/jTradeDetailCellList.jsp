<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>

<body>
<form name="icform" method="post">
    <input type="hidden" id="courseId" name="courseId" value="${obj.id}"/>
    <input type="hidden" id="state" name="state" value="${state}">
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('createclass.action','_self');this.blur();">开班</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">课程信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">课程名称：</td>
                        <td class="tableContent">${obj.courseName}</td>
                        <td class="columnTitle">课程摘要：</td>
                        <td class="tableContent">${obj.courseAbstract}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">价格：</td>
                        <td class="tableContent">${obj.coursePrice}</td>
                        <td class="columnTitle">区域：</td>
                        <td class="tableContent">${obj.areaName}</td>
                </table>
            </div>
        </div>
    </div>
    <div class="textbox-header">
        <div class="textbox-inner-header">
            <div class="textbox-title">
                可选授课老师：
                <c:if test="${state==0}">
                    <font color="red">该课程没有老师授课，请先为该课程添加老师</font>
                    <a href="/qggy/basicinfo/course/toaddteacher.action?courseId=${obj.id}">选取授课老师</a>
                </c:if>
                <c:if test="${state==1}">
                    <c:forEach items="${list}" var="o" varStatus="status">${o.teacherName}
                    </c:forEach>
                    <a href="/qggy/basicinfo/course/toaddteacher.action?courseId=${obj.id}">选取其他授课老师</a>
                </c:if>
            </div>
        </div>
    </div>
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">${obj.courseName}报名列表
                    ${fn:length(dataList)} 条
                </div>
            </div>
        </div>

        <div>

            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid"
                                                       onclick="checkAll('id',this)"></td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">学员名</td>
                        <td class="tableHeader">手机</td>
                        <td class="tableHeader">区域</td>
                        <td class="tableHeader">状态</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/></td>
                            <td>${status.index+1}</td>
                            <td>${o.userName}</td>
                            <td>${o.userPhone}</td>
                            <td>${o.areaName}</td>
                            <td><c:if test="${o.classState==1}">已开班</c:if> <c:if
                                    test="${o.classState==0}">
                                <font color="green">待开班</font>
                            </c:if></td>
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