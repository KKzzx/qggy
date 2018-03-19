package cn.itcast.jk.controller.basicinfo.phonetrade;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.service.WXPayService;
import cn.itcast.jk.vo.TradeVO;
import cn.itcast.qg.wxpay.CodeUtils;
import cn.itcast.qg.wxpay.JsApiPay;
import cn.itcast.qg.wxpay.MyWxPayConfig;
import cn.itcast.util.OrderUtil;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneTradeController extends BaseController {
    @Resource
    TradeService tradeService;
    @Resource
    AreaService areaService;
    @Resource
    StudentService studentService;
    @Resource
    CourseService courseService;
    @Resource
    TradeDetailService tradedetailService;
    @Resource
    WXPayService wXPayService;

    // 列表
    @RequestMapping("/phone/user/basicinfo/trade/list.action")
    public
    @ResponseBody
    String list(HttpSession session, int state) {
        Student s = (Student) session.getAttribute("user");

        Map<String, String> map = new HashMap<>();
        map.put("payUserId", s.getId());
        if (state >= 0) {
            map.put("state", state + "");
        }
        map.put("category", "0");
        List<Trade> dataList = tradeService.find(map);

        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);
        // System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    // 个人充值查询
    @RequestMapping("/phone/user/basicinfo/trade/listrecharge.action")
    public
    @ResponseBody
    String listrecharge(HttpSession session, int category) {
        Student s = (Student) session.getAttribute("user");

        Map<String, String> map = new HashMap<>();
        map.put("payUserId", s.getId());
        map.put("category", category + "");
        List<Trade> dataList = tradeService.find(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);

        return jsonObject.toString();
    }

    // 个人充值查询
    @RequestMapping("/phone/user/basicinfo/trade/listrechargeone.action")
    public
    @ResponseBody
    String listrechargeone(HttpSession session, String id) {

        Trade dataList = tradeService.get(id);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);

        return jsonObject.toString();
    }

    @RequestMapping("/phone/public/basicinfo/trade/czsucess.action")
    public String czsucess(HttpSession session, String id, Model model) {

        Trade dataList = tradeService.get(id);
        Student student = (Student) session.getAttribute("user");
        DecimalFormat df = new DecimalFormat("######0.00");
        // 更新课程余额
        String xianjin = df.format(student.getXianjin() - dataList.getWeixinmoney());
        model.addAttribute("xianjin", xianjin);
        model.addAttribute("trade", dataList);

        return "/basicinfo/trade/czsucess.jsp";
    }

    @RequestMapping("/phone/user/basicinfo/trade/tocreate.action")
    public String tocreate(Model model, int id, HttpSession session) {

        Course course = courseService.get(id);
        model.addAttribute("course", course);
        Student s = (Student) session.getAttribute("user");

        if (s.getAvailableAssets() > course.getCoursePrice()) {
            model.addAttribute("xianjinpay", 0.00);
            model.addAttribute("coursepay", course.getCoursePrice());
            model.addAttribute("wxpay", 0.00);
        } else {
            model.addAttribute("coursepay", s.getAvailableAssets());
            if (s.getAvailableAssets() + s.getXianjin() > course.getCoursePrice()) {
                model.addAttribute("wxpay", 0.00);
                model.addAttribute("xianjinpay", Double.parseDouble(
                        new DecimalFormat("######0.00").format(course.getCoursePrice() - s.getAvailableAssets())));

            } else {
                model.addAttribute("xianjinpay", s.getXianjin());

                model.addAttribute("wxpay", Double.parseDouble(new DecimalFormat("######0.00")
                        .format(course.getCoursePrice() - s.getAvailableAssets() - s.getXianjin())));
            }
        }

        return "/basicinfo/trade/order.jsp";

    }

    // 充值生成预订单
    @RequestMapping(value = "/phone/user/basicinfo/trade/czprepay.action", method = RequestMethod.GET)
    public String czpaypre(double money, HttpSession session, Model model) {

        JsApiPay jspay = new JsApiPay();
        jspay.setTotalfee(money);
        jspay.setCountmoney(0);
        jspay.setWeixinmoney(money);
        jspay.setXianjin(0);
        String out_trade_no = WXPayUtil.generateNonceStr();
        String body = "账户充值 ";
        jspay.setOuttradeno(out_trade_no);
        jspay.setBody(body);

        Map<String, String> unifiedOrderParams = new HashMap<String, String>();
        unifiedOrderParams.put("appid", MyWxPayConfig.APPID);
        unifiedOrderParams.put("mch_id", MyWxPayConfig.MCHID);
        unifiedOrderParams.put("device_info", "WEB");
        unifiedOrderParams.put("body", body);
        unifiedOrderParams.put("trade_type", "JSAPI");
        unifiedOrderParams.put("nonce_str", WXPayUtil.generateNonceStr());
        unifiedOrderParams.put("out_trade_no", out_trade_no);
        unifiedOrderParams.put("notify_url", MyWxPayConfig.NOTIFY_URL);
        System.out.println(jspay.getWeixinmoney() * 100);
        unifiedOrderParams.put("total_fee", (int) (jspay.getWeixinmoney() * 100) + "");
        unifiedOrderParams.put("openid", ((Student) session.getAttribute("user")).getUserOpenid());

        Map<String, String> jsApiParams = null;
        String wxJsApiParam = null;
        try {
            unifiedOrderParams.put("sign", WXPayUtil.generateSignature(unifiedOrderParams, MyWxPayConfig.KEY));
            // System.out.println(WXPayUtil.mapToXml(unifiedOrderParams));
            Map<String, String> unifiedOrderResult;

            unifiedOrderResult = new WXPay(new MyWxPayConfig()).unifiedOrder(unifiedOrderParams);

            String prepay_id = unifiedOrderResult.get("prepay_id");
            System.out.println("prepay_Id" + prepay_id + "++++++++++++++");

            jspay.setPrepayid(prepay_id);
            jsApiParams = new HashMap<String, String>();
            jsApiParams.put("appId", MyWxPayConfig.APPID);
            jsApiParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            jsApiParams.put("package", "prepay_id=" + prepay_id);
            jsApiParams.put("nonceStr", WXPayUtil.generateNonceStr());
            jsApiParams.put("signType", WXPayConstants.MD5);

            jsApiParams.put("paySign", WXPayUtil.generateSignature(jsApiParams, MyWxPayConfig.KEY));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        wxJsApiParam = CodeUtils.mapToJson(jsApiParams);

        session.setAttribute("czjspay", jspay);
        System.out.println(wxJsApiParam);
        model.addAttribute("wxJsApiParam", wxJsApiParam);
        model.addAttribute("money", money);

        Student student = (Student) session.getAttribute("user");
        DecimalFormat df = new DecimalFormat("######0.00");
        // 更新课程余额
        String xianjin = df.format(student.getXianjin() + money);
        model.addAttribute("xianjin", xianjin);

        return "/basicinfo/trade/czpaymoney.jsp";

    }

    // 生成预订单
    @RequestMapping(value = "/phone/user/basicinfo/trade/paypreorder.action", method = RequestMethod.POST)
    public String paypre(String[] userName, String[] phoneNumber, Model model, String id, int amount, String remark,
                         HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {

        List<Student> list = new ArrayList<Student>();
        System.out.println(userName.length + "比较" + amount);
        Student user = (Student) session.getAttribute("user");
        if (userName.length == amount || (userName.length == 0 && amount == 1)) {
            System.out.println("包含用户自己");
            Student student = new Student();

            student.setUserName(user.getUserName());
            student.setPhoneNumber(user.getPhoneNumber());
            student.setWeiXin(user.getWeiXin());
            System.out.println(student.toString());
            list.add(student);
        }
        for (int i = 0; i < userName.length - 1; i++) {
            System.out.println(userName[i]);
            Student student = new Student();
            student.setUserName(userName[i]);
            student.setPhoneNumber(phoneNumber[i]);

            list.add(student);
        }
        Course cd = courseService.get(id);
        double sum = cd.getCoursePrice() * amount;
        System.out.println("sum" + sum);
        JsApiPay jspay = new JsApiPay();
        jspay.setTotalfee(sum);
        if (sum <= user.getAvailableAssets()) {
            jspay.setCountmoney(sum);
            jspay.setWeixinmoney(0.00);
            jspay.setXianjin(0.00);
        } else {
            if (sum <= user.getAvailableAssets() + user.getXianjin()) {
                jspay.setCountmoney(user.getAvailableAssets());
                jspay.setWeixinmoney(0.00);
                jspay.setXianjin(
                        Double.parseDouble(new DecimalFormat("######0.00").format(sum - user.getAvailableAssets())));
                System.out.println("xianjin" + jspay.getXianjin());
            } else {
                jspay.setCountmoney(user.getAvailableAssets());
                jspay.setXianjin(user.getXianjin());
                jspay.setWeixinmoney(Double.parseDouble(
                        new DecimalFormat("######0.00").format(sum - user.getAvailableAssets() - user.getXianjin())));

            }
        }
        System.out.println("asdasd" + jspay.getCountmoney());
        jspay.setStudentlist(list);
        System.out.println("remark" + remark + "-----------------------");
        jspay.setRemark(remark);

        String out_trade_no = WXPayUtil.generateNonceStr();
        String body = cd.getCourseName();
        jspay.setOuttradeno(out_trade_no);
        jspay.setCourseid(cd.getId());
        jspay.setAmount(amount);
        jspay.setPrice(cd.getCoursePrice());
        jspay.setBody(body);

        // jspay.setTotalfee(totalfee);
        // 统一下单参数
        if (jspay.getWeixinmoney() > 0) {
            Map<String, String> unifiedOrderParams = new HashMap<String, String>();
            unifiedOrderParams.put("appid", MyWxPayConfig.APPID);
            unifiedOrderParams.put("mch_id", MyWxPayConfig.MCHID);
            unifiedOrderParams.put("device_info", "WEB");
            unifiedOrderParams.put("body", body);
            unifiedOrderParams.put("trade_type", "JSAPI");
            unifiedOrderParams.put("nonce_str", WXPayUtil.generateNonceStr());
            unifiedOrderParams.put("out_trade_no", out_trade_no);
            unifiedOrderParams.put("notify_url", MyWxPayConfig.NOTIFY_URL);
            unifiedOrderParams.put("total_fee", (int) (jspay.getWeixinmoney() * 100) + "");
            unifiedOrderParams.put("openid", ((Student) session.getAttribute("user")).getUserOpenid());

            Map<String, String> jsApiParams = null;
            String wxJsApiParam = null;
            try {
                unifiedOrderParams.put("sign", WXPayUtil.generateSignature(unifiedOrderParams, MyWxPayConfig.KEY));
                // System.out.println(WXPayUtil.mapToXml(unifiedOrderParams));
                Map<String, String> unifiedOrderResult;

                unifiedOrderResult = new WXPay(new MyWxPayConfig()).unifiedOrder(unifiedOrderParams);
                System.out.println(WXPayUtil.mapToXml(unifiedOrderResult));
                String prepay_id = unifiedOrderResult.get("prepay_id");
                System.out.println("prepay_Id" + prepay_id + "++++++++++++++");

                jspay.setPrepayid(prepay_id);
                jsApiParams = new HashMap<String, String>();
                jsApiParams.put("appId", MyWxPayConfig.APPID);
                jsApiParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                jsApiParams.put("package", "prepay_id=" + prepay_id);
                jsApiParams.put("nonceStr", WXPayUtil.generateNonceStr());
                jsApiParams.put("signType", WXPayConstants.MD5);

                jsApiParams.put("paySign", WXPayUtil.generateSignature(jsApiParams, MyWxPayConfig.KEY));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            wxJsApiParam = CodeUtils.mapToJson(jsApiParams);
            System.out.println(wxJsApiParam.toString());

            model.addAttribute("course", cd);

            model.addAttribute("jspay", jspay);
            session.setAttribute("jspay", jspay);
            model.addAttribute("wxJsApiParam", wxJsApiParam);
            // model.addAttribute("notifyUrl", MyWxPayConfig.NOTIFY_URL);
        } else {
            System.out.println("不产生微信支付");
            model.addAttribute("course", cd);

            model.addAttribute("jspay", jspay);
            session.setAttribute("jspay", jspay);
            model.addAttribute("wxJsApiParam", "123");
        }
        return "/basicinfo/trade/paymoney.jsp";

    }

    // 查看
    @RequestMapping("/phone/user/basicinfo/trade/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        System.out.println(id + "000000000000");
        TradeVO obj = tradeService.view(id);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(obj, config);

        return jsonObject.toString();
    }

    // 查看
    @RequestMapping("/phone/public/basicinfo/trade/fenxiang.action")
    public String fenxiang(String id, Model model, HttpSession session) {

        TradeVO obj = tradeService.view(id);
        Student student = (Student) session.getAttribute("user");
        System.out.println(student);
        if (student != null && obj.getTradedetails().size() == 1
                && obj.getTradedetails().get(0).getUserId().equals(student.getId())) {
            System.out.println("bunengfenxiang");
            return "redirect:/phone/user/myorder.action";
        } else {
            System.out.println("fenxiang");
            model.addAttribute("obj", obj);

            return "/basicinfo/trade/ordersuccess.jsp";
        }
    }

    // 下单付款
    @RequestMapping(value = "/phone/user/basicinfo/trade/orderinsert.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String orderinsert(HttpServletRequest request) {
        // System.out.println("YYYYYYYYYYYYYYYYYYYY");
        JsApiPay jspay = (JsApiPay) request.getSession().getAttribute("jspay");
        System.out.println("RRRRRRRRRRRRRRRRRRR" + jspay.getTransactionid());
        if (jspay.getWeixinmoney() > 0) {
            Map<String, String> orderResult = wXPayService.QueryOrderByOut_Trade_No(jspay.getOuttradeno());
            if (orderResult != null) {
                jspay.setBanktype(orderResult.get("bank_type"));
                jspay.setTransactionid(orderResult.get("transaction_id"));
                jspay.setFeetype(orderResult.get("fee_type"));
                jspay.setDeviceinfo(orderResult.get("device_info"));
                jspay.setTradetype(orderResult.get("trade_type"));
                jspay.setTimeend(orderResult.get("time_end"));
                jspay.setCashfee(Double.parseDouble(orderResult.get("cash_fee").trim()));
                // jspay.setTotalfee(Double.parseDouble(orderResult.get("total_fee")));
            }
        }

        Trade trade = new Trade();
        trade.setCategory(0);
        trade.setTotalFee(jspay.getTotalfee());
        trade.setFmoney(jspay.getTotalfee());
        trade.setWeixinmoney(jspay.getWeixinmoney());
        trade.setCountmoney(jspay.getCountmoney());
        trade.setXianjinPay(jspay.getXianjin());
        trade.setState(0);
        trade.setPayTime(new Date());
        trade.setBankType(jspay.getBanktype());
        trade.setCashFee(jspay.getCashfee() / 100);
        trade.setDeviceInfo(jspay.getDeviceinfo());
        trade.setFeeType(jspay.getFeetype());
        trade.setOutTradeNo(jspay.getOuttradeno());
        trade.setTimeEnd(jspay.getTimeend());
        trade.setTradeType(jspay.getTradetype());
        trade.setTransactionId(jspay.getTransactionid());
        trade.setId(OrderUtil.getOrderNo());
        Student s = (Student) request.getSession().getAttribute("user");
        // System.out.println("s.getAvailableAssets()" +
        // s.getAvailableAssets());
        // System.out.println("jspay.getCountmoney()" + jspay.getCountmoney());
        String temp = new DecimalFormat("######0.00").format(s.getAvailableAssets() - jspay.getCountmoney());
        s.setAvailableAssets(Double.parseDouble(temp));
        // System.out.println("s.setAvailableAssets()" +
        // s.getAvailableAssets());
        String xinjin = new DecimalFormat("######0.00").format(s.getXianjin() - jspay.getXianjin());
        s.setXianjin(Double.parseDouble(xinjin));
        studentService.update(s);
        // 更新session中user
        request.getSession().setAttribute("user", s);

        trade.setPayUserId(s.getId());
        trade.setPayUserName(s.getUserName());
        trade.setPayUserOpenid(s.getUserOpenid());
        Course course = courseService.get(jspay.getCourseid());
        trade.setAreaId(course.getAreaId());
        trade.setAreaName(course.getAreaName());
        trade.setName(course.getCourseName());
        tradeService.insert(trade);
        for (int i = 0; i < jspay.getStudentlist().size(); i++) {
            Map<String, String> paraMap = new HashMap<String, String>();
            paraMap.put("phoneNumber", jspay.getStudentlist().get(i).getPhoneNumber());
            paraMap.put("userName", jspay.getStudentlist().get(i).getUserName());

            List<Student> ss = studentService.find(paraMap);
            if (ss != null && ss.size() > 0) {
                TradeDetail detail = new TradeDetail();
                detail.setCourseId(jspay.getCourseid() + "");
                detail.setId(OrderUtil.getOrderNo());
                detail.setTradeId(trade.getId());
                detail.setUserId(ss.get(0).getId());
                detail.setUserName(ss.get(0).getUserName());
                detail.setCourseName(course.getCourseName());
                detail.setUserPhone(ss.get(0).getPhoneNumber());

                detail.setAreaId(course.getAreaId());
                detail.setAreaName(course.getAreaName());
                detail.setClassprice(course.getCoursePrice());
                detail.setCourseCover(course.getCourseCover());

                tradedetailService.insert(detail);

            } else {
                Student sd = jspay.getStudentlist().get(i);
                sd.setId(OrderUtil.getOrderNo());
                sd.setState(1);

                studentService.insert(sd);
                TradeDetail detail = new TradeDetail();
                detail.setCourseId(jspay.getCourseid() + "");
                detail.setId(OrderUtil.getOrderNo());
                detail.setTradeId(trade.getId());
                detail.setUserId(sd.getId());
                detail.setUserName(sd.getUserName());
                detail.setUserPhone(sd.getPhoneNumber());
                detail.setCourseName(course.getCourseName());
                detail.setAreaId(course.getAreaId());
                detail.setAreaName(course.getAreaName());
                detail.setClassprice(course.getCoursePrice());
                detail.setCourseCover(course.getCourseCover());

                tradedetailService.insert(detail);

            }

        }

        request.getSession().removeAttribute("jspay");
        JSONArray jsonObject = JSONArray.fromObject(trade);
        return jsonObject.toString();

    }

    // 下单付款
    @RequestMapping(value = "/phone/user/basicinfo/trade/czinsert.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String czinsert(HttpServletRequest request) {
        System.out.println("YYYYYYYYYYYYYYYYYYYY");
        JsApiPay jspay = (JsApiPay) request.getSession().getAttribute("czjspay");
        System.out.println("RRRRRRRRRRRRRRRRRRR" + jspay.getTransactionid());
        if (jspay.getWeixinmoney() > 0) {
            Map<String, String> orderResult = wXPayService.QueryOrderByOut_Trade_No(jspay.getOuttradeno());
            if (orderResult != null) {
                jspay.setBanktype(orderResult.get("bank_type"));
                jspay.setTransactionid(orderResult.get("transaction_id"));
                jspay.setFeetype(orderResult.get("fee_type"));
                jspay.setDeviceinfo(orderResult.get("device_info"));
                jspay.setTradetype(orderResult.get("trade_type"));
                jspay.setTimeend(orderResult.get("time_end"));
                jspay.setCashfee(Double.parseDouble(orderResult.get("cash_fee").trim()));
                // jspay.setTotalfee(Double.parseDouble(orderResult.get("total_fee")));
            }
        }

        Trade trade = new Trade();
        trade.setCategory(1);
        trade.setTotalFee(jspay.getTotalfee());
        trade.setFmoney(jspay.getTotalfee());
        trade.setWeixinmoney(jspay.getWeixinmoney());
        trade.setCountmoney(jspay.getCountmoney());
        trade.setXianjinPay(jspay.getXianjin());
        trade.setState(0);
        trade.setPayTime(new Date());
        trade.setBankType(jspay.getBanktype());
        trade.setCashFee(jspay.getCashfee() / 100);
        trade.setDeviceInfo(jspay.getDeviceinfo());
        trade.setFeeType(jspay.getFeetype());
        trade.setOutTradeNo(jspay.getOuttradeno());
        trade.setTimeEnd(jspay.getTimeend());
        trade.setTradeType(jspay.getTradetype());
        trade.setTransactionId(jspay.getTransactionid());
        trade.setId(OrderUtil.getOrderNo());
        Student s = (Student) request.getSession().getAttribute("user");
        // System.out.println("s.getAvailableAssets()" +
        // s.getAvailableAssets());
        // System.out.println("jspay.getCountmoney()" + jspay.getCountmoney());
        String temp = new DecimalFormat("######0.00").format(s.getXianjin() + jspay.getTotalfee());
        s.setXianjin(Double.parseDouble(temp));
        // System.out.println("s.setAvailableAssets()" +
        // s.getAvailableAssets());

        studentService.update(s);
        // 更新session中user
        request.getSession().setAttribute("user", s);

        trade.setPayUserId(s.getId());
        trade.setPayUserName(s.getUserName());
        trade.setPayUserOpenid(s.getUserOpenid());

        trade.setAreaId(s.getAreaId());
        trade.setAreaName(s.getAreaName());
        trade.setName("充值");
        tradeService.insert(trade);

        request.getSession().removeAttribute("czjspay");
        JSONArray jsonObject = JSONArray.fromObject(trade);
        return jsonObject.toString();

    }

}
