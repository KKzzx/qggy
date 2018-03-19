package cn.itcast.jk.service;

import java.util.Map;

import cn.itcast.jk.domain.AcToken;
import cn.itcast.jk.params.AbstractParams;
import cn.itcast.qg.wxpay.MyWxPayConfig;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface WXPayService {
    MyWxPayConfig myWxPayConfig = new MyWxPayConfig();
    WXPay wxPay = new WXPay(myWxPayConfig, WXPayConstants.SignType.MD5, false);

    /**
     * 根据内部订单号向微信查询订单
     *
     * @param out_trade_no
     * @return
     */
    public Map<String, String> QueryOrderByOut_Trade_No(String out_trade_no);

    /**
     * 统一预下单，获取prepay_id
     */
    public Map<String, String> preOrder(AbstractParams preOrderParam);

    /**
     * 退订单，订单已经超过可退款的最大期限(支付后一年内可退款)
     */
    public Map<String, String> refund(AbstractParams refundOrderParams);

    /**
     * 提现,返回XML数据 <return_code><![CDATA[SUCCESS]]></return_code>
     * <return_msg><![CDATA[]]></return_msg>
     * <mch_appid><![CDATA[wxec38b8ff840bd989]]></mch_appid>
     * <mchid><![CDATA[10013274]]></mchid>
     * <device_info><![CDATA[]]></device_info>
     * <nonce_str><![CDATA[lxuDzMnRjpcXzxLx0q]]></nonce_str>
     * <result_code><![CDATA[SUCCESS]]></result_code>
     * <partner_trade_no><![CDATA[
     * 10013574201505191526582441]]></partner_trade_no>
     * <payment_no><![CDATA[1000018301201505190181489473]]></payment_no>
     * <payment_time><![CDATA[2015-05-19 15：26：59]]></payment_time>
     */
    public String transfers(AbstractParams transfersParams);

    /**
     * {"access_token":"ACCESS_TOKEN","expires_in":7200}
     */
    public AcToken getACCESS_TOKEN();

}
