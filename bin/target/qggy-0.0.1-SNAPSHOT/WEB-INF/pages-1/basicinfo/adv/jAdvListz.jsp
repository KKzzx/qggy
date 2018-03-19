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
                                         onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('listall.action','_self');this.blur();">所有</a></li>
                    </ul>
                </div>
            </div>
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
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <c:if test="${o.adstate!=0&&o.adstate!=1}">
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
                                    <td><c:if test="${o.adstate==0}">
                                        草稿 </c:if> <c:if test="${o.adstate==1}">
                                        待分部审核
                                    </c:if>
                                        <c:if test="${o.adstate==2}">
                                            待总部审核 </c:if> <c:if test="${o.adstate==3}">
                                            发布
                                        </c:if>
                                        <c:if test="${o.adstate==4}">
                                            停用
                                        </c:if>
                                    </td>
                                    <td><c:if test="${o.adstate==2}"><a
                                            href="upstate.action?id=${o.advertiseId}&state=3">[发布]</a></c:if></td>
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
                                    <td><c:if test="${o.adstate==0}">
                                        草稿 </c:if> <c:if test="${o.adstate==1}">
                                        待分部审核
                                    </c:if>
                                        <c:if test="${o.adstate==2}">
                                            待总部审核 </c:if> <c:if test="${o.adstate==3}">
                                            发布
                                        </c:if>
                                        <c:if test="${o.adstate==4}">
                                            停用
                                        </c:if>
                                    </td>
                                    <td><c:if test="${o.adstate==2}"><a
                                            href="upstate.action?id=${o.advertiseId}&state=3">[发布]</a></c:if></td>
                                </c:if>

                            </c:if></tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>

    </div>


</form>
</body>
</html>

