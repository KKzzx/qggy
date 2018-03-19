package cn.itcast.jk.controller.basicinfo.phonetradedetail;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TradeDetailService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneTradeDetailController extends BaseController {
    @Resource
    TradeDetailService tradedetailService;
    @Resource
    ClasssService classsService;
    @Resource
    StudentService studentService;

    // 列表
    @RequestMapping("/phone/user/basicinfo/tradedetail/list.action")
    public
    @ResponseBody
    String list(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("tradeId", id);
        List<TradeDetail> dataList = tradedetailService.find(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);
        System.out.println(jsonObject.toString());
        return jsonObject.toString(); // 转向页面
    }

    // 新增保存
    @RequestMapping("/phone/user/basicinfo/tradedetail/insert.action")
    public String insert(@RequestParam("id") String id, Classs classs) {
        System.out.println(classs.getId());
        classs.setId(id);
        classsService.update(classs);
        return "redirect:/phone/user/basicinfo/classs/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/phone/user/basicinfo/tradedetail/toupdate.action")
    public String toupdate(String id, Model model) {
        TradeDetail obj = tradedetailService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/tradedetail/jTradeDetailUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/phone/user/basicinfo/tradedetail/update.action")
    public String update(TradeDetail tradedetail) {
        tradedetailService.update(tradedetail);

        return "redirect:/phone/user/basicinfo/tradedetail/list.action";
    }

    // 查看
    @RequestMapping("/phone/user/basicinfo/tradedetail/toview.action")
    public String toview(String id, Model model) {
        TradeDetail obj = tradedetailService.get(id);
        model.addAttribute("obj", obj);

        return "/phone/user/basicinfo/tradedetail/jTradeDetailView.jsp";
    }

    // 修改订单中的手机号和姓名
    @RequestMapping("/phone/user/basicinfo/tradedetail/updatetradedetail.action")
    public
    @ResponseBody
    String updatetradedetail(TradeDetail tradedetail) throws UnsupportedEncodingException {
        System.out.println("修改");
        TradeDetail tDetail = tradedetailService.get(tradedetail.getId());
        System.out.println(tradedetail.getUserName());
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("phoneNumber", tDetail.getUserPhone());
        paraMap.put("userName", tDetail.getUserName());

        List<Student> ss = studentService.find(paraMap);
        System.out.println(ss.get(0).getUserOpenid());
        System.out.println(ss != null);
        if (ss != null && ss.get(0).getUserOpenid() != null && !ss.get(0).getUserOpenid().equals("")) {//账户已经使用
            System.out.println("无法修改");
            return "123";
        } else {
            System.out.println("可以修改");
            Map<String, String> paraMap1 = new HashMap<String, String>();
            paraMap1.put("phoneNumber", tradedetail.getUserPhone());
            paraMap1.put("userName", tradedetail.getUserName());
            List<Student> ss1 = studentService.find(paraMap1);
            if (ss1 != null && ss1.size() > 0) {
                System.out.println("已注册");
                studentService.deleteById(tDetail.getUserId());
                tradedetail.setUserId(ss1.get(0).getId());
                tradedetailService.updatePhoneandname(tradedetail);
            } else {
                System.out.println("未注册");
                tradedetailService.updatePhoneandname(tradedetail);
                Student s = ss.get(0);
                s.setUserName(tradedetail.getUserName());
                s.setPhoneNumber(tradedetail.getUserPhone());

                studentService.update(s);


            }


        }
        System.out.println(tradedetail.getUserName());

        JSONArray jsonObject = JSONArray.fromObject(tradedetail);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();

    }

    @RequestMapping("/phone/public/basicinfo/tradedetail/nomodify.action")
    public String nomodify() {


        return "/basicinfo/tradedetail/nomodify.jsp";
    }

}
