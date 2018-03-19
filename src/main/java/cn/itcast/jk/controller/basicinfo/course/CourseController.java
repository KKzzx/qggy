package cn.itcast.jk.controller.basicinfo.course;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Category;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.CourseAdv;
import cn.itcast.jk.domain.Rank;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.AdvService;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RankService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TeacherCourseService;
import cn.itcast.jk.vo.CourseadvVO;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class CourseController extends BaseController {
    @Resource
    CourseService courseService;
    @Resource
    RankService rankService;
    @Resource
    CategoryService categoryService;
    @Resource
    AdvService advService;
    @Resource
    AreaService areaService;
    @Resource
    TeacherCourseService teacherCourseService;
    @Resource
    StudentService studentService;

    // 列表
    @RequestMapping("/basicinfo/course/list.action")
    public String list(Model model, String likes, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("1"))// 录入人员
            {
                Map<String, String> parmap = new HashMap<>();
                parmap.put("releaseUserId", sysUserVO.getId());
                if (!StringUtils.isBlank(likes)) {
                    parmap.put("likes", likes);
                }
                List<Course> dataList = courseService.find(parmap);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/course/jCourseListj.jsp";
            } else if (r.getSroleId().equals("2"))// 分部领导
            {
                Map<String, String> parmap = new HashMap<>();
                parmap.put("areaId", sysUserVO.getAreaId() + "");
                if (!StringUtils.isBlank(likes)) {
                    parmap.put("likes", likes);
                }
                List<Course> dataList = courseService.find(parmap);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/course/jCourseListf.jsp";
            } else if (r.getSroleId().equals("3"))// 总部领导
            {
                Map<String, String> parmap = new HashMap<>();
                if (!StringUtils.isBlank(likes)) {
                    parmap.put("likes", likes);
                }
                List<Course> dataList = courseService.find(parmap);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/course/jCourseListz.jsp";
            }
        }
        return "/baseinfo/error.jsp";
    }

    // 查询所有课程
    @RequestMapping("/basicinfo/course/listall.action")
    public String listall(Model model, HttpSession session) {

        List<Course> dataList = courseService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/course/jCourseList.jsp";

    }

    // 转向新增页面
    @RequestMapping("/basicinfo/course/tocreate.action")
    public String tocreate(Model model) {
        List<Rank> rankdataList = rankService.find(null);
        model.addAttribute("rankList", rankdataList);

        List<Category> categorydataList = categoryService.find(null);
        model.addAttribute("categoryList", categorydataList);
        return "/basicinfo/course/jCourseCreate.jsp";
    }

    @RequestMapping(value = "/basicinfo/course/insertTeacher.action", method = RequestMethod.POST)
    public String insert(TeacherCourse teacherCourse) throws UnsupportedEncodingException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", teacherCourse.getTeacherId());
        teacherCourse.setTeacherName(studentService.find(map).get(0).getUserName());
        teacherCourseService.insert(teacherCourse);
        return "redirect:/basicinfo/course/toaddteacher.action?courseId=" + teacherCourse.getCourseId();

    }

    @RequestMapping("/basicinfo/course/insert.action")
    public String insert(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            // 转型为MultipartHttpRequest：
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("courseCover");
            // 获得文件名：
            // String fileName = file.getOriginalFilename();
            // rename file
            String localFileName = System.currentTimeMillis() + ".jpg";
            // 上传到images/cover目录下
            String path = request.getSession().getServletContext().getRealPath("/images/cover/" + localFileName);
            File localFile = new File(path); // 文件路径（路径+文件名）
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(localFile));
            int c;
            while ((c = bin.read()) != -1) {
                bout.write(c);
            }
            bout.flush();
            bout.close();
            bin.close();
            // System.out.println("上传文件的绝对地址是：" + localFile.getAbsolutePath());
            Course course = new Course();
            course.setCategoryId(multipartRequest.getParameter("categoryId"));
            course.setCategoryName(multipartRequest.getParameter("categoryName"));
            course.setRankId(multipartRequest.getParameter("rankId"));
            course.setRankName(multipartRequest.getParameter("rankName"));
            course.setCourseName(multipartRequest.getParameter("courseName"));
            course.setCourseAbstract(multipartRequest.getParameter("courseAbstract"));
            course.setCourseCover(
                    localFile.getAbsolutePath().substring(localFile.getAbsolutePath().indexOf("\\images\\cover\\")));
            course.setState(Integer.parseInt(multipartRequest.getParameter("state")));
            course.setCoursePrice(Double.parseDouble(multipartRequest.getParameter("coursePrice")));
            course.setOpenNumber(Integer.parseInt(multipartRequest.getParameter("openNum")));
            course.setCourseRemark(multipartRequest.getParameter("courseRemark"));
            course.setCourseContent(multipartRequest.getParameter("courseContent"));
            // System.out.println(multipartRequest.getParameter("courseRemark")
            // + " ========");
            // System.out.println(course.toString());
            SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
            course.setAreaId(sysUserVO.getAreaId() + "");
            course.setAreaName(sysUserVO.getAreaName());
            course.setReleaseName(sysUserVO.getName());
            course.setReleaseOpenId(sysUserVO.getOpenid());
            course.setReleaseUserId(sysUserVO.getId());
            course.setReleaseTime(new Date());

            courseService.insert(course);

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return "redirect:/basicinfo/course/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/course/toupdate.action")
    public String toupdate(String id, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("obj", obj);
        List<Rank> rankdataList = rankService.find(null);
        model.addAttribute("rankList", rankdataList);

        List<Category> categorydataList = categoryService.find(null);
        model.addAttribute("categoryList", categorydataList);
        return "/basicinfo/course/jCourseUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/course/update.action")
    public String update(HttpServletRequest request, HttpServletResponse response) {

        try {
            Course course = new Course();
            // 转型为MultipartHttpRequest：
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("courseCover");
            // 获得文件名：
            String fileName = file.getOriginalFilename();
            // System.out.println(StringUtils.isBlank(fileName));
            if (!StringUtils.isBlank(fileName)) {
                // rename file
                String localFileName = System.currentTimeMillis() + ".jpg";
                // 上传到images/cover目录下
                String path = request.getSession().getServletContext().getRealPath("/images/cover/" + localFileName);
                File localFile = new File(path); // 文件路径（路径+文件名）
                if (!localFile.exists()) {
                    localFile.createNewFile();
                }
                BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
                BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(localFile));
                int c;
                while ((c = bin.read()) != -1) {
                    bout.write(c);
                }
                bout.flush();
                bout.close();
                bin.close();
                // System.out.println("上传文件的绝对地址是：" +
                // localFile.getAbsolutePath());
                course.setCourseCover(localFile.getAbsolutePath()
                        .substring(localFile.getAbsolutePath().indexOf("\\images\\cover\\")));
            }
            course.setId(Integer.parseInt(multipartRequest.getParameter("id")));
            course.setCategoryId(multipartRequest.getParameter("categoryId"));
            course.setCategoryName(multipartRequest.getParameter("categoryName"));
            course.setRankId(multipartRequest.getParameter("rankId"));
            course.setRankName(multipartRequest.getParameter("rankName"));
            course.setCourseName(multipartRequest.getParameter("courseName"));
            course.setCourseAbstract(multipartRequest.getParameter("courseAbstract"));
            course.setState(Integer.parseInt(multipartRequest.getParameter("state")));
            course.setCoursePrice(Double.parseDouble(multipartRequest.getParameter("coursePrice")));
            course.setOpenNumber(Integer.parseInt(multipartRequest.getParameter("openNum")));
            course.setCourseRemark(multipartRequest.getParameter("courseRemark"));
            // System.out.println("beizhu" +
            // multipartRequest.getParameter("courseRemark"));
            course.setCourseContent(multipartRequest.getParameter("courseContent"));
            // System.out.println(course.toString());
            int state = Integer.parseInt(multipartRequest.getParameter("state"));
            if (state == 3) {
                course.setState(2);
            }
            courseService.update(course);
            Advertisement adv = new Advertisement();
            adv.setAdverurl(course.getId() + "");
            adv.setAdvertiseCover(course.getCourseCover());
            adv.setContent(course.getCourseName());
            Map<String, String> map = new HashMap<String, String>();
            map.put("advertiseUrl", course.getId() + "");
            List<Advertisement> list = advService.find(map);
            if (list != null && list.size() > 0) {
                if (list.get(0).getAdstate() == 3) {
                    adv.setAdstate(2);
                }
                advService.update(adv);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return "redirect:/basicinfo/course/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/course/toview.action")
    public String toview(String id, Model model) {
        HashMap<String, String> paraMap = new HashMap<>();
        paraMap.put("id", id);
        CourseadvVO obj = courseService.see(paraMap);
        // System.out.println("leibie="+obj.getAdvertisements().get(0).getAdcategory());
        model.addAttribute("obj", obj);

        return "/basicinfo/course/jCourseView.jsp";
    }

    // 删除某个课程下面的某个广告
    @RequestMapping("/basicinfo/course/deleteAdv.action")
    public String deleteAdv(String id, String courseId) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("advertiseId", id);
        paramap.put("courseId", courseId);
        courseService.deleteAdv(paramap);

        return "redirect:/basicinfo/course/toAddAdv.action?id=" + courseId;
    }

    @RequestMapping("/basicinfo/course/addadv.action")
    public String addAdv(@RequestParam("id") String[] ids, @RequestParam("courseId") String courseId, Model model) {
        // System.out.println("tianjia");
        List<CourseAdv> courseAdvs = new ArrayList<>(ids.length);
        for (int i = 0; i < ids.length; i++) {
            CourseAdv courseAdv = new CourseAdv();
            courseAdv.setCourseId(Integer.parseInt(courseId));
            courseAdv.setAdvertiseId(Integer.parseInt(ids[i]));
            courseAdvs.add(courseAdv);
        }
        courseService.addAdv(courseAdvs);
        // 将数据传递到页面
        return "redirect:/basicinfo/course/toAddAdv.action?id=" + courseId;
    }

    // 转向新增广告页面
    @RequestMapping("/basicinfo/course/toAddAdv.action")
    public String toAddAdv(Model model, String id) {
        model.addAttribute("courseId", id);
        Map<String, String> pMap = new HashMap<String, String>();
        pMap.put("id", id);
        CourseadvVO obj = courseService.see(pMap);
        model.addAttribute("coursename", obj.getCourseName());

        List<Advertisement> dataList = advService.find(null);

        for (int j = 0; j < obj.getAdvertisements().size(); j++) {
            for (int i = 0; i < dataList.size(); i++) {

                if (dataList.get(i).getAdvertiseId() == obj.getAdvertisements().get(j).getAdvertiseId()) {
                    dataList.remove(i);
                    break;
                }
            }
        }

        model.addAttribute("dataList", dataList);

        model.addAttribute("obj", obj);
        // 将数据传递到页面

        return "/basicinfo/course/jCourseAddadv.jsp";
    }

    // 课程增加老师
    @RequestMapping("/basicinfo/course/toaddteacher.action")
    public String toAddTeacher(Model model, String courseId, String likes, String firstSelect) {
        System.out.println("kechengid" + courseId);
        Course course = courseService.get(courseId);
        model.addAttribute("obj", course);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("nz", 1);
        List<Area> dataList = areaService.find(map);
        model.addAttribute("firstAreaList", dataList);

        Map<String, String> paraMap = new HashMap<>();
        if (firstSelect == null) {
            paraMap.put("areaId", course.getAreaId());
            model.addAttribute("areaid", course.getAreaId());
        } else if (firstSelect.equals("0")) {
            model.addAttribute("areaid", 0);
        } else {
            paraMap.put("areaId", firstSelect);
            model.addAttribute("areaid", firstSelect);
        }

        paraMap.put("courseId", courseId);
        if (likes != null && likes.length() > 0) {
            paraMap.put("likes", likes);
            model.addAttribute("likes", likes);

        }
        paraMap.put("state", "3");
        List<Student> liststudent = studentService.find(paraMap);
        model.addAttribute("liststudent", liststudent);

        Map<String, String> paraMap1 = new HashMap<>();

        paraMap1.put("courseId", courseId);
        List<TeacherCourse> listcourse1 = teacherCourseService.find(paraMap1);
        model.addAttribute("listcourse1", listcourse1);

        return "/basicinfo/course/jCourseAddteacher.jsp";
    }

    // 老师删除课程
    @RequestMapping("/basicinfo/course/deleteTeacher.action")
    public String deleteTeacher(String id, String courseId) {

        teacherCourseService.deleteById(id);
        return "redirect:/basicinfo/course/toaddteacher.action?courseId=" + courseId;
    }

    @RequestMapping("/basicinfo/course/upstate.action")
    public String upstate(int id, int state, Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        Course course = new Course();
        if (state == 3) {// 总部审核通过
            course.setId(id);
            course.setzCheckId(sysUserVO.getId());
            course.setzCheckOpenId(sysUserVO.getOpenid());
            course.setzCheckTime(new Date());
            course.setzCheckUserName(sysUserVO.getName());
            course.setState(3);
        } else if (state == 2) {// 分部审核通过
            course.setId(id);
            course.setfCheckId(sysUserVO.getId());
            course.setfCheckOpenId(sysUserVO.getOpenid());
            course.setfCheckTime(new Date());
            course.setfCheckUserName(sysUserVO.getName());
            course.setState(2);
        } else if (state == 4) {// 基础人员停课
            course.setId(id);
            course.setState(4);
        } else if (state == 1) {// 基础人员上报
            course.setId(id);
            course.setState(1);
        }
        courseService.upstate(course);
        return "redirect:/basicinfo/course/list.action";

    }

    @RequestMapping("/basicinfo/course/uprecommend.action")
    public String uprecommend(int id, int recommend) {
        Map<String, Integer> paramap = new HashMap<>();
        paramap.put("id", id);
        paramap.put("recommend", recommend);
        courseService.uprecommend(paramap);
        return "redirect:/basicinfo/course/list.action";

    }
}
