windows server 2008 R2 enterprose 64 中文
java1.7 mysql 5.5.44 tomcat 7.0.42（必须使用80端口）


数据库配置在jdbc.properties
图片上传配置 uedotor/jsp/config.json
tomcat http://blog.csdn.net/xzs1980/article/details/71545512
JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"
tomcat server <Connector port="80" protocol="org.apache.coyote.http11.Http11Protocol" maxHttpHeaderSize="8192" maxThreads="1000" minSpareThreads="100" maxSpareThreads="1000" minProcessors="100" maxProcessors="1000" enableLookups="false" compression="on" compressionMinSize="2048" compressableMimeType="text/html,text/xml,text/javascript,text/css,text/plain" connectionTimeout="20000" URIEncoding="utf-8" acceptCount="1000" redirectPort="8443" disableUploadTimeout="true" />
tomcat cala JAVA_OPTS="-Xms1024m -Xmx2048m -XX: PermSize=256M -XX:MaxNewSize=256m -XX:MaxPermSize=256m"

网址  "http://wxtest.iamlj.com/qggy"

cn.itcast.qg.wxpay.MyWxPayConfig.java
webapp.ueditor.jsp.config.json
pages.basicinfo.phoneuser.cellOrderDetail
pages.basicinfo.phoneuser.orderDetail
pages.basicinfo.trade.czpaymoney
pages.basicinfo.trade.paymoney
pages.basicinfo.trade.paymoney1
pages.home.homeurl.jsp

IP
cn.itcast.qg.wxpay.MyWxPayConfig.java
jdbc.properties


模版ID B030cpEImkL1I2vZrEbO4JOznBsN-gEsRD6M-HvQuIo
开发者调用模版消息接口时需提供模版ID
标题课表通知
行业文体娱乐 - 文化|传媒
详细内容
{{first.DATA}}
课程时间：{{keyword1.DATA}}
课程列表：{{keyword2.DATA}}
{{remark.DATA}}
在发送时，需要将内容中的参数（{{.DATA}}内为参数）赋值替换为需要的信息
内容示例
明天将会有以下几门课程
课程时间：星期五（上午）
课程列表：高等数学（1-2节）、计算机组成原理（3-4节）
注意整理好相关课本
