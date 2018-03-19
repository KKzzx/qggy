<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>青果系统管理员注册</title>
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

            $('#zc')
                .click(
                    function (event) {
                        if (!document.getElementById("checkbox").checked) {
                            alert("必须同意协议！！！");
                            return;
                        }

                        //手机号校验
                        var value = document.getElementById("phonenumber").value;
                        if (value != null && value != "") {
                            if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                                //alert("不是完整的11位手机号或者正确的手机号前七位");
                                $('#phonenumber').val("");
                                $('#phonenumber').attr('placeholder', "请输入正确手机号码");
                                $('#phonenumber').focus();
                                return;
                            }
                        } else {
                            $('#phonenumber').attr('placeholder', "手机号码不能为空");

                            $('#phonenumber').focus();
                            return;
                        }

                        //姓名
                        value = document.getElementById("name").value;
                        if (value == null || value == "") {
                            $('#name').attr('placeholder', "姓名不能为空");
                            $('#name').focus();
                            return;
                        } else {
                            if (!(/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(value))) {
                                //alert("不是完整的11位手机号或者正确的手机号前七位");
                                $('#name').val("");
                                $('#name').attr('placeholder', "请输入真实姓名");
                                $('#name').focus();
                                return;
                            }
                        }
                        //邮箱校验
                        value = document.getElementById("email").value;
                        if (value != null && value != "") {
                            if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                                    .test(value))) {
                                //	alert("邮箱格式不正确");
                                $("#email").val("");
                                $('#email').attr('placeholder', "请输入正确格式邮箱");

                                $("#email").focus();
                                return;
                            }
                        } else {
                            //$("#email").focus();
                            //return;
                        }

                        /*value = document.getElementById("weixin").value;
                         if (value == null || value == "") {
                         //alert("微信不能为空！！！");
                         $("#weixin").focus();
                         return;
                         }*/
                        value = document.getElementById("areaId").value;
                        if (value == null || value == "") {
                            //	alert("昵称不能为空！！！");
                            alert("请选择区域");
                            return;
                        }

                        value = document.getElementById("password").value;
                        if (value == null || value == "") {
                            //alert("微信不能为空！！！");
                            $('#password').attr('placeholder', "请输入密码");
                            $("#password").focus();
                            return;
                        }
                        if (value.length < 6) {
                            alert("密码长度不能少于6位");
                            $('#password').attr('placeholder', "密码长度不能少于6位");
                            $("#password").focus();
                            return;
                        }
                        var pvalue = document.getElementById("password1").value;
                        if (pvalue == null || pvalue == "") {
                            alert("请输入重复密码");
                            $('#password1').attr('placeholder', "请输入密码");
                            $("#password1").focus();
                            return;
                        } else {
                            if (value != pvalue) {
                                $('#password').attr('placeholder', "密码不一致");
                                alert("密码不一致");
                                return;
                            }
                        }

                        $("#form").submit();

                    });

            //手机号校验
            $('#phonenumber').change(function (event) {
                var value = document.getElementById("phonenumber").value;
                if (value != null && value != "") {
                    if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        $('#phonenumber').val("");
                        $('#phonenumber').attr('placeholder', "请输入正确手机号码");
                        $('#phonenumber').focus();
                    }
                } else {
                    //alert("不能为空！！！");
                    $('#phonenumber').attr('placeholder', "手机号码不能为空");

                    $('#phonenumber').focus();

                }

            });
            //姓名校验
            //var s=/^[\u4e00-\u9fa5]{2,4}$/
            $('#name').change(function (event) {
                var value = document.getElementById("name").value;
                if (value != null && value != "") {
                    if (!(/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        $('#name').val("");
                        $('#name').attr('placeholder', "请输入真实姓名");
                        $('#name').focus();
                    }
                } else {
                    //alert("不能为空！！！");

                    $('#name').attr('placeholder', "姓名不能为空");
                    $('#name').focus();

                }

            });
            //邮箱校验
            $('#email').change(function (event) {
                var value = document.getElementById("email").value;
                var t = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                if (value != null && value != "") {
                    if (!(t.test(value))) {
                        $("#email").val("");
                        $('#email').attr('placeholder', "请输入正确格式邮箱");

                        $("#email").focus();
                    }
                } else {
                    //alert("不能为空！！！");
                }
            });

        });
        function setFirstParent(val) {
            var param = {"parentId": val};
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
            var param = {"parentId": val};
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
    <h1>欢迎加入青果管理员</h1>
    <form id="form"
          action="${pageContext.request.contextPath}/phone/public/basicinfo/sysuser/insert.action"
          method="post" class="formList">

        <p>
            <input type="hidden" value="${userOpenid}" name="openid"/>
        </p>
        <input type="hidden" name="photoPath" id="file1" style="height:20px"/>
        <p>
            <input type="text" name="phonenumber" id="phonenumber"
                   placeholder="请输入手机号码（登录用户名）" maxlength="11" required="required" style="width: 95%;"/><font
                color="red">*</font>
        </p>
        <p>
            <input type="text" name="name" id="name" placeholder="请输入您的姓名"
                   maxlength="20" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <input type="text" name="email" id="email" placeholder="请输入您的电子邮箱"
                   maxlength="30" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <input type="text" name="weixin" id="weixin" placeholder="微信号"
                   maxlength="50" required="required"/>
        </p>
        <p>
            <input type="password" name="password" id="password"
                   placeholder="密码" maxlength="50" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <input type="password" name="password1" id="password1"
                   placeholder="重复密码" maxlength="50" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <label for="">选择性别</label><br/> <input type="radio" name="sex"
                                                   id="" value="0"/>男 <input type="radio" name="sex" id="" value="1"/>女
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
            <input type="checkbox" name="checkbox" id="checkbox" value=""/> <span>我已确认并完全遵守
				<b><a
                        href="${pageContext.request.contextPath}/basicinfo/sysuser/agreement.action">《青果公益管理条例》</a>
			</b> </span></p><input id="zc" type="buttom" value="注册" readonly/>
    </form>
</div>
</body>
</html>
