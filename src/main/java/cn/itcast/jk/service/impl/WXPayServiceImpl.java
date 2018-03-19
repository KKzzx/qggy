package cn.itcast.jk.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import cn.itcast.jk.domain.AcToken;
import cn.itcast.jk.params.AbstractParams;
import cn.itcast.jk.service.WXPayService;
import cn.itcast.qg.wxpay.CodeUtils;
import cn.itcast.qg.wxpay.MyWxPayConfig;

import com.github.wxpay.sdk.WXPayUtil;

/**
 * 支付服务
 *
 * @author Administrator
 */
@Service
public class WXPayServiceImpl implements WXPayService {

    /**
     * 根据内部订单号向微信查询订单
     *
     * @param out_trade_no
     * @return
     */
    public Map<String, String> QueryOrderByOut_Trade_No(String out_trade_no) {
        Map<String, String> orderParams = new HashMap<String, String>();
        orderParams.put("appid", MyWxPayConfig.APPID);
        orderParams.put("mch_id", MyWxPayConfig.MCHID);
        orderParams.put("out_trade_no", out_trade_no);
        orderParams.put("nonce_str", WXPayUtil.generateNonceStr());
        // 默认使用MD5
        Map<String, String> resultResult;
        try {
            orderParams.put("sign", WXPayUtil.generateSignature(orderParams, MyWxPayConfig.KEY));
            resultResult = wxPay.orderQuery(orderParams);
            String return_code = resultResult.get("return_code");
            if (return_code.equals("SUCCESS")) {
                return resultResult;
            } else if (return_code.equals("NOTENOUGH")) {
                System.out.println("余额不足 用户帐号余额不足  ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统一预下单，获取prepay_id
     */
    @Override
    public Map<String, String> preOrder(AbstractParams PreOrderParam) {
        Map<String, String> resultResult;
        try {
            resultResult = wxPay.unifiedOrder(PreOrderParam.getParams());
            String return_code = resultResult.get("return_code");
            if (return_code.equals("SUCCESS")) {
                return resultResult;
            } else if (return_code.equals("OUT_TRADE_NO_USED")) {
                System.out.println("商户订单号重复 同一笔交易不能多次提交 请核实商户订单号是否重复提交 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退订单，订单已经超过可退款的最大期限(支付后一年内可退款)
     */
    @Override
    public Map<String, String> refund(AbstractParams refundOrderParams) {
        Map<String, String> resultResult;
        try {
            resultResult = wxPay.refund(refundOrderParams.getParams());
            String return_code = resultResult.get("return_code");
            if (return_code.equals("SUCCESS")) {
                return resultResult;
            } else if (return_code.equals("NOTENOUGH")) {
                System.out.println("余额不足");
            } else if (return_code.equals("INVALID_TRANSACTIONID")) {
                System.out.println("无效transaction_id");
            } else if (return_code.equals("INVALID_REQ_TOO_MUCH")) {
                System.out.println("无效请求过多");
            } else if (return_code.equals("TRADE_OVERDUE")) {
                System.out.println("订单已经超过退款期限");
            } else if (return_code.equals("SYSTEMERROR")) {
                System.out.println("系统超时等 请不要更换商户退款单号，请使用相同参数再次调用API。 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提现,返回XML数据 <return_code><![CDATA[SUCCESS]]></return_code>
     */
    @Override
    public String transfers(AbstractParams transfersParams) {
        try {
            return wxPay.requestWithCert(MyWxPayConfig.TRANSFERS, transfersParams.getParams(),
                    myWxPayConfig.getHttpConnectTimeoutMs(), myWxPayConfig.getHttpReadTimeoutMs());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AcToken getACCESS_TOKEN() {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("appid", MyWxPayConfig.APPID);
        params.put("secret", MyWxPayConfig.APPSECRET);
        params.put("grant_type", "client_credential");
        String url;
        URLConnection connection;
        HttpURLConnection httpURLConnection = null;
        try {
            url = CodeUtils.mapToPath(params, MyWxPayConfig.GET_ACCESS_TOKEN);
            URL localURL = new URL(url);
            connection = localURL.openConnection();
            httpURLConnection = (HttpURLConnection) connection;
            // 获取响应输入流
            InputStream inStream = httpURLConnection.getInputStream();
            String result = CodeUtils.getResponseBodyAsString(inStream, "utf-8");
            // System.out.println(result);
            JSONObject res = new JSONObject(result);
            AcToken acToken = new AcToken();
            acToken.setAccess_token(res.getString("access_token"));
            httpURLConnection.disconnect();

            return acToken;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        return null;
    }
}
