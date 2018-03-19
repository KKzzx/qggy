<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>�����ʦע��</title>
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
        function crchange() {
            var ele = document.getElementById("zishu");
            ele.value = document.getElementById("zishus").value;
        }
    </script>
    <script type="text/javascript">
        $(function () {

            $('#zc')
                .click(
                    function (event) {
                        if (!document.getElementById("checkbox").checked) {
                            alert("����ͬ��Э�飡����");
                            return;
                        }

                        //�ֻ���У��
                        var value = document.getElementById("phoneNumber").value;
                        if (value != null && value != "") {
                            if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                                //alert("����������11λ�ֻ��Ż�����ȷ���ֻ���ǰ��λ");
                                document.getElementById("phoneNumber").val("");
                                document.getElementById("phoneNumber").attr('placeholder', "��������ȷ�ֻ�����");
                                document.getElementById("phoneNumber").focus();
                                return;
                            }
                        } else {
                            document.getElementById("phoneNumber").attr('placeholder', "�ֻ����벻��Ϊ��");

                            document.getElementById("phoneNumber").focus();
                            return;
                        }

                        //����
                        value = document.getElementById("userName").value;
                        if (value == null || value == "") {
                            $('#userName').attr('placeholder', "��������Ϊ��");
                            $('#userName').focus();
                            return;
                        } else {
                            if (!(/^[\u4E00-\u9FA5\uf900-\ufa2d��s]{2,20}$/
                                    .test(value))) {
                                //alert("����������11λ�ֻ��Ż�����ȷ���ֻ���ǰ��λ");
                                $('#userName').val("");
                                $('#userName').attr('placeholder', "��������ʵ����");

                                $('#userName').focus();
                                return;
                            }
                        }
                        value = document.getElementById("email").value;
                        if (value != null && value != "") {
                            if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                                    .test(value))) {
                                //	alert("�����ʽ����ȷ");
                                $("#email").val("");
                                $('#email').attr('placeholder', "��������ȷ��ʽ����");

                                $("#email").focus();
                            }
                        } else {

                            //$("#email").focus();
                            //return;
                        }
                        value = document.getElementById("adress").value;
                        if (value != null && value != "") {
                        } else {

                            $('#adress').attr('placeholder', "�������ַ");

                            $("#adress").focus();
                            return;
                        }
                        //���֤
                        value = document.getElementById("shenFen").value;
                        if (value != null && value != "") {
                            if (!(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
                                    .test(value))) {
                                //	alert("�����ʽ����ȷ");
                                $('#shenFen').val("");
                                $('#shenFen').attr('placeholder', "��������ȷ���֤��");

                                $('#shenFen').focus();
                            }
                        } else {


                            $('#shenFen').attr('placeholder', "���֤�Ų���Ϊ��");
                            $('#shenFen').focus();
                            return;
                        }
                        //����У��
                        value = document.getElementById("areaId").value;
                        if (value == null || value == "") {
                            //	alert("�ǳƲ���Ϊ�գ�����");
                            alert("��ѡ������");
                            return;
                        }


                        /*value = document.getElementById("weiXin").value;
                         if (value == null || value == "") {
                         $("#weiXin").val("΢�Ų���Ϊ�գ�����");
                         $("#weiXin").focus();
                         return;
                         }*/

                        value = document.getElementById("likes").value;
                        if (value != null && value != "") {

                        } else {
                            $('#likes').attr('placeholder', "�������ó�");

                            $("#likes").focus();
                            return;
                        }

                        value = document.getElementById("zishu").value;
                        if (value != null && value != "") {

                        } else {
                            $('#zishus').attr('placeholder', "����������");

                            $("#zishus").focus();
                            return;
                        }
                        //if (document.getElementById("areaId").selectedIndex == 0) {
                        //	alert("��ѡ����������");
                        //	return;
                        //}
                        $("#form").submit();

                    });

            //�ֻ���У��
            $('#phoneNumber').change(function (event) {
                var value = document.getElementById("phoneNumber").value;
                if (value != null && value != "") {
                    if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                        //alert("����������11λ�ֻ��Ż�����ȷ���ֻ���ǰ��λ");
                        document.getElementById("phoneNumber").val("");
                        document.getElementById("phoneNumber").attr('placeholder', "��������ȷ�ֻ�����");
                        document.getElementById("phoneNumber").focus();
                    }
                } else {
                    //alert("����Ϊ�գ�����");
                    document.getElementById("phoneNumber").attr('placeholder', "�ֻ����벻��Ϊ��");

                    document.getElementById("phoneNumber").focus();

                }

            });
            //����У��
            //var s=/^[\u4e00-\u9fa5]{2,4}$/
            $('#userName').change(function (event) {
                var value = document.getElementById("userName").value;
                if (value != null && value != "") {
                    if (!(/^[\u4E00-\u9FA5\uf900-\ufa2d��s]{2,20}$/.test(value))) {
                        //alert("����������11λ�ֻ��Ż�����ȷ���ֻ���ǰ��λ");
                        $('#userName').val("");
                        $('#userName').attr('placeholder', "��������ʵ����");

                        $('#userName').focus();
                    }
                } else {
                    //alert("����Ϊ�գ�����");
                    $('#userName').attr('placeholder', "��������Ϊ��");
                    $('#userName').focus();
                    return;
                }

            });
            //����У��
            $('#email').change(function (event) {
                var value = document.getElementById("email").value;
                var t = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                if (value != null && value != "") {
                    if (!(t.test(value))) {
                        //$('#email').val("��������ȷ�����ַ");
                        $("#email").val("");
                        $('#email').attr('placeholder', "��������ȷ��ʽ����");

                        $("#email").focus();
                    }
                } else {
                    //alert("����Ϊ�գ�����");
                }
            });

        });
        function setFirstParent(val) {
            var param = {"parentId": val};
            var ele1 = document.getElementById("areaId");
            ele1.value = "";
            var select = document.getElementById("secondSelect");
            //����գ������һ����ѡ��
            $("#secondSelect").find("option").remove();
            var option = document.createElement("option");
            //option.setAttribute("value", "0");
            option.appendChild(document.createTextNode("--��ѡ��--"));
            select.appendChild(option);
            $
                .ajax({
                    url: "${pageContext.request.contextPath}/public/basicinfo/area/getChild.action", //�����ַ
                    type: "POST", //����ʽ
                    data: JSON.stringify(param), //�������
                    dataType: "json",
                    contentType: 'application/json;charset=utf-8', //ָ��Ϊjson����
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
                        alert("�ӿڵ����쳣");
                    }
                });
        }
        function setSecondParent(val, text) {
            var param = {"parentId": val};
            var select = document.getElementById("areaId1");
            //����գ������һ����ѡ��
            $("#areaId1").find("option").remove();
            var option = document.createElement("option");
            //option.setAttribute("value", "0");
            option.appendChild(document.createTextNode("--��ѡ��--"));
            select.appendChild(option);
            $
                .ajax({
                    url: "${pageContext.request.contextPath}/public/basicinfo/area/getChild.action", //�����ַ
                    type: "POST", //����ʽ
                    data: JSON.stringify(param), //�������
                    dataType: "json",
                    contentType: 'application/json;charset=utf-8', //ָ��Ϊjson����
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
                        alert("�ӿڵ����쳣");
                    }
                });
        }
    </script>

</head>
<body>
<div class="wrap">
    <h1>��ӭ��Ϊ������浼ʦ</h1>
    <form id="form"
          action="${pageContext.request.contextPath}/phone/public/basicinfo/student/insertteacher.action"
          method="post" class="formList">

        <p>
            <input type="hidden" value=${userOpenid } name=userOpenid>
        </p>
        <input type="hidden" name="photoPath" id="file1" style="height:20px"/>
        <p>
            <input type="text" name="phoneNumber" id="phoneNumber"
                   placeholder="�������ֻ����루��¼�û��������" maxlength="11" required="required"
                   style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <input type="text" name="userName" id="userName"
                   placeholder="������������ʵ���������" maxlength="20" required="required" style="width: 95%;"/><font
                color="red">*</font>
        </p>
        <p>
            <input type="text" name="email" id="email" placeholder="���������ĵ������䣨���"
                   maxlength="50" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>

        <p>
            <input type="text" name="company" id="company"
                   placeholder="���������Ĺ�����λ" maxlength="50" required="required"/>
        </p>
        <p>
            <label for="">ѡ���Ա�</label><br/> <input type="radio" name="sex"
                                                     id="" value="0"/>�� <input type="radio" name="sex" id=""
                                                                                value="1"/>Ů
        </p>
        <!--<p>
            <label for="">���״̬</label><br /> <input type="radio" name="marryd"
                id="" value="0" />�ѻ�<input type="radio" name="marryd" id=""
                value="1" />δ��
        </p>-->
        <p>
            <input type="text" name="adress" id="adress" placeholder="��ͥ��ϸ��ַ�����"
                   maxlength="50" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <input type="text" name="weiXin" id="weiXin" placeholder="΢�ź�"
                   maxlength="50" required="required"/>
        </p>

        <p>
            <input type="text" name="shenFen" id="shenFen" placeholder="���֤�ţ����"
                   maxlength="18" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <label for="">����</label><br/> <select id="firstSelect"
                                                    name="firstSelect"
                                                    onchange="setFirstParent(this.options[this.selectedIndex].value);">

            <c:forEach items="${firstAreaList}" var="f">
                <option value="${f.id}">${f.areaName}</option>
            </c:forEach>
        </select><select id="secondSelect"
                         onchange="setSecondParent(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
            <option value="">--��ѡ��--</option>
            <c:forEach items="${secondAreaList}" var="f">
                <option value="${f.id}">${f.areaName}</option>
            </c:forEach>
        </select> <select id="areaId1" name="areaId1"
                          onchange="setAreaName(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
            <option value="">--��ѡ��--</option>
            <c:forEach items="${thirdAreaList}" var="f">
                <option value="${f.id}">${f.areaName}</option>
            </c:forEach>

        </select> <input type="hidden" id="areaId" name="areaId" value=""/> <input
                type="hidden" id="areaName" name="areaName" value=""/>
        </p>
        <p>

            <input type="text" name="likes" id="likes" placeholder="�ó����򣨱��"
                   maxlength="50" required="required" style="width: 95%;"/><font color="red">*</font>
        </p>
        <p>
            <label for="">���������������</label><br/> <input type="hidden" name="zishu"
                                                               id="zishu"/>
            <textarea name="zishus" id="zishus" onchange="crchange()" wrap="soft" cols="45" rows="4"
                      maxlength="200"
                      placeholder="��Ŀ���λ�ܹ�ʤ�ε������������Թ������֪���Կγ̵���Ϥ�̶�Ҳ���Կγ̽�ѧ�Ľ���ȣ�������200��"></textarea>
        </p>
        <p>
            <input type="checkbox" name="checkbox" id="checkbox" value=""/> <span>���Ѿ������Ķ���ͬ��
					<b><a
                            href="${pageContext.request.contextPath}/phone/public/basicinfo/student/agreement.action">��������ӹ��浼ʦЭ�顷</a>
				</b> </span>
        </p>
        <input id="zc" type="buttom" value="ע��" readonly/>
    </form>
</div>
</body>
</html>
