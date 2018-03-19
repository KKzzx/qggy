package cn.itcast.jk.controller.basicinfo.phonecourse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Category;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Rank;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RankService;
import cn.itcast.jk.vo.CourseVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneCourseSysController extends BaseController {
    @Resource
    CourseService courseService;
    @Resource
    RankService rankService;
    @Resource
    CategoryService categoryService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/course/list.action")
    public String list(Model model) {
        List<Course> dataList = courseService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/course/jCourseList.jsp"; // 转向页面
    }

    // 顾客手机查看所有课程
    @RequestMapping("/public/phone/basicinfo/course/view.action")
    public String view(Model model) {
        List<CourseVO> dataList = courseService.view(null);
        //System.out.println("[" + dataList.size() + "]");
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        JSONArray jsonObject = JSONArray.fromObject(dataList);
        //System.out.println("[" + jsonObject.toString() + "]");
        //System.out.println("[" + dataList.get(0).getClass() + "]");
//		System.out.println("["
//				+ JSONArray.fromObject(dataList.get(0).getCourses()).toString()
//				+ "]");
        model.addAttribute("successInfo", jsonObject.toString());

        return "/basicinfo/course/PhCourseView.jsp"; // 转向页面
    }

    // 手机查看课程详情
    @RequestMapping("/public/phone/basicinfo/course/phonecoursedetail.action")
    public String phonecoursedetail(String id, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("course", obj);

        return "/basicinfo/course/PhCourseDetail.jsp";
    }

    // 转向新增页面
    @RequestMapping("/phone/sys/basicinfo/course/tocreate.action")
    public String tocreate(Model model) {
        List<Rank> rankdataList = rankService.find(null);
        model.addAttribute("rankList", rankdataList);

        List<Category> categorydataList = categoryService.find(null);
        model.addAttribute("categoryList", categorydataList);
        return "/basicinfo/course/jCourseCreate.jsp";
    }

    @RequestMapping("/phone/sys/basicinfo/course/insert.action")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 转型为MultipartHttpRequest：
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("courseCover");
            // 获得文件名：
            //String fileName = file.getOriginalFilename();
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
            System.out.println("上传文件的绝对地址是：" + localFile.getAbsolutePath());
            Course course = new Course();
            course.setCategoryId(multipartRequest.getParameter("categoryId"));
            course.setCategoryName(multipartRequest.getParameter("categoryName"));
            course.setRankId(multipartRequest.getParameter("rankId"));
            course.setRankName(multipartRequest.getParameter("rankName"));
            course.setCourseName(multipartRequest.getParameter("courseName"));
            course.setCourseAbstract(multipartRequest.getParameter("courseAbstract"));
            course.setCourseCover(localFile.getAbsolutePath().substring(localFile.getAbsolutePath().indexOf("\\images\\cover\\")));
            course.setState(Integer.parseInt(multipartRequest.getParameter("state")));
            course.setCoursePrice(Double.parseDouble(multipartRequest.getParameter("coursePrice")));
            course.setOpenNumber(Integer.parseInt(multipartRequest.getParameter("openNum")));
            course.setCourseRemark(request.getParameter("courseRemark"));
            course.setCourseContent(multipartRequest.getParameter("courseContent"));
            //System.out.println(multipartRequest.getParameter("courseContent") + " ========");
            //System.out.println(course.toString());


            courseService.insert(course);

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return "redirect:/basicinfo/course/list.action"; // 转向列表的action
    }


    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/course/toupdate.action")
    public String toupdate(String id, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/course/jCourseUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/course/update.action")
    public String update(Course course) {
        courseService.update(course);

        return "redirect:/phone/sys/basicinfo/course/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/course/toview.action")
    public String toview(String id, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/course/jCourseView.jsp";
    }


}
