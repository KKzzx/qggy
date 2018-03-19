package cn.itcast.jk.controller.basicinfo.phonesystemUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Role;
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
public class PhoneSysUserController extends BaseController {
    @Resource
    SysUserService sysUserService;
    @Resource
    AreaService areaService;
    @Resource
    RoleService roleService;
    @Resource
    UrRoService urRoService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/sysuser/list.action")
    public String list(Model model) {
        List<SysUser> dataList = sysUserService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/sysuser/jSysUserList.jsp"; // 转向页面
    }

    // 转向注册页面
    @RequestMapping("/phone/sys/basicinfo/sysuser/tocreate.action")
    public String tocreate(Model model, HttpServletRequest request) {
        String userOpenid = request.getAttribute("userOpenid").toString();

        model.addAttribute("userOpenid", userOpenid);
        HashMap<String, Integer> firstAreaList = new HashMap<String, Integer>();
        firstAreaList.put("level", 1);
        List<Area> dataList = areaService.find(firstAreaList);
        model.addAttribute("firstAreaList", dataList); // 将数据传递到页面
        HashMap<String, Integer> secondAreaList = new HashMap<String, Integer>();
        secondAreaList.put("parentId", 1);
        List<Area> dataList1 = areaService.find(secondAreaList);
        model.addAttribute("secondAreaList", dataList1); // 将数据传递到页面
        HashMap<String, Integer> thirdAreaList = new HashMap<String, Integer>();
        thirdAreaList.put("parentId", 2);
        List<Area> dataList2 = areaService.find(thirdAreaList);
        model.addAttribute("thirdAreaList", dataList2); // 将数据传递到页面

        return "/basicinfo/sysuser/jSysUserRegister.jsp";
    }

    @RequestMapping("/phone/sys/basicinfo/sysuser/agreement.action")
    public String agreement() {

        return "/basicinfo/sysuser/agreement.jsp";
    }

    // 新增保存
    @RequestMapping("/phone/public/basicinfo/sysuser/insert.action")
    public String insert(SysUser sysyUser) {
        if (sysyUser.getName() != null && !sysyUser.getName().equals("")) {
            sysUserService.insert(sysyUser);
        }
        return "redirect:/basicinfo/sysuser/list.action"; // 转向列表的action
    }

    // 新增连接保存
    @RequestMapping("/phone/sys/basicinfo/sysuser/insertUrRo.action")
    public String insertUrRo(UrRo urRo) {
        urRoService.insert(urRo);
        return "redirect:/phone/sys/basicinfo/sysuser/toUrRoupdate.action?id="
                + urRo.getSuserId(); // 转向列表的action
    }

    // 转向连接修改页面
    @RequestMapping("/phone/sys/basicinfo/sysuser/toUrRoupdate.action")
    public String toUrRoupdate(String id, Model model) {
        SysUserVO obj = sysUserService.view(id);
        model.addAttribute("obj", obj);
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("state", "1");
        List<Role> rolelist = roleService.find(paraMap);
        if (null != obj.getRoles()) {
            for (UrRo u : obj.getRoles()) {
                for (Role r : rolelist) {
                    if (r.getId() == u.getSroleId()) {
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
    @RequestMapping("/phone/sys/basicinfo/sysuser/deleteUrRoById.action")
    public String deleteUrRoByID(String id, String userId) {
        urRoService.deleteById(id);
        return "redirect:/phone/sys/basicinfo/sysuser/toUrRoupdate.action?id=" + userId;
    }

    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/sysuser/toupdate.action")
    public String todate(String id, Model model) {
        SysUserVO obj = sysUserService.view(id);
        model.addAttribute("obj", obj);
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("state", "1");
        List<Area> arealist = areaService.find(paraMap);
        model.addAttribute("arealist", arealist);
        return "/basicinfo/sysuser/jSysUserUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/sysuser/update.action")
    public String update(SysUser sysyUser) {
        if (sysyUser.getPassword() != null
                && !sysyUser.getPassword().equals("")) {
            sysUserService.update(sysyUser);
        }
        return "redirect:/phone/sys/basicinfo/sysuser/toview.action?id="
                + sysyUser.getId();
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/sysuser/toview.action")
    public String toview(String id, Model model) {
        SysUserVO obj = sysUserService.view(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/sysuser/jSysUserView.jsp";
    }

    // 批量启用
    @RequestMapping("/phone/sys/basicinfo/sysuser/start.action")
    public String start(@RequestParam("id") String[] ids) {
        sysUserService.start(ids);

        return "redirect:/phone/sys/basicinfo/sysuser/list.action";
    }

    // 批量停用
    @RequestMapping("/phone/sys/basicinfo/sysuser/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        sysUserService.stop(ids);

        return "redirect:/phone/sys/basicinfo/sysuser/list.action";
    }

}
