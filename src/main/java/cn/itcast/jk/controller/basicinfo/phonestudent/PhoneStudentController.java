package cn.itcast.jk.controller.basicinfo.phonestudent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.StudentService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneStudentController extends BaseController {
    @Resource
    StudentService studentService;
    @Resource
    CourseService courseService;
    @Resource
    AreaService areaService;

    // 转向新增页面
    @RequestMapping("/phone/user/basicinfo/student/tocreate.action")
    public String tocreate(Model model, String id, HttpServletRequest request) {

        String userOpenid = request.getAttribute("userOpenid").toString();

        model.addAttribute("ids", id);

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

        return "/basicinfo/student/register.jsp";

    }

    @RequestMapping("/phone/user/basicinfo/teacher/tocreateteacher.action")
    public String tocreateTeacher(Model model, HttpServletRequest request) {
        System.out.println(request.getAttribute("userOpenid"));
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

        return "/basicinfo/teacher/register.jsp";

    }

    // 新增保存
    @RequestMapping("/phone/public/basicinfo/student/insert.action")
    public String insert(Student student, String ids, Model model, HttpSession session) {
        // System.out.println(student.toString());
        student.setAvailableAssets(0);
        student.setFrozenAssets(0);
        student.setState(1);
        student.setXianjin(0);
        Map<String, String> paraMap = new HashMap<String, String>();
        // paraMap.put("weiXin", student.getWeiXin());
        paraMap.put("phoneNumber", student.getPhoneNumber());
        paraMap.put("userName", student.getUserName());
        List<Student> ss = studentService.find(paraMap);
        if (ss != null && ss.size() > 0) {
            if (ss.get(0).getUserOpenid() != null && ss.get(0).getUserOpenid().length() > 0) {
                System.out.println("yizhuce");
                return "";

            } else {
                student.setId(ss.get(0).getId());
                System.out.println(student.toString());
                studentService.update(student);
            }
        } else {
            studentService.insert(student);
        }
        session.setAttribute("user", student);

        if (ids == null || ids.trim().length() > 0) {
            Course course = courseService.get(ids);
            model.addAttribute("course", course);

            return "/basicinfo/trade/order.jsp";
        } else {
            return "redirect:/phone/public/basicinfo/course/view.action";
        }
    }

    // 新增老师
    @RequestMapping("/phone/public/basicinfo/student/insertteacher.action")
    public String insertteacher(Student student, Model model, HttpSession session) {
        // System.out.println(student.toString());
        student.setAvailableAssets(0);
        student.setFrozenAssets(0);

        student.setState(0);
        student.setXianjin(0);
        Map<String, String> paraMap = new HashMap<String, String>();
        // paraMap.put("weiXin", student.getWeiXin());
        paraMap.put("phoneNumber", student.getPhoneNumber());
        paraMap.put("UserName", student.getUserName());
        List<Student> ss = studentService.find(paraMap);
        if (ss != null && ss.size() > 0) {
            studentService.update(student);
        } else {
            studentService.insert(student);
        }
        session.setAttribute("user", student);

        return "redirect:/phone/public/basicinfo/course/view.action";

    }

    // 转向修改页面
    @RequestMapping("/phone/user/basicinfo/student/toupdate.action")
    public String toupdate(String id, Model model) {
        Student obj = studentService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/student/jStudentUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/user/basicinfo/student/update.action")
    public String update(HttpServletRequest httpServletRequest, Student student) {
        studentService.update(student);
        httpServletRequest.getSession().setAttribute("user", student);
        return "redirect:/phone/user/basicinfo/student/list.action";
    }

    @RequestMapping("/phone/public/basicinfo/student/agreement.action")
    public String agreement() {

        return "/basicinfo/student/agreement.jsp";
    }

    // ajax查看个人信息
    @RequestMapping("/phone/user/basicinfo/student/findbyOID.action")
    public
    @ResponseBody
    String findbyOID(HttpSession session) {

        Student s = (Student) session.getAttribute("user");
        JSONArray jsonObject = JSONArray.fromObject(s);
        System.out.println(s.getUserName());
        return jsonObject.toString();

    }

}
