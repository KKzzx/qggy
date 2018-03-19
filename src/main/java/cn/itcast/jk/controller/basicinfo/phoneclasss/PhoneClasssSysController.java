package cn.itcast.jk.controller.basicinfo.phoneclasss;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Category;
import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.domain.Rank;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.service.RankService;
import cn.itcast.jk.vo.ClasssVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneClasssSysController extends BaseController {
    @Resource
    ClasssService classsService;
    @Resource
    RankService rankService;
    @Resource
    CategoryService categoryService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/classs/list.action")
    public String list(Model model) {
        List<Classs> dataList = classsService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/classs/jClasssList.jsp"; // 转向页面
    }


    @SuppressWarnings("unchecked")
    @RequestMapping("/phone/basicinfo/classs/view.action")
    public String view(Model model) {
        List<ClasssVO> dataList = (List<ClasssVO>) classsService.view(null);
        System.out.println("[" + dataList.size() + "]");
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        JSONArray jsonObject = JSONArray.fromObject(dataList);
        System.out.println("[" + jsonObject.toString() + "]");
        System.out.println("[" + dataList.get(0).getClass() + "]");
        model.addAttribute("successInfo", jsonObject.toString());

        return "/basicinfo/classs/PhClasssView.jsp"; // 转向页面
    }

    // 手机查看课程详情
    @RequestMapping("/phone/sys/basicinfo/classs/phoneclasssdetail.action")
    public String phoneclasssdetail(String id, Model model) {
        Classs obj = classsService.get(id);
        model.addAttribute("classs", obj);

        return "/basicinfo/classs/PhClasssDetail.jsp";
    }

    @RequestMapping("/phone/sys/basicinfo/classs/addADV.action")
    public String addADV(String id, Model model) {
        Classs obj = classsService.get(id);
        model.addAttribute("classs", obj);

        return "/basicinfo/classs/PhClasssDetail.jsp";
    }

    // 转向新增页面
    @RequestMapping("/phone/sys/basicinfo/classs/tocreate.action")
    public String tocreate(Model model) {
        List<Rank> rankdataList = rankService.find(null);
        model.addAttribute("rankList", rankdataList);

        List<Category> categorydataList = categoryService.find(null);
        model.addAttribute("categoryList", categorydataList);
        return "/basicinfo/classs/jClasssCreate.jsp";
    }

    @RequestMapping("/phone/sys/basicinfo/classs/insert.action")
    public String insert(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:/phone/sys/basicinfo/classs/list.action"; // 转向列表的action
    }


    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/classs/toupdate.action")
    public String toupdate(String id, Model model) {
        Classs obj = classsService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/classs/jClasssUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/classs/update.action")
    public String update(Classs classs) {
        classsService.update(classs);

        return "redirect:/phone/sys/basicinfo/classs/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/classs/toview.action")
    public String toview(String id, Model model) {
        Classs obj = classsService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/classs/jClasssView.jsp";
    }

    // 批量启用
    @RequestMapping("/phone/sys/basicinfo/classs/start.action")
    public String start(@RequestParam("id") String[] ids) {
        classsService.start(ids);

        return "redirect:/phone/sys/basicinfo/classs/list.action";
    }

    // 批量停用
    @RequestMapping("/phone/sys/basicinfo/classs/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        classsService.stop(ids);


        return "redirect:/phone/sys/basicinfo/classs/list.action";
    }
}
