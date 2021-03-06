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
                        <li id="new"><a href="#"
                                        onclick="formSubmit('celllist.action','_self');this.blur();">选中开班</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">课程-报名学员列表</div>
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
                        <td class="tableHeader">课程名</td>
                        <td class="tableHeader">已报名未开班学员人数</td>
                        <td class="tableHeader">区域</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.courseId}"/>
                            </td>
                            <td>${status.index+1}</td>
                            <td><a
                                    href="${pageContext.request.contextPath}/basicinfo/tradedetail/celllist.action?id=${o.courseId}">
                                    ${o.courseName}</td>
                            <td><c:if test="${o.num>=16}">
                                <font color="green">${o.num}</font>
                            </c:if> <c:if test="${o.num<16}">
                                <font color="black">${o.num}</font>
                            </c:if></td>
                            <td>${o.areaName}</td>
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