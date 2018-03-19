<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <title>${course.courseName}</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/common.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
          rel="stylesheet">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="https://one.teegon.com/jslib/t-charging.min.js"></script>

    <style>
        body {
            font-family: '微软雅黑';
            background: #f5f7fa;
        }

        h5, i, textarea {
            margin: 0;
            padding: 0;
        }

        .clearfix:after {
            visibility: hidden;
            display: block;
            font-size: 0;
            content: ' ';
            clear: both;
            height: 0;
        }

        .clearfix {
            zoom: 1;
        }

        .my-from {
            width: 950px;
            margin: 0 auto;
            border: 1px solid #ccc;
            background: #fff;
        }

        .my-title {
            width: 100%;
            height: 60px;
            font-size: 20px;
            text-align: center;
            position: relative;
        }

        .my-title h5 {
            font-size: 18px;
            line-height: 60px;
        }

        .my-title .my-icon-close {
            font-size: 20px;
            position: absolute;
            right: 20px;
            top: 0px;
            cursor: pointer;
        }

        .my-pic-title {
            width: 100%;
            height: 40px;
            margin: 20px 0;
            background: #f5f7fa;
        }

        .my-pic-title div {
            width: 40%;
            height: 40px;
            line-height: 40px;
            float: left;
        }

        .my-pic-content {
            width: 100%;
            position: relative;
        }

        .my-pic-content .my-item {
            width: 100%;
            position: relative;
            padding: 10px 0;
        }

        .my-pic-content .my-item label {
            cursor: pointer;
        }

        .my-pic-content .my-radio {
            width: 40%;
            margin-left: 10%;
            display: inline-block;
            font-weight: normal;
        }

        .my-pic-content .my-item-money {
            width: 40%;
            display: inline-block;
            text-align: center;
        }

        .my-remarks {
            width: 100%;
            background: #f5f5f5;
            padding: 10px 25% 10px 10%;
            margin-top: 10px;
            color: #b80218;
        }

        .my-user-message {
            width: 100%;
            position: relative;
        }

        .my-user-message .my-input {
            width: 100%;
            position: relative;
            padding: 10px 0;
        }

        .my-user-message .my-input label {
            width: 10%;
            margin-left: 10%;
            display: inline-block;
            font-weight: normal;
        }

        .my-user-message .my-input input {
            width: 20%;
            height: 30px;
            font-size: 16px;
            border-radius: 5px;
            margin-left: 5%;
            outline: none;
            border: 1px solid #ccc;
            text-indent: 1em;
        }

        .my-user-message .my-input .my-span {
            display: inline-block;
            width: 20%;
            color: #b80218;
            margin-left: 5%;
            height: 25px;
            line-height: 25px;
            font-size: 16px;
        }

        .my-user-message .my-input .my-select {
            display: inline-block;
            margin-left: 5%;
            width: 70%;
        }

        .my-user-message .my-input textarea {
            margin-left: 6.2%;
            width: 50%;
            height: 100px;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: none;
        }

        .my-btn {
            width: 100%;
            text-align: center;
            padding: 20px 0;
        }

        .my-btn button {
            padding: 6px 50px;
            background: #2196f3;
            color: #fff;
            border: none;
            font-size: 16px;
            border-radius: 5px;
        }

        .my-btn button:hover {
            background: #0b80dc;
            cursor: pointer;
        }

        .tip-err2 {
            line-height: 34px;
            height: 34px;
            color: red;
            text-align: left;
            display: none;
            margin-left: 10px;
        }

        .bg {
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, .3);
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 2;
        }

        .bg i {
            font-size: 100px;
            position: absolute;
            left: 50%;
            top: 45%;
            color: #fff;
        }
    </style>

    <style>
        #native img {
            width: 100%;
            height: 100%;
        }

        body {
            background: #f5f7fa;
        }

        .text-top img {
            vertical-align: middle;
        }

        .o-space {
            width: 10px;
            display: inline-block;
        }

        .alipay-msg {
            font-size: 16px;
        }

        .o-pay-marT {
            margin-top: 30px;
        }

        .o-pay-nopad-lr {
            padding-left: 0;
            padding-right: 0;
        }

        .o-pay-pad-tb {
            padding-bottom: 10px;
        }

        .o-pay-pad-tb img {
            width: 90px;
        }

        .o-pay-nomarb {
            margin-bottom: 0;
        }

        .o-pay-padL {
            padding-left: 20px;
        }

        .pay_danger {
            color: #c71622;
        }

        .pay_textC {
            color: #959595;
        }

        .o-pay_text {
            color: #535353;
            padding-top: 8px;
            font-size: 14px;
        }

        .o-pay_text2 {
            color: #959595;
            padding-top: 30px;
            font-size: 12px !important;
        }

        .pay_head {
            border-bottom: 1px solid #dedede;
            padding-top: 5px;
        }

        .pay_prompt {
            text-indent: 33px;
            color: #535353;
            padding-bottom: 10px;
        }

        .pay_title {
            padding-left: 20px;
            padding-right: 20px;
        }

        .pay_content {
            padding-top: 20px;
            border-bottom: 1px solid #dedede;
        }

        .pay_footer {
            width: 100%;
        }

        #native img {
            width: 100%;
        }

        .select_type img {
            width: 129px;
        }

        .sub-pay {
            width: 140px;
            height: 40px;
        }

        .sub-pay button {
            background: #2196f3;
            color: #fff;
        }

        .sub-pay button:hover {
            color: #fff;
            background: #1b7dcc;
        }

        .sub-pay button:focus {
            color: #fff;
            background: #1b7dcc;
        }
    </style>


</head>

<body>

<form
        action="${pageContext.request.contextPath}/phone/user/basicinfo/trade/paypreorder.action"
        id="form">
    <div class="my-from" style="width: 100%">

        <div class="my-title"
             style="font-size: 60px; height: 5%; margin-top: 5%;">
            <h5 style="font-size: 60px">填写订单信息</h5>
        </div>


        <div class="my-pic-title clearfix" style="height: 100px">
            <div
                    style="text-align: left; margin-left: 10%; font-size: 30px; margin-top: 40px;">课程名称
            </div>
            <div style="text-align: center; font-size: 30px; margin-top: 40px;">价格（元）</div>
        </div>

        <div class="my-pic-content">
            <div class="my-item">
                <label class="my-radio" style="font-size: 20px">
                    ${course.courseName} </label> <span class="my-item-money"
                                                        style="font-size: 20px"> ${course.coursePrice} </span>
            </div>

        </div>


        <div class="my-pic-content"></div>
        <div class="my-remarks" style="font-size: 20px">${course.courseRemark}</div>


        <div class="my-user-message">
            <div
                    style="font-size: 30px; height: 100px; margin-top: 20px; margin-left: 10%">
                <label style="width: 20%">购买数量:</label> <span><input
                    id="buy-num" value="1" readonly type="text" name="amount">
						<input id="self" type="checkbox" checked>本人<input
                        type="button" value="添加" onclick="add()"/> <input type="button"
                                                                          value="删除" onclick="delet()"/> </span>

            </div>
            <div id="dtbg"
                 style="margin-left: 10%; display: none">
                <table id="tb_teamUser">
                    <tr style="font-size:30px">
                        <td style="width:20%">真实姓名</td>
                        <td style="width:40%">手机号</td>
                        <td style="width:40%">微信号</td>
                    </tr>
                </table>
            </div>
            <div class="my-input" style="font-size: 30px; height: 100px;">
                <label style="width: 20%">订单总价:</label> <span class="my-span"
                                                              id="total">${course.coursePrice}元</span>
            </div>


            <div class="my-input clearfix">
                <label style="float: left; font-size: 30px; width: 20%"><span
                        style="color: #ff1f50; font-size: 30px;">*</span>留言:</label>
                <textarea name="remark" id="leave-message"
                          style="font-size: 16px; float: left;"></textarea>
            </div>


            <input type="hidden" name="userName"/><input type="hidden"
                                                         name="phoneNumber"/><input type="hidden" name="weiXin"/>

        </div>
        <div class="my-btn">
            <input type="hidden" value="${course.id }" name="id">
            <button id="submit-btn"
                    style="width: 60%; height: 80px; font: bold 3.0rem/3 '微软雅黑'; color: #fff; background: none; background-color: #B91C22; letter-spacing: 0.2rem; text-align: center; border-radius: 0.2rem;">
                立即下单
            </button>
        </div>
    </div>
</form>
<script type="text/javascript">
    $(function () {

        $('#submit-btn').click(function (event) {
            $("#form").submit();
        });
        var num1 = 0;
        $("#self").on('click', function (e) {
            if (num1 % 2 == 0) {
                add();
                var num = parseInt($("#buy-num").val()) - 1;
                $("#buy-num").val(num);
                var total = $("#total");

                total.html("<span style='font-size:25px'>" + num * ${course.coursePrice} +"元</span>");
            } else {
                document.getElementById('tb_teamUser').deleteRow(temp - 1);
                temp = temp - 1;
            }
            num1 = num1 + 1;
            if (temp > 1) {
                document.getElementById("dtbg").style.display = "";
            } else {
                document.getElementById("dtbg").style.display = "none";
            }
        });

    });
    var temp = 1;
    $("#buy-num").val(1);

    function add() {
        var num = parseInt($("#buy-num").val()) + 1;
        $("#buy-num").val(num);
        var total = $("#total");

        total.html("<span>" + ${course.coursePrice} * num + "元</span>");

        var newTr = tb_teamUser.insertRow();

        var newTd0 = newTr.insertCell();
        var newTd1 = newTr.insertCell();
        var newTd2 = newTr.insertCell();

        newTd0.innerHTML = '<tr><td ><input type="text"  name="userName" style="height:50px ;width:100%"   /></td>';
        newTd1.innerHTML = '<td><input type="text"  name="phoneNumber" style="height:50px ;width:100%" /></td>';
        newTd2.innerHTML = '<td><input type="text" name="weiXin" style="height:50px ;width:100%"  /></td><tr>';

        temp = temp + 1;
        if (temp > 1)
            document.getElementById("dtbg").style.display = "";
        else
            document.getElementById("dtbg").style.display = "none";

    }
    function delet() {
        var num = parseInt($("#buy-num").val()) - 1;
        if (num < 1) {
            return;
        }
        $("#buy-num").val(num);
        var total = $("#total");

        total.html("<span>" + num * ${course.coursePrice} +"元</span>");

        document.getElementById('tb_teamUser').deleteRow(temp - 1);
        temp = temp - 1;
        if (temp > 1) {
            document.getElementById("dtbg").style.display = "";
        } else {
            document.getElementById("dtbg").style.display = "none";
        }
    }
</script>
</body>