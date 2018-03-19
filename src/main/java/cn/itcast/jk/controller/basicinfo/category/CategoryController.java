package cn.itcast.jk.controller.basicinfo.category;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Category;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class CategoryController extends BaseController {
    @Resource
    CategoryService categoryService;

    // 列表
    @RequestMapping("/basicinfo/category/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("11"))// 课程类别管理
            {
                List<Category> dataList = categoryService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/category/jCategoryList.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 转向新增页面
    @RequestMapping("/basicinfo/category/tocreate.action")
    public String tocreate() {
        return "/basicinfo/category/jCategoryCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/basicinfo/category/insert.action")
    public String insert(Category category) {
        if (category.getCategoryName() != null
                && !category.getCategoryName().equals("")) {
            categoryService.insert(category);
        }
        return "redirect:/basicinfo/category/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/category/toupdate.action")
    public String toupdate(String id, Model model) {
        Category obj = categoryService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/category/jCategoryUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/category/update.action")
    public String update(Category category) {
        categoryService.update(category);
        return "redirect:/basicinfo/category/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/category/toview.action")
    public String toview(String id, Model model) {
        Category obj = categoryService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/category/jCategoryView.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/category/start.action")
    public String start(@RequestParam("id") String[] ids) {
        categoryService.start(ids);

        return "redirect:/basicinfo/category/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/category/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        categoryService.stop(ids);

        return "redirect:/basicinfo/category/list.action";
    }
}
