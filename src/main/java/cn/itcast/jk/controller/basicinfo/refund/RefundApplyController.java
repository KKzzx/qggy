package cn.itcast.jk.controller.basicinfo.refund;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RefundService;

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
public class RefundApplyController extends BaseController {
    @Resource
    RefundService refundService;
    @Resource
    TradeService tradeService;
    @Resource
    TradeDetailService tradeDetailService;
    @Resource
    CourseService courseService;

    // 列表
    @RequestMapping("/basicinfo/refundapply/list.action")
    public String list(Model model) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("order", "STATE");
        List<Trade> dataList = tradeService.find(paramap);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/refundapply/jRefundapplyList.jsp"; // 转向页面
    }

    // 申请退订单
    @RequestMapping("/basicinfo/refundapply/toapplyorder.action")
    public String toapplyorder(String id, Model model) {
        TradeVO obj = tradeService.view(id);
        model.addAttribute("obj", obj); // 将数据传递到页面
        return "/basicinfo/refundapply/jRefundCreate.jsp"; // 转向页面
    }

    // 新增保存
    @RequestMapping("/basicinfo/refundapply/insertapply.action")
    public
    @ResponseBody
    String insertapply(String id, HttpSession session) {
        // 退订单
        // 更新trade和detail
        Student student = (Student) session.getAttribute("user");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("state", "4"); // 1启用
        tradeService.updateState(map);
        Refund refund = new Refund();
        refund.setTradeId(id);
        refund.setState(1);

        TradeVO tradeVO = tradeService.view(id);
        for (TradeDetail td : tradeVO.getTradedetails()) {
            td.setTradeState(3);

            tradeDetailService.update(td);
            refund.setId(OrderUtil.getOrderNo());
            refund.setTradeDetailId(td.getId());
            refund.setMoney(td.getClassprice());
            refund.setApplyTime(new Date());
            refund.setAreaId(student.getAreaId());
            refund.setAreaName(student.getAreaName());
            refund.setRefundUserId(student.getId());
            refund.setRefundUserName(student.getUserName());
            refund.setDealOpenId(student.getUserOpenid());
            refundService.insert(refund);
        }


        return "redirect:/basicinfo/refundapply/list.action"; // 转向列表的action
    }

    // 查看
    @RequestMapping("/basicinfo/refundapply/toview.action")
    public String toview(String id, Model model) {
        TradeVO tradeVO = tradeService.view(id);
        Map<String, String> map = new HashMap<>();
        map.put("tradeId", id);
        map.put("tradeDetailId", tradeVO.getTradedetails().get(0).getId()); // 1启用
        List<Refund> refunds = refundService.find(map);
        if (null != refunds && refunds.size() > 0) {
            model.addAttribute("o", refunds.get(0));
        }
        model.addAttribute("obj", tradeVO);
        return "/basicinfo/refundapply/jRefundapplyView.jsp";
    }

    // 申请退单个课程
    @RequestMapping("/basicinfo/refundapply/toapplyone.action")
    public String toapplyone(String id, Model model) {
        TradeDetail tradeDetail = tradeDetailService.get(id);
        Trade trade = tradeService.get(tradeDetail.getTradeId());
        Course course = courseService.get(tradeDetail.getCourseId());
        model.addAttribute("obj", trade); // 将数据传递到页面
        model.addAttribute("td", tradeDetail);
        model.addAttribute("co", course);
        return "/basicinfo/refundapply/jRefundOneCreate.jsp"; // 转向页面
    }

    // 新增保存
    @RequestMapping("/basicinfo/refundapply/insertapplyone.action")
    public String insertapplyone(Refund refund) {
        // 退单个课程
        // 更新trade和detail
        Map<String, String> map = new HashMap<>();
        map.put("id", refund.getTradeId());
        map.put("state", "1"); // 1启用
        tradeService.updateState(map);
        TradeDetail td = tradeDetailService.get(refund.getTradeDetailId());
        td.setTradeState(1);
        tradeDetailService.update(td);
        refund.setId(OrderUtil.getOrderNo());
        refund.setTradeDetailId(td.getId());
        refundService.insert(refund);
        return "redirect:/basicinfo/refundapply/toview.action?id="
                + refund.getTradeId(); // 转向列表的action
    }

}
