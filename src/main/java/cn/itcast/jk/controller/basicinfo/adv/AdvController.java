package cn.itcast.jk.controller.basicinfo.adv;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.CourseAdv;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.service.AdvService;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RankService;
import cn.itcast.jk.vo.AdvVO;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class AdvController extends BaseController {
    @Resource
    CourseService courseService;
    @Resource
    RankService rankService;
    @Resource
    CategoryService categoryService;
    @Resource
    AdvService advService;

    // 列表
    @RequestMapping("/basicinfo/adv/list.action")
    public String list(Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        for (UrRo r : sysUserVO.getRoles()) {
            if (r.getSroleId().equals("1"))// 录入人员
            {
                Map<String, String> parmap = new HashMap<>();
                parmap.put("releaseUserId", sysUserVO.getId());
                List<Advertisement> dataList = advService.find(parmap);

                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/adv/jAdvListj.jsp";
            } else if (r.getSroleId().equals("2"))// 分部领导
            {
                Map<String, String> parmap = new HashMap<>();
                parmap.put("areaId", sysUserVO.getAreaId() + "");

                List<Advertisement> dataList = advService.find(parmap);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/adv/jAdvListf.jsp";
            } else if (r.getSroleId().equals("3"))// 总部领导
            {

                List<Advertisement> dataList = advService.find(null);
                model.addAttribute("dataList", dataList); // 将数据传递到页面

                return "/basicinfo/adv/jAdvListz.jsp";
            }
        }
        return "/baseinfo/error.jsp";

        // return "/basicinfo/adv/jAdvList.jsp"; // 转向页面
    }

    // 查看所有广告信息
    @RequestMapping("/basicinfo/adv/listall.action")
    public String listall(Model model, HttpSession session) {

        List<Advertisement> dataList = advService.find(null);

        model.addAttribute("dataList", dataList); // 将数据传递到页面

        return "/basicinfo/adv/jAdvList.jsp";
    }

    // 转向新增页面
    @RequestMapping("/basicinfo/adv/toCreate.action")
    public String tocreate(Model model, String id) {
        // 查询没有生成广告的课程
        Map<String, String> paramap = new HashMap<>();
        paramap.put("noggkc", "00");
        List<Course> dataList = courseService.find(paramap);
        model.addAttribute("dataList", dataList); // 将数据传递到页面
        return "/basicinfo/adv/jAdvCreate.jsp";
    }

    @RequestMapping("/basicinfo/adv/insertInnerAdv.action")
    public String insertAdvInner(@RequestParam("id") String[] ids, @RequestParam("adcategory") String adcategory,
                                 HttpSession session) {
        try {
            SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
            List<Advertisement> list = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                Advertisement advertisement = new Advertisement();
                advertisement.setAdcategory(Integer.parseInt(adcategory));
                advertisement.setAdvertiseId(Integer.parseInt(ids[i]));
                advertisement.setAreaId(sysUserVO.getAreaId() + "");
                advertisement.setAreaName(sysUserVO.getAreaName());
                advertisement.setReleaseName(sysUserVO.getName());
                advertisement.setReleaseOpenId(sysUserVO.getOpenid());
                advertisement.setReleaseTime(new Date());
                advertisement.setReleaseUserId(sysUserVO.getId());
                list.add(advertisement);
            }
            advService.insertList(list);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return "redirect:/basicinfo/adv/list.action"; // 转向列表的action
    }

    @RequestMapping("/basicinfo/adv/insertCroAdv.action")
    public String insertCroInner(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // System.out.println(ServletFileUpload.isMultipartContent(request));
        try {
            // 转型为MultipartHttpRequest：
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("advCover");
            // 获得文件名：
            // String fileName = file.getOriginalFilename();
            // rename file
            String localFileName = System.currentTimeMillis() + ".jpg";
            // 上传到images/cover目录下
            String path = request.getSession().getServletContext().getRealPath("/images/cover/" + localFileName);
            File localFile = new File(path); // 文件路径（路径+文件名）
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(localFile));
            int c;
            while ((c = bin.read()) != -1) {
                bout.write(c);
            }
            bout.flush();
            bout.close();
            bin.close();
            // System.out.println("上传文件的绝对地址是：" + localFile.getAbsolutePath());
            SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");

            Advertisement advertisement = new Advertisement();
            advertisement.setAreaId(sysUserVO.getAreaId() + "");
            advertisement.setAreaName(sysUserVO.getAreaName());
            advertisement.setReleaseName(sysUserVO.getName());
            advertisement.setReleaseOpenId(sysUserVO.getOpenid());
            advertisement.setReleaseTime(new Date());
            advertisement.setReleaseUserId(sysUserVO.getId());

            advertisement.setAdcategory(Integer.parseInt(multipartRequest.getParameter("adcategory")));
            advertisement.setAdvertiseCover(
                    localFile.getAbsolutePath().substring(localFile.getAbsolutePath().indexOf("\\images\\cover\\")));
            advertisement.setAdvertiseUrl(multipartRequest.getParameter("advUrl"));
            advertisement.setIsMain(Integer.parseInt(multipartRequest.getParameter("isMain")));

            // System.out.println(advertisement.toString());
            advertisement.setContent(multipartRequest.getParameter("content"));

            advService.insert(advertisement);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return "redirect:/basicinfo/adv/list.action"; // 转向列表的action
    }

    // 转向修改页面
    @RequestMapping("/basicinfo/adv/toupdate.action")
    public String toupdate(String id, Model model) {
        Advertisement obj = advService.get(id);
        if (obj.getAdcategory() == 1) {
            model.addAttribute("dataList", obj);
            return "/basicinfo/adv/jAdvUpdate.jsp";
        } else {

            return "redirect:/basicinfo/course/toupdate.action?id=" + obj.getAdvertiseUrl();

        }
    }

    // 修改保存
    @RequestMapping("/basicinfo/adv/update.action")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Advertisement advertisement = new Advertisement();
            // 转型为MultipartHttpRequest：
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            // 获得文件：
            MultipartFile file = multipartRequest.getFile("advCover");
            // 获得文件名：
            String fileName = file.getOriginalFilename();
            // System.out.println(StringUtils.isBlank(fileName));
            if (!StringUtils.isBlank(fileName)) {
                // rename file
                String localFileName = System.currentTimeMillis() + ".jpg";
                // 上传到images/cover目录下
                String path = request.getSession().getServletContext().getRealPath("/images/cover/" + localFileName);
                File localFile = new File(path); // 文件路径（路径+文件名）
                if (!localFile.exists()) {
                    localFile.createNewFile();
                }
                BufferedInputStream bin = new BufferedInputStream(file.getInputStream());
                BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(localFile));
                int c;
                while ((c = bin.read()) != -1) {
                    bout.write(c);
                }
                bout.flush();
                bout.close();
                bin.close();
                // System.out.println("上传文件的绝对地址是：" +
                // localFile.getAbsolutePath());
                advertisement.setAdvertiseCover(localFile.getAbsolutePath()
                        .substring(localFile.getAbsolutePath().indexOf("\\images\\cover\\")));
            }
            advertisement.setAdvertiseId(Integer.parseInt(multipartRequest.getParameter("advertiseId")));
            advertisement.setAdcategory(Integer.parseInt(multipartRequest.getParameter("adcategory")));
            advertisement.setAdvertiseUrl(multipartRequest.getParameter("advUrl"));
            advertisement.setIsMain(Integer.parseInt(multipartRequest.getParameter("isMain")));
            advertisement.setContent(multipartRequest.getParameter("content"));
            // System.out.println("content" + advertisement.getContent());
            Advertisement advertisement2 = advService.get(advertisement.getAdvertiseId());
            if (advertisement2.getAdstate() == 3) {
                advertisement.setAdstate(2);
            }

            advService.update(advertisement);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return "redirect:/basicinfo/adv/list.action";
    }

    // 查看
    @RequestMapping("/basicinfo/adv/toview.action")
    public String toview(String id, Model model) {
        HashMap<String, String> paraMap = new HashMap<>();
        paraMap.put("id", id);
        AdvVO obj = advService.view(paraMap);
        model.addAttribute("obj", obj);

        return "/basicinfo/adv/jAdvView.jsp";
    }

    // 转向广告增加课程页面
    @RequestMapping("/basicinfo/adv/toaddcourse.action")
    public String toaddcourse(Model model, String id) {
        model.addAttribute("advertiseId", id);
        Map<String, String> pMap = new HashMap<String, String>();
        pMap.put("id", id);
        AdvVO obj = advService.view(pMap);
        model.addAttribute("content", obj.getContent());

        List<Course> dataList = courseService.find(null);

        for (int j = 0; j < obj.getCourses().size(); j++) {
            for (int i = 0; i < dataList.size(); i++) {

                if (dataList.get(i).getId() == obj.getCourses().get(j).getId()) {
                    dataList.remove(i);
                    break;
                }
            }
        }

        model.addAttribute("dataList", dataList);
        // System.out.println(obj.getCourses().size());
        // if(obj.getCourses().get(0).getId()==0)
        // obj=null;
        model.addAttribute("obj", obj);
        // 将数据传递到页面

        return "/basicinfo/adv/jAdvAddcourse.jsp";
    }

    @RequestMapping("/basicinfo/adv/addCourse.action")
    public String addCourse(@RequestParam("id") String[] ids, @RequestParam("advertiseId") String advertiseId,
                            Model model) {
        // System.out.println("tianjia");
        List<CourseAdv> courseAdvs = new ArrayList<>(ids.length);
        for (int i = 0; i < ids.length; i++) {
            CourseAdv courseAdv = new CourseAdv();
            courseAdv.setCourseId(Integer.parseInt(ids[i]));
            courseAdv.setAdvertiseId(Integer.parseInt(advertiseId));
            courseAdvs.add(courseAdv);
        }
        courseService.addAdv(courseAdvs);
        // 将数据传递到页面
        return "redirect:/basicinfo/adv/toaddcourse.action?id=" + advertiseId;
    }

    @RequestMapping("/basicinfo/adv/upstate.action")
    public String upstate(int id, int state, Model model, HttpSession session) {
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
        Advertisement advertisement = new Advertisement();
        if (state == 3) {// 总部审核通过
            advertisement.setAdvertiseId(id);
            advertisement.setzCheckId(sysUserVO.getId());
            advertisement.setzCheckOpenId(sysUserVO.getOpenid());
            advertisement.setzCheckTime(new Date());
            advertisement.setzCheckUserName(sysUserVO.getName());
            advertisement.setAdstate(3);
        } else if (state == 2) {// 分部审核通过
            advertisement.setAdvertiseId(id);
            advertisement.setfCheckId(sysUserVO.getId());
            advertisement.setfCheckOpenId(sysUserVO.getOpenid());
            advertisement.setfCheckTime(new Date());
            advertisement.setfCheckUserName(sysUserVO.getName());
            advertisement.setAdstate(2);
        } else if (state == 4) {// 基础人员停课
            advertisement.setAdvertiseId(id);
            advertisement.setAdstate(4);
        } else if (state == 1) {// 基础人员上报
            advertisement.setAdvertiseId(id);
            advertisement.setAdstate(1);
        }
        advService.update(advertisement);
        return "redirect:/basicinfo/adv/list.action";

    }

    @RequestMapping("/basicinfo/adv/toupisMain.action")
    public String toupisMain(int id, int isMain, Model model) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertiseId(id);
        advertisement.setIsMain(isMain);
        advService.toupisMain(advertisement);
        return "redirect:/basicinfo/adv/list.action";

    }

}
