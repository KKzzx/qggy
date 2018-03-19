package cn.itcast.jk.params;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.itcast.qg.wxpay.MyWxPayConfig;

import com.github.wxpay.sdk.WXPayUtil;

/**
 * 统一预下单参数，已添加sign参数，共8个set
 *
 * @author Administrator
 */
public class PreOrderParams extends AbstractParams implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1697416247611583327L;
    private String appid;
    private String mch_id;
    private String openid;
    private String device_info;
    private String body;
    private String trade_type;
    private String nonce_str;
    private String out_trade_no;
    private String notify_url;
    private String total_fee;

    public PreOrderParams() {
        super();
    }

    /**
     * 参数组装
     *
     * @return
     */
    public Map<String, String> getParams() throws UnsupportedEncodingException {
        Map<String, String> unifiedOrderParams = new HashMap<String, String>();
        unifiedOrderParams.put("appid", MyWxPayConfig.APPID);
        unifiedOrderParams.put("mch_id", MyWxPayConfig.MCHID);
        unifiedOrderParams.put("device_info", this.device_info);
        unifiedOrderParams.put("body", this.body);
        unifiedOrderParams.put("trade_type", this.trade_type);
        unifiedOrderParams.put("nonce_str", this.nonce_str);
        unifiedOrderParams.put("out_trade_no", this.out_trade_no);
        unifiedOrderParams.put("notify_url", this.notify_url);
        unifiedOrderParams.put("total_fee", this.total_fee);
        unifiedOrderParams.put("openid", this.openid);
        unifiedOrderParams.put("openid", this.openid);
        try {
            unifiedOrderParams.put("sign", WXPayUtil.generateSignature(
                    unifiedOrderParams, MyWxPayConfig.KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unifiedOrderParams;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }


    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}