<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>青果会员注册</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/reset.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/login.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        var meta = document.createElement('meta');
        meta.name = "viewport";
        var rp = window.devicePixelRatio;
        var scale = 1 / rp.toFixed(1);
        meta.content = "width=device-width,initial-scale=" + scale
            + ",minimum-scale=" + scale + ",maximum-scale=" + scale
            + ",user-scalable=no";
        document.head.appendChild(meta);
    </script>
    <script type="text/javascript">
        var html = document.documentElement;
        var htmlWidth = html.getBoundingClientRect().width;
        html.style.fontSize = htmlWidth / 16 + "px";
    </script>
    <script type="text/javascript">
        function setAreaName(val, text) {
            var ele = document.getElementById("areaName");
            ele.value = text;
            var ele1 = document.getElementById("areaId");
            ele1.value = val;
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#zc').click(function (event) {
                if (!document.getElementById("checkbox").checked) {
                    alert("必须同意协议！！！");
                    return;
                }

                //手机号校验
                var value = document.getElementById("phoneNumber").value;
                if (value != null && value != "") {
                    if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        //$('#phoneNumber').val("请输入正确手机号码");
                        document.getElementById("phoneNumber").val("");
                        document.getElementById("phoneNumber").attr('placeholder', "请输入正确手机号码");
                        document.getElementById("phoneNumber").focus();
                        return;
                    }
                } else {
                    document.getElementById("phoneNumber").attr('placeholder', "手机号码不能为空");

                    document.getElementById("phoneNumber").focus();
                    return;
                }

                //姓名
                value = document.getElementById("userName").value;
                if (value == null || value == "") {
                    $('#userName').attr('placeholder', "姓名不能为空");
                    $('#userName').focus();
                    return;
                } else {
                    if (!(/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        $('#userName').val("");
                        $('#userName').attr('placeholder', "请输入真实姓名");

                        $('#userName').focus();
                        return;
                    }
                }
                //区域校验
                value = document.getElementById("areaId").value;
                if (value == null || value == "") {
                    //	alert("昵称不能为空！！！");
                    alert("请选择区域");
                    return;
                }
                /*value = document.getElementById("email").value;
                 if (value != null && value != "") {
                 if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                 .test(value))) {
                 //	alert("邮箱格式不正确");
                 $("#email").val("请输入正确的邮箱");
                 $("#email").focus();
                 return;
                 }
                 } else {

                 $("#email").focus();
                 return;
                 }

                 value = document.getElementById("weiXin").value;
                 if (value == null || value == "") {
                 //alert("微信不能为空！！！");
                 $("#weiXin").focus();
                 return;
                 }*/
                //if (document.getElementById("areaId").selectedIndex == 0) {
                //	alert("请选择所属区域");
                //	return;
                //}
                $("#form").submit();

            });

            //手机号校验
            $('#phoneNumber').change(function (event) {
                var value = document.getElementById("phoneNumber").value;
                if (value != null && value != "") {
                    if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        //$('#phoneNumber').val("请输入正确手机号码");
                        document.getElementById("phoneNumber").val("");
                        document.getElementById("phoneNumber").attr('placeholder', "请输入正确手机号码");
                        document.getElementById("phoneNumber").focus();

                    }
                } else {
                    //alert("不能为空！！！");
                    document.getElementById("phoneNumber").attr('placeholder', "手机号码不能为空");

                    document.getElementById("phoneNumber").focus();

                }

            });

            //姓名校验
            //var s=/^[\u4e00-\u9fa5]{2,4}$/
            $('#userName').change(function (event) {
                var value = document.getElementById("userName").value;
                if (value != null && value != "") {
                    if (!(/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        $('#userName').val("");
                        $('#userName').attr('placeholder', "请输入真实姓名");
                        $('#userName').focus();


                    }
                } else {
                    //alert("不能为空！！！");
                    $('#userName').attr('placeholder', "姓名不能为空");
                    $('#userName').focus();
                }

            });

            //邮箱校验
            /*$('#email').change(function(event) {
             var value = document.getElementById("email").value;
             var t = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
             if (value != null && value != "") {
             if (!(t.test(value))) {
             $('#email').val("请输入正确邮箱地址");
             }
             } else {
             alert("不能为空！！！");
             }
             });*/

        });
        function setFirstParent(val) {
            var param = {
                "parentId": val
            };
            var ele1 = document.getElementById("areaId");
            ele1.value = "";
            var select = document.getElementById("secondSelect");
            //先清空，在添加一个请选择
            $("#secondSelect").find("option").remove();
            var option = document.createElement("option");
            //option.setAttribute("value", "0");
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
        function setSecondParent(val, text) {
            var param = {
                "parentId": val
            };
            var select = document.getElementById("areaId1");
            //先清空，在添加一个请选择
            $("#areaId1").find("option").remove();
            var option = document.createElement("option");
            //option.setAttribute("value", "0");
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
                        if (response.length == 0) {
                            var ele = document.getElementById("areaName");
                            ele.value = text;
                            var ele1 = document.getElementById("areaId");
                            ele1.value = val;
                            $("#areaId1").css("display", "none");

                        } else {
                            var ele1 = document.getElementById("areaId");
                            ele1.value = "";
                            $("#areaId1").css("display", "");
                        }

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

</head>
<body>
<div class="wrap">
    <h1>欢迎加入青果会员</h1>
    <form id="form"
          action="${pageContext.request.contextPath}/phone/public/basicinfo/student/insert.action"
          method="post" class="formList">

        <p>
            <c:if test="${ids==''}">
                <input type="hidden" name="ids">
            </c:if>
            <c:if test="${ids!=''}">
                <input type="hidden" value=${ids } name="ids">
            </c:if>
            <input type="hidden" value=${userOpenid } name=userOpenid>
        </p>

        <input type="hidden" name="photoPath" id="file1" style="height:20px"/>
        <p>
            <input type="text" name="phoneNumber" id="phoneNumber"
                   placeholder="请输入手机号码（登录用户名）" maxlength="11" required="required" style="width: 95%;"/><font
                color="red">*</font>
        </p>
        <p>
            <input type="text" name="userName" id="userName"
                   placeholder="请务必填写真实姓名" maxlength="20" required="required" style="width: 95%;"/><font
                color="red">*</font>
        </p>
        <p>
            <input type="hidden" name="email" id="email" placeholder="请输入您的电子邮箱">
        </p>

        <p>
            <input type="hidden" name="company" id="company"
                   placeholder="请输入您的工作单位" maxlength="50"/>
        </p>
        <!--  <p>
            <label for="">选择性别</label><br /> <input type="radio" name="sex"
                id="" value="0" />男 <input type="radio" name="sex" id="" value="1" />女
        </p>
        <p>
            <label for="">结婚状态</label><br /> <input type="radio" name="marryd"
                id="" value="0" />已婚<input type="radio" name="marryd" id=""
                value="1" />未婚
        </p>-->
        <p>
            <input type="hidden" name="adress" id="adress" placeholder="家庭详细地址"
                   maxlength="50"/>
        </p>
        <p>
            <input type="hidden" name="weiXin" id="weiXin" placeholder="微信号"
                   maxlength="50"/>
        </p>

        <p>
            <input type="hidden" name="shenFen" id="shenFen" placeholder="身份证号"
                   maxlength="18"/>
        </p>
        <p>
            <label for="">区域</label><br/> <select id="firstSelect"
                                                  name="firstSelect"
                                                  onchange="setFirstParent(this.options[this.selectedIndex].value);">

            <c:forEach items="${firstAreaList}" var="f">
                <option value="${f.id}">${f.areaName}</option>
            </c:forEach>
        </select><select id="secondSelect"
                         onchange="setSecondParent(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
            <option value="">--请选择--</option>
            <c:forEach items="${secondAreaList}" var="f">
                <option value="${f.id}">${f.areaName}</option>
            </c:forEach>
        </select> <select id="areaId1" name="areaId1"
                          onchange="setAreaName(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
            <option value="">--请选择--</option>
            <c:forEach items="${thirdAreaList}" var="f">
                <option value="${f.id}">${f.areaName}</option>
            </c:forEach>

        </select> <input type="hidden" id="areaId" name="areaId" value=""/> <input
                type="hidden" id="areaName" name="areaName" value=""/>
        </p>
        <p>
            <input type="checkbox" name="checkbox" id="checkbox" value=""/> <span>我已经认真阅读并同意
					<b><a
                            href="${pageContext.request.contextPath}/phone/public/basicinfo/student/agreement.action">《青果巷子网站使用协议》</a>
				</b> </span>
        </p>
        <input id="zc" type="buttom" value="注册" readonly/>
    </form>
</div>
</body>
</html>
