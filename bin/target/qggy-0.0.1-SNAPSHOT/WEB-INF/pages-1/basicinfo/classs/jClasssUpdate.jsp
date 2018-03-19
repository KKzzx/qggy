<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">

        function setSroleName(val) {
            var ele = document.getElementById("teacherName");
            ele.value = val;
        }
    </script>
</head>
<body>
<form method="post" name="fileinfo">
    <input type="hidden" name="id" value="${obj.id}"/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('update.action','_self');">确定</a></li>
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">修改班级信息</div>
            </div>
        </div>
        <div>
            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">班级名称：</td>
                        <td class="tableContent"><input type="text" name="className" maxlength="10"
                                                        value="${obj.className}"/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">班长名称：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classMonitor" value="${obj.classMonitor}" maxlength="10"/>
                        </td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">上课时间：：</td>
                        <td class="tableContent"><input type="text" name="classTime" maxlength="100"
                                                        value="${obj.classTime}"/></td>
                    </tr>
                    <tr style="height: 10px">
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">上课地点：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classAddress" value="${obj.classAddress}" maxlength="50"/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">上课次数：</td>
                        <td class="tableContent"><input type="text"
                                                        name="classAddress" value="${obj.classCishu}"
                                                        maxlength="10"/><br/></td>
                    </tr>
                    <tr>
                        <td class="columnTitle_mustbe">上课教师：</td>
                        <td class="tableContent"><select name="teacherId"
                                                         onchange="setSroleName(this.options[this.selectedIndex].text);">
                            <option value="">--请选择--</option>
                            <c:forEach items="${listteacher}" var="f">
                                <option value="${f.teacherId}"
                                        <c:if test="${obj.teacherId==f.teacherId}">selected</c:if>>${f.teacherName}</option>
                            </c:forEach>
                        </select><input type="hidden"
                                        name="teacherName"/><br/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">学员列表</div>
            </div>
        </div>
        <div class="eXtremeTable">
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader">序号</td>
                    <td class="tableHeader">名称</td>
                    <td class="tableHeader">手机</td>
                    <td class="tableHeader">操作</td>
                </tr>
                </thead>
                <tbody class="tableBody">

                <c:forEach items="${obj.students}" var="td" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'"
                        onmouseout="this.className='odd'">
                        <td>${status.index+1}</td>
                        <td>${td.userName}</td>
                        <td>${td.phoneNumber}</td>

                        <td><a href="toreturn.action?id=${td.id}&classId=${obj.id}">退班</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</form>
</body>
</html>

