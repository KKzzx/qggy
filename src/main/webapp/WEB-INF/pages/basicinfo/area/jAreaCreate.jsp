<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
</head>
<body>
<form method="post">

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


        <div class="textbox-title">
            <p style="">
                <font color="red">请注意 </font> 在添加三级区域时，只允许添加至市级，例如<br> <font
                    color="red">中国>河南省>郑州市<br>中国>上海>上海市
            </font>
            </p>
            <div class="textbox-header">
                <div class="textbox-inner-header">
                    <div class="textbox-title">新增区域信息</div>
                </div>
            </div>
            <div>
                <div>
                    <table class="commonTable" cellspacing="1">
                        <tr>
                            <td class="columnTitle_mustbe">请选择添加区域等级：</td>

                            <td class="tableContent"><select
                                    onchange="setAreaLevel(this.options[this.selectedIndex].value);">
                                <option value="0">--请选择--</option>
                                <option value="1">一级区域</option>
                                <option value="2" selected>二级区域</option>
                                <option value="3">三级区域</option>

                            </select> <input type="hidden" id="level" name="areaLevel" value="2"/></td>
                        </tr>
                        <tr id="tr1">


                            <td class="columnTitle_mustbe">父级区域一：</td>


                            <td class="tableContent"><select id="firstSelect"
                                                             onchange="setFirstParent(this.options[this.selectedIndex].value);">
                                <option value="">--请选择--</option>
                                <c:forEach items="${firstAreaList}" var="r">
                                    <option value="${r.id}">${r.areaName}</option>
                                </c:forEach>
                            </select> <input type="hidden" id="fp" name="firstParent" value=""/></td>


                        </tr>
                        <tr id="tr2" style="display: none">
                            <td class="columnTitle_mustbe">父级区域二：</td>

                            <td class="tableContent"><select id="secondSelect"
                                                             onchange="setSecondParent(this.options[this.selectedIndex].value);">
                                <option value="">--请选择--</option>
                            </select> <input type="hidden" id="secondParent" name="secondParent"
                                             value=""/></td>

                        </tr>
                        <tr>
                            <td class="columnTitle_mustbe">区域名称：</td>
                            <td class="tableContent"><input type="text" name="areaName" maxlength="10"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="columnTitle_mustbe">备注：</td>
                            <td class="tableContent"><textarea name="areaRemark"
                                                               style="height: 120px;" maxlength="200"></textarea></td>
                        </tr>
                        <input type="hidden" name="areaState" value="1"/>
                    </table>
                </div>
            </div>
        </div>

</form>
</body>
<script type="text/javascript">
    function setAreaLevel(val) {
        var ele = document.getElementById("level");
        //隐藏域提交
        ele.value = val;
        //控制菜单的显示隐藏
        if (val == 1) {
            $("#tr1").css("display", "none");
            $("#tr2").css("display", "none");
        } else if (val == 2) {
            $("#tr1").css("display", "");
            $("#tr2").css("display", "none");
        } else if (val == 3) {
            $("#tr1").css("display", "");
            $("#tr2").css("display", "");
        }
    }
    function setSecondParent(val) {
        var ele = document.getElementById("secondParent");
        ele.value = val;
    }
    //val 提交隐藏域，并且调用ajax去联动第二个表单
    function setFirstParent(val) {
        var element = document.getElementById("fp");
        element.value = val;
        var param = {
            "parentId": val
        };
        var select = document.getElementById("secondSelect");
        //先清空，在添加一个请选择
        $("#secondSelect").find("option").remove();
        var option = document.createElement("option");
        option.setAttribute("value", "0");
        option.appendChild(document.createTextNode("--请选择--"));
        select.appendChild(option);
        $
            .ajax({
                url: "${pageContext.request.contextPath}/public/basicinfo/area/getChild.action", //请求地址
                type: "POST", //请求方式
                data: JSON.stringify(param), //请求参数
                dataType: "json",
                contentType: 'application/json;charset=utf-8', //指定为json类型
                success: function (response) {
                    for (var i = 0; i < response.length; i++) {
                        var option = document.createElement("option");
                        option.setAttribute("value", response[i].id);
                        option.appendChild(document
                            .createTextNode(response[i].areaName));
                        select.appendChild(option);
                    }
                },
                fail: function (status) {
                    alert("接口调用异常");
                }
            });
    }
</script>
</html>

