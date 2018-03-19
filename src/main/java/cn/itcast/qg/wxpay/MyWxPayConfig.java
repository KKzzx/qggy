package cn.itcast.qg.wxpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyWxPayConfig implements WXPayConfig {

    // =======【基本信息设置】=====================================
    /*
	 * 微信公众号信息配置wxa5696d8241cb626c APPID：绑定支付的APPID（必须配置） MCHID：商户号（必须配置）
	 * KEY：商户支付密钥，参考开户邮件设置（必须配置） APPSECRET：公众帐号secert（仅JSAPI支付的时候需要配置）
	 */
    // public final static String APPID = "wxa5696d8241cb626c";
    public final static String HTTP_URL = "http://wxtest.iamlj.com/qggy";

    public final static String APPID = "wxb12c86bf0b51f2fd";
    // 操作设置操作密码 ABCD1234-
    // public final static String MCHID = "1328510801";
    public final static String MCHID = "1496314082";
    public final static String KEY = "ABCDEFGH12345678abcdefgh87654321";
    // public final static String APPSECRET =
    // "1be77de45cb705ec7d3061c1abb5c7c4";
    public final static String APPSECRET = "db0900116f692dfa4d294950e13b3ad5";
    public final static String OAUTH_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    // =======【证书路径设置】=====================================
	/*
	 * 证书路径,注意应该填写绝对路径（仅退款、撤销订单时需要）
	 */
    public final static String SSLCERT_PATH = "C:\\Users\\Administrator\\Desktop\\cert\\cert\\apiclient_cert.p12";
    public final static String SSLCERT_PASSWORD = MCHID;
    /**
     * 企业付款接口链接
     */
    public final static String TRANSFERS = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    /**
     * 获取公众号ACCESS_TOKEN
     */
    public final static String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?";
    /**
     * 网址前缀
     */
    public final static String PRE_URL = "http://wxtest.iamlj.com/qggy";
    /**
     * 模板消息前缀
     */
    public final static String Template_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    // =======【支付结果通知url】=====================================
	/*
	 * 支付结果通知回调url，用于商户接收支付结果
	 */
    public final static String NOTIFY_URL = "http://wxtest.iamlj.com/qggy/PaySuccessServlet";

    // =======【商户系统后台机器IP】=====================================
	/*
	 * 此参数可手动配置也可在程序中自动获取
	 */
    public final static String IP = "47.97.181.205";

    // =======【代理服务器设置】===================================
	/*
	 * 默认IP和端口号分别为0.0.0.0和0，此时不开启代理（如有需要才设置）
	 */
    public final static String PROXY_URL = "http://10.152.18.220:8080";

    // =======【上报信息配置】===================================
	/*
	 * 测速上报等级，0.关闭上报; 1.仅错误时上报; 2.全量上报
	 */
    public final static int REPORT_LEVENL = 1;

    // =======【日志级别】===================================
	/*
	 * 日志等级，0.不输出日志；1.只输出错误信息; 2.输出错误和正常信息; 3.输出错误信息、正常信息和调试信息
	 */
    public final static int LOG_LEVENL = 0;

    public String getAppID() {

        return MyWxPayConfig.APPID;
    }

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public String getMchID() {

        return MyWxPayConfig.MCHID;
    }

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public String getKey() {

        return MyWxPayConfig.KEY;
    }

    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    public InputStream getCertStream() {
        try {
            return new FileInputStream(new File(MyWxPayConfig.SSLCERT_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
