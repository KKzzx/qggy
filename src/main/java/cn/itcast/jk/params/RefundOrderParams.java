package cn.itcast.jk.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.itcast.qg.wxpay.MyWxPayConfig;

import com.github.wxpay.sdk.WXPayUtil;

/**
 * 退订单下单参数，已添加sign参数，共6个set
 *
 * @author Administrator
 */
public class RefundOrderParams extends AbstractParams implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1697416247611583327L;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String transaction_id;
    private String out_refund_no;
    private String total_fee;
    private String refund_fee;
    private String refund_desc;

    public RefundOrderParams() {
        super();
    }

    /**
     * 参数组装
     *
     * @return
     */
    public Map<String, String> getParams() {
        Map<String, String> refundParams = new HashMap<String, String>();
        refundParams.put("appid", MyWxPayConfig.APPID);
        refundParams.put("mch_id", MyWxPayConfig.MCHID);
        refundParams.put("nonce_str", this.nonce_str);
        refundParams.put("transaction_id", this.transaction_id);
        refundParams.put("out_refund_no", this.out_refund_no);
        refundParams.put("total_fee", this.total_fee);
        refundParams.put("refund_fee", this.refund_fee);
        refundParams.put("refund_desc", this.refund_desc);
        try {
            refundParams.put("sign", WXPayUtil.generateSignature(refundParams,
                    MyWxPayConfig.KEY));
            return refundParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    /**
     * 整数，分
     *
     * @param total_fee
     */
    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    /**
     * 整数，分
     *
     * @param refund_fee
     */
    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

}