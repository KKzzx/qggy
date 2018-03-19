<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
        //设置冗余的生产厂家名称
        function setSroleName(val) {
            var ele = document.getElementById("sroleName");
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
                                         onclick="formSubmit('insertUrRo.action','_self');">确定</a>
                        </li>
                        <li id="back"><a href="${ctx}/basicinfo/sysuser/list.action">返回</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <c:if test="${!empty(rolelist)}">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">新增角色信息</div>
            </div>
        </div>
        <div>
            <div>
                <input type="hidden" name="suserId" id="suserId" value="${obj.id}"/>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">角色名称：</td>
                        <td class="tableContent"><select name="sroleId"
                                                         onchange="setSroleName(this.options[this.selectedIndex].text);">
                            <option value="">--请选择--</option>
                            <c:forEach items="${rolelist}" var="f">
                                <option value="${f.id}">${f.roleName}</option>
                            </c:forEach>
                        </select> <input type="hidden" name="sroleName" id="sroleName" value=""/>
                        </td>
                    </tr>
                </table>
                </c:if>
            </div>
        </div>

        <div class="textbox" id="centerTextbox">
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">角色列表</div>
                </div>
            </div>

            <div>

                <div class="eXtremeTable">
                    <table id="ec_table" class="tableRegion" width="98%">
                        <thead>
                        <tr>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">角色</td>
                            <td class="tableHeader">操作</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody">

                        <c:forEach items="${obj.roles}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'"
                                onmouseout="this.className='odd'">

                                <td>${status.index+1}</td>
                                <td>${o.sroleName}</td>
                                <td><a
                                        href="deleteUrRoById.action?id=${o.id}&userId=${obj.id}">[删除]</a>
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

