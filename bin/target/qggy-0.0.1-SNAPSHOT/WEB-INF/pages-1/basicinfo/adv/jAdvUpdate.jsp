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
<div class="tab-content">
    <div id="CroAdv">
        <form method="post" name="fileinfo" enctype="multipart/form-data"
              action="update.action">
            <div id="menubar">
                <div id="middleMenubar">
                    <div id="innerMenubar">
                        <div id="navMenubar">
                            <ul>
                                <input type="submit" value="修改">
                                <li id="back"><a href="list.action">返回</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="textbox" id="centerTextbox">

                <div class="textbox-header">
                    <div class="textbox-inner-header">
                        <div class="textbox-title">修改广告</div>
                    </div>
                </div>
                <div>
                    <div>
                        <table class="commonTable" cellspacing="1">
                            <input type="hidden" name="adcategory" value="1">
                            <input type="hidden" name="advertiseId" value="${dataList.advertiseId}">
                            <tr>
                                <td class="columnTitle_mustbe">广告链接：</td>
                                <td class="tableContent"><input type="text" name="advUrl"
                                                                value="${dataList.advertiseUrl}" maxlength="200"/><br/>
                                </td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">首页展示：</td>
                                <td class="tableContent"><select name="isMain">
                                    <option value="0"
                                            <c:if test="${dataList.isMain==0}">selected</c:if> >否
                                    </option>
                                    <option value="1" <c:if test="${dataList.isMain==1}">selected</c:if>>是</option>
                                </select></td>
                            </tr>
                            <tr style="height: 10px"></tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告封面图片：</td>
                                <td class="tableContent"><input type="file"
                                                                name="advCover" value="${dataList.advertiseCover}"/>
                                </td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告价格：</td>
                                <td class="tableContent"><input type="text"
                                                                name="advPrice" value="${dataList.price}"
                                                                maxlength="20"/><br/></td>
                            </tr>
                            <tr style="height: 10px">
                            </tr>
                            <tr>
                                <td class="columnTitle_mustbe">广告内容：</td>
                                <td class="tableContent"><input type="text" name="content" value="${dataList.content}"
                                                                maxlength="200"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

