<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成为公益导师</title>
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
        function crchange() {
            var ele = document.getElementById("likes");
            ele.value = document.getElementById("like1").value;
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
                        //身份证
                        var value = document.getElementById("shenFen").value;
                        if (value != null && value != "") {
                            if (!(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
                                    .test(value))) {
                                //	alert("邮箱格式不正确");
                                $('#shenFen').val("");
                                $('#shenFen').attr('placeholder', "请输入正确身份证号");

                                $('#shenFen').focus();
                            }
                        } else {

                            $('#shenFen').attr('placeholder', "身份证号不能为空");
                            $('#shenFen').focus();
                            return;
                        }
                        value = document.getElementById("email").value;
                        if (value != null && value != "") {
                            if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                                    .test(value))) {
                                $("#email").val("");
                                $('#email').attr('placeholder', "请输入正确格式邮箱");

                                $("#email").focus();
                            }
                        } else {

                            /*$("#email").focus();
                             return;*/
                        }

                        /*value = document.getElementById("weiXin").value;
                         if (value == null || value == "") {
                         //alert("微信不能为空！！！");
                         $("#weiXin").focus();
                         return;
                         }

                         value = document.getElementById("company").value;
                         if (value == null || value == "") {
                         //alert("微信不能为空！！！");
                         $("#company").focus();
                         return;
                         }
                         value = document.getElementById("adress").value;
                         if (value == null || value == "") {
                         //alert("微信不能为空！！！");
                         $("#adress").focus();
                         return;
                         }*/
                        value = document.getElementById("like1").value;
                        if (value == null || value == "") {
                            //alert("微信不能为空！！！");
                            $('#like1').attr('placeholder', "个人爱好不能为空");
                            $('#like1').focus();
                            return;
                        }
                        //if (document.getElementById("areaId").selectedIndex == 0) {
                        //	alert("请选择所属区域");
                        //	return;
                        //}
                        $("#form").submit();

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
    </script>

</head>
<body>
<div class="wrap">
    <h1>欢迎加入青果公益导师</h1>
    <form id="form"
          action="${pageContext.request.contextPath}/phone/user/basicinfo/teacher/becometeacher.action"
          method="post" class="formList">
        <p>
            <input type="hidden" name="id" id="id" value="${teacher.id}"/>
        </p>
        <p>
            <input type="text" name="email" id="email" placeholder="请输入您的电子邮箱"
                   required="required" maxlength="30"/>
        </p>

        <p>
            <input type="text" name="company" id="company"
                   placeholder="请输入您的工作单位" maxlength="50" required="required"/>
        </p>
        <p>
            <label for="">选择性别</label><br/> <input type="radio" name="sex"
                                                   id="" value="0"/>男 <input type="radio" name="sex" id="" value="1"/>女
        </p>
        <p>
            <label for="">结婚状态</label><br/> <input type="radio" name="marryd"
                                                   id="" value="0"/>已婚<input type="radio" name="marryd" id=""
                                                                             value="1"/>未婚
        </p>
        <p>
            <input type="text" name="adress" id="adress" placeholder="家庭详细地址"
                   maxlength="50" required="required"/>
        </p>
        <p>
            <input type="text" name="weiXin" id="weiXin" placeholder="微信号"
                   maxlength="50" required="required"/>
        </p>

        <p>
            <input type="text" name="shenFen" id="shenFen" placeholder="身份证号"
                   maxlength="18" required="required"/>
        </p>

        <p>
            <label for="">擅长领域</label><br/> <input type="hidden" name="likes"
                                                   id="likes"/>
            <textarea name="like1" id="like1" onchange="crchange()"
                      style="width:100%;height:150px" maxlength="500"></textarea>
        </p>
        <p>
            <input type="checkbox" name="checkbox" id="checkbox" value=""/> <span>我已经认真阅读并同意
					<b><a
                            href="${pageContext.request.contextPath}/phone/public/basicinfo/student/agreement.action">《青果巷子网站使用协议》</a>
				</b> </span>
        </p>
        <input id="zc" type="buttom" value="提交申请" readonly/>
    </form>
</div>
</body>
</html>
