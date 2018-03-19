<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
        //设置冗余的生产厂家名称
        function setSroleName(val) {
            var ele = document.getElementById("courseName");
            ele.value = val;
        }
    </script>
</head>
<body>
<form method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('insertCourse.action','_self');">确定</a></li>
                        <li id="back"><a
                                href="${ctx}/basicinfo/teacher/list.action?state=3">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="textbox-header">
        <div class="textbox-inner-header">
            <div class="textbox-title">当前老师：${obj.userName}<input type="hidden" name="teacherId" id="teacherId"
                                                                  value="${obj.id}"/>
                <input type="hidden" name="teacherName" id="teacherName" value="${obj.userName}"/>
            </div>
        </div>
    </div>
    <div class="textbox" id="centerTextbox">
        <c:if test="${!empty(listcourse)}">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">新增课程</div>
                </div>
            </div>

            <input type="hidden" name="courseName" id="courseName"/>
            <table class="commonTable" cellspacing="1">
                <tr>
                    <td class="columnTitle_mustbe">课程名称：</td>
                    <td class="tableContent"><select name="courseId"
                                                     onchange="setSroleName(this.options[this.selectedIndex].text);">
                        <option value="">--请选择--</option>
                        <c:forEach items="${listcourse}" var="f">
                            <option value="${f.id}">${f.courseName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
            </table>
        </c:if>
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
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">课程名</td>
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${listcourse1}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">

                            <td>${status.index+1}</td>
                            <td>${o.courseName}</td>
                            <td><a
                                    href="deleteCourse.action?id=${o.id}&userId=${obj.id}">[删除]</a>
                            </td>
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

