package cn.itcast.jk.controller.basicinfo.phonetradedetail;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.service.TradeDetailService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneTradeDetailSysController extends BaseController {
    @Resource
    TradeDetailService tradedetailService;
    @Resource
    ClasssService classsService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/tradedetail/list.action")
    public String list(Model model) {
        List<TradeDetail> dataList = tradedetailService.getTradeDetailList();
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/tradedetail/jTradeDetailList.jsp"; // 转向页面
    }


    // 新增保存
    @RequestMapping("/phone/sys/basicinfo/tradedetail/insert.action")
    public String insert(@RequestParam("id") String id, Classs classs) {
        System.out.println(classs.getId());
        classs.setId(id);
        classsService.update(classs);
        return "redirect:/phone/sys/basicinfo/classs/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/tradedetail/toupdate.action")
    public String toupdate(String id, Model model) {
        TradeDetail obj = tradedetailService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/tradedetail/jTradeDetailUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/tradedetail/update.action")
    public String update(TradeDetail tradedetail) {
        tradedetailService.update(tradedetail);

        return "redirect:/phone/sys/basicinfo/tradedetail/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/tradedetail/toview.action")
    public String toview(String id, Model model) {
        TradeDetail obj = tradedetailService.get(id);
        model.addAttribute("obj", obj);

        return "/phone/sys/basicinfo/tradedetail/jTradeDetailView.jsp";
    }

}
