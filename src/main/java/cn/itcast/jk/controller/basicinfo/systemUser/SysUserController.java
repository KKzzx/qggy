package cn.itcast.jk.controller.basicinfo.systemUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.service.SysUserService;
import cn.itcast.jk.service.UrRoService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class SysUserController extends BaseController {
    @Resource
    SysUserService sysUserService;
    @Resource
    AreaService areaService;
    @Resource
    RoleService roleService;
    @Resource
    UrRoService urRoService;

    // 列表
    @RequestMapping("/basicinfo/sysuser/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("9"))// 分部审核
            {
                List<SysUser> dataList = sysUserService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/sysuser/jSysUserList.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 转向注册页面
    @RequestMapping("/basicinfo/sysuser/tocreate.action")
    public String tocreate(Model model) {
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("state", "1");
        List<Area> obj = areaService.find(paraMap);
        model.addAttribute("obj", obj);
        return "/basicinfo/sysuser/jSysUserRegister.jsp";
    }

    @RequestMapping("/basicinfo/sysuser/agreement.action")
    public String agreement() {

        return "/basicinfo/sysuser/agreement.jsp";
    }

    // 新增保存
    @RequestMapping("/basicinfo/sysuser/insert.action")
    public String insert(SysUser sysyUser) {
        //根据电话号码，防止重复添加
        HashMap<String, String> map = new HashMap<>();
        map.put("phonenumber", sysyUser.getPhonenumber());
        List<SysUser> list = sysUserService.find(map);
        if (list.size() > 0) {
            return "redirect:/basicinfo/sysuser/list.action"; // 转向列表的action
        }
        if (sysyUser.getName() != null && !sysyUser.getName().equals("") && list.size() == 0) {
            sysUserService.insert(sysyUser);
        }
        return "redirect:/basicinfo/sysuser/list.action"; // 转向列表的action
    }

    // 新增连接保存
    @RequestMapping("/basicinfo/sysuser/insertUrRo.action")
    public String insertUrRo(UrRo urRo) {
        if (urRo.getSroleName() != null && urRo.getSroleName() != "") {
            urRoService.insert(urRo);
        }
        return "redirect:/basicinfo/sysuser/toUrRoupdate.action?id=" + urRo.getSuserId(); // 转向列表的action
    }

    // 转向连接修改页面
    @RequestMapping("/basicinfo/sysuser/toUrRoupdate.action")
    public String toUrRoupdate(String id, Model model) {
        SysUserVO obj = sysUserService.view(id);
        model.addAttribute("obj", obj);
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("state", "1");
        List<Role> rolelist = roleService.find(paraMap);
        if (null != obj.getRoles()) {
            for (UrRo u : obj.getRoles()) {
                for (Role r : rolelist) {
                    if (r.getId().equals(u.getSroleId())) {
                        rolelist.remove(r);
                        break;
                    }
                }
            }
        }
        model.addAttribute("rolelist", rolelist);
        return "/basicinfo/sysuser/jUrRoUpdate.jsp";
    }

    // 删除连接
    @RequestMapping("/basicinfo/sysuser/deleteUrRoById.action")
    public String deleteUrRoByID(String id, String userId) {
        urRoService.deleteById(id);
        return "redirect:/basicinfo/sysuser/toUrRoupdate.action?id=" + userId;
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/sysuser/toupdate.action")
    public String toupdate(String id, Model model) {
        SysUserVO obj = sysUserService.view(id);
        model.addAttribute("obj", obj);
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("state", "1");
        List<Area> arealist = areaService.find(paraMap);
        model.addAttribute("arealist", arealist);
        return "/basicinfo/sysuser/jSysUserUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/sysuser/update.action")
    public String update(SysUser sysyUser) {
        if (sysyUser.getPassword() != null && !sysyUser.getPassword().equals("")) {
            sysUserService.update(sysyUser);
        }
        return "redirect:/basicinfo/sysuser/toview.action?id=" + sysyUser.getId();
    }

    // 查看
    @RequestMapping("/basicinfo/sysuser/toview.action")
    public String toview(String id, Model model) {
        SysUserVO obj = sysUserService.view(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/sysuser/jSysUserView.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/sysuser/start.action")
    public String start(@RequestParam("id") String[] ids) {
        sysUserService.start(ids);

        return "redirect:/basicinfo/sysuser/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/sysuser/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        sysUserService.stop(ids);

        return "redirect:/basicinfo/sysuser/list.action";
    }

    // 转向修改密码
    @RequestMapping("/basicinfo/sysuser/changepassword.action")
    public String changepassword() {
        return "/basicinfo/sysuser/jSysUserPassword.jsp";
    }

    // 确认修改密码
    @RequestMapping("/basicinfo/sysuser/surechangepassword.action")
    public String surechangepassword(String password, String password1, HttpSession session, Model model) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        if (password.trim().equals(password1.trim())) {
            model.addAttribute("msg", "两次输入密码不能一样！！！");
            return "/msg.jsp";
        } else {
            if (sysUserVO.getPassword().equals(password.trim())) {
                SysUser sysUser = sysUserService.get(sysUserVO.getId());
                sysUser.setPassword(password1);
                sysUserService.update(sysUser);
                session.removeAttribute("sysUserVO");
                return "redirect:/home.action";
            } else {
                model.addAttribute("msg", "原始密码错误！！！");
                return "/msg.jsp";
            }
        }
    }
}
