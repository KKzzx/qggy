<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

    <div class="textbox-header">
        <div class="textbox-inner-header">
            <div class="textbox-title">
                当前老师：${obj.userName}<input type="hidden" name="teacherId"
                                           id="teacherId" value="${obj.id}"/> <input type="hidden"
                                                                                     name="teacherName" id="teacherName"
                                                                                     value="${obj.userName}"/>
            </div>
            <div class="textbox-title">老师爱好：${obj.likes}</div>
            <div class="textbox-title">老师区域：${obj.areaName}</div>
        </div>
    </div>


    <div class="textbox-header">
        <div class="textbox-inner-header">

            <div class="textbox-title">
                查询条件<input type="text" name="cname" id="cname" value="${cname}">区域

                <select id="firstSelect" name="firstSelect">
                    <option value="0" <c:if test="${areaid==0}">selected</c:if>>--请选择--</option>
                    <c:forEach items="${firstAreaList}" var="f">
                        <option value="${f.id}"
                                <c:if test="${areaid==f.id}">selected</c:if>>${f.areaName}</option>
                    </c:forEach>
                </select> <a href="#" onclick="formSubmit('toaddCourse.action','_self');">查询</a>

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


            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>

                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">课程名</td>
                        <td class="tableHeader">摘要</td>
                        <td class="tableHeader">区域</td>
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${listcourse}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <c:if test="${o.state==3}">
                                <td>${status.index+1}</td>
                                <td>${o.courseName}</td>
                                <td>${o.courseAbstract}</td>
                                <td>${o.areaName}</td>
                                <td><a href="#"
                                       onclick="formSubmit('insertCourse.action?courseId=${o.id}','_self');">添加</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>


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
<script type='text/javascript'>
    function stringToHex(str) {
        var val = "";
        for (var i = 0; i < str.length; i++) {
            if (val == "")
                val = str.charCodeAt(i).toString(16);
            else
                val += "," + str.charCodeAt(i).toString(16);
        }
        return val;
    }
</script>
</html>

