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
                                         onclick="formSubmit('toview.action','_self');this.blur();">查看</a>
                        </li>
                        <li id="update"><a href="#"
                                           onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a>
                        </li>


                        <li id="new"><a href="#"
                                        onclick="formSubmit('classinsert.action','_self');this.blur();">插班</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">班级列表</div>
            </div>
        </div>

        <div>

            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid"
                                                       onclick="checkAll('id',this)">
                        </td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">班级名称</td>
                        <td class="tableHeader">班长</td>
                        <td class="tableHeader">班级人数</td>
                        <td class="tableHeader">上课时间</td>
                        <td class="tableHeader">上课地点</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/>
                            </td>
                            <td>${status.index+1}</td>
                            <td><a href="toview.action?id=${o.id}">${o.className}</a>
                            </td>
                            <td>${o.classMonitor}</td>
                            <td>${o.classNumber}</td>
                            <td>${o.classTime}</td>
                            <td>${o.classAddress}</td>

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

