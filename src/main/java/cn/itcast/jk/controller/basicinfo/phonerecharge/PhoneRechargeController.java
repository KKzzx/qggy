package cn.itcast.jk.controller.basicinfo.phonerecharge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPayUtil;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.domain.Withdraw;
import cn.itcast.jk.params.TransfersParams;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.service.WXPayService;
import cn.itcast.qg.wxpay.MyWxPayConfig;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
public class PhoneRechargeController extends BaseController {
    @Resource
    WXPayService wxPayService;
    @Resource
    TradeService tradeService;

    // 查看个人充值记录
    @RequestMapping("/phone/user/basicinfo/recharge/list.action")
    public
    @ResponseBody
    String list(HttpSession session, String state) {
        Student student = (Student) session.getAttribute("user");

        Map<String, String> map = new HashMap<>();
        map.put("payUserId", student.getUserOpenid());
        map.put("category", "1");
        List<Trade> dataList = tradeService.find(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    // 查看
    @RequestMapping("/phone/user/basicinfo/recharge/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        Trade obj = tradeService.get(id);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(obj, config);
        return jsonObject.toString();

    }

    /**
     * 充值
     *
     * @param ids
     * @return
     */
    @RequestMapping("/basicinfo/recharge/moneysure.action")
    public String moneysure(HttpServletRequest request, String id) {
        // openid
        Student student = (Student) request.getSession().getAttribute("user");
        Withdraw withdraw = null;
        int money = (int) (withdraw.getMoney() * 100);
        TransfersParams params = new TransfersParams();
        params.setAmount(money + "");
        params.setCheck_name("FORCE_CHECK");
        params.setDesc("退款");
        params.setNonce_str(WXPayUtil.generateNonceStr());
        params.setOpenid(withdraw.getUserOpenId());
        params.setPartner_trade_no(withdraw.getId());
        params.setRe_user_name(withdraw.getUserName());
        params.setSpbill_create_ip(MyWxPayConfig.IP);
        // String result = wxPayService.transfers(params);
        String result = null;

        // 跳过微信

        return "redirect:/basicinfo/withdraw/list.action?state=1";

        // if (null != result) {
        // int index = result.indexOf("return_code");
        // String return_code = result.substring(index + 21,
        // result.indexOf("return_code", index + 20) - 5);
        // if (return_code.equals("SUCCESS")) {
        // index = result.indexOf("result_code");
        // String result_code = result.substring(index + 21,
        // result.indexOf("result_code", index + 20) - 5);
        // if (result_code.equals("SUCCESS")) {
        // index = result.indexOf("payment_no");
        // String payment_no = result.substring(index + 20,
        // result.indexOf("payment_no", index + 20) - 5);
        // index = result.indexOf("payment_time");
        // String payment_time = result.substring(index + 22,
        // result.indexOf("payment_time", index + 20) - 5);
        //
        // withdraw.setPaymentNo(payment_no);
        // withdraw.setPaymentTime(payment_time);
        // // 处理人信息
        // withdraw.setDealUserId(sysUserVO.getId());
        // withdraw.setDealUserName(sysUserVO.getName());
        // withdraw.setDealOpenId(sysUserVO.getOpenid());
        // withdraw.setDealTime(new Date());
        // withdraw.setState(1);
        // withdrawService.update(withdraw);
        // return "redirect:/basicinfo/withdraw/list.action?state=1";
        // } else {
        // index = result.indexOf("err_code");
        // String err_code = result.substring(index + 18,
        // result.indexOf("err_code", index + 20) - 5);
        // System.out.println("err_code:" + err_code);
        // System.out.println(result);
        // }
        // } else if (return_code.equals("AMOUNT_LIMIT")) {
        // System.out.println("付款金额不能小于最低限额");
        // } else if (return_code.equals("PARAM_ERROR")) {
        // System.out.println("参数错误");
        // System.out.println(result);
        // } else if (return_code.equals("NOTENOUGH")) {
        // System.out.println("余额不足");
        // } else if (return_code.equals("SEND_FAILED")) {
        // System.out.println("付款错误 付款失败，请换单号重试 ");
        // } else if (return_code.equals("NAME_MISMATCH")) {
        // System.out.println("姓名校验出错");
        // } else if (return_code.equals("MONEY_LIMIT")) {
        // System.out.println("已经达到今日付款总额上限/已达到付款给此用户额度上限");
        // } else if (return_code.equals("CA_ERROR")) {
        // System.out.println("证书出错");
        // } else if (return_code.equals("V2_ACCOUNT_SIMPLE_BAN")) {
        // System.out.println("无法给非实名用户付款");
        // } else if (return_code.equals("SYSTEMERROR")) {
        // System.out.println("系统错误，请重试 请使用原单号以及原请求参数重试，否则可能造成重复支付等资金风险 ");
        // }
        // }
        // return "redirect:/basicinfo/withdraw/list.action?state=0";
    }

}
