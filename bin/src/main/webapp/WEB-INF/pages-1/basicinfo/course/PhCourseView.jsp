<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="charset" content="UTF-8">
    <title>青果官微</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/reset.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/phonecourseview.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">
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
        function redirect(val) {
            window.location.href = "${pageContext.request.contextPath}/phone/public/basicinfo/course/viewbyAreaID.action?areaid="
                + val;
        }
    </script>
</head>
<style>
    .scroll {
        overflow: hidden;
    }

    .index_search .starting:before {
        content: "";
        width: 50px;
        height: 45px;
        display: inline-block;
        vertical-align: -2px;
        background-position: -140px -248px;
        background-image: url(https://pic.c-ctrip.com/h5/group_travel/un_index_v6.png?20150512.png);
        background-repeat: no-repeat;
        background-size: 700px auto;
    }
</style>
<body>

<div class="box">
    <div class="scroll">
        <div class="swiper-container">
            <div class="swiper-wrapper"></div>
        </div>
        <div class="content">
            <!--
				<div class="Area" style="font-size:0.6rem;height:100px">&nbsp;&nbsp;&nbsp;&nbsp;区&nbsp;域&nbsp;&nbsp;
				<select style="height:80px;" id="firstSelect" name="firstSelect" onchange="redirect(this.options[this.selectedIndex].value);">
		            	<option value="0" <c:if test="${areaid==0}">selected</c:if> >--精华--</option>
	            		<c:forEach items="${firstAreaList}" var="f">
	            			<option value="${f.id}" <c:if test="${areaid==f.id}">selected</c:if> >${f.areaName}</option>
	            		</c:forEach>
	            	</select>
	            	</div>
	            	-->
            <div class="index_search"
                 style="font-size:0.6rem;text-align:right;margin-right:3%;margin-top:2%">
                <div class="starting js_depart">

                    <span class="chooseArea" id="${area.id }" title="点击切换">${area.areaName}</span>
                </div>
            </div>
            <div class="nav" style="margin-top:0.5rem"></div>
            <ul class="page"></ul>
        </div>
    </div>
</div>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/mTween.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
<script>
    //清除默认
    //document.addEventListener('touchstart',function( e ){
    //	e.preventDefault();
    //});
    //临时数据
    /*var broadData = [{
     "imgsrc": "/images/cover/0.jpg",
     "title": "《古琴》初级班 报名入口"
     }, {
     "imgsrc": "/images/cover/0.jpg",
     "title": "《古琴》初级班 报名入口2"
     }];*/
    var broadData = ${broadData};
    var areaid = ${area.id};
    //添加轮播
    (function () {
        var container = document.querySelector(".swiper-container");
        container.style.weight = document.body.clientWidth + "px";
        container.style.height = document.body.clientWidth * 500 / 760 + "px";

        var picTab = document.querySelector(".swiper-wrapper");
        for (var i = 0; i < broadData.length; i++) {
            var div = document.createElement("div");
            div.setAttribute("class", "swiper-slide");
            var a = document.createElement("a");
            var len = broadData[i].advertiseCover.length;
            var imgpath = "${pageContext.request.contextPath}/images/cover/" + broadData[i].advertiseCover.substr(14, len);
            a.setAttribute("style", "background-image: url(" + imgpath + ");background-size:cover;background-position: center;background-repeat: no-repeat;width:100%;height:100%;display:block;text-decoration:none;");

            if (broadData[i].adcategory == 1) {
                a.setAttribute("href", broadData[i].advertiseUrl);
            } else if (broadData[i].adcategory == 0) {
                a.setAttribute("href", "${pageContext.request.contextPath}/phone/public/basicinfo/course/phonecoursedetail.action?id=" + broadData[i].advertiseUrl);
            }

            var font = document.createElement("font");
            font.setAttribute("style", "font-size: 1.0rem;color: white;position:relative;top:80%;left: 5%;");
            font.innerHTML = broadData[i].content;
            a.appendChild(font);
            div.appendChild(a);
            picTab.appendChild(div);
        }
        var swiper = new Swiper('.swiper-container', {
            spaceBetween: 0,
            centeredSlides: true,
            autoplay: {
                delay: 2500,
                disableOnInteraction: false,
            },
        });
    })();


    //var data1=[{"courses":[{"categoryid":1,"classtime":"","contentn":"【社会组织简介】上海青果巷子传统文化促进中心，是上海市首家经市文广局、市民政局特批成立的传统","courseid":0,"cover":"img/1.jpg","level":1,"pdf":"34354","pricemoney":301,"time":null,"title":"301"},{"categoryid":1,"classtime":"","contentn":"【社会组织简介】上海青果巷子传统文化促进中心，是上海市首家经市文广局、市民政局特批成立的传统","courseid":0,"cover":"img/1.jpg","level":2,"pdf":"345","pricemoney":301,"time":null,"title":"301"}],"xianmgu":"301计划"},{"courses":[{"categoryid":2,"classtime":"","contentn":"【社会组织简介】上海青果巷子传统文化促进中心，是上海市首家经市文广局、市民政局特批成立的传统","courseid":0,"cover":"img/1.jpg","level":1,"pdf":"2342","pricemoney":801,"time":null,"title":"801"},{"categoryid":2,"classtime":"","contentn":"【社会组织简介】上海青果巷子传统文化促进中心，是上海市首家经市文广局、市民政局特批成立的传统","courseid":0,"cover":"img/1.jpg","level":1,"pdf":"456你","pricemoney":801,"time":null,"title":"801"},{"categoryid":2,"classtime":"","contentn":"【社会组织简介】上海青果巷子传统文化促进中心，是上海市首家经市文广局、市民政局特批成立的传统","courseid":0,"cover":"img/1.jpg","level":2,"pdf":"3453","pricemoney":801,"time":null,"title":"801"}],"xianmgu":"801"}];

    var data1 = ${successInfo};

    (function () {
        var nav = document.querySelector(".nav");
        var page = document.querySelector(".page");
        var ul = document.createElement("ul");
        ul.className = 'clearFix';
        for (var i = 0; i < data1.length; i++) {
            var li = document.createElement("li");
            li.setAttribute("index", i);
            li.style.width = document.documentElement.getBoundingClientRect().width
                / data1.length + "px";

            var li2 = document.createElement("li");
            li2.className = 'hide';
            li.addEventListener("click", function () {
                var lis = document.querySelectorAll(".nav li");
                for (var j = 0; j < lis.length; j++) {
                    lis[j].className = "";
                }

                this.className = "active";
                var lis2 = document.querySelectorAll(".page >li");
                console.log(lis2);
                //alert(lis2.length);
                for (var j = 0; j < lis2.length; j++) {
                    lis2[j].className = "hide";
                }
                //alert(this.getAttribute("index"));
                lis2[this.getAttribute("index")].className = "";
            });
            page.appendChild(li2);
            li.innerHTML = data1[i].categoryName;
            ul.appendChild(li);
        }
        nav.appendChild(ul);
        //设置默认
        var lis2 = document.querySelectorAll(".page li");
        var lis = document.querySelectorAll(".nav li");
        lis2[0].className = "";
        lis[0].className = "active";

        for (var i = 0; i < data1.length; i++) {

            var ulx = document.createElement("ul");
            if (areaid == 0) {
                var li = document.createElement("li");
                li.className = "clearFix";
                li.innerHTML = "<div style='font-size:0.5rem'>"
                    + data1[i].categoryRemark + "</div>";
                ulx.appendChild(li);
            }
            for (var j = 0; j < data1[i].courses.length; j++) {
                if (data1[i].categoryName == '301') {
                } else if (data1[i].categoryName == '801') {

                    if (j == 0
                        || data1[i].courses[j].rankName != data1[i].courses[j - 1].rankName) {
                        var li3 = document.createElement("li");
                        li3.className = "clearFix";
                        //alert(data1[i].courses[j].rankName);
                        li3.innerHTML = "<div>" + data1[i].courses[j].rankName
                            + "</div>";
                        ulx.appendChild(li3);
                    }
                }
                var li = document.createElement("li");
                li.className = "clearFix";
                li.style = "margin-top: 0.05rem;";
                li.innerHTML = "<a href=phonecoursedetail.action?id="
                    + data1[i].courses[j].id
                    + "><img src=" + "${pageContext.request.contextPath}" + data1[i].courses[j].courseCover + " /><div class='right'><h2>"
                    + data1[i].courses[j].courseName
                    + "</h2><p style='font-size:0.6rem' >"
                    + data1[i].courses[j].courseAbstract.substr(0, 36)
                    + "</p></div></a>";
                ulx.appendChild(li);
            }

            lis2[i].appendChild(ulx);
        }

    })();

    //选择地点打开界面
    document.querySelector(".chooseArea").onclick = function () {
        var id = this.id;
        window.location.href = "${pageContext.request.contextPath}/chooseArea.html?id=${area.id}&areaName=${area.areaName}";
    }
</script>
</html>