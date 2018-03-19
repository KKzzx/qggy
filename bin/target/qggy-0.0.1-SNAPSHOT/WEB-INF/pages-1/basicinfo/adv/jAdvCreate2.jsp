<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <style>
        .tab-item {
            display: inline-block
        }
    </style>
</head>

<body>
<div class="tab-div">
    <div class="tab-item" data-id="InnerAdv">内部广告</div>
    <div class="tab-item" data-id="CroAdv">外部广告</div>
</div>
<div class="tab-content">
    <div id="InnerAdv">
        <form name="icform" method="post">

            <div id="menubar">
                <div id="middleMenubar">
                    <div id="innerMenubar">
                        <div id="navMenubar">
                            <ul>
                                <li id="new"><a href="#"
                                                onclick="formSubmit('insertInnerAdv.action','_self');this.blur();">新增</a>
                                </li>
                                <li id="update"><a href="#"
                                                   onclick="formSubmit('list.action','_self');this.blur();">返回</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
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
                                <td class="tableHeader"><input type="checkbox"
                                                               name="selid" onclick="checkAll('id',this)"></td>
                                <td class="tableHeader">序号</td>
                                <td class="tableHeader">课程名</td>
                                <td class="tableHeader">摘要</td>
                                <td class="tableHeader">价格</td>
                                <td class="tableHeader">最低开班人数</td>
                                <td class="tableHeader">状态</td>
                            </tr>
                            </thead>
                            <tbody class="tableBody">
                            <input type="hidden" name="categoryId" value="0" }>
                            <c:forEach items="${dataList}" var="o" varStatus="status">
                                <tr class="odd" onmouseover="this.className='highlight'"
                                    onmouseout="this.className='odd'">
                                    <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                    <td>${status.index+1}</td>
                                    <td><a href="toview.action?id=${o.id}">${o.courseName}</a></td>
                                    <td>${o.courseAbstract}</td>
                                    <td>${o.coursePrice}</td>
                                    <td>${o.openNumber}<img src="${o.courseCover}"></td>
                                    <td><c:if test="${o.state==1}">
                                        <font color="green">启用</font>
                                    </c:if> <c:if test="${o.state==0}">
                                        停用
                                    </c:if></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div id="CroAdv">
        <form method="post" name="fileinfo" enctype="multipart/form-data"
              action="insertCroAdv.action">
            <div id="menubar">
                <div id="middleMenubar">
                    <div id="innerMenubar">
                        <div id="navMenubar">
                            <ul>
                                <li id="save"><input type="submit" value="添加"></li>
                                <li id="back"><a href="list.action">返回</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="textbox" id="centerTextbox">

                <div class="textbox-header">
                    <div class="textbox-inner-header">
                        <div class="textbox-title">新增课程</div>
                    </div>
                </div>
                <div>
                    <div>
                        <table class="commonTable" cellspacing="1">
                            <input type="hidden" name="categoryId" value="1">
                            <tr>
                                <td class="columnTitle_mustbe">广告链接：</td>
                                <td class="tableContent"><input type="text" name="advUrl"/><br/></td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">首页展示：</td>
                                <td class="tableContent"><select name="isMain">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select></td>
                            </tr>
                            <tr style="height: 10px"></tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告封面图片：</td>
                                <td class="tableContent"><input type="file"
                                                                name="advCover"/></td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告价格：</td>
                                <td class="tableContent"><input type="text"
                                                                name="advPrice"/><br/></td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript">
    $('.tab-item').unbind('click').bind('click', function () {

        var tabId = $(this).attr('data-id');
        console.log(tabId)
        $('#' + tabId).show().siblings().hide();
    })
</script>
</body>
</html>

