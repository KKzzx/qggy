package cn.itcast.jk.controller.basicinfo.attendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AttendanceController extends BaseController {
    @Resource
    AttendanceService attendanceService;

    // 列表
    @RequestMapping("/basicinfo/attendance/list.action")
    public String list(Model model, String userName) {
        Map<String, String> paramap = new HashMap<>();
        if (null != userName && !"".equals(userName)) {
            paramap.put("userName", userName);
        }
        List<Attendance> dataList = attendanceService.find(paramap);
        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/attendance/jAttendanceList.jsp"; // 转向页面
    }

    // 查看
    @RequestMapping("/basicinfo/attendance/toview.action")
    public String toview(String id, Model model) {
        Attendance obj = attendanceService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/attendance/jAttendanceView.jsp";
    }

}
