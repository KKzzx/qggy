package cn.itcast.jk.controller.basicinfo.recharge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.domain.Transfer;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class RechargeController extends BaseController {
    @Resource
    TradeService tradeService;

    // 列表
    @RequestMapping("/basicinfo/recharge/list.action")
    public String list(HttpServletRequest request, Model model) {
        SysUserVO sysUserVO = (SysUserVO) request.getSession().getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("25"))//
            {
                Map<String, String> map = new HashMap<>();
                map.put("areaId", sysUserVO.getAreaId() + "");
                map.put("category", "1");
                List<Trade> dataList = tradeService.find(map);
                model.addAttribute("dataList", dataList); // 将数据传递到页面
                return "/basicinfo/recharge/jRechargeListf.jsp"; // 转向页面

            } else if (r.getSroleId().equals("26"))//
            {
                Map<String, String> map = new HashMap<>();

                map.put("category", "1");
                List<Trade> dataList = tradeService.find(map);
                model.addAttribute("dataList", dataList); // 将数据传递到页面
                return "/basicinfo/recharge/jRechargeListz.jsp"; // 转向页面

            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";


    }

    // 查看
    @RequestMapping("/basicinfo/recharge/toview.action")
    public String toview(String id, Model model) {
        Trade obj = tradeService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/recharge/jRechargeView.jsp";
    }

}
