package cn.itcast.jk.controller.basicinfo.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class RoleController extends BaseController {
    @Resource
    RoleService roleService;

    // 列表
    @RequestMapping("/basicinfo/role/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("6"))// 总部角色管理
            {
                List<Role> dataList = roleService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/role/jRoleList.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 转向新增页面
    @RequestMapping("/basicinfo/role/tocreate.action")
    public String tocreate() {
        return "/basicinfo/role/jRoleCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/basicinfo/role/insert.action")
    public String insert(Role role) {
        if (role.getRoleName() != null && !role.getRoleName().equals("")) {
            roleService.insert(role);
        }
        return "redirect:/basicinfo/role/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/role/toupdate.action")
    public String toupdate(String id, Model model) {
        Role obj = roleService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/role/jRoleUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/role/update.action")
    public String update(Role role) {
        roleService.update(role);

        return "redirect:/basicinfo/role/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/role/toview.action")
    public String toview(String id, Model model) {
        Role obj = roleService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/role/jRoleView.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/role/start.action")
    public String start(@RequestParam("id") String[] ids) {
        roleService.start(ids);

        return "redirect:/basicinfo/role/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/role/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        roleService.stop(ids);

        return "redirect:/basicinfo/role/list.action";
    }
}
