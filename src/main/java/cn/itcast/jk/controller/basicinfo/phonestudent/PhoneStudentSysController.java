package cn.itcast.jk.controller.basicinfo.phonestudent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.StudentService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneStudentSysController extends BaseController {
    @Resource
    StudentService studentService;
    @Resource
    CourseService courseService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/student/list.action")
    public String list(Model model, String userName) {
        Map<String, String> paramap = new HashMap<>();
        if (userName != null) {
            paramap.put("userName", userName);
        }
        List<Student> dataList = studentService.find(paramap);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/student/jStudentList.jsp"; // 转向页面
    }


    @RequestMapping("/phone/sys/basicinfo/student/toview.action")
    public String toview(String id, Model model) {
        System.out.println("asadsd" + id);
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("id", id);
        Student obj = studentService.find(paraMap).get(0);
        model.addAttribute("obj", obj);

        return "/basicinfo/student/jStudentView.jsp";
    }


    // 批量启用
    @RequestMapping("/phone/sys/basicinfo/student/start.action")
    public String start(@RequestParam("id") String[] ids) {
        System.out.println("启动");
        studentService.start(ids);

        return "redirect:/basicinfo/student/list.action";
    }

    // 批量停用
    @RequestMapping("/phone/sys/basicinfo/student/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        System.out.println("tingyong");
        studentService.stop(ids);

        return "redirect:/phone/sys/basicinfo/student/list.action";
    }

    @RequestMapping("/phone/sys/basicinfo/student/deleteById.action")
    public String deleteById(String id) {

        studentService.deleteById(id);
        ;

        return "redirect:/basicinfo/student/list.action";
    }

    @RequestMapping("/phone/sys/basicinfo/student/delete.action")
    public String delete(@RequestParam("id") String[] ids) {

        studentService.delete(ids);

        return "redirect:/phone/sys/basicinfo/student/list.action";
    }
}
