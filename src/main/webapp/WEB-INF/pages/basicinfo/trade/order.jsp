<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<head>
    <meta charset="utf-8">
    <title>填写订单信息</title>


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no">

    <meta name="description"
          content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/weui/lib/weui.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/weui/css/jquery-weui.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/weui/css/jquery-weui.css">
    <script src="https://one.teegon.com/jslib/t-charging.min.js"></script>
</head>

<body>
<header class="demos-header">
    <h2 class="demos-title" style="text-align: center">填写订单信息</h2>
</header>

<div style="padding-left: 10px">课程信息</div>
<div class="weui-panel__bd">
    <a href="javascript:void(0);"
       class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__hd">
            <img class="weui-media-box__thumb" style="width: 100%;height: 80%;margin-top: 20%;"
                 src="${pageContext.request.contextPath}${course.courseCover}">
        </div>
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">${course.courseName}</h4>
            <p class="weui-media-box__desc">课程单价：${course.coursePrice}</p>
            <p class="weui-media-box__desc">课程所属区域：${course.areaName}</p>

        </div>
    </a>


</div>
<form
        action="${pageContext.request.contextPath}/phone/user/basicinfo/trade/paypreorder.action" method="post"
        id="form">
    <div class="weui-cells" style="margin-top: 0px">
        <div class="weui-cell">
            <div style="color: red; font-size: 10px">${course.courseRemark}
            </div>

        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>数量</p>
            </div>
            <div class="weui-cell__ft">
                <div class="weui-count">
                    <a class="weui-count__btn weui-count__decrease"></a> <input
                        name="amount" readonly id="buy-num" class="weui-count__number"
                        type="number" value="1"> <a
                        class="weui-count__btn weui-count__increase"></a>
                </div>
            </div>
        </div>
    </div>

    <div class="weui-cells weui-cells_checkbox" style="margin-top: 0px">
        <label class="weui-cell weui-check__label" for="s11">
            <div class="weui-cell__hd">
                <input type="checkbox" class="weui-check" name="checkbox1" id="s11"
                       checked="checked"> <i class="weui-icon-checked"></i>
            </div>
            <div class="weui-cell__bd">
                <p>本人</p>
            </div>
        </label>

    </div>


    <div class="weui-cells weui-cells_form" style="margin-top: 0px">
        <table id="tb_teamUser">

        </table>

    </div>
    <input type="hidden" name="userName"/><input type="hidden"
                                                 name="phoneNumber"/>

    <div class="weui-cells" style="margin-top: 0px">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>订单总价</p>
            </div>
            <div class="weui-cell__ft" id="total">${course.coursePrice}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>当前课程账户余额</p>
            </div>
            <div class="weui-cell__ft" id="coursemoney">
                ${sessionScope.user.availableAssets}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>当前现金账户余额</p>
            </div>
            <div class="weui-cell__ft" id="xianjinmoney">
                ${sessionScope.user.xianjin}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>课程账户支付金额</p>
            </div>


            <div class="weui-cell__ft" id="coursepay">${coursepay}</div>


        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>现金账户支付金额</p>
            </div>


            <div class="weui-cell__ft" id="xianjinpay">${xianjinpay}</div>


        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>微信支付金额</p>
            </div>


            <div class="weui-cell__ft" id="wxpay">${wxpay}</div>


        </div>
        <div class="weui-cells__title">备注</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
						<textarea class="weui-textarea" name="remark" placeholder="请输入文本"
                                  rows="3"></textarea>
                    <div class="weui-textarea-counter">
                        <span>0</span>/200
                    </div>
                </div>
            </div>
        </div>
        <div
                style="width: 80%; margin-left: 10% ;margin-top: 0.2rem;margin-bottom: 0.2rem;">
            <input type="hidden" value="${course.id }" name="id"> <a
                href="javascript:;" id="submit-btn" class="weui-btn weui-btn_warn">确认订单</a>
        </div>

    </div>
</form>
</body>
<script type="text/javascript">
    $(function () {

        $('#submit-btn').click(function (event) {

            var x = document.getElementsByName("userName");
            var y = document.getElementsByName("phoneNumber");
            for (var i = 0; i < x.length - 1; i++) {
                if (x[i].value == "") {
                    $(x[i]).attr('placeholder', "姓名不能为空");
                    x[i].focus();
                    return;
                }
            }
            for (var j = 0; j < y.length - 1; j++) {
                value = y[j].value;
                if (value != null && value != "") {
                    if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
                        //alert("不是完整的11位手机号或者正确的手机号前七位");
                        $(y[j]).val("");
                        $(y[j]).attr('placeholder', "请输入正确手机号码");
                        y[j].focus();
                        return;
                    }
                } else {

                    $(y[j]).attr('placeholder', "手机号码不能为空");
                    y[j].focus();
                    return;
                }
            }
            $("#form").submit();

        });
        var num1 = 0;
        $("#s11").on('click', function (e) {

            if (num1 % 2 == 0) {

                add();
                var num = parseInt($("#buy-num").val()) - 1;
                $("#buy-num").val(num);
                var total = $("#total");

                var totalmoney = num * ${course.coursePrice};
                totalmoney = totalmoney.toFixed(2);
                if (totalmoney.toString().split(".").length <= 1) {
                    totalmoney = totalmoney + ".0";

                }

                total.html(totalmoney);
                var wepay = $("#wxpay");
                var xinjinpay = $("#xianjinpay");
                var coursepay = $("#coursepay");

                var coursemoney = document.getElementById("coursemoney").innerHTML;
                var xianjinmoney = document.getElementById("xianjinmoney").innerHTML;

                if (numSub(totalmoney, coursemoney) > 0) {
                    if (numSub(numSub(totalmoney, coursemoney), xianjinmoney) > 0) {
                        wepay.html(numSub(numSub(totalmoney, coursemoney), xianjinmoney));
                        xinjinpay.html(xianjinmoney);
                        coursepay.html(coursemoney);
                    }
                    else {
                        wepay.html(0.00);
                        xinjinpay.html(numSub(totalmoney, coursemoney));
                        coursepay.html(coursemoney);
                    }

                } else {
                    wepay.html(0.00);
                    xinjinpay.html(0.00);
                    coursepay.html(totalmoney);
                }
            } else {
                document.getElementById('tb_teamUser').deleteRow(temp - 1);
                temp = temp - 1;
            }

            num1 = num1 + 1;
        })

    });
    $("#buy-num").val(1);

    $('.weui-count__decrease').click(function (e) {
        delet();

    })
    $('.weui-count__increase').click(function (e) {
        add();
    })
    function add() {
        var num = parseInt($("#buy-num").val()) + 1;
        $("#buy-num").val(num);
        var total = $("#total");
        var totalmoney = num * ${course.coursePrice};
        totalmoney = totalmoney.toFixed(2);
        if (totalmoney.toString().split(".").length <= 1) {
            totalmoney = totalmoney + ".0";

        }

        total.html(totalmoney);

        var newTr = tb_teamUser.insertRow();

        var newTd0 = newTr.insertCell();
        temp = temp + 1;

        newTd0.innerHTML = '<div style="margin-left: 0.5rem;">第'
            + temp
            + '个</div>'
            + '<div class="weui-cell"><div class="weui-cell__hd"><label class="weui-label">姓名</label></div><div class="weui-cell__bd"> <input class="weui-input" type="text" name="userName"   placeholder="请输入姓名"></div></div>'
            + ' <div class="weui-cell"><div class="weui-cell__hd"><label class="weui-label">电话</label></div><div class="weui-cell__bd"><input class="weui-input" type="tel" name="phoneNumber"  placeholder="请输入电话号码"></div></div>';
        var wepay = $("#wxpay");
        var xinjinpay = $("#xianjinpay");
        var coursepay = $("#coursepay");

        var coursemoney = document.getElementById("coursemoney").innerHTML;
        var xianjinmoney = document.getElementById("xianjinmoney").innerHTML;

        if (numSub(totalmoney, coursemoney) > 0) {
            if (numSub(numSub(totalmoney, coursemoney), xianjinmoney) > 0) {
                wepay.html(numSub(numSub(totalmoney, coursemoney), xianjinmoney));
                xinjinpay.html(xianjinmoney);
                coursepay.html(coursemoney);
            }
            else {
                wepay.html(0.00);
                xinjinpay.html(numSub(totalmoney, coursemoney));
                coursepay.html(coursemoney);
            }

        } else {
            wepay.html(0.00);
            xinjinpay.html(0.00);
            coursepay.html(totalmoney);
        }

    }
    var temp = 0;
    function delet() {
        var num = parseInt($("#buy-num").val()) - 1;
        if (num < 1) {
            return;
        }
        $("#buy-num").val(num);
        var total = $("#total");
        var totalmoney = num * ${course.coursePrice};
        totalmoney = totalmoney.toFixed(2);
        if (totalmoney.toString().split(".").length <= 1) {
            totalmoney = totalmoney + ".0";

        }

        total.html(totalmoney);
        var wepay = $("#wxpay");
        var xinjinpay = $("#xianjinpay");
        var coursepay = $("#coursepay");

        var coursemoney = document.getElementById("coursemoney").innerHTML;
        var xianjinmoney = document.getElementById("xianjinmoney").innerHTML;

        if (numSub(totalmoney, coursemoney) > 0) {
            if (numSub(numSub(totalmoney, coursemoney), xianjinmoney) > 0) {
                wepay.html(numSub(numSub(totalmoney, coursemoney), xianjinmoney));
                xinjinpay.html(xianjinmoney);
                coursepay.html(coursemoney);
            }
            else {
                wepay.html(0.00);
                xinjinpay.html(numSub(totalmoney, coursemoney));
                coursepay.html(coursemoney);
            }

        } else {
            wepay.html(0.00);
            xinjinpay.html(0.00);
            coursepay.html(totalmoney);
        }
        document.getElementById('tb_teamUser').deleteRow(temp - 1);
        temp = temp - 1;

    }
    function numSub(num1, num2) {
        var baseNum, baseNum1, baseNum2;
        try {
            baseNum1 = num1.toString().split(".")[1].length;
        } catch (e) {
            baseNum1 = 0;
        }
        try {
            baseNum2 = num2.toString().split(".")[1].length;
        } catch (e) {
            baseNum2 = 0;
        }
        baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
        var precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
        return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);
    }
</script>
</html>

