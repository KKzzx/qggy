<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/css/viewdetail.css"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <div class="textbox-title">浏览班级信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">班级名称：</td>
                        <td class="tableContent">${obj.className}</td>
                        <td class="columnTitle">班长名称：</td>
                        <td class="tableContent">${obj.classMonitor}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">班级人数：</td>
                        <td class="tableContent">${obj.classNumber}</td>
                        <td class="columnTitle">上课区域：</td>
                        <td class="tableContent">${obj.areaName}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">上课地点：</td>
                        <td class="tableContent">${obj.classAddress}</td>
                        <td class="columnTitle">上课时间：</td>
                        <td class="tableContent">${obj.classTime}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="textbox" id="centerTextbox">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">学员列表</div>
                </div>
            </div>
            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">名称</td>
                        <td class="tableHeader">手机</td>

                        <td class="tableHeader">状态</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${obj.tradeDetails}" var="td" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td>${status.index+1}</td>
                            <td>${td.userName}</td>
                            <td>${td.userPhone}</td>
                            <td><c:if test="${td.classState==1}">
                                <c:if test="${td.tradeState==4}">已确认</c:if>
                                <c:if test="${td.tradeState==0}">待确认</c:if>
                            </c:if></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <!--
					<img alt="" src="${ctx}/${erwei}">
					-->
            </div>
        </div>
    </div>
</form>
</body>
</html>

