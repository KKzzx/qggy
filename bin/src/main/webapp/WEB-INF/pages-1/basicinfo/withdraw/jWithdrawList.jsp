<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                        <li id="view"><a href="#"
                                         onclick="formSubmit('list.action?state=1','_self');this.blur();">已批准</a>
                        </li>
                        <li id="view"><a href="#"
                                         onclick="formSubmit('list.action?state=3','_self');this.blur();">全部</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">提现列表</div>
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
                        <td class="tableHeader">用户名</td>
                        <td class="tableHeader">提现金额</td>
                        <td class="tableHeader">地区</td>
                        <td class="tableHeader">时间</td>
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/></td>
                            <td>${status.index+1}</td>
                            <td><a href="toview.action?id=${o.id}">${o.userName}</a></td>
                            <td>${o.money}</td>
                            <td>${o.areaName}</td>
                            <td><fmt:formatDate value="${o.withdrawTime}"
                                                pattern="yyyy-MM-dd HH:mm"/>
                            </td>
                            <td><c:if test="${o.state==0}"><a href="getmoneysure.action?id=${o.id}"><font color="green">批准</font></a></c:if>
                            </td>
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

