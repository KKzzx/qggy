package cn.itcast.qg.wxpay;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.JSONObject;

public class CodeUtils {
    /**
     * 通过code换取网页授权access_token和openid的返回数据，正确时返回的JSON数据包如下： {
     * "access_token":"ACCESS_TOKEN", "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE",
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL" } 其中access_token可用于获取共享收货地址
     * openid是微信支付jsapi支付接口统一下单时必须的参数
     * 更详细的说明请参考网页授权获取用户基本信息：http://mp.weixin.qq.com
     * /wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html
     *
     * @return
     * @失败时抛异常WxPayException
     */
    public static Map<String, String> GetOpenidAndAccessTokenFromCode(String code) {
        try {
            // 构造获取openid及access_token的url
            Map<String, String> params = new TreeMap<String, String>();
            params.put("appid", MyWxPayConfig.APPID);
            params.put("secret", MyWxPayConfig.APPSECRET);
            params.put("code", code);
            params.put("grant_type", "authorization_code");
            String url = CodeUtils.mapToPath(params, MyWxPayConfig.ACCESS_TOKEN_URL);
            // System.out.println(url);
            // 请求url以获取数据
            URL localURL = new URL(url);
            URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 获取响应输入流
            InputStream inStream = httpURLConnection.getInputStream();
            String result = getResponseBodyAsString(inStream, "utf-8");
            // System.out.println(result);
            return CodeUtils.jsonToMap(result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // {
    // "access_token":
    // "6OmglSOUq3VdnERMKY6w4xZYs0lVb99pldIQL953NAe5ya1yTZnn0wCWLzga-rXkC72FCgkDmvFz6_9t9SFx8Q",
    // "expires_in": 7200,
    // "refresh_token":
    // "m6TK1HgPbxUQp1Ipo8zkRqqNZfG4oE3wDyyaAYTjFGhmLFOEXmXIjJ9o69ri-56YxOrm9Ja1GOIELa7q_XlT2Q",
    // "openid": "oKfuSw9k5nL6rRM9bLJF8og2TP2g",
    // "scope": "snsapi_base"
    // }

    /**
     * 将code获取的oppenidjson数据转化为map
     *
     * @param json
     * @return
     */
    public static Map<String, String> jsonToMap(String json) {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject dataJson = new JSONObject(json);
        map.put("access_token", dataJson.getString("access_token"));
        map.put("expires_in", dataJson.getInt("expires_in") + "");
        map.put("refresh_token", dataJson.getString("refresh_token"));
        map.put("openid", dataJson.getString("openid"));
        map.put("scope", dataJson.getString("scope"));
        return map;
    }

    /**
     * 将Map<String, String> map转化为json数据
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, String> map) {
        String sb = "{";

        for (Entry<String, String> en : map.entrySet()) {
            sb += "\"" + en.getKey() + "\":\"" + en.getValue() + "\",";
        }
        return sb.substring(0, sb.length() - 1) + "}";
    }

    public static String getResponseBodyAsString(InputStream is, String ResponseCharSet) throws IOException {

        InputStreamReader isr = new InputStreamReader(is, ResponseCharSet);
        java.io.BufferedReader br = new java.io.BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String tempbf;
        while ((tempbf = br.readLine()) != null) {
            sb.append(tempbf);
            sb.append("\r\n");
        }
        br.close();
        isr.close();
        is.close();
        return sb.toString();
    }

    /**
     * 将map转化为 a=b&c=d的形式
     *
     * @param params 参数
     * @param path   授权请求网址
     * @return
     * @throws Exception
     */
    public static String mapToPath(Map<String, String> params, String path) throws Exception {
        Map<String, String> map = new TreeMap<String, String>(params);
        for (Entry<?, ?> en : map.entrySet()) {
            path += en.getKey() + "=" + en.getValue() + "&";
        }
        return path.substring(0, path.length() - 1);
    }
}
