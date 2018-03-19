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
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
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
                    <td class="tableContent"><c:if test="${o.category==0}">课程</c:if>
                        <c:if test="${o.category==1}">
                            充值
                        </c:if> <c:if test="${o.category==2}">商品
                        </c:if>
                    </td>
                    <td class="columnTitle">状态：</td>
                    <td><c:if test="${o.state==0}">
                        <font color="green">申请退款</font>
                    </c:if> <c:if test="${o.state==1}">
                        <font color="blue"> 退款审查中</font>
                    </c:if> <c:if test="${o.state==2}">
                        <font color="red"> 已驳回</font>
                    </c:if> <c:if test="${o.state==3}">
                        <font color="black"> 部分退款</font>
                    </c:if> <c:if test="${o.state==4}">
                        <font color="green"> 已退款</font>
                    </c:if> <c:if test="${o.state==5}">
                        <font color="green">交易完成</font>
                    </c:if></td>
                </tr>
                <tr>
                    <td class="columnTitle">支付人姓名：</td>
                    <td class="tableContent">${o.payUserName}</td>
                    <td class="columnTitle">支付时间：</td>
                    <td class="tableContentAuto"><fmt:formatDate
                            value="${o.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td class="columnTitle">总金额：</td>
                    <td class="tableContent">${o.totalFee}</td>
                    <td class="columnTitle">支付金额：</td>
                    <td class="tableContent">${o.cashFee}</td>
                </tr>
            </table>
        </div>
    </div>
    <br>
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">退款详情列表</div>
            </div>
        </div>
        <div>
            <div>

                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle">申请退款人员：</td>
                        <td class="tableContent">${obj.refundUserName}</td>
                        <td class="columnTitle">申请退款时间：</td>
                        <td class="tableContentAuto"><fmt:formatDate
                                value="${obj.applyTime}" pattern="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="columnTitle">申请退款金额：</td>
                        <td class="tableContent">${obj.money}</td>
                    </tr>
                    <tr>
                        <td class="columnTitle">申请退款原因：</td>
                        <td class="tableContent">${obj.refundReason}</td>
                    </tr>
                </table>
            </div>
        </div>

        <br>
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
                            <td class="tableHeader">状态</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody">

                        <c:forEach items="${o.tradedetails}" var="td"
                                   varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'"
                                onmouseout="this.className='odd'">
                                <td>${status.index+1}</td>
                                <td>${td.userName}</td>
                                <td>${td.courseName}</td>
                                <td>${td.areaName}</td>
                                <td><c:if test="${td.tradeState==0}">
                                    <a href="toapplyone.action?id=${td.id}"><font
                                            color="green">申请退款</font> </a>
                                </c:if> <c:if test="${td.tradeState==1}">
                                    <font color="blue"> 退款审查中</font>
                                </c:if> <c:if test="${td.tradeState==2}">
                                    <font color="red"> 已驳回</font>
                                </c:if> <c:if test="${td.tradeState==3}">
                                    <font color="black"> 已退款</font>
                                </c:if> <c:if test="${td.tradeState==4}">
                                    <font color="black">订单完成</font>
                                </c:if></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>