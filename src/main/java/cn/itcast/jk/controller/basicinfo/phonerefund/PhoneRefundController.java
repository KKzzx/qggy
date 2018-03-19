package cn.itcast.jk.controller.basicinfo.phonerefund;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RefundService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.vo.TradeVO;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneRefundController extends BaseController {
    @Resource
    RefundService refundService;
    @Resource
    TradeService tradeService;
    @Resource
    TradeDetailService tradeDetailService;
    @Resource
    CourseService courseService;
    @Resource
    StudentService studentService;

    // 个人退款申请列表
    @RequestMapping("/phone/user/basicinfo/refund/list.action")
    public
    @ResponseBody
    String list(HttpSession session, String state) {
        Student student = (Student) session.getAttribute("user");
        Map<String, String> map = new HashMap<>();

        map.put("refundUserId", student.getId());
        List<Refund> dataList = refundService.find(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);

        return jsonObject.toString();
    }

    @RequestMapping("/phone/user/basicinfo/refund/insertapply.action")
    public
    @ResponseBody
    String insertapply(String id, HttpSession session) {
        // 退订单
        // 更新trade和detail
        System.out.println(id + "0000000000000000000000");
        TradeVO tradeVO = tradeService.view(id);
        if (tradeVO.getState().equals("0")) {
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("state", "4"); // 1启用
            map.put("fmoney", "0.00");
            tradeService.updateState(map);
            Student student = (Student) session.getAttribute("user");

            for (TradeDetail td : tradeVO.getTradedetails()) {
                System.out.println("tdtdtdtdd" + td.getClassprice());
                td.setTradeState(3);
                tradeDetailService.update(td);
                Refund refund = new Refund();
                refund.setId(OrderUtil.getOrderNo());
                refund.setMoney(td.getClassprice());
                refund.setTradeDetailId(td.getId());
                refund.setApplyTime(new Date());
                refund.setAreaId(student.getAreaId());
                refund.setAreaName(student.getAreaName());
                refund.setTradeId(id);
                refund.setRefundUserId(student.getId());
                refund.setRefundUserName(student.getUserName());
                refund.setRefundUserOpenid(student.getUserOpenid());
                refund.setState(1);
                System.out.println(refund.getMoney());

                refundService.insert(refund);
            }
            String temp = new DecimalFormat("######0.00").format(student.getAvailableAssets() + tradeVO.getFmoney());
            student.setAvailableAssets(Double.parseDouble(temp));

            studentService.update(student);
            session.setAttribute("user", student);
            return JSONArray.fromObject(student).toString(); // 转向列表的action
        } else {
            return "123"; // 转向列表的action

        }
    }

    @RequestMapping("/phone/user/basicinfo/refund/insertapplyone.action")
    public
    @ResponseBody
    String insertapplyone(String tradeid, String tradedetailid, HttpSession session) {
        // 先更新子表，然后更新总表

        TradeDetail td1 = tradeDetailService.get(tradedetailid);
        if (td1.getClassState() == 0 && td1.getTradeState() == 0) {

            td1.setTradeState(3);
            tradeDetailService.update(td1);
            TradeVO tradeVO = tradeService.view(tradeid);
            boolean f = true;
            for (TradeDetail td : tradeVO.getTradedetails()) {
                if (td.getTradeState() != 3) {
                    f = false;
                    break;
                }
            }
            Map<String, String> map = new HashMap<>();
            map.put("id", tradeid);
            if (f) {
                map.put("state", "4");
            } else {
                map.put("state", "3");
            }
            map.put("fmoney", tradeVO.getFmoney() - tradeVO.getTradedetails().get(0).getClassprice() + "");
            tradeService.updateState(map);

            Student student = (Student) session.getAttribute("user");

            Refund refund = new Refund();
            refund.setId(OrderUtil.getOrderNo());
            refund.setMoney(tradeVO.getTradedetails().get(0).getClassprice());
            refund.setTradeDetailId(td1.getId());
            refund.setApplyTime(new Date());
            refund.setAreaId(student.getAreaId());
            refund.setAreaName(student.getAreaName());
            refund.setTradeId(tradeid);
            refund.setRefundUserId(student.getId());
            refund.setRefundUserName(student.getUserName());
            refund.setRefundUserOpenid(student.getUserOpenid());
            refund.setState(1);

            refundService.insert(refund);
            String temp = new DecimalFormat("######0.00")
                    .format(student.getAvailableAssets() + tradeVO.getTradedetails().get(0).getClassprice());
            student.setAvailableAssets(Double.parseDouble(temp));
            studentService.update(student);

            // student.setAvailableAssets(student.getAvailableAssets()+tradeVO.getTradedetails().get(0).getClassprice());
            session.setAttribute("user", student);
            return JSONArray.fromObject(refund).toString();
        } else {
            return "123";
        }
    }

    // 查询单个退款记录
    @RequestMapping("/phone/user/basicinfo/refund/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        Refund refund = refundService.get(id);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(refund, config);

        return jsonObject.toString();
    }
}
