package cn.itcast.jk.controller.basicinfo.phoneclasss;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RefundService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.vo.PerCourseVO;
import cn.itcast.jk.vo.TradeVO;

@Controller
public class PhoneClasssController extends BaseController {
    @Resource
    ClasssService classsService;
    @Resource
    RefundService refundService;
    @Resource
    TradeDetailService tradeDetailService;
    @Resource
    TradeService tradeService;
    @Resource
    CourseService courseService;
    @Resource
    StudentService studentService;

    // 列表
    @RequestMapping("/phone/user/basicinfo/class/list.action")
    public
    @ResponseBody
    String list(HttpSession session, String state) {
        System.out.println(state + "hjk");
        Student student = (Student) session.getAttribute("user");
        Map<String, String> paramap = new HashMap<>();
        paramap.put("id", student.getId());
        if (state.equals("0")) {
            paramap.put("tradeState", "0");
            paramap.put("classState", "0");

        } else if (state.equals("1")) {
            paramap.put("tradeState", "0");
            paramap.put("classState", "1");

        } else {
            paramap.put("tradeState", "4");
            paramap.put("classState", "1");
        }

        List<PerCourseVO> dataList = tradeDetailService.getpersonal(paramap);

        JSONArray jsonObject = JSONArray.fromObject(dataList);

        return jsonObject.toString();
    }

    // 查看
    @RequestMapping("/phone/user/basicinfo/class/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        Map<String, String> paramap = new HashMap<>();
        paramap.put("detailid", id);
        List<PerCourseVO> data = tradeDetailService.getpersonal(paramap);
        JSONArray jsonObject = JSONArray.fromObject(data);

        return jsonObject.toString();
    }

    // 准备退课
    @RequestMapping("/phone/user/basicinfo/class/waitclass.action")
    public String waitclass(String id, String tradedetailid, Model model) {
        Course obj = courseService.get(id);
        model.addAttribute("course", obj);
        model.addAttribute("tradedetailid", tradedetailid);
        return "/basicinfo/course/PhCourseDetailreturn.jsp";
    }

    // 未开班退课程
    @RequestMapping("/phone/user/basicinfo/class/returncourse.action")
    public String returncourse(HttpSession session, String id) {
        // System.out.println("detailId:\t" + id);
        // Map<String, String> paramap = new HashMap<>();
        // paramap.put("tradeState", "0");
        // paramap.put("classState", "0");
        // paramap.put("courseId", id);
        // paramap.put("userId", ((Student)
        // session.getAttribute("user")).getId());
        // TradeDetail tradeDetail = tradeDetailService.find(paramap).get(0);
        // tradeDetail.setClassState(0);
        // tradeDetail.setClassId("");
        // tradeDetailService.update(tradeDetail);
        TradeDetail tradeDetail = tradeDetailService.get(id);
        if (tradeDetail.getClassState() == 0 && tradeDetail.getTradeState() == 0) {
            TradeDetail td1 = new TradeDetail();
            td1.setId(id);
            td1.setTradeState(3);
            tradeDetailService.update(td1);

            TradeVO tradeVO = tradeService.view(tradeDetail.getTradeId());
            boolean f = true;
            for (TradeDetail td : tradeVO.getTradedetails()) {
                if (td.getTradeState() != 3) {
                    f = false;
                    break;
                }
            }
            Map<String, String> map = new HashMap<>();
            map.put("id", tradeDetail.getTradeId());
            if (f) {
                map.put("state", "4");
            } else {
                map.put("state", "3");
            }
            map.put("fmoney", tradeVO.getFmoney() - tradeVO.getTradedetails().get(0).getClassprice() + "");
            tradeService.updateState(map);

            Student student = studentService.get(tradeVO.getPayUserOpenid());
            Student student2 = (Student) session.getAttribute("user");
            Refund refund = new Refund();
            refund.setId(UUID.randomUUID().toString());
            refund.setMoney(tradeVO.getTradedetails().get(0).getClassprice());
            refund.setTradeDetailId(td1.getId());
            refund.setApplyTime(new Date());
            refund.setAreaId(student2.getAreaId());
            refund.setAreaName(student2.getAreaName());
            refund.setTradeId(tradeDetail.getTradeId());
            refund.setRefundUserId(student2.getId());
            refund.setRefundUserName(student2.getUserName());
            refund.setRefundUserOpenid(student2.getUserOpenid());
            refund.setState(1);

            refundService.insert(refund);
            String temp = new DecimalFormat("######0.00")
                    .format(student.getAvailableAssets() + tradeVO.getTradedetails().get(0).getClassprice());
            student.setAvailableAssets(Double.parseDouble(temp));
            studentService.update(student);
            if (student2.getUserOpenid().equals(student.getUserOpenid()))

            // student.setAvailableAssets(student.getAvailableAssets()+tradeVO.getTradedetails().get(0).getClassprice());
            {
                session.setAttribute("user", student);
            }

            return "redirect:/phone/user/myclass.action?state=0";
        } else if (tradeDetail.getClassState() == 1 && tradeDetail.getTradeState() == 0) {
            return "/basicinfo/phoneuser/ykb.jsp";//已开班，先退班，后退钱
        } else {

            return "/basicinfo/phoneuser/flush.jsp";
        }
    }

    // 确认课
    @RequestMapping("/phone/user/basicinfo/class/checkclass.action")
    public
    @ResponseBody
    String checkclass(HttpSession session, String id) {
        System.out.println("id" + id);
        Map<String, String> paramap = new HashMap<>();
        paramap.put("detailid", id);
        List<PerCourseVO> dataList = tradeDetailService.getpersonal(paramap);
        JSONObject jsonObject = JSONObject.fromObject(dataList.get(0));
        return jsonObject.toString();
    }

    // 确认课
    @RequestMapping("/phone/user/basicinfo/class/joinclass.action")
    public String joinclass(HttpSession session, String state, String id) {
        TradeDetail obj = tradeDetailService.get(id);
        if (obj.getClassState() == 1 && obj.getTradeState() == 0) {

            if (state.equals("0")) {

                obj.setClassState(0);
                obj.setClassId("");

                tradeDetailService.update(obj);
                TradeVO tradeVO = tradeService.view(obj.getTradeId());
                boolean b = true;
                for (TradeDetail td : tradeVO.getTradedetails()) {
                    if (td.getClassState() != 0) {
                        b = false;
                        break;
                    }
                }
                if (b) {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", tradeVO.getId());
                    map.put("state", "0");
                    tradeService.updateState(map);
                }

                return "redirect:/phone/user/myclass.action?state=0";
            } else {
                Map<String, String> paramap = new HashMap<>();
                paramap.put("id", id);
                paramap.put("state", "4");
                tradeDetailService.updateState(paramap);

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
                return "redirect:/phone/user/myclass.action?state=2";

            }
        } else {
            return "/basicinfo/phoneuser/flush.jsp";
        }
    }

}
