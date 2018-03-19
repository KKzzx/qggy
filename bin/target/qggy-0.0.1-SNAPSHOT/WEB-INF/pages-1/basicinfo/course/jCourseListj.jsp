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
                                        onclick="formSubmit('tocreate.action','_self');this.blur();">新增</a></li>
                        <li id="update"><a href="#"
                                           onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a></li>
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
                <div class="textbox-title">课程列表</div>
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
                        <td class="tableHeader">摘要</td>
                        <td class="tableHeader">价格</td>
                        <td class="tableHeader">最低开班人数</td>
                        <td class="tableHeader">是否精华</td>

                        <td class="tableHeader">状态</td>
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/></td>
                            <td>${status.index+1}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=${o.id}">${o.courseName}</a>
                            </td>
                            <td>${o.courseAbstract}</td>
                            <td>${o.coursePrice}</td>
                            <td>${o.openNumber}</td>
                            <td><c:if test="${o.recommend==0}">
                                否
                            </c:if> <c:if test="${o.recommend==1}">
                                是
                            </c:if></td>
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
                                </c:if>
                            </td>
                            <td><c:if test="${o.state==0}">
                                <a href="upstate.action?id=${o.id}&state=1"><font
                                        color="green">[提交分部审核]</font></a>
                            </c:if><c:if test="${o.state==3}">
                                <a href="upstate.action?id=${o.id}&state=4"><font
                                        color="green">[停用]</font></a><a href="toAddAdv.action?id=${o.id}"><font
                                    color="green">[添加广告]</font></a>
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

