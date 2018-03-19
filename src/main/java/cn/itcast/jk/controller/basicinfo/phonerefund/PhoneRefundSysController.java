package cn.itcast.jk.controller.basicinfo.phonerefund;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.service.RefundService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneRefundSysController extends BaseController {
    @Resource
    RefundService refundService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/refund/list.action")
    public String list(Model model) {
        List<Refund> dataList = refundService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/refund/jRefundList.jsp"; // 转向页面
    }

    // 转向新增页面
    @RequestMapping("/phone/sys/basicinfo/refund/tocreate.action")
    public String tocreate() {
        return "/basicinfo/refund/jRefundCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/phone/sys/basicinfo/refund/insert.action")
    public String insert(Refund refund) {
        refundService.insert(refund);

        return "redirect:/basicinfo/refund/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/refund/toupdate.action")
    public String toupdate(String id, Model model) {
        Refund obj = refundService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/refund/jRefundUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/refund/update.action")
    public String update(Refund refund) {
        refundService.update(refund);

        return "redirect:/phone/sys/basicinfo/refund/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/refund/toview.action")
    public String toview(String id, Model model) {
        Refund obj = refundService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/refund/jRefundView.jsp";
    }

    // 批量启用
    @RequestMapping("/phone/sys/basicinfo/refund/start.action")
    public String start(@RequestParam("id") String[] ids) {
        refundService.start(ids);

        return "redirect:/phone/sys/basicinfo/refund/list.action";
    }

    // 批量停用
    @RequestMapping("/phone/sys/basicinfo/refund/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        refundService.stop(ids);

        return "redirect:/phone/sys/basicinfo/refund/list.action";
    }
}
