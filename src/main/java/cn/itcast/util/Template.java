package cn.itcast.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.itcast.jk.service.WXPayService;
import cn.itcast.qg.wxpay.CodeUtils;
import cn.itcast.qg.wxpay.MyWxPayConfig;

public class Template {

    /**
     * 发送开班，班信息修改通知，不允许任何形式制表符存在，如\t,\n,[Tab也不行]
     *
     * @param access_token ACCESS_TOKEN
     * @param openid       用户openid
     * @param data         4个参数
     */
    public static void notifyClass(String url, String access_token, String openid, String[] data) {
        if (access_token != null && openid != null) {
            // String template_id =
            // "YPWFBWuiI9Nrj5GBAqaub48-JsklHGWJz7QqWdnJDF4";
            StringBuffer sss = new StringBuffer();
            sss.append("{\"data\":{\"first\":{\"value\":\"");
            sss.append(data[0]);
            sss.append("\"},\"keyword1\":{\"value\":\"");
            sss.append(data[1]);
            sss.append("\"},\"keyword2\":{\"value\":\"");
            sss.append(data[2]);
            sss.append("\"},\"remark\":{\"value\":\"");
            sss.append(data[3]);
            sss.append("\"}},\"template_id\":\"");
            sss.append("B030cpEImkL1I2vZrEbO4JOznBsN-gEsRD6M-HvQuIo");
            sss.append("\",\"touser\":\"" + openid + "\",");
            sss.append("\"url\":\"" + url + "\"}");
            System.out.println(sss.toString());
            int count = 0;
            HttpURLConnection httpURLConnection = null;
            while (count < 5) {
                try {
                    String strUrl = MyWxPayConfig.Template_URL + access_token;
                    URL httpUrl = new URL(strUrl);
                    httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(WXPayService.myWxPayConfig.getHttpConnectTimeoutMs());
                    httpURLConnection.setReadTimeout(WXPayService.myWxPayConfig.getHttpReadTimeoutMs());
                    httpURLConnection.connect();
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(sss.toString().getBytes("UTF-8"));

                    if (httpURLConnection.getResponseCode() != 200) {
                        throw new Exception(String.format("模板消息HTTP response code is %d, not 200",
                                httpURLConnection.getResponseCode()));
                    }
                    InputStream is = httpURLConnection.getInputStream();
                    String rejson = CodeUtils.getResponseBodyAsString(is, "UTF-8");
                    if (rejson.contains("ok")) {
                        httpURLConnection.disconnect();
                        count = 10;
                    } else {
                        // org.json.JSONObject res = new
                        // org.json.JSONObject(rejson);
                        httpURLConnection.disconnect();
                        System.out.println("[" + rejson + "]");
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    httpURLConnection.disconnect();
                }

            }
        }
    }
}
