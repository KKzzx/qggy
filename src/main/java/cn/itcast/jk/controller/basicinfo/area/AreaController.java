package cn.itcast.jk.controller.basicinfo.area;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.vo.SysUserVO;


@Controller
public class AreaController extends BaseController {
    @Resource
    AreaService areaService;

    // 列表
    @RequestMapping("/basicinfo/area/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("10"))// 总部角色管理
            {
                List<Area> dataList = areaService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/area/jAreaList.jsp"; // 转向页面
            }
        }

        // 将数据传递到页面

        return "/baseinfo/error.jsp";

    }

    // 列表
    @RequestMapping(value = "/public/basicinfo/area/getChild.action", method = {RequestMethod.POST})
    public
    @ResponseBody
    List<Area> getChild(@RequestBody Area area) {
        System.out.println(area.getParentId() + "-----");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("parentId", area.getParentId());
        List<Area> dataList = areaService.find(map);
        return dataList;
    }

    // 转向新增页面
    @RequestMapping("/basicinfo/area/tocreate.action")
    public String tocreate(Model model) {
        HashMap<String, Integer> firstAreaList = new HashMap<String, Integer>();
        firstAreaList.put("level", 1);
        List<Area> dataList = areaService.find(firstAreaList);
        model.addAttribute("firstAreaList", dataList); // 将数据传递到页面
        return "/basicinfo/area/jAreaCreate.jsp";
    }

    // 新增保存
    @RequestMapping("/basicinfo/area/insert.action")
    public String insert(Area area) {
        // 一级区域
        if (area.getAreaLevel() == 1) {
            area.setAreaCode(generateAreaCode(area));
            area.setParentId(0);
            areaService.insert(area);
        }
        if (area.getAreaLevel() == 2) {
            area.setParentId(Integer.parseInt(area.getFirstParent()));
            area.setAreaCode(generateAreaCode(area));
            area.setParentName("#");
            System.out.println(area.toString());
            areaService.insert(area);
        }
        if (area.getAreaLevel() == 3) {
            area.setParentId(Integer.parseInt(area.getSecondParent()));
            area.setAreaCode(generateAreaCode(area));
            area.setParentName("#");
            System.out.println(area.toString());
            areaService.insert(area);
        }
        return "redirect:/basicinfo/area/list.action"; // 转向列表的action
    }

    /**
     * @param areaLevel
     * @param parentId  根据传递过来的区域的等级生成code
     */
    private String generateAreaCode(Area area) {
        // firstParent为空，代表没有父级区域
        if (StringUtils.isBlank(area.getFirstParent()) && area.getAreaLevel() == 1) {

            String currMaxCode = areaService.queryCodeByLevel(area);
            // 003 000 000
            return currMaxCode.substring(0, 2) + (Integer.parseInt(currMaxCode.charAt(2) + "") + 1) + "000000";

            // 二级地域
        } else if (!StringUtils.isBlank(area.getFirstParent()) && area.getAreaLevel() == 2) {
            String currMaxCode = areaService.queryCodeByLevel(area);
            // 一级地域下没有任何二级地域，查找这个一级地域加一
            if (StringUtils.isBlank(currMaxCode)) {
                String parentCode = areaService.queryCodeById(area.getParentId());
                return parentCode.substring(0, 5) + (Integer.parseInt(parentCode.charAt(5) + "") + 1) + "000";
            } else {
                return currMaxCode.substring(0, 5) + (Integer.parseInt(currMaxCode.charAt(5) + "") + 1) + "000";
            }
        } else {
            String currMaxCode = areaService.queryCodeByLevel(area);
            // 二级地域下没有任何三级地域，查找这个二级父级地域加一
            if (StringUtils.isBlank(currMaxCode)) {
                String parentCode = areaService.queryCodeById(area.getParentId());
                return parentCode.substring(0, 8) + (Integer.parseInt(parentCode.charAt(8) + "") + 1);
            }
            return currMaxCode.substring(0, 8) + (Integer.parseInt(currMaxCode.charAt(8) + "") + 1);
        }
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/area/toupdate.action")
    public String toupdate(String id, Model model) {
        Area obj = areaService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/area/jAreaUpdate.jsp";
    }

    // 修改保存
    @RequestMapping("/basicinfo/area/update.action")
    public String update(Area area) {
        areaService.update(area);

        return "redirect:/basicinfo/area/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/area/toview.action")
    public String toview(String id, Model model) {
        Area obj = areaService.get(id);
        model.addAttribute("obj", obj);

        return "/basicinfo/area/jAreaView.jsp";
    }

    // 批量启用
    @RequestMapping("/basicinfo/area/start.action")
    public String start(@RequestParam("id") String[] ids) {
        areaService.start(ids);

        return "redirect:/basicinfo/area/list.action";
    }

    // 批量停用
    @RequestMapping("/basicinfo/area/stop.action")
    public String stop(@RequestParam("id") String[] ids) {
        areaService.stop(ids);

        return "redirect:/basicinfo/area/list.action";
    }
}
