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
            <div class="textbox-title">当前课程：${obj.courseName}<input type="hidden" name="courseId" id="courseId"
                                                                    value="${obj.id}"/>
                <input type="hidden" name="courseName" id="courseName" value="${obj.courseName}"/>
            </div>
            <div class="textbox-title">课程摘要：${obj.courseAbstract}</div>
            <div class="textbox-title">课程区域：${obj.areaName}</div>
        </div>
    </div>


    <div class="textbox-header">
        <div class="textbox-inner-header">

            <div class="textbox-title">查询条件<input type="text" name="likes" id="likes" value="${likes}">区域

                <select id="firstSelect" name="firstSelect">
                    <option value="0"
                            <c:if test="${areaid==0}">selected</c:if> >--请选择--
                    </option>
                    <c:forEach items="${firstAreaList}" var="f">
                        <option value="${f.id}"
                                <c:if test="${areaid==f.id}">selected</c:if> >${f.areaName}</option>
                    </c:forEach>
                </select>


                <a href="#"
                   onclick="formSubmit('toaddteacher.action','_self');">查询</a>

            </div>
        </div>
    </div>


    <div class="textbox" id="centerTextbox">
        <c:if test="${!empty(liststudent)}">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">新增老师</div>
                </div>
            </div>


            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>

                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">老师姓名</td>
                        <td class="tableHeader">爱好</td>
                        <td class="tableHeader">电话</td>
                        <td class="tableHeader">区域</td>
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${liststudent}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">
                            <c:if test="${o.state==3}">
                                <td>${status.index+1}</td>
                                <td>${o.userName}</td>
                                <td>${o.likes}</td>
                                <td>${o.phoneNumber}</td>
                                <td>${o.areaName}</td>
                                <td><a href="#"
                                       onclick="formSubmit('insertTeacher.action?teacherId=${o.id}&teacherName=${o.userName}','_self');">添加</a>
                                </td>
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
                <div class="textbox-title">教师列表</div>
            </div>
        </div>

        <div>

            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">教师姓名</td>
                        <td class="tableHeader">操作</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${listcourse1}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'"
                            onmouseout="this.className='odd'">

                            <td>${status.index+1}</td>
                            <td>${o.teacherName}</td>
                            <td><a
                                    href="deleteTeacher.action?id=${o.id}&courseId=${obj.id}">[删除]</a>
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

