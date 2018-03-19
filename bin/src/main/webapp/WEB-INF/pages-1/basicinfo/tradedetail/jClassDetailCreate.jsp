<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
        //设置冗余的生产厂家名称
        function setSroleName(val) {
            var ele = document.getElementById("teacherName");
            ele.value = val;
        }
    </script>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" id="id" value=${classId}>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('insert.action','_self');">确定</a></li>
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">新增班级信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">班级名称：</td>
                        <td class="tableContent"><input type="text" name="className"/>
                        </td>
                        <td class="columnTitle_mustbe">班长：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classMonitor"/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">上课时间：</td>
                        <td class="tableContent"><input type="text" name="classTime"/>
                        </td>
                        <td class="columnTitle_mustbe">上课人数：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classNumber"/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">上课地点：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classAddress"/></td>
                        <td class="columnTitle_mustbe">上课次数：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classCishu"/></td>
                    </tr>
                    <tr>

                        <td class="columnTitle_mustbe">上课老师：</td>
                        <td class="tableContent">
                            <div class="textbox"
                                 id="centerTextbox">
                                <c:if test="${!empty(listteacher)}">


                                    <select name="teacherId"
                                            onchange="setSroleName(this.options[this.selectedIndex].text);">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${listteacher}" var="f">
                                            <option value="${f.teacherId}">${f.teacherName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="teacherName" id="teacherName" value=""/>

                                </c:if>

                            </div>
                        </td>
                        <td class="columnTitle_mustbe"></td>
                        <td class="tableContent"></td>
                    </tr>
                </table>


                <div class="textbox" id="centerTextbox">
                    <div class="textbox-header">
                        <div class="textbox-inner-header">
                            <div class="textbox-title">学生列表</div>
                        </div>
                    </div>

                    <div>

                        <div class="eXtremeTable">
                            <table id="ec_table" class="tableRegion" width="98%">
                                <thead>
                                <tr>
                                    <td class="tableHeader">序号</td>
                                    <td class="tableHeader">姓名</td>
                                    <td class="tableHeader">手机号</td>

                                </tr>
                                </thead>
                                <tbody class="tableBody">

                                <c:forEach items="${obj.students}" var="td"
                                           varStatus="status">
                                    <tr class="odd" onmouseover="this.className='highlight'"
                                        onmouseout="this.className='odd'">
                                        <td>${status.index+1}</td>
                                        <td>${td.userName}</td>
                                        <td>${td.phoneNumber}</td>

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

