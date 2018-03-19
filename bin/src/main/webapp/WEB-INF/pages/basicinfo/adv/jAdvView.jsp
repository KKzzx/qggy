<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/css/viewdetail.css"/>
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
                <div class="textbox-title">浏览广告信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="tableHeader">广告封面</td>
                        <td class="tableContent"><img src="${pageContext.request.contextPath}/${obj.advertiseCover}"
                                                      style="width:120px;height:80px"/></td>

                        <td class="tableHeader">广告链接</td>
                        <td class="tableContent"><c:if test="${obj.adcategory==1}"><a
                                href="${obj.advertiseUrl}">${obj.advertiseUrl}</a></c:if>

                            <c:if test="${obj.adcategory==0}"><a
                                    href="${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${obj.advertiseUrl}">${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${obj.advertiseUrl}</a>
                            </c:if>


                        </td>


                    </tr>
                    <tr>
                        <td class="tableHeader">首页显示</td>
                        <td class="tableContent"><c:if test="${obj.isMain==1}">
                            <font color="green">显示</font>
                        </c:if> <c:if test="${obj.isMain==0}">不显示
                        </c:if></td>
                        </td>
                        <td class="tableHeader">广告内容</td>

                        <td class="tableContent">${obj.content}</td>


                    </tr>

                </table>
            </div>
        </div>
    </div>


    <div class="eXtremeTable">
        <table id="ec_table" class="tableRegion" width="98%">
            <thead>
            <tr>

                <td class="tableHeader">序号</td>
                <td class="tableHeader">课程名</td>
                <td class="tableHeader">摘要</td>
                <td class="tableHeader">价格</td>
                <td class="tableHeader">最低开班人数</td>
                <td class="tableHeader">状态</td>

            </tr>
            </thead>
            <tbody class="tableBody">

            <c:forEach items="${obj.courses}" var="o" varStatus="status">
                <tr class="odd" onmouseover="this.className='highlight'"
                    onmouseout="this.className='odd'">

                    <td>${status.index+1}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.id}">${o.courseName}</a>
                    </td>
                    <td>${o.courseAbstract}</td>
                    <td>${o.coursePrice}</td>
                    <td>${o.openNumber}</td>
                    <td><c:if test="${o.state==0}">
                        草稿
                    </c:if> <c:if test="${o.state==1}">
                        待分部审核
                    </c:if>

                        <c:if test="${o.state==2}">
                            待总部审核
                        </c:if> <c:if test="${o.state==3}">
                            发布
                        </c:if><c:if test="${o.state==4}">
                            停用
                        </c:if></td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</form>
</body>
</html>

