package cn.itcast.jk.controller.basicinfo.phoneteacher;

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
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TeacherCourseService;
import cn.itcast.jk.vo.ClassTradetail;
import cn.itcast.jk.vo.TeacherCourseVO;

@Controller
public class PhoneTeacherController extends BaseController {
    @Resource
    StudentService studentService;
    @Resource
    CourseService courseService;
    @Resource
    AreaService areaService;
    @Resource
    TeacherCourseService teacherCourseService;

    // 列表
    @RequestMapping("/phone/user/basicinfo/teachercourse/list.action")
    public
    @ResponseBody
    String listteachercourse(HttpSession session) {

        Student student = (Student) session.getAttribute("user");
        Map<String, String> paramap = new HashMap<>();
        paramap.put("teacherId", student.getId());

        List<TeacherCourseVO> dataList = teacherCourseService.view(paramap);

        JSONArray jsonObject = JSONArray.fromObject(dataList);

        return jsonObject.toString();
    }

    @RequestMapping("/phone/user/basicinfo/teachercourse/coursedetail.action")
    public String coursedetail(Model model, String id) {

        Course course = courseService.get(id);
        model.addAttribute("course", course);

        return "/basicinfo/teacher/PhCourseDetail.jsp";
    }

    @RequestMapping("/phone/user/basicinfo/teachercourse/listclass.action")
    public
    @ResponseBody
    String listclass(HttpSession session, int state) {

        Student student = (Student) session.getAttribute("user");
        Map<String, String> paramap = new HashMap<>();
        paramap.put("teacherId", student.getId());
        paramap.put("classsState", state + "");

        List<ClassTradetail> dataList = teacherCourseService.getpersonal(paramap);

        JSONArray jsonObject = JSONArray.fromObject(dataList);

        return jsonObject.toString();
    }

    @RequestMapping("/phone/user/classlistDetail.action")
    public String listclassdetail(String id, Model model) {

        System.out.println(id + "--------------------");
        Map<String, String> paramap = new HashMap<>();
        paramap.put("classsid", id);

        List<ClassTradetail> dataList = teacherCourseService.getpersonal(paramap);

        model.addAttribute("obj", dataList.get(0));

        return "/basicinfo/teacher/classlistDetail.jsp";
    }

    @RequestMapping("/phone/user/basicinfo/teacher/becometeacher.action")
    public String becometeacher(Model model, Student s, HttpSession session) {

        Student ss = (Student) session.getAttribute("user");
        ss.setAdress(s.getAdress());
        ss.setCompany(s.getCompany());
        ss.setEmail(s.getEmail());
        ss.setLikes(s.getLikes());
        ss.setMarryd(s.getMarryd());
        ss.setSex(s.getSex());
        ss.setShenFen(s.getShenFen());
        ss.setWeiXin(s.getWeiXin());
        ss.setState(0);
        studentService.update(ss);
        System.out.println(ss.toString());
        session.setAttribute("user", ss);

        return "/basicinfo/teacher/shenqingsucess.jsp";
    }

}
