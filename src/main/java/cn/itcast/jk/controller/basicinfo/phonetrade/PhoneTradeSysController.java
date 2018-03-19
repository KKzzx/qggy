package cn.itcast.jk.controller.basicinfo.phonetrade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
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
import cn.itcast.qg.wxpay.JsApiPay;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneTradeSysController extends BaseController {
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
    @RequestMapping("/phone/sys/basicinfo/trade/list.action")
    public String list(Model model) {
        List<Trade> dataList = tradeService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/trade/jTradeList.jsp"; // 转向页面
    }

    // 列表
    @RequestMapping("/phone/sys/basicinfo/trade/view.action")
    public String view(Model model) {
        TradeVO obj = tradeService.view(null);
        System.out.println("[" + obj.getTradedetails().size() + "]");
        model.addAttribute("obj", obj); // 将数据传递到页面

        return "/basicinfo/trade/jTradeView.jsp"; // 转向页面
    }

    // 转向新增页面测试
    @RequestMapping("/phone/sys/basicinfo/trade/tocreatetest.action")
    public String tocreatetest(Model model, HttpServletRequest request) {

        List<Area> obj1 = areaService.find(null);
        model.addAttribute("userOpenid", "123456");
        model.addAttribute("obj", obj1);
        return "/basicinfo/student/register.jsp";

    }

    // 新增保存
    @RequestMapping("/phone/sys/basicinfo/trade/insert.action")
    public String insert(Trade trade) {
        if (trade.getTransactionId() != null && !trade.getTransactionId().equals("")) {
            tradeService.insert(trade);
        }
        return "redirect:/basicinfo/trade/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/trade/toupdate.action")
    public String toupdate(String id, Model model) {
        Trade obj = tradeService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/trade/jTradeUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/trade/update.action")
    public String update(Trade trade) {
        tradeService.update(trade);

        return "redirect:/basicinfo/trade/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/trade/toview.action")
    public String toview(String id, Model model) {
        TradeVO obj = tradeService.view(id);
        model.addAttribute("obj", obj); // 将数据传递到页面
        return "/basicinfo/trade/jTradeView.jsp";
    }

    // 批量启用
    @RequestMapping("/phone/sys/basicinfo/trade/start.action")
    public String start(@RequestParam("id") String[] ids) {
        tradeService.start(ids);

        return "redirect:/phone/sys/basicinfo/trade/list.action";
    }

    // 批量停用
    @RequestMapping("/phone/sys/basicinfo/trade/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        tradeService.stop(ids);

        return "redirect:/phone/sys/basicinfo/trade/list.action";
    }

    @RequestMapping(value = "/phone/sys/basicinfo/trade/orderinsert.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String orderinsert(HttpServletRequest request) {
        System.out.println("YYYYYYYYYYYYYYYYYYYY");
        JsApiPay jspay = (JsApiPay) request.getSession().getAttribute("jspay");
        System.out.println("RRRRRRRRRRRRRRRRRRR" + jspay.getTransactionid());

        Map<String, String> orderResult = wXPayService.QueryOrderByOut_Trade_No(jspay.getOuttradeno());
        if (orderResult != null) {
            jspay.setBanktype(orderResult.get("bank_type"));
            jspay.setTransactionid(orderResult.get("transaction_id"));
            jspay.setFeetype(orderResult.get("fee_type"));
            jspay.setDeviceinfo(orderResult.get("device_info"));
            jspay.setTradetype(orderResult.get("trade_type"));
            jspay.setTimeend(orderResult.get("time_end"));
            jspay.setCashfee(Double.parseDouble(orderResult.get("cash_fee").trim()));
            jspay.setTotalfee(Double.parseDouble(orderResult.get("total_fee")));
        }
        Trade trade = new Trade();
        trade.setCategory(0);
        trade.setTotalFee(jspay.getTotalfee());
        trade.setState(0);
        trade.setPayTime(new Date());
        trade.setBankType(jspay.getBanktype());
        trade.setCashFee(jspay.getCashfee());
        trade.setDeviceInfo(jspay.getDeviceinfo());
        trade.setFeeType(jspay.getFeetype());
        trade.setOutTradeNo(jspay.getOuttradeno());
        trade.setTimeEnd(jspay.getTimeend());
        trade.setTradeType(jspay.getTradetype());
        trade.setTransactionId(jspay.getTransactionid());
        trade.setId(UUID.randomUUID().toString());
        tradeService.insert(trade);
        for (int i = 0; i < jspay.getStudentlist().size(); i++) {
            Map<String, String> paraMap = new HashMap<String, String>();
            paraMap.put("weiXin", jspay.getStudentlist().get(i).getWeiXin());
            paraMap.put("phoneNumber", jspay.getStudentlist().get(i).getPhoneNumber());
            paraMap.put("UserName", jspay.getStudentlist().get(i).getUserName());
            List<Student> ss = studentService.find(paraMap);
            if (ss.size() > 0) {
                TradeDetail detail = new TradeDetail();
                detail.setCourseId(jspay.getCourseid() + "");
                detail.setId(UUID.randomUUID().toString());
                detail.setTradeId(trade.getId());
                detail.setUserId(ss.get(0).getId());
                detail.setUserName(ss.get(0).getUserName());
                tradedetailService.insert(detail);

            } else {
                Student sd = jspay.getStudentlist().get(i);
                sd.setId(UUID.randomUUID().toString());
                studentService.insert(sd);
                TradeDetail detail = new TradeDetail();
                detail.setCourseId(jspay.getCourseid() + "");
                detail.setId(UUID.randomUUID().toString());
                detail.setTradeId(trade.getId());
                detail.setUserId(sd.getId());
                detail.setUserName(sd.getUserName());
                tradedetailService.insert(detail);

            }

        }

        JSONArray jsonObject = JSONArray.fromObject(trade);
        return jsonObject.toString();

    }

}
