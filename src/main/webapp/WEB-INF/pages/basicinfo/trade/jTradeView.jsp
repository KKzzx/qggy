<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
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
                <div class="textbox-title">订单详细信息</div>
            </div>
        </div>
        <div>
            <div>

                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">交易类型：</td>
                        <td class="tableContent"><c:if test="${obj.category==0}">课程</c:if>
                            <c:if test="${obj.category==1}">
                                充值
                            </c:if> <c:if test="${obj.category==2}">商品
                            </c:if></td>
                        <td class="columnTitle">状态：</td>
                        <td><c:if test="${o.state==0}">
                            已支付
                        </c:if> <c:if test="${o.state==1}">
                            <font color="red">申请退款</font>
                        </c:if> <c:if test="${o.state==2}">
                            <font color="green">驳回</font>
                        </c:if> <c:if test="${o.state==3}">
                            <font color="red">部分退款</font>
                        </c:if> <c:if test="${o.state==4}">
                            <font color="green">已退款</font>
                        </c:if><c:if test="${o.state==5}">
                            <font color="green">已完成</font>
                        </c:if>
                            <c:if test="${o.state==6}">
                                <font color="green">已开班</font>
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td class="columnTitle">支付人姓名：</td>
                        <td class="tableContent">${obj.payUserName}</td>
                        <td class="columnTitle">支付时间：</td>
                        <td class="tableContentAuto"><fmt:formatDate
                                value="${obj.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle">总金额：</td>
                        <td class="tableContent">${obj.totalFee}</td>
                        <td class="columnTitle">支付金额：</td>
                        <td class="tableContent">${obj.cashFee}</td>
                    </tr>
                </table>
            </div>
        </div>


        <div class="textbox" id="centerTextbox">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">人员列表</div>
                </div>
            </div>

            <div>

                <div class="eXtremeTable">
                    <table id="ec_table" class="tableRegion" width="98%">
                        <thead>
                        <tr>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">参加人员</td>
                            <td class="tableHeader">参加课程</td>
                            <td class="tableHeader">参加区域</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody">

                        <c:forEach items="${obj.tradedetails}" var="td"
                                   varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'"
                                onmouseout="this.className='odd'">
                                <td>${status.index+1}</td>
                                <td>${td.userName}</td>
                                <td>${td.courseName}</td>
                                <td>${td.areaName}</td>
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

