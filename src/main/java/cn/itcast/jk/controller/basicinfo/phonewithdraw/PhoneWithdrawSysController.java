package cn.itcast.jk.controller.basicinfo.phonewithdraw;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Withdraw;
import cn.itcast.jk.service.WithdrawService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneWithdrawSysController extends BaseController {
    @Resource
    WithdrawService withdrawService;

    // 列表
    @RequestMapping("/phone/sys/sys/basicinfo/withdraw/list.action")
    public String list(Model model) {
        List<Withdraw> dataList = withdrawService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/withdraw/jWithdrawList.jsp"; // 转向页面
    }

    // 转向新增页面
    @RequestMapping("/phone/sys/basicinfo/withdraw/tocreate.action")
    public String tocreate() {
        return "/basicinfo/withdraw/jWithdrawCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/phone/sys/basicinfo/withdraw/insert.action")
    public String insert(Withdraw withdraw) {

        withdrawService.insert(withdraw);

        return "redirect:/phone/sys/basicinfo/withdraw/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/withdraw/toupdate.action")
    public String toupdate(String id, Model model) {
        Withdraw obj = withdrawService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/withdraw/jWithdrawUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/withdraw/update.action")
    public String update(Withdraw withdraw) {
        withdrawService.update(withdraw);

        return "redirect:/phone/sys/basicinfo/withdraw/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/withdraw/toview.action")
    public String toview(String id, Model model) {
        Withdraw obj = withdrawService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/withdraw/jWithdrawView.jsp";
    }

}
