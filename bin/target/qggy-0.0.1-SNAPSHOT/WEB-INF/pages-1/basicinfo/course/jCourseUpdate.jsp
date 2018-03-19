<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>课程修改</title>
    <script type="text/javascript" src="../../js/uploadPic.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<form method="post" name="fileinfo"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${obj.id}"/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="getContent(),formSubmit('update.action?state=1','_self');">发布</a></li>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('update.action?state=0','_self');">草稿</a></li>
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>


    <div class="textbox-header">
        <div class="textbox-inner-header">
            <div class="textbox-title">课程修改</div>
        </div>
    </div>
    <div>
        <div>
            <table class="commonTable" cellspacing="1">
                <tr>
                    <td class="columnTitle_mustbe">课程类别：</td>
                    <td class="tableContent"><select name="categoryId"
                                                     onchange="setCategoryName(this.options[this.selectedIndex].text);">
                        <option value="">--请选择--</option>
                        <c:forEach items="${categoryList}" var="c">
                            <option value="${c.id}"
                                    <c:if test="${obj.categoryId==c.id}">selected</c:if> >${c.categoryName}</option>
                        </c:forEach>
                    </select> <input type="hidden" id="categoryName" name="categoryName"
                                     value="${obj.categoryName }"/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">课程等级：</td>
                    <td class="tableContent"><select name="rankId"
                                                     onchange="setRankName(this.options[this.selectedIndex].text);">
                        <option value="">--请选择--</option>
                        <c:forEach items="${rankList}" var="r">
                            <option value="${r.id}"
                                    <c:if test="${obj.rankId==r.id}">selected</c:if>
                            >${r.rankName}</option>
                        </c:forEach>
                    </select> <input type="hidden" id="rankName" name="rankName" value="${obj.rankName}"/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">课程名：</td>
                    <td class="tableContent"><input type="text"
                                                    name="courseName" value="${obj.courseName}"/><br/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">课程摘要：</td>
                    <td class="tableContent"><input type="text"
                                                    name="courseAbstract" value="${obj.courseAbstract}"/><br/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">课程封面图片：</td>
                    <td class="tableContent"><input type="file"
                                                    name="courseCover" id="pic_file" value="${obj.courseCover}"/> <br/>
                    </td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">课程价格：</td>
                    <td class="tableContent"><input type="text"
                                                    name="coursePrice" value="${obj.coursePrice}"/><br/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">最低开班人数：</td>
                    <td class="tableContent"><input type="text" name="openNum" value="${obj.openNumber}"/><br/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td class="columnTitle_mustbe">课程备注：<input type="hidden" name="state" value="${obj.state}"/></td>
                </tr>
                <tr style="height: 10px">
                </tr>
                <tr>
                    <td></td>
                    <td><input type="hidden" name="courseRemark" id="courseRemark"
                               value="${obj.courseRemark}"/><textarea name="courseRemark1"
                                                                      onchange="crchange()" id="courseRemark1"
                                                                      style="height: 120px; width: 300px">${obj.courseRemark}</textarea>
                    </td>
                </tr>
                <tr>
                </tr>
                <tr>
                    <td>课程内容:</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <script id="editor" type="text/plain"
                                style="width: 1024px; height: 500px;">${obj.courseContent}</script>
                        <textarea
                                name="courseContent" rows="5" cols="20" style="display: none"
                                id="txt"></textarea></td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');

    function setCategoryName(val) {
        var ele = document.getElementById("categoryName");
        ele.value = val;
    }

    function setRankName(val) {
        var ele = document.getElementById("rankName");
        ele.value = val;
    }
    function crchange() {
        var ele = document.getElementById("courseRemark");
        ele.value = document.getElementById("courseRemark1").value;
    }
    function getContent() {
        var content = UE.getEditor('editor').getContent();
        var ele = document.getElementById("txt");
        ele.value = content;
    }
</script>
</html>

