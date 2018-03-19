<%@ page contentType="text/html; charset=utf-8" %>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <title>正在跳转</title>

</head>
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

<body>

<form name=loading>

    <p align=center>
        <font color="#0066ff">您不是青果会员正在跳转注册页面，请稍等</font><font color="#0066ff"
                                                              face="Arial">...</font> <input type=text name=chart
                                                                                             size=46
                                                                                             style="font-family: Arial; font-weight: bolder; color: #0066ff; background-color: #fef4d9; padding: 0px; border-style: none;">


        <input type=text name=percent
               style="color: #0066ff; text-align: center; border-width: medium; border-style: none;">

        <script>
            var bar = 0;

            var line = "||";

            var amount = "||";

            count();

            function count() {

                bar = bar + 5;

                amount = amount + line;

                document.loading.chart.value = amount;

                document.loading.percent.value = bar + "%";

                if (bar < 99) {
                    setTimeout("count()", 100);
                } else {

                    window.location = "${pageContext.request.contextPath}/phone/user/basicinfo/student/tocreate.action?id=${id}";
                }

            }
        </script>

    </p>

</form>

<p align="center">
    如果您的浏览器不支持跳转,<a style="text-decoration: none"
                    href="${pageContext.request.contextPath}/phone/user/basicinfo/student/tocreate.action?id=${id}"><font
        color="#FF0000">请点这里</font>
</a>.
</p>

</body>

</html>