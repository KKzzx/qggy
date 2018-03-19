package cn.itcast.jk.controller.basicinfo.phonecourse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.AdvService;
import cn.itcast.jk.service.AreaService;
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
public class PhoneCourseController extends BaseController {
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

    // 手机查看所有课程
    @RequestMapping("/phone/public/basicinfo/course/view.action")
    public String view(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        if (student != null && student.getAreaId() != null) {
            Map<String, String> map = new HashMap<>();
            map.put("areaId", student.getAreaId());
            List<CourseVO> dataList = courseService.view(map);
            //System.out.println("[" + dataList.size() + "]");
            model.addAttribute("dataList", dataList); // 将数据传递到页面
            JSONArray jsonObject = JSONArray.fromObject(dataList);
            //System.out.println("[" + jsonObject.toString() + "]");
            //System.out.println("[" + dataList.get(0).getClass() + "]");
            //System.out.println("[" + JSONArray.fromObject(dataList.get(0).getCourses()).toString() + "]");
            model.addAttribute("successInfo", jsonObject.toString());
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("isMain", "1");
            List<Advertisement> listad = advService.find(paraMap);
            JSONArray jsonObject1 = JSONArray.fromObject(listad);
            model.addAttribute("broadData", jsonObject1.toString());
            /*
			 * HashMap<String, Integer> firstAreaList = new HashMap<String,
			 * Integer>(); firstAreaList.put("nz", 3);
			 * 
			 * List<Area> dataList2 = areaService.find(firstAreaList);
			 * model.addAttribute("firstAreaList", dataList2);
			 * model.addAttribute("areaid", student.getAreaId());
			 */
            Area area = new Area();
            area.setId(student.getAreaId());
            area.setAreaName(student.getAreaName());
            model.addAttribute("area", area);
            return "/basicinfo/course/PhCourseView.jsp"; // 转向页面
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("recommend", "1");
            List<CourseVO> dataList = courseService.view(map);
            //System.out.println("[" + dataList.size() + "]");
            model.addAttribute("dataList", dataList); // 将数据传递到页面
            JSONArray jsonObject = JSONArray.fromObject(dataList);
            //System.out.println("[" + jsonObject.toString() + "]");
            //System.out.println("[" + dataList.get(0).getClass() + "]");
            //System.out.println("[" + JSONArray.fromObject(dataList.get(0).getCourses()).toString() + "]");
            model.addAttribute("successInfo", jsonObject.toString());
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("isMain", "1");
            List<Advertisement> listad = advService.find(paraMap);
            JSONArray jsonObject1 = JSONArray.fromObject(listad);
            model.addAttribute("broadData", jsonObject1.toString());

			/*
			 * HashMap<String, Integer> firstAreaList = new HashMap<String,
			 * Integer>(); firstAreaList.put("nz", 3);//nz表示没有子
			 * 
			 * List<Area> dataList2 = areaService.find(firstAreaList);
			 * model.addAttribute("firstAreaList", dataList2); // 将数据传递到页面
			 */
            // model.addAttribute("area", 0);
            Area area = new Area();
            area.setId(0 + "");
            area.setAreaName("全国");

            model.addAttribute("area", area);
            return "/basicinfo/course/PhCourseView.jsp"; // 转向页面
        }

    }

    // 切换区域查看课程
    @RequestMapping("/phone/public/basicinfo/course/viewbyAreaID.action")
    public String viewbyAreaId(Model model, HttpSession session, int areaid) {
        //System.out.println(areaid);
        if (areaid == 0) {
            Map<String, String> map = new HashMap<>();
            map.put("recommend", "1");
            List<CourseVO> dataList = courseService.view(map);
            //System.out.println("[" + dataList.size() + "]");
            model.addAttribute("dataList", dataList); // 将数据传递到页面
            JSONArray jsonObject = JSONArray.fromObject(dataList);
            model.addAttribute("successInfo", jsonObject.toString());
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("isMain", "1");
            List<Advertisement> listad = advService.find(paraMap);
            JSONArray jsonObject1 = JSONArray.fromObject(listad);
            model.addAttribute("broadData", jsonObject1.toString());

            // HashMap<String, Integer> firstAreaList = new HashMap<String,
            // Integer>();
            // firstAreaList.put("nz", 3);

            // List<Area> dataList2 = areaService.find(firstAreaList);

            // model.addAttribute("firstAreaList", dataList2); // 将数据传递到页面
            Area area = new Area();
            area.setId(0 + "");
            area.setAreaName("全国");

            model.addAttribute("area", area);
            return "/basicinfo/course/PhCourseView.jsp"; // 转向页面

        } else {
            Map<String, String> map = new HashMap<>();
            map.put("areaId", areaid + "");
            List<CourseVO> dataList = courseService.view(map);
            //System.out.println("[" + dataList.size() + "]");
            model.addAttribute("dataList", dataList); // 将数据传递到页面
            JSONArray jsonObject = JSONArray.fromObject(dataList);

            model.addAttribute("successInfo", jsonObject.toString());
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("isMain", "1");
            List<Advertisement> listad = advService.find(paraMap);
            JSONArray jsonObject1 = JSONArray.fromObject(listad);
            model.addAttribute("broadData", jsonObject1.toString());
            //HashMap<String, Integer> firstAreaList = new HashMap<String, Integer>();
            //firstAreaList.put("nz", 3);

            //List<Area> dataList2 = areaService.find(firstAreaList);
            // model.addAttribute("firstAreaList", dataList2); // 将数据传递到页面

            Area area = areaService.get(areaid);

            model.addAttribute("area", area);


            return "/basicinfo/course/PhCourseView.jsp"; // 转向页面
        }

    }

    // 手机查看课程详情
    @RequestMapping("/phone/public/basicinfo/course/phonecoursedetail.action")
    public String phonecoursedetail(String id, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("course", obj);

        return "/basicinfo/course/PhCourseDetail.jsp";
    }

    @RequestMapping("/phone/public/basicinfo/course/phonecoursedetail1.action")
    public String phonecoursedetail1(String id, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("course", obj);

        return "/basicinfo/course/fxPhCourseDetail.jsp";
    }

}
