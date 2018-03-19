package cn.itcast.jk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.service.SysUserService;
import cn.itcast.jk.vo.SysUserVO;

@Controller
public class HomeController {

    // 系统首页模块
    @Resource
    SysUserService sysUserService;

    @RequestMapping(value = {"/home.action"})
    // 配合web下<url-pattern>/</url-pattern>
    public String login() {
        System.out.println("跳转登陆");
        return "/index.jsp"; // 首页，删除根目录下index.jsp，否则上面url将被拦截进不来
    }

    @RequestMapping(value = "/fmain.action")
    public String fmain(SysUser sysUser, HttpSession session, Model model) {
        System.out.println("jinru");
        if (sysUser.getName() == null || sysUser.getPassword() == null)
            return "/index.jsp";
        System.out.println(sysUser.getName().length() + ""
                + sysUser.getPassword().length() + "123");
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("phonenumber", sysUser.getName());
        paraMap.put("password", sysUser.getPassword());
        paraMap.put("state", "1");
        List<SysUser> list = sysUserService.find(paraMap);
        if (list.size() > 0) {
            System.out.println("成功");
            SysUserVO sysUserVO = sysUserService.view(list.get(0).getId());
            session.setAttribute("sysUserVO", sysUserVO);
            return "/home/fmain.jsp";
        } else {
            paraMap.remove("password");
            list = sysUserService.find(paraMap);
            if (list.size() > 0) {
                model.addAttribute("loginFailed", 1);
            } else {
                model.addAttribute("loginFailed", 2);
            }
            System.out.println("shibai");
            return "/index.jsp";
        }

    }

    @RequestMapping(value = "/title.action")
    public String title() {
        return "/home/title.jsp";
    }

    @RequestMapping(value = "/homeurl.action")
    public String homeurl() {
        return "/home/homeurl.jsp";
    }

    @RequestMapping(value = "/left.action")
    public String left() {
        return "/home/left.jsp";
    }

    @RequestMapping(value = "/main.action")
    public String main() {
        return "/home/olmsgList.jsp"; // 首页转向留言板
    }

    // 系统管理模块

    @RequestMapping("/sysadminMain.action")
    public String sysadminMain() {
        return "/sysadmin/main.jsp";
    }

    @RequestMapping("/sysadminLeft.action")
    public String sysadminLeft() {
        return "/sysadmin/left.jsp";
    }

    // 基础信息模块

    @RequestMapping("/baseinfoMain.action")
    public String baseinfoMain() {
        return "/baseinfo/main.jsp";
    }

    @RequestMapping("/baseinfoLeft.action")
    public String baseinfoLeft() {
        return "/baseinfo/left.jsp";
    }

    // 统计分析

    @RequestMapping("/statMain.action")
    public String statMain() {
        return "/stat/main.jsp";
    }

    @RequestMapping("/statLeft.action")
    public String statLeft() {
        return "/stat/left.jsp";
    }

    // 货运管理模块

    @RequestMapping("/cargoMain.action")
    public String cargoMain() {
        return "/cargo/main.jsp";
    }

    @RequestMapping("/cargoLeft.action")
    public String cargoLeft() {
        return "/cargo/left.jsp";
    }

}
