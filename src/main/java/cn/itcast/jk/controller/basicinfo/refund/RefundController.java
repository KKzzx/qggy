package cn.itcast.jk.controller.basicinfo.refund;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.RefundService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.vo.SysUserVO;
import cn.itcast.jk.vo.TradeVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class RefundController extends BaseController {
    @Resource
    RefundService refundService;
    @Resource
    TradeDetailService tradeDetailService;
    @Resource
    TradeService tradeService;
    @Resource
    StudentService studentService;

    // 列表
    @RequestMapping("/basicinfo/refund/list.action")
    public String list(Model model, HttpSession session, String likes) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("19"))//
            {
                Map<String, String> map = new HashMap<>();
                map.put("areaId", sysUserVO.getAreaId() + "");
                if (!StringUtils.isBlank(likes)) {
                    map.put("likes", likes);
                }
                List<Refund> dataList = refundService.find(map);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/refund/jRefundListf.jsp"; // 转向页面

            } else if (r.getSroleId().equals("20"))//
            {
                Map<String, String> map = new HashMap<>();
                if (!StringUtils.isBlank(likes)) {
                    map.put("likes", likes);
                }
                List<Refund> dataList = refundService.find(map);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/refund/jRefundListz.jsp"; // 转向页面

            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 批准退款
    @RequestMapping("/basicinfo/refund/toreturn.action")
    public String toreturn(String id, HttpServletRequest request) {
        SysUserVO sysUserVO = (SysUserVO) request.getSession().getAttribute(
                "sysUserVO");
        Refund refund = refundService.get(id);
        refund.setDealUserId(sysUserVO.getId());
        refund.setDealUserName(sysUserVO.getName());
        refund.setDealOpenId(sysUserVO.getOpenid());
        refund.setDealTime(new Date());
        refund.setState(1);
        refundService.update(refund);
        Map<String, String> paraMap2 = new HashMap<>();
        paraMap2.put("id", refund.getTradeDetailId());
        paraMap2.put("state", "3");

        tradeDetailService.updateState(paraMap2);
        TradeVO tradeVO = tradeService.view(refund.getTradeId());
        boolean f = true;
        for (TradeDetail td : tradeVO.getTradedetails()) {
            if (td.getTradeState() != 3) {
                f = false;
                break;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", refund.getTradeId());
        if (f) {
            map.put("state", "4");
        } else {
            map.put("state", "3");
        }
        map.put("fmoney", tradeVO.getFmoney() - tradeVO.getTradedetails().get(0).getClassprice() + "");
        tradeService.updateState(map);

        Student student = studentService.get(refund.getRefundUserOpenid());
        double availableAssets = student.getAvailableAssets();
        student.setAvailableAssets(availableAssets + refund.getMoney());

        studentService.update(student);
        request.getSession().setAttribute("user", student);

        return "redirect:/basicinfo/refund/list.action";
    }

    // 驳回退款
    @RequestMapping("/basicinfo/refund/toback.action")
    public String toback(String id, HttpServletRequest request) {
        SysUserVO sysUserVO = (SysUserVO) request.getSession().getAttribute(
                "sysUserVO");
        Refund refund = refundService.get(id);
        refund.setDealUserId(sysUserVO.getId());
        refund.setDealUserName(sysUserVO.getName());
        refund.setDealOpenId(sysUserVO.getOpenid());
        refund.setDealTime(new Date());
        refund.setState(2);
        refundService.update(refund);

        Map<String, String> paraMap2 = new HashMap<>();
        paraMap2.put("id", refund.getTradeDetailId());
        paraMap2.put("state", "2");
        tradeDetailService.updateState(paraMap2);
        Map<String, String> map = new HashMap<>();
        map.put("id", refund.getTradeId());
        map.put("state", "2");
        tradeService.updateState(map);
        return "redirect:/basicinfo/refund/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/refund/toview.action")
    public String toview(String id, Model model) {
        Refund refund = refundService.get(id);
        TradeVO tradeVO = tradeService.view(refund.getTradeId());
        model.addAttribute("obj", refund); // 将数据传递到页面
        model.addAttribute("o", tradeVO); // 将数据传递到页面
        return "/basicinfo/refund/jRefundView.jsp";
    }
}
