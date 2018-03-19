package cn.itcast.jk.controller.basicinfo.transfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.domain.Transfer;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.TransferService;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class TransferController extends BaseController {
    @Resource
    TransferService transferService;

    // 列表

    @RequestMapping("/basicinfo/transfer/list.action")
    public String list(HttpSession session, Model model) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("23"))//
            {
                Map<String, String> map = new HashMap<>();
                map.put("areaId", sysUserVO.getAreaId() + "");
                List<Transfer> dataList = transferService.find(map);
                model.addAttribute("dataList", dataList);
                return "/basicinfo/transfer/jTransferListf.jsp";

            } else if (r.getSroleId().equals("24"))//
            {
                List<Transfer> dataList = transferService.find(null);
                model.addAttribute("dataList", dataList);
                return "/basicinfo/transfer/jTransferListz.jsp";

            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";


    }

    // 查询单个记录
    @RequestMapping("/basicinfo/transfer/toview.action")
    public String toview(String id, Model model) {
        Transfer transfer = transferService.get(id);
        model.addAttribute("obj", transfer);
        return "/basicinfo/transfer/jTransferView.jsp";
    }

}
