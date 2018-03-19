package cn.itcast.jk.controller.basicinfo.tradedetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;

import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.domain.CouStu;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TeacherCourseService;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.service.WXPayService;
import cn.itcast.jk.vo.ClasssVO;
import cn.itcast.jk.vo.SysUserVO;
import cn.itcast.qg.wxpay.MyWxPayConfig;
import cn.itcast.util.GetAcToken;
import cn.itcast.util.OrderUtil;
import cn.itcast.util.Template;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class TradeDetailController extends BaseController {
    @Resource
    TradeDetailService tradedetailService;
    @Resource
    ClasssService classsService;
    @Resource
    CourseService courseService;
    @Resource
    WXPayService wxPayService;

    @Resource
    StudentService studentService;
    @Resource
    TradeService tradeService;
    @Resource
    private TeacherCourseService teacherCourseService;

    // 列表
    @RequestMapping("/basicinfo/tradedetail/list.action")
    public String list(Model model, String courseId, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("13")) {
                // 总部角色管理

                List<CouStu> dataList = tradedetailService.coustu(sysUserVO
                        .getAreaId() + "");
                model.addAttribute("dataList", dataList); // 将数据传递到页面
                return "/basicinfo/tradedetail/jTradeDetailListf.jsp"; // 转向页面
            } else if (r.getSroleId().equals("14")) {
                List<CouStu> dataList = tradedetailService.coustu(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面
                return "/basicinfo/tradedetail/jTradeDetailListz.jsp"; // 转向页面
            }
        }

        return "/baseinfo/error.jsp";
    }

    // 单个课程详细列表
    @RequestMapping("/basicinfo/tradedetail/celllist.action")
    public String celllist(Model model, String id, HttpSession session) {
        Course course = courseService.get(id);
        model.addAttribute("obj", course); // 将数据传递到页面

        Map<String, String> paramap = new HashMap<>();
        paramap.put("classState", "0");
        paramap.put("tradeState", "0");
        paramap.put("courseid", id);
        List<TradeDetail> dataList = tradedetailService.find(paramap);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        Map<String, String> map = new HashMap<>();
        map.put("courseId", id);
        List<TeacherCourse> list = teacherCourseService.find(map);
        if (null != list && list.size() > 0) {
            // 允许开班
            model.addAttribute("state", "1"); // 将数据传递到页面
            model.addAttribute("list", list); // 将数据传递到页面
        } else {
            model.addAttribute("state", "0"); // 将数据传递到页面
            model.addAttribute("list", list); // 将数据传递到页面
        }
        return "/basicinfo/tradedetail/jTradeDetailCellList.jsp"; // 转向页面
    }

    // 新增保存 通知
    @RequestMapping("/basicinfo/tradedetail/insert.action")
    public String insert(String id, Classs classs, HttpServletRequest request) {
        classs.setId(id);
        classsService.update(classs);
        // 通知
        // https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277
        System.out.println("通知");
        String access_token = GetAcToken.getAcToken(request.getSession(),
                wxPayService);

        // System.out.println("access_token:" + access_token);
        if (null != access_token) {
            Map<String, String> paramap = new HashMap<>();
            paramap.put("id", classs.getId());
            paramap.put("tradeState", "0");
            paramap.put("classState", "1");
            ClasssVO classsVO = classsService.view(paramap);
            String[] data = {"上课地点：" + classs.getClassAddress(),
                    classs.getClassTime(), classs.getClassName(),
                    "开班通知 请去个人中心>我的课程>待确认 确认是否参加开课班级或者直接点击本通知"};
            for (Student stu : classsVO.getStudents()) {
                String openid = stu.getUserOpenid();
                if (openid != null) {
                    Template.notifyClass(MyWxPayConfig.HTTP_URL
                                    + "/phone/user/myclass.action?state=1",
                            access_token, openid, data);
                }
            }
        }
        return "redirect:/basicinfo/classs/list.action"; // 转向列表的action

    }

    // 修改保存
    @RequestMapping("/basicinfo/tradedetail/update.action")
    public String update(TradeDetail tradedetail) {
        tradedetailService.update(tradedetail);

        return "redirect:/basicinfo/tradedetail/list.action";
    }

    // 有问题没老师可以开班
    @RequestMapping("/basicinfo/tradedetail/createclass.action")
    public String creatclass(@RequestParam("id") String[] ids, String courseId,
                             String state, Model model, HttpServletRequest request) {
        if (state.equals("1")) {
            String classId = OrderUtil.getOrderNo();
            Classs classs = new Classs();
            classs.setId(classId);

            Course course = courseService.get(courseId);
            classs.setAreaId(course.getAreaId());
            classs.setAreaName(course.getAreaName());
            classsService.insert(classs);
            tradedetailService.updateClass(ids, classId, 1);
            for (String s : ids) {
                TradeDetail detail = tradedetailService.get(s);
                Map<String, String> map = new HashMap<>();
                map.put("id", detail.getTradeId());
                map.put("state", "6");
                tradeService.updateState(map);
            }
            model.addAttribute("classId", classId);
            Map<String, String> paramap = new HashMap<>();
            paramap.put("classState", "1");
            paramap.put("id", classId);
            ClasssVO classsVO = classsService.view(paramap);
            model.addAttribute("obj", classsVO);
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("courseId", courseId);
            System.out.println("课程ID" + courseId);
            List<TeacherCourse> listteacher = teacherCourseService
                    .find(paraMap);
            model.addAttribute("listteacher", listteacher);
            return "/basicinfo/tradedetail/jClassDetailCreate.jsp";
        } else {
            // 消息
            return "redirect:/basicinfo/course/toaddteacher.action?courseId="
                    + courseId;
        }
    }

}
