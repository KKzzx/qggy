<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<link rel="stylesheet" type="text/css"
      href="${ctx}/css/viewdetail.css"/>
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
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">浏览课程信息</div>
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
                        <td class="columnTitle">最低开班人数：</td>
                        <td class="tableContent">${obj.openNumber}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">类别：</td>
                        <td class="tableContent">${obj.categoryName}</td>
                        <td class="columnTitle">等级：</td>
                        <td class="tableContent">${obj.rankName}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">区域：</td>
                        <td class="tableContent">${obj.areaName}</td>
                        <td class="columnTitle">状态：</td>
                        <td class="tableContent"><c:if test="${obj.state==0}">
                            草稿
                        </c:if> <c:if test="${obj.state==1}">
                            待分部审核
                        </c:if>

                            <c:if test="${obj.state==2}">
                                待总部审核
                            </c:if> <c:if test="${obj.state==3}">
                                发布
                            </c:if><c:if test="${obj.state==4}">
                                停用
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="columnTitle">审批人：</td>
                        <td class="tableContent">${obj.releaseName}</td>
                        <td class="columnTitle">审批时间：</td>
                        <td class="tableContent"><fmt:formatDate
                                value="${obj.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>

                </table>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">备注：</td>
                        <td class="tableContent">${obj.courseRemark}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">封面：</td>
                        <td class="tableContent"><img src="${ctx}/${obj.courseCover}"></td>
                    </tr>
                    <tr>
                        <td class="columnTitle">详情：</td>
                        <td class="tableContent">${obj.courseContent}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div>
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">课程下的广告信息</div>
            </div>
        </div>
        <div class="eXtremeTable">
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader">序号</td>
                    <td class="tableHeader">广告封面</td>
                    <td class="tableHeader">广告链接</td>
                    <td class="tableHeader">首页显示</td>
                    <td class="tableHeader">广告内容</td>
                    <td class="tableHeader">状态</td>

                </tr>
                </thead>
                <c:if test="${obj.advertisements[0].advertiseId!=0}">
                    <tbody class="tableBody">

                    <c:forEach items="${obj.advertisements}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td>${status.index+1}</td>
                            <td><img src="${pageContext.request.contextPath}/${o.advertiseCover}"
                                     style="width:120px;height:80px"/></td>
                            <td><c:if test="${o.adcategory==1}"><a href="${o.advertiseUrl}">${o.advertiseUrl}</a></c:if>

                                <c:if test="${o.adcategory==0}"><a
                                        href="${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.advertiseUrl}">${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.advertiseUrl}</a></c:if>
                            </td>
                            <td><c:if test="${o.isMain==1}">
                                <font color="green">显示</font>
                            </c:if> <c:if test="${o.isMain==0}">不显示
                            </c:if></td>
                            <td>${o.content}</td>
                            <td><c:if test="${o.adstate==0}">
                                草稿 </c:if> <c:if test="${o.adstate==1}">
                                待分部审核
                            </c:if> <c:if test="${o.adstate==2}">
                                待总部审核 </c:if> <c:if test="${o.adstate==3}">
                                发布
                            </c:if> <c:if test="${o.adstate==4}">
                                停用
                            </c:if></td>
                        </tr>
                    </c:forEach></tbody>
                </c:if>


            </table>
        </div>
    </div>
</form>
</body>
</html>

