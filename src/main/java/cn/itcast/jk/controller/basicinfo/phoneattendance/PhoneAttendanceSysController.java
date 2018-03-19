package cn.itcast.jk.controller.basicinfo.phoneattendance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Attendance;
import cn.itcast.jk.service.AttendanceService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneAttendanceSysController extends BaseController {
    @Resource
    AttendanceService attendanceService;

    // 列表
    @RequestMapping("/phone/sys/basicinfo/attendance/list.action")
    public String list(Model model) {
        List<Attendance> dataList = attendanceService.find(null);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/attendance/jAttendanceList.jsp"; // 转向页面
    }

    // 转向新增页面
    @RequestMapping("/phone/sys/basicinfo/attendance/tocreate.action")
    public String tocreate() {
        return "/basicinfo/attendance/jAttendanceCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/phone/sys/basicinfo/attendance/insert.action")
    public String insert(Attendance attendance) {

        return "redirect:/basicinfo/attendance/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/phone/sys/basicinfo/attendance/toupdate.action")
    public String toupdate(String id, Model model) {
        Attendance obj = attendanceService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/attendance/jAttendanceUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/sys/basicinfo/attendance/update.action")
    public String update(Attendance attendance) {
        attendanceService.update(attendance);

        return "redirect:/phone/sys/basicinfo/attendance/list.action";
    }

    // 查看
    @RequestMapping("/phone/sys/basicinfo/attendance/toview.action")
    public String toview(String id, Model model) {
        Attendance obj = attendanceService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/attendance/jAttendanceView.jsp";
    }

}
