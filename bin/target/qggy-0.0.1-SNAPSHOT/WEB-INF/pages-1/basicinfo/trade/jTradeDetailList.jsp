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
                <div class="textbox-title">报名列表</div>
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
                        <td class="tableHeader">detailID</td>
                        <td class="tableHeader">课程号</td>
                        <td class="tableHeader">课程名</td>
                        <td class="tableHeader">姓名</td>
                        <td class="tableHeader">用户ID</td>
                        <td class="tableHeader">状态</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/>
                            </td>
                            <td>${status.index+1}</td>
                            <td>${o.id}</td>
                            <td>${o.courseId}</td>
                            <td>${o.courseName}</td>
                            <td>${o.userName}</td>
                            <td>${o.userId}</td>
                            <td><c:if test="${o.tradeState==1}">已开班</c:if> <c:if
                                    test="${o.tradeState==0}">
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