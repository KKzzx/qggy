<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>

<body>
<form name="icform" method="post">
    <input type="hidden" name="courseId" value="${courseId}"/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="#"
                                         onclick="formSubmit('addadv.action','_self');this.blur();">添加</a></li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('list.action','_self');this.blur();">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">当前课程：${coursename}</div>
            </div>
        </div>
        <div class="textbox" id="centerTextbox">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">广告列表</div>
                </div>
            </div>

            <div>

                <div class="eXtremeTable">
                    <table id="ec_table" class="tableRegion" width="98%">
                        <thead>
                        <tr>
                            <td class="tableHeader"><input type="checkbox" name="selid"
                            ></td>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">广告类型</td>
                            <td class="tableHeader">广告封面</td>
                            <td class="tableHeader">广告链接</td>
                            <td class="tableHeader">首页显示</td>
                            <td class="tableHeader">广告内容</td>
                            <td class="tableHeader">状态</td>
                            <td class="tableHeader">添加课程</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody">

                        <c:forEach items="${dataList}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'"
                                onmouseout="this.className='odd'">
                                <c:if test="${o.adstate==3}">
                                    <c:if test="${o.adcategory==1}">

                                        <td><input type="checkbox" name="id" value="${o.advertiseId}"/></td>
                                        <td>${status.index+1}</td>
                                        <td>外部广告</td>
                                        <td><img src="${pageContext.request.contextPath}/${o.advertiseCover}"
                                                 style="width:120px;height:80px"/></td>
                                        <td><a href="${o.advertiseUrl}">${o.advertiseUrl}</a></td>
                                        <td><c:if test="${o.isMain==1}">
                                            <font color="green">显示</font>
                                        </c:if> <c:if test="${o.isMain==0}">不显示
                                        </c:if></td>
                                        <td>${o.content}</td>
                                        <td><c:if test="${o.adstate==1}">
                                            <a href="stop.action?id=${o.advertiseId}"><font color="green">启用</font></a>
                                        </c:if> <c:if test="${o.adstate==0}">
                                            <a href="start.action?id=${o.id}">停用</a>
                                        </c:if></td>
                                        <td><a href="addadv.action?id=${o.advertiseId}&courseId=${courseId}"><font
                                                color="green">添加</font></a></td>
                                    </c:if>
                                    <c:if test="${o.adcategory==0}">

                                        <td><input type="checkbox" name="id" value="${o.advertiseId}"/></td>
                                        <td>${status.index+1}</td>
                                        <td>内部广告</td>
                                        <td><img src="${pageContext.request.contextPath}/${o.advertiseCover}"
                                                 style="width:120px;height:80px"/></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.advertiseUrl}">${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.advertiseUrl}</a>
                                        </td>
                                        <td><c:if test="${o.isMain==1}">
                                            <font color="green">显示</font>
                                        </c:if> <c:if test="${o.isMain==0}">不显示
                                        </c:if></td>
                                        <td>${o.content}</td>
                                        <td><c:if test="${o.adstate==1}">
                                            <a href="stop.action?id=${o.advertiseId}"><font color="green">启用</font></a>
                                        </c:if> <c:if test="${o.adstate==0}">
                                            <a href="start.action?id=${o.id}">停用</a>
                                        </c:if></td>
                                        <td><a href="addadv.action?id=${o.advertiseId}&courseId=${courseId}"><font
                                                color="green">添加</font></a></td>
                                    </c:if> </c:if></tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>

        </div>
        <div class="textbox" id="centerTextbox">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">已加广告列表</div>
                </div>
            </div>

            <div>

                <div class="eXtremeTable">
                    <table id="ec_table" class="tableRegion" width="98%">
                        <thead>
                        <tr>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">广告类型</td>
                            <td class="tableHeader">广告封面</td>
                            <td class="tableHeader">广告链接</td>
                            <td class="tableHeader">首页显示</td>
                            <td class="tableHeader">广告内容</td>
                            <td class="tableHeader">状态</td>
                            <td class="tableHeader">删除</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody">

                        <c:forEach items="${obj.advertisements}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'"
                                onmouseout="this.className='odd'">
                                <td>${status.index+1}</td>

                                <c:if test="${o.adcategory==1}">

                                    <td>外部广告</td>

                                </c:if>
                                <c:if test="${o.adcategory==0}">

                                    <td>内部广告</td>
                                </c:if>

                                <td><img src="${pageContext.request.contextPath}/${o.advertiseCover}"
                                         style="width:120px;height:80px"/></td>


                                <c:if test="${o.adcategory==1}">


                                    <td><a href="${o.advertiseUrl}">${o.advertiseUrl}</a></td>

                                </c:if>
                                <c:if test="${o.adcategory==0}">


                                    <td>
                                        <a href="${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.advertiseUrl}">${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.advertiseUrl}</a>
                                    </td>
                                </c:if>


                                <td><c:if test="${o.isMain==1}">
                                    <font color="green">显示</font>
                                </c:if> <c:if test="${o.isMain==0}">不显示
                                </c:if></td>
                                <td>${o.content}</td>
                                <td><c:if test="${o.adstate==1}">
                                    <a href="stop.action?id=${o.advertiseId}"><font color="green">启用</font></a>
                                </c:if> <c:if test="${o.adstate==0}">
                                    <a href="start.action?id=${o.advertiseId}">停用</a>
                                </c:if></td>
                                <td><a href="deleteAdv.action?id=${o.advertiseId}&courseId=${obj.id}"><font
                                        color="green">删除</font></a></td>
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

