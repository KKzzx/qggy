<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/reset.css"/>
    <!-- 微信源style -->
    <script async="async" type="text/javascript"
            src="https://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/pages/player_adaptor39d6d3.js,/mmbizwap/zh_CN/htmledition/js/appmsg/weapp39d5b2.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/coursedetail.css"/>
    <script type="text/javascript">
        $(function () {

            $('#zc')
                .click(
                    function (event) {
                        //window.location.href = "http://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa5696d8241cb626c&redirect_uri=http://wxtest.iamlj.com/example/phone/user/basicinfo/trade/tocreate.action?id=1&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                        window.location.href = "${pageContext.request.contextPath}/phone/user/basicinfo/trade/tocreate.action?id=${course.id}";

                        //window.location.href ="coursedetail.do?id="+${course.id}+"&flag=1";
                    });

        });
    </script>


    <title>${course.courseName}</title>

</head>
<body id="activity-detail"
      class="zh_CN mm_appmsg rich_media_empty_extra not_in_mm">

<div id="js_article" class="rich_media">

    <div style="display: none;" id="js_top_ad_area" class="top_banner"></div>

    <div class="rich_media_inner">

        <div id="page-content" class="rich_media_area_primary">

            <div id="img-content">

                <h2 class="rich_media_title" id="activity-name">
                    ${course.courseName}</h2>
                <div id="meta_content" class="rich_media_meta_list">
                    <em id="post-date" class="rich_media_meta rich_media_meta_text"><fmt:formatDate
                            value="${course.releaseTime}" pattern="yyyy-MM-dd"/> </em> <a
                        class="rich_media_meta rich_media_meta_link rich_media_meta_nickname"
                        href="##" id="post-user">青果官微</a> <span
                        class="rich_media_meta rich_media_meta_text rich_media_meta_nickname">青果官微</span>

                    <div id="js_profile_qrcode" class="profile_container"
                         style="display:none;">
                        <div class="profile_inner">
                            <strong class="profile_nickname">青果官微</strong> <img
                                class="profile_avatar" id="js_profile_qrcode_img" src="" alt="">

                            <p class="profile_meta">
                                <label class="profile_meta_label">微信号</label> <span
                                    class="profile_meta_value">qingguogngyi</span>
                            </p>

                            <p class="profile_meta">
                                <label class="profile_meta_label">功能介绍</label> <span
                                    class="profile_meta_value">青果公益旨为建立一个关于文化与生活的交流平台，汇聚传统文化相关资源进行交流和学习，注重与现代生活方式的融合，通过各种形式的活动方式让文化尤其是传统文化走入寻常百姓家。</span>
                            </p>

                        </div>
                        <span class="profile_arrow_wrp" id="js_profile_arrow_wrp">
								<i class="profile_arrow arrow_out"></i> <i
                                class="profile_arrow arrow_in"></i> </span>
                    </div>
                </div>

                <div class="rich_media_content " id="js_content">
                    ${course.courseContent}
                    <hr>
                    <p
                            style="min-height: 1em;color: rgb(62, 62, 62);font-size: 16px;white-space: normal;text-align: center;max-width: 100% !important;box-sizing: border-box !important;word-wrap: break-word !important;">
							<span
                                    style="font-size: 14px;border: 0px;font-family: 'Microsoft YaHei', 宋体, Verdana, Arial, Helvetica, sans-serif;line-height: inherit;vertical-align: baseline;word-break: break-all;background-color: rgb(255, 255, 255);max-width: 100% !important;box-sizing: border-box !important;word-wrap: break-word !important;"><span
                                    style="color: rgb(128, 128, 128);max-width: 100% !important;"></span><span
                                    style="line-height: inherit;vertical-align: baseline;word-break: break-all;color: rgb(255, 104, 39);max-width: 100% !important;">人人都能参与公益，人人都能做公益，青果公益期待您的加入！</span>
							</span><br>
                    </p>
                    <p
                            style="min-height: 1em;color: rgb(62, 62, 62);font-size: 16px;white-space: normal;text-align: center;max-width: 100% !important;box-sizing: border-box !important;word-wrap: break-word !important;">
							<span
                                    style="font-size: 14px;max-width: 100% !important;box-sizing: border-box !important;word-wrap: break-word !important;font-family: 'Microsoft YaHei', 宋体, Verdana, Arial, Helvetica, sans-serif;line-height: inherit;vertical-align: baseline;word-break: break-all;background-color: rgb(255, 255, 255);"><img
                                    style="box-sizing: border-box ! important; word-wrap: break-word ! important; visibility: visible ! important; width: auto ! important; height: auto ! important;"
                                    data-w="258" data-type="jpeg"
                                    data-src="/qggy/images/gzh.jpg?wx_fmt=jpeg" data-s="300,640"
                                    data-ratio="1" _width="auto" class="" src="/qggy/images/gzh.jpg"
                                    data-fail="0"> </span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>