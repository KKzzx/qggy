package cn.itcast.jk.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.itcast.qg.wxpay.MyWxPayConfig;

import com.github.wxpay.sdk.WXPayUtil;

/**
 * 提现下单参数，已添加sign参数，共7+1个set
 *
 * @author Administrator
 */
public class TransfersParams extends AbstractParams implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -543884937522394794L;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String partner_trade_no;
    private String openid;
    private String check_name;
    private String re_user_name;
    private String desc;
    private String amount;
    private String spbill_create_ip;

    public TransfersParams() {
        super();
    }

    /**
     * 参数组装
     *
     * @return
     */
    public Map<String, String> getParams() {
        Map<String, String> transfersParams = new HashMap<String, String>();
        transfersParams.put("mch_appid", MyWxPayConfig.APPID);
        transfersParams.put("mchid", MyWxPayConfig.MCHID);
        transfersParams.put("nonce_str", this.nonce_str);
        transfersParams.put("partner_trade_no", this.partner_trade_no);
        transfersParams.put("openid", this.openid);
        transfersParams.put("check_name", this.check_name);
        if (this.check_name.equals("FORCE_CHECK")) {
            transfersParams.put("re_user_name", this.re_user_name);
        }
        transfersParams.put("desc", desc);
        transfersParams.put("amount", amount);
        transfersParams.put("spbill_create_ip", spbill_create_ip);
        try {
            transfersParams.put("sign", WXPayUtil.generateSignature(
                    transfersParams, MyWxPayConfig.KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transfersParams;
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

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    /**
     * 内部订单号
     *
     * @param partner_trade_no
     */
    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    /**
     * NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
     * 如果校验姓名，则re_user_name必须传入退款人姓名，且退款人必须实名认证
     *
     * @param check_name
     */
    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 退款原因
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAmount() {
        return amount;
    }

    /**
     * 退款金额，以分为单位，必须金额>=100分
     *
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    /**
     * 192.168.0.1 调用接口的机器Ip地址
     *
     * @param spbill_create_ip
     */
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

}