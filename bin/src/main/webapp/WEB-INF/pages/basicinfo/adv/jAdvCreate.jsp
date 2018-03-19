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
<script>

    function setvisible(val) {

        if (val == "1") {

            $('#InnerAdv').css("display", "none");
            $('#CroAdv').css("display", "");


        } else if (val == "0") {

            $('#InnerAdv').css("display", "");
            $('#CroAdv').css("display", "none");

        }
    }
</script>
<body>
<div class="tab-content">

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">请选择增加广告的类型<select
                        onchange="setvisible(this.options[this.selectedIndex].value);">
                    <option value="2">请选择</option>
                    <option value="1">外部广告</option>
                    <option value="0">内部广告</option>
                </select></div>
            </div>
        </div>
    </div>
    <div id="InnerAdv" style="display:none">
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
                        <div class="textbox-title">请选择需要推广的课程</div>
                    </div>
                </div>

                <div>

                    <div class="eXtremeTable">
                        <table id="ec_table" class="tableRegion" width="98%">
                            <thead>
                            <tr>
                                <td class="tableHeader"></td>
                                <td class="tableHeader">序号</td>
                                <td class="tableHeader">课程名</td>
                                <td class="tableHeader">摘要</td>

                            </tr>
                            </thead>
                            <tbody class="tableBody">
                            <input type="hidden" name="adcategory" value="0">
                            <c:forEach items="${dataList}" var="o" varStatus="status">
                                <tr class="odd" onmouseover="this.className='highlight'"
                                    onmouseout="this.className='odd'">
                                    <c:if test="${o.state==3}">
                                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                        <td>${status.index+1}</td>
                                        <td><a href="toview.action?id=${o.id}">${o.courseName}</a></td>
                                        <td>${o.courseAbstract}</td>
                                    </c:if>

                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div id="CroAdv" style="display:none">
        <form method="post" name="fileinfo" enctype="multipart/form-data"
              action="insertCroAdv.action">
            <div id="menubar">
                <div id="middleMenubar">
                    <div id="innerMenubar">
                        <div id="navMenubar">
                            <ul>
                                <input type="submit" value="添加">
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
                            <input type="hidden" name="adcategory" value="1">
                            <tr>
                                <td class="columnTitle_mustbe">广告链接：</td>
                                <td class="tableContent"><input type="text" name="advUrl" maxlength="200"/><br/></td>
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
                                                                name="advCover"/><font
                                        color="red">推荐尺寸400*300，图片大小不要超过200k</font></td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告价格：</td>
                                <td class="tableContent"><input type="text"
                                                                name="advPrice" maxlength="20"/><br/></td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告内容：</td>
                                <td class="tableContent"><input type="text"
                                                                name="content" maxlength="200"/></td>
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

        $('#' + tabId).show().siblings().hide();
    })
</script>
</body>
</html>

