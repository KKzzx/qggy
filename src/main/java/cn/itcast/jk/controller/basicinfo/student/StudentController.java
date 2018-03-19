package cn.itcast.jk.controller.basicinfo.student;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TeacherCourseService;
import cn.itcast.jk.service.WithdrawService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class StudentController extends BaseController {
    @Resource
    StudentService studentService;
    @Resource
    CourseService courseService;
    @Resource
    AreaService areaService;

    @Resource
    WithdrawService withdrawService;
    @Resource
    TeacherCourseService teacherCourseService;

    // 列表
    @RequestMapping("/basicinfo/student/list.action")
    public String listStudent(Model model, int state, HttpSession session,String namelikes) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("7"))// 分部审核
            {
                Map<String, String> paramap = new HashMap<>();
                if (!StringUtils.isBlank(namelikes)) {
                    paramap.put("namelikes", namelikes);
                }
                System.out.println(state + "123");
                if (state > 0) {
                    paramap.put("state", state + "");
                }
                System.out.println(sysUserVO.getAreaId());
                paramap.put("areaId", sysUserVO.getAreaId() + "");
                List<Student> dataList = studentService.find(paramap);
                model.addAttribute("dataList", dataList);// 将数据传递到页面

                return "/basicinfo/student/jStudentList.jsp"; // 转向页面
            } else if (r.getSroleId().equals("8"))// 总部审核
            {
                Map<String, String> paramap = new HashMap<>();
                if (!StringUtils.isBlank(namelikes)) {
                    paramap.put("namelikes", namelikes);
                }
                List<Student> dataList = studentService.find(paramap);

                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/student/jStudentList.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 列表
    @RequestMapping("/basicinfo/teacher/list.action")
    public String listTeacher(Model model, String namelikes, int state, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("4"))// 分部审核
            {
                Map<String, String> paramap = new HashMap<>();
                if (!StringUtils.isBlank(namelikes)) {
                    paramap.put("namelikes", namelikes);
                }
                System.out.println(state + "123");
                if (state > 0) {
                    paramap.put("state", state + "");
                }
                System.out.println(sysUserVO.getAreaId());
                paramap.put("areaId", sysUserVO.getAreaId() + "");
                List<Student> dataList = studentService.find(paramap);
                model.addAttribute("dataList", dataList);// 将数据传递到页面
                System.out.println(dataList.size() + "asasd");
                return "/basicinfo/teacher/jTeacherf.jsp"; // 转向页面
            } else if (r.getSroleId().equals("5"))// 总部审核
            {
                Map<String, String> paramap = new HashMap<>();
                if (!StringUtils.isBlank(namelikes)) {
                    paramap.put("namelikes", namelikes);
                }
                paramap.put("state", "2");
                List<Student> dataList = studentService.find(paramap);

                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/teacher/jTeacherz.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";
    }

    // 转向新增页面
    @RequestMapping("/basicinfo/student/tocreate.action")
    public String tocreate(Model model, String userOpenid) {

        return "/basicinfo/student/register.jsp";
    }

    // 新增保存
    @RequestMapping("/basicinfo/student/insert.action")
    public String insert(Student student, String ids, Model model, HttpSession session) {
        System.out.println(student.toString());
        student.setAvailableAssets(0);
        student.setFrozenAssets(0);
        student.setXianjin(0);
        student.setState(1);

        studentService.insert(student);
        session.setAttribute("user", student);
        Course course = courseService.get(ids);
        model.addAttribute("course", course);

        return "/basicinfo/trade/order.jsp";
    }

    // 老师添加课程
    @RequestMapping(value = "/basicinfo/teacher/insertCourse.action", method = RequestMethod.POST)
    public String insert(TeacherCourse teacherCourse) throws UnsupportedEncodingException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", teacherCourse.getCourseId());
        System.out.println(courseService.find(map).get(0).getCourseName());
        teacherCourse.setCourseName(courseService.find(map).get(0).getCourseName());
        teacherCourseService.insert(teacherCourse);
        return "redirect:/basicinfo/teacher/toaddCourse.action?teacherId=" + teacherCourse.getTeacherId();

    }

    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    // 老师删除课程
    @RequestMapping("/basicinfo/teacher/deleteCourse.action")
    public String deleteCourse(String id, String userId) {

        teacherCourseService.deleteById(id);
        return "redirect:/basicinfo/teacher/toaddCourse.action?teacherId=" + userId;
    }

    @RequestMapping("/basicinfo/teacher/toaddCourse.action")
    public String toaddCourse(String teacherId, Model model, String cname, String firstSelect) {
        System.out.println("id" + teacherId);
        System.out.println("cname" + cname);
        System.out.println("firstarea" + firstSelect);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("nz", 1);
        List<Area> dataList = areaService.find(map);
        model.addAttribute("firstAreaList", dataList);

        Map<String, String> parmap = new HashMap<>();
        parmap.put("id", teacherId);
        Student obj = studentService.find(parmap).get(0);
        System.out.println(obj.getAreaId());
        model.addAttribute("obj", obj);
        Map<String, String> paraMap = new HashMap<>();
        if (firstSelect == null) {
            paraMap.put("areaId", obj.getAreaId());
            model.addAttribute("areaid", obj.getAreaId());
        } else if (firstSelect.equals("0")) {
            model.addAttribute("areaid", 0);
        } else {
            paraMap.put("areaId", firstSelect);
            model.addAttribute("areaid", firstSelect);
        }

        paraMap.put("teacherID", teacherId);
        if (cname != null && cname.length() > 0) {
            paraMap.put("cname", cname);
            model.addAttribute("cname", cname);

        }
        paraMap.put("state", "3");
        List<Course> listcourse = courseService.find(paraMap);
        model.addAttribute("listcourse", listcourse);
        Map<String, String> paraMap1 = new HashMap<>();

        paraMap1.put("teacherId", teacherId);
        List<TeacherCourse> listcourse1 = teacherCourseService.find(paraMap1);
        model.addAttribute("listcourse1", listcourse1);

        return "/basicinfo/teacher/teacherAddCourse.jsp";
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/student/toupdate.action")
    public String toupdate(String id, Model model) {
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("id", id);
        Student obj = studentService.find(paraMap).get(0);
        model.addAttribute("obj", obj);

        return "/basicinfo/student/jStudentUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/student/update.action")
    public String update(Student student) {
        System.out.println(student.toString());
        studentService.update(student);

        return "redirect:/basicinfo/student/list.action?state=0";
    }

    @RequestMapping("/basicinfo/student/toview.action")
    public String toview(String id, Model model) {
        // System.out.println("asadsd" + id);
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("id", id);
        Student obj = studentService.find(paraMap).get(0);
        model.addAttribute("obj", obj);

        return "/basicinfo/student/jStudentView.jsp";
    }

    @RequestMapping("/basicinfo/student/agreement.action")
    public String agreement() {

        return "/basicinfo/student/agreement.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/student/start.action")
    public String start(@RequestParam("id") String[] ids) {
        System.out.println("启动");
        studentService.start(ids);

        return "redirect:/basicinfo/student/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/student/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        System.out.println("tingyong");
        studentService.stop(ids);

        return "redirect:/basicinfo/student/list.action";
    }

    @RequestMapping("/basicinfo/student/deleteById.action")
    public String deleteById(String id) {

        studentService.deleteById(id);
        return "redirect:/basicinfo/student/list.action";
    }

    @RequestMapping("/basicinfo/student/delete.action")
    public String delete(@RequestParam("id") String[] ids) {

        studentService.delete(ids);

        return "redirect:/basicinfo/student/list.action";
    }

    // 审核通过
    @RequestMapping("/basicinfo/teacher/shenhe.action")
    public String shenhe(HttpServletRequest httpServletRequest, String id, int state) {
        System.out.println("id" + id + "++++++++");
        Student student = studentService.get(id);
        student.setState(state);
        studentService.update(student);

        return "redirect:/basicinfo/teacher/list.action?state=2";
    }

    // 审核通过
    @RequestMapping("/basicinfo/teacher/teachertoview.action")
    public String teachertoview(HttpServletRequest httpServletRequest, String id, Model model) {

        Student student = studentService.get(id);
        model.addAttribute("obj", student);
        return "/basicinfo/teacher/jTeacherView.jsp";
    }

    // 拉黑用户
    @RequestMapping("/basicinfo/student/blackUser.action")
    public String blackUser(HttpServletRequest httpServletRequest, String id, Model model) {
        Student student = new Student();
        student.setId(id);
        student.setState(4);  //黑名单的用户状态是4
        studentService.update(student);
        return "redirect:/basicinfo/student/list.action?state=0";
    }

    // 取消拉黑用户
    @RequestMapping("/basicinfo/student/unblackUser.action")
    public String unblackUser(HttpServletRequest httpServletRequest, String id, Model model) {
        Student student = new Student();
        student.setId(id);
        student.setState(0);  //取消黑名单后的用户状态是0
        studentService.update(student);
        return "redirect:/basicinfo/student/list.action?state=0";
    }
}
