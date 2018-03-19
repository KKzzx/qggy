package cn.itcast.jk.controller.basicinfo.phoneattendance;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Attendance;
import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.AttendanceService;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneAttendanceController extends BaseController {
    @Resource
    AttendanceService attendanceService;
    @Resource
    ClasssService classsService;

    // 会员查看个人考勤记录
    @RequestMapping("/phone/user/basicinfo/attendance/list.action")
    public
    @ResponseBody
    String list(HttpSession session) {
        Student s = (Student) session.getAttribute("user");
        Map<String, String> map = new HashMap<>();
        map.put("userid", s.getId());
        List<Attendance> dataList = attendanceService.find(map);
        System.out.println(dataList.get(0).getSaocodeTime().toString());
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);

        return jsonObject.toString();
    }

    // 新增保存
    @RequestMapping("/phone/user/basicinfo/attendance/insert.action")
    public String insert(HttpServletRequest request, String id) {

        Classs classs = classsService.get(id);
        Attendance attendance = new Attendance();
        attendance.setId(OrderUtil.getOrderNo());
        attendance.setSaocodeTime(new Date());
        Student student = (Student) request.getSession().getAttribute("user");
        attendance.setUserId(student.getId());
        attendance.setUserName(student.getUserName());
        attendance.setUserOpenid(student.getUserOpenid());
        attendance.setClassId(classs.getId());
        attendance.setClassName(classs.getClassName());
        Map<String, String> map = new HashMap<>();
        map.put("userid", student.getId());
        map.put("classId", id);
        List<Attendance> list = attendanceService.find(map);
        if (list != null && list.size() > 0) {
            DateFormat df1 = DateFormat.getDateInstance();
            if (!df1.format(new Date()).equals(df1.format(list.get(0).getSaocodeTime()))) {
                attendanceService.insert(attendance);
                return "/basicinfo/attendance/qdcg.jsp";
            } else {
                return "/basicinfo/attendance/cfqd.jsp";
            }

        } else {
            attendanceService.insert(attendance);
            return "/basicinfo/attendance/qdcg.jsp";
        }

    }

    // 查看
    @RequestMapping("/phone/user/basicinfo/attendance/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        Attendance obj = attendanceService.get(id);

        JSONArray jsonObject = JSONArray.fromObject(obj);

        return jsonObject.toString();
    }


}
