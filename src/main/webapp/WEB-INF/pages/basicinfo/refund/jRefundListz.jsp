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
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox-header">
        <div class="textbox-inner-header">

            <div class="textbox-title">
                请输入退款申请人姓名<input type="text" name="likes" id="likes" value="${likes}">
                <a href="#" onclick="formSubmit('list.action','_self');">查询</a>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">退款列表 ${fn:length(dataList)} 条</div>
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
                        <td class="tableHeader">交易订单</td>
                        <td class="tableHeader">申请退款人</td>
                        <td class="tableHeader">退款金额</td>

                        <td class="tableHeader">申请时间</td>

                        <td class="tableHeader">区域</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">
                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/></td>
                            <td>${status.index+1}</td>
                            <td><a href="toview.action?id=${o.id}">${o.tradeId}</a></td>
                            <td>${o.refundUserName}</td>
                            <td>${o.money}</td>

                            <td><fmt:formatDate value="${o.applyTime}"
                                                pattern="yyyy-MM-dd HH:mm"/>
                            </td>
                            <td>${o.areaName}</td>


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