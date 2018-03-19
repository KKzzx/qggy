package cn.itcast.jk.controller.basicinfo.phonetransfer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.JsonDateValueProcessor;
import cn.itcast.jk.domain.Transfer;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.TransferService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class PhoneTransferController extends BaseController {
    @Resource
    TransferService transferService;
    @Resource
    StudentService studentService;

    // 内部转账
    @RequestMapping("/phone/user/basicinfo/transfer/list.action")
    public
    @ResponseBody
    String list(HttpSession session, String state) {
        Student student = (Student) session.getAttribute("user");
        Map<String, String> map = new HashMap<>();
        map.put("transferUserid", student.getId());
        List<Transfer> dataList = transferService.find(map);
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(dataList, config);

        return jsonObject.toString();
    }

    @RequestMapping("/phone/user/basicinfo/transfer/insert.action")
    public String insertapply(HttpSession session, double money, Model model) {

        Student student = (Student) session.getAttribute("user");

        DecimalFormat df = new DecimalFormat("######0.00");
        // 更新课程余额
        if (student.getAvailableAssets() - money < 0) {
            return "/basicinfo/phoneuser/error.jsp"; // 转向列表的action
        }
        String temp = df.format(student.getAvailableAssets() - money);

        student.setAvailableAssets(Double.parseDouble(temp));
        // 更新现金余额
        temp = df.format(student.getXianjin() + money * 0.97);
        student.setXianjin(Double.parseDouble(temp));

        Transfer transfer = new Transfer();
        transfer.setId(UUID.randomUUID().toString());
        transfer.setTransferUserid(student.getId());
        transfer.setTransferUsername(student.getUserName());
        transfer.setTransferUseropenid(student.getUserOpenid());
        transfer.setTransferMoney(money);

        transfer.setTransferCommission(Double.parseDouble(df.format(money * 0.03)));
        transfer.setTransferCash(Double.parseDouble(df.format(money * 0.97)));
        transfer.setAreaId(student.getAreaId());
        transfer.setAreaName(student.getAreaName());
        transferService.insert(transfer);
        studentService.update(student);
        // 更新session中user
        session.setAttribute("user", student);

        return "redirect:/phone/user/basicinfo/transfer/toviewone.action?id=" + transfer.getId(); // 转向列表的action
    }

    @RequestMapping("/phone/user/basicinfo/transfer/toviewone.action")
    public String toviewone(String id, Model model, HttpSession session) {
        Transfer transfer = transferService.get(id);
        model.addAttribute("transfer", transfer);
        Student student = (Student) session.getAttribute("user");
        DecimalFormat df = new DecimalFormat("######0.00");
        // 更新课程余额
        String availableAssets = df.format(student.getAvailableAssets() + transfer.getTransferMoney());
        model.addAttribute("availableAssets", availableAssets);
        String xianjin = df.format(student.getXianjin() - transfer.getTransferCash());
        model.addAttribute("xianjin", xianjin);
        return "/basicinfo/phoneuser/suretiqutoxianjin.jsp"; // 转向列表的action

    }

    // 查询单个
    @RequestMapping("/phone/user/basicinfo/transfer/toview.action")
    public
    @ResponseBody
    String toview(String id) {
        Transfer refund = transferService.get(id);

        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(refund, config);

        return jsonObject.toString();
    }
}
