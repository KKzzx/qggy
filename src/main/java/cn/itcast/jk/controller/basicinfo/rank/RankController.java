package cn.itcast.jk.controller.basicinfo.rank;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Rank;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.RankService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class RankController extends BaseController {
    @Resource
    RankService rankService;

    // 列表
    @RequestMapping("/basicinfo/rank/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("12"))// 总部角色管理
            {
                List<Rank> dataList = rankService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/rank/jRankList.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 转向新增页面
    @RequestMapping("/basicinfo/rank/tocreate.action")
    public String tocreate() {
        return "/basicinfo/rank/jRankCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/basicinfo/rank/insert.action")
    public String insert(Rank rank) {
        if (rank.getRankName() != null && !rank.getRankName().equals("")) {
            rankService.insert(rank);
        }
        return "redirect:/basicinfo/rank/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/rank/toupdate.action")
    public String toupdate(String id, Model model) {
        System.out.println("[" + id + "]");
        Rank obj = rankService.get(id);
        System.out.println("[" + obj.getRankName() + "]");
        model.addAttribute("obj", obj);

        return "/basicinfo/rank/jRankUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/rank/update.action")
    public String update(Rank rank) {
        rankService.update(rank);

        return "redirect:/basicinfo/rank/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/rank/toview.action")
    public String toview(String id, Model model) {
        Rank obj = rankService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/rank/jRankView.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/rank/start.action")
    public String start(@RequestParam("id") String[] ids) {
        rankService.start(ids);

        return "redirect:/basicinfo/rank/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/rank/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        rankService.stop(ids);

        return "redirect:/basicinfo/rank/list.action";
    }
}
