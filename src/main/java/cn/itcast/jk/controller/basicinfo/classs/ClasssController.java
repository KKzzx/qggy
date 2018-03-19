package cn.itcast.jk.controller.basicinfo.classs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.AcToken;
import cn.itcast.jk.domain.Classs;
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
import cn.itcast.jk.vo.ClassTradetail;
import cn.itcast.jk.vo.ClasssVO;
import cn.itcast.jk.vo.SysUserVO;
import cn.itcast.jk.vo.TradeVO;
import cn.itcast.qg.wxpay.MyWxPayConfig;
import cn.itcast.qg.wxpay.QRCode;
import cn.itcast.util.GetAcToken;
import cn.itcast.util.Template;

@Controller
public class ClasssController extends BaseController {
    @Resource
    ClasssService classsService;
    @Resource
    TradeDetailService tradeDetailService;
    @Resource
    CourseService courseService;
    @Resource
    WXPayService wxPayService;
    @Resource
    TeacherCourseService teacherCourseService;
    @Resource
    TradeService tradeService;
    @Resource
    StudentService studentService;

    // 列表
    @RequestMapping("/basicinfo/classs/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        System.out.println("----------------------------");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("15"))// 分部审核
            {
                Map<String, String> paramap = new HashMap<>();
                paramap.put("areaId", sysUserVO.getAreaId() + "");
                List<Classs> dataList = classsService.find(paramap);
                model.addAttribute("dataList", dataList); // 将数据传递到页面
                System.out.println("----------------------------");
                return "/basicinfo/classs/jClasssListf.jsp"; // 转向页面

            } else if (r.getSroleId().equals("16"))// 总部审核
            {
                List<Classs> dataList = classsService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面
                System.out.println("===========================");
                return "/basicinfo/classs/jClasssListz.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    @RequestMapping("/basicinfo/classs/addADV.action")
    public String addADV(String id, Model model) {
        Classs obj = classsService.get(id);
        model.addAttribute("classs", obj);

        return "/basicinfo/classs/PhClasssDetail.jsp";
    }

    // 转向新增页面
    @RequestMapping("/basicinfo/classs/tocreate.action")
    public String tocreate(String id, Model model) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("id", id);
        // paramap.put("tradeState", "0");
        paramap.put("classState", "1");
        ClasssVO obj = classsService.view(paramap);
        model.addAttribute("obj", obj);
        return "/basicinfo/classs/jClasssUpdate.jsp";
    }

    @RequestMapping("/basicinfo/classs/insert.action")
    public String insert(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:/basicinfo/classs/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/classs/toupdate.action")
    public String toupdate(String id, Model model) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("id", id);
        // paramap.put("tradeState", "0");
        paramap.put("classState", "1");
        ClasssVO obj = classsService.view(paramap);
        model.addAttribute("obj", obj);
        Map<String, String> paramap1 = new HashMap<>();
        paramap1.put("classId", id);

        List<TradeDetail> tDetails = tradeDetailService.find(paramap1);
        TradeDetail tDetail = new TradeDetail();
        if (tDetails.size() > 0) {
            tDetail = tDetails.get(0);
        } else {
            throw new RuntimeException("数据错误：该班级未找到对应的classId！");
        }
        Map<String, String> paraMap = new HashMap<>();
        paramap.put("courseId", tDetail.getCourseId());
        List<TeacherCourse> listteacher = teacherCourseService.find(paraMap);
        model.addAttribute("listteacher", listteacher);
        return "/basicinfo/classs/jClasssUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/classs/update.action")
    public String update(Classs classs, HttpServletRequest request) {
        classsService.update(classs);
        // 通知在/basicinfo/tradedetail/insert.action
        // 通知
        // https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277
        System.out.println("更新通知");
        String access_token = GetAcToken.getAcToken(request.getSession(), wxPayService);

        // System.out.println("access_token:" + access_token);
        if (null != access_token) {
            Map<String, String> paramap = new HashMap<>();
            paramap.put("id", classs.getId());
            paramap.put("tradeState", "0");
            paramap.put("classState", "1");
            ClasssVO classsVO = classsService.view(paramap);
            String[] data = {"上课地点：" + classs.getClassAddress(), classs.getClassTime(), classs.getClassName(),
                    "班级信息修改通知"};
            for (Student stu : classsVO.getStudents()) {
                String openid = stu.getUserOpenid();
                if (openid != null) {
                    Template.notifyClass(MyWxPayConfig.HTTP_URL + "/phone/user/myclass.action?state=1", access_token,
                            openid, data);
                }
            }
        }
        return "redirect:/basicinfo/classs/list.action";
    }

    // 退班操作
    @RequestMapping("/basicinfo/classs/toreturn.action")
    public String toreturn(String id, String classId) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("userId", id);
        paramap.put("classId", classId);
        TradeDetail obj = (tradeDetailService.find(paramap)).get(0);
        obj.setClassState(0);
        tradeDetailService.update(obj);
        return "redirect:/basicinfo/classs/toupdate.action?id=" + classId;
    }

    // 查看
    @RequestMapping("/basicinfo/classs/toview.action")
    public String toview(String id, Model model, HttpServletRequest request) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("classsid", id);
        ClassTradetail obj = teacherCourseService.getclassStudent(paramap);
        model.addAttribute("obj", obj);

        // String erweiPath = "images\\erwei\\" + obj.getId() + ".png";
        // System.out.println(request.getSession().getServletContext()
        // .getRealPath("/")
        // + erweiPath);
        //
        // QRCode.QRCodeCreate(MyWxPayConfig.PRE_URL
        // + "/phone/user/basicinfo/attendance/insert.action?id=" + id,
        // request.getSession().getServletContext().getRealPath("/")
        // + erweiPath, 7, request.getSession()
        // .getServletContext().getRealPath("/")
        // + "images/gzh.jpg");
        // model.addAttribute("erwei", erweiPath);
        return "/basicinfo/classs/jClasssView.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/classs/start.action")
    public String start(@RequestParam("id") String[] ids) {
        classsService.start(ids);

        return "redirect:/basicinfo/classs/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/classs/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        classsService.stop(ids);

        return "redirect:/basicinfo/classs/list.action";
    }

    // 删除班级
    @RequestMapping("/basicinfo/classs/classdelete.action")
    public String delete(@RequestParam("id") String[] ids) {
        classsService.delete(ids);
        return "redirect:/basicinfo/classs/list.action";
    }

    // 插班级
    @RequestMapping("/basicinfo/classs/classinsert.action")
    public String classinsert(String id, Model model) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("classsid", id);
        ClassTradetail obj = teacherCourseService.getclassStudent(paramap);
        model.addAttribute("obj", obj);
        System.out.println("courseid" + obj.getTradeDetails().get(0).getCourseId());
        if (obj.getTradeDetails().get(0).getCourseId() != null && obj.getTradeDetails().get(0).getCourseId() != "") {
            Map<String, String> paramap1 = new HashMap<>();
            paramap1.put("courseid", obj.getTradeDetails().get(0).getCourseId());
            paramap1.put("tradeState", "0");
            paramap1.put("classState", "0");
            List<TradeDetail> list = tradeDetailService.find(paramap1);

            model.addAttribute("dataList", list);

            return "/basicinfo/classs/jClasssInsert.jsp";
        } else {
            return "/basicinfo/classs/noInsert.jsp";
        }

    }

    // 确认插班级
    @RequestMapping("/basicinfo/classs/sureinsert.action")
    public String sureinsert(HttpServletRequest request, String id, String classsid, Model model) {
        System.out.println("[" + id + "]");
        System.out.println("[" + classsid + "]");
        TradeDetail td = tradeDetailService.get(id);
        td.setClassState(1);
        td.setClassId(classsid);
        tradeDetailService.update(td);

        String access_token = GetAcToken.getAcToken(request.getSession(), wxPayService);

        // System.out.println("access_token:" + access_token);
        if (null != access_token) {
            Classs classs = classsService.get(classsid);
            String[] data = {"上课地点：" + classs.getClassAddress(), classs.getClassTime(), classs.getClassName(),
                    "请去个人中心>我的课程>待确认 确认是否参加开课班级或者直接点击本通知"};
            Map<String, String> paramap = new HashMap<>();
            paramap.put("id", td.getUserId());
            Student stu = studentService.find(paramap).get(0);
            String openid = stu.getUserOpenid();
            if (openid != null) {
                Template.notifyClass(MyWxPayConfig.HTTP_URL + "/phone/user/myclass.action?state=1", access_token,
                        openid, data);
            }
        }

        return "redirect:/basicinfo/classs/classinsert.action?id=" + classsid;
    }

    // 管理员取消确认
    @RequestMapping("/basicinfo/classs/quitconfirm.action")
    public String quitconfirm(String id, String classsid, Model model) {
        System.out.println("[" + id + "]");
        System.out.println("[" + classsid + "]");
        TradeDetail td1 = tradeDetailService.get(id);
        td1.setClassState(0);
        td1.setTradeState(0);
        td1.setClassId("");
        tradeDetailService.update(td1);
        TradeDetail obj = tradeDetailService.get(id);
        TradeVO tradeVO = tradeService.view(obj.getTradeId());
        boolean b = true;
        if (tradeVO.getState().equals(5)) {
            b = true;
        }
        if (b) {
            Map<String, String> map = new HashMap<>();
            map.put("id", tradeVO.getId());
            map.put("state", "0");
            tradeService.updateState(map);
        }

        return "redirect:/basicinfo/classs/classinsert.action?id=" + classsid;
    }

    // 管理员帮助确认
    @RequestMapping("/basicinfo/classs/confirm.action")
    public String confirm(String id, Model model, String classsid) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("id", id);
        paramap.put("state", "4");
        tradeDetailService.updateState(paramap);
        TradeDetail obj = tradeDetailService.get(id);
        TradeVO tradeVO = tradeService.view(obj.getTradeId());
        boolean b = true;
        for (TradeDetail td : tradeVO.getTradedetails()) {
            if (td.getTradeState() != 4) {
                b = false;
                break;
            }
        }
        if (b) {
            Map<String, String> map = new HashMap<>();
            map.put("id", tradeVO.getId());
            map.put("state", "5");
            tradeService.updateState(map);
        }

        return "redirect:/basicinfo/classs/classinsert.action?id=" + classsid;

    }

}
