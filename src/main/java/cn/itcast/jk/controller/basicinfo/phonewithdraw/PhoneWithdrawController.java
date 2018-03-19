package cn.itcast.jk.controller.basicinfo.phonewithdraw;

import java.text.DecimalFormat;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPayUtil;
import com.mchange.v2.csv.MalformedCsvException;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.Withdraw;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.WithdrawService;
import cn.itcast.util.OrderUtil;

@Controller
public class PhoneWithdrawController extends BaseController {
    @Resource
    WithdrawService withdrawService;
    @Resource
    StudentService studentService;

    // 查看个人提现记录
    @RequestMapping("/phone/user/basicinfo/withdraw/list.action")
    public
    @ResponseBody
    String list(HttpSession session, int state) {
        Student s = (Student) session.getAttribute("user");
        Map<String, String> map = new HashMap<>();
        map.put("userid", s.getId());
        if (state == -1) {

        } else if (state == 0) {
            map.put("nostate", "2");
        } else if (state == 1) {
            map.put("state", "2");
        }
        List<Withdraw> dataList = withdrawService.find(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();

    }

    // 查看
    @RequestMapping("/phone/user/basicinfo/withdraw/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        System.out.println(id);
        Withdraw obj = withdrawService.get(id);

        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(obj, config);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();

    }

    @RequestMapping("/phone/user/basicinfo/withdraw/getmoneyapply.action")
    public String getmoneyapply(HttpServletRequest request, double money) {
        // openid
        if (money > 1) {
            Student student = (Student) request.getSession().getAttribute("user");
            String partner_trade_no = OrderUtil.getOrderNo();
            Withdraw withdraw = new Withdraw();
            withdraw.setId(partner_trade_no);
            withdraw.setUserId(student.getId());
            withdraw.setUserName(student.getUserName());
            withdraw.setUserOpenId(student.getUserOpenid());
            withdraw.setMoney(money);
            withdraw.setWithdrawTime(new Date());
            withdraw.setAreaId(student.getAreaId());
            withdraw.setAreaName(student.getAreaName());
            withdraw.setState(0);
            withdrawService.insert(withdraw);
            DecimalFormat df = new DecimalFormat("######0.00");
            // 更新现金余额
            String temp = df.format(student.getXianjin() - money);

            student.setXianjin(Double.parseDouble(temp));
            studentService.update(student);
            request.getSession().setAttribute("user", student);
            return "redirect:/phone/user/mywithdrawapply.action";
        } else {
            return "";
        }
    }

    @RequestMapping("/phone/user/mywithdrawapply.action")
    public String mywithdrawapply(HttpServletRequest request, Model model) {
        Student student = (Student) request.getSession().getAttribute("user");
        Map<String, String> map = new HashMap<>();
        map.put("userid", student.getId());
        map.put("state", "0");
        List<Withdraw> withdraw = withdrawService.find(map);
        model.addAttribute("obj", withdraw.get(0));
        return "/basicinfo/trade/withdrawapply.jsp";
    }

}
