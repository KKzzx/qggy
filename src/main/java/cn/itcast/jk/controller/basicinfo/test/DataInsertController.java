package cn.itcast.jk.controller.basicinfo.test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.domain.Category;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.Rank;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.service.AreaService;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.service.RankService;
import cn.itcast.jk.service.StudentService;
import cn.itcast.jk.service.SysUserService;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.service.TradeService;

import com.github.wxpay.sdk.WXPayUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Controller
public class DataInsertController extends BaseController {
    @Resource
    RankService rankService;
    @Resource
    CategoryService categoryService;
    @Resource
    AreaService areaService;

    @Resource
    CourseService courseService;
    @Resource
    ClasssService classsService;
    @Resource
    TradeService tradeService;
    @Resource
    TradeDetailService tradeDetailService;
    @Resource
    StudentService studentService;
    @Resource
    SysUserService sysUserService;

    static String words = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄曲家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴鬱胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍卻璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐钟离宇文长孙慕容鲜于闾丘司徒司空丌官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓跋夹谷宰父谷梁晋楚闫法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况郈有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓终";
    static String nums = "70667670624059264366051107214656378549157842316207485069439893786410079";
    static Random r = new Random();

    // 列表
    @RequestMapping("/basicinfo/test/list.action")
    public String list() {

        return "/basicinfo/test/jTestView.jsp";
    }

    // 增加学员
    @RequestMapping("/basicinfo/test/addstudent.action")
    public String addstudent() {
        List<Area> areas = areaService.find(null);
        Student xp = new Student();
        xp.setId(UUID.randomUUID().toString());
        int t = r.nextInt(550);
        xp.setUserName(words.substring(t, t + 1 + r.nextInt(4)));
        t = r.nextInt(60);
        xp.setPhoneNumber(nums.substring(t, t + 11));
        t = r.nextInt(60);
        xp.setEmail(nums.substring(t, t + 5) + "@" + nums.substring(t, t + 3)
                + "com");
        t = r.nextInt(550);
        xp.setCompany(words.substring(t, t + 1 + r.nextInt(7)));
        xp.setSex(r.nextInt(2));
        t = r.nextInt(550);
        xp.setAdress(words.substring(t, t + 1 + r.nextInt(8)));
        t = r.nextInt(60);
        xp.setWeiXin(words.substring(t, t + 4) + nums.substring(t, t + 3));
        xp.setMarryd(r.nextInt(2));
        t = r.nextInt(530);
        xp.setUserOpenid(words.substring(t, t + 32));
        t = r.nextInt(areas.size());
        xp.setAreaId(areas.get(t).getId());
        xp.setAreaName(areas.get(t).getAreaName());
        t = r.nextInt(60);
        xp.setShenFen(nums.substring(t, t + 11));

        studentService.insert(xp);

        return "/basicinfo/test/jTestView.jsp"; // 转向页面
    }

    // 增加管理员
    @RequestMapping("/basicinfo/test/addsysuser.action")
    public String addsysuser() {
        List<Area> areas = areaService.find(null);
        SysUser xp = new SysUser();
        xp.setId(UUID.randomUUID().toString());
        int t = r.nextInt(550);
        xp.setName(words.substring(t, t + 1 + r.nextInt(4)));
        t = r.nextInt(60);
        xp.setPhonenumber(nums.substring(t, t + 11));
        t = r.nextInt(60);
        xp.setEmail(nums.substring(t, t + 5) + "@" + nums.substring(t, t + 3)
                + "com");
        t = r.nextInt(60);
        xp.setWeixin(words.substring(t, t + 4) + nums.substring(t, t + 3));
        xp.setSex(r.nextInt(2) + "");
        t = r.nextInt(areas.size());
        xp.setAreaId(areas.get(t).getId());
        xp.setAreaName(areas.get(t).getAreaName());
        xp.setPassword("000000");
        xp.setState(r.nextInt(2));

        sysUserService.insert(xp);

        return "/basicinfo/test/jTestView.jsp"; // 转向页面
    }

    // 增加管理员
    @RequestMapping("/basicinfo/test/addcourse.action")
    public String addcourse() {
        List<Area> areas = areaService.find(null);
        List<Rank> ranks = rankService.find(null);
        List<Category> categorys = categoryService.find(null);
        List<SysUser> syss = sysUserService.find(null);
        Course xp = new Course();
        int t = r.nextInt(550);
        xp.setCourseName(words.substring(t, t + 1 + r.nextInt(10)));
        t = r.nextInt(550);
        xp.setCourseAbstract(words.substring(t, t + 11));
        xp.setCoursePrice(0.01);
        t = r.nextInt(450);
        xp.setCourseContent(words.substring(t, t + 10 + r.nextInt(90)));
        t = r.nextInt(450);
        xp.setCourseRemark(words.substring(t, t + 10 + r.nextInt(10)));
        xp.setOpenNumber(16 + r.nextInt(5));
        xp.setState(r.nextInt(5));

        t = r.nextInt(syss.size());
        xp.setReleaseUserId(syss.get(t).getId());
        xp.setReleaseName(syss.get(t).getName());
        xp.setReleaseOpenId(syss.get(t).getOpenid());
        t = r.nextInt(areas.size());
        xp.setAreaId(areas.get(t).getId());
        xp.setAreaName(areas.get(t).getAreaName());
        t = r.nextInt(categorys.size());
        xp.setCategoryId(categorys.get(t).getId());
        xp.setCategoryName(categorys.get(t).getCategoryName());
        t = r.nextInt(ranks.size());
        xp.setRankId(ranks.get(t).getId());
        xp.setRankName(ranks.get(t).getRankName());
        boolean f = false;
        if (f) {
            t = r.nextInt(syss.size());
            xp.setfCheckId(syss.get(t).getId());
            xp.setfCheckUserName(syss.get(t).getName());
            xp.setfCheckOpenId(syss.get(t).getOpenid());
            t = r.nextInt(syss.size());
            xp.setzCheckId(syss.get(t).getId());
            xp.setzCheckUserName(syss.get(t).getName());
            xp.setzCheckOpenId(syss.get(t).getOpenid());
        }
        courseService.insert(xp);
        return "/basicinfo/test/jTestView.jsp"; // 转向页面
    }

    // 增加订单
    @RequestMapping("/basicinfo/test/addorder.action")
    public String addorder() {
        List<Area> areas = areaService.find(null);
        List<Student> stus = studentService.find(null);
        List<Course> cs = courseService.find(null);
        Area a = areas.get(r.nextInt(areas.size()));
        Course c = cs.get(r.nextInt(cs.size()));
        Trade xp = new Trade();
        xp.setId(UUID.randomUUID().toString());
        xp.setCategory(r.nextInt(3));
        int t = r.nextInt(stus.size());
        Student st = stus.get(t);
        int n = 1 + r.nextInt(5);
        Set<Integer> set = new HashSet<>();
        set.add(t);
        while (set.size() == n) {
            set.add(r.nextInt(stus.size()));
        }
        for (Integer i : set) {
            Student s = stus.get(i);
            TradeDetail td = new TradeDetail();
            td.setId(UUID.randomUUID().toString());
            td.setTradeId(xp.getId());
            td.setUserId(s.getId());
            td.setUserName(s.getUserName());
            td.setCourseId(c.getId() + "");
            td.setCourseName(c.getCourseName());
            td.setAreaId(c.getAreaId());
            td.setAreaName(c.getAreaName());
            td.setClassState(0);
            td.setTradeState(0);
            tradeDetailService.insert(td);
        }

        xp.setTotalFee(c.getCoursePrice() * n);
        xp.setPayUserId(st.getId());
        xp.setPayUserName(st.getUserName());
        xp.setPayUserOpenid(st.getUserOpenid());
        xp.setState(0);
        xp.setBankType("CFT");
        xp.setCashFee(c.getCoursePrice() * n);
        xp.setDeviceInfo("WEB");
        xp.setFeeType("CNY");
        xp.setOutTradeNo(WXPayUtil.generateNonceStr());
        xp.setTimeEnd((20170213 + r.nextInt(5000)) + "");
        xp.setTradeType("JSAPI");
        xp.setTransactionId(WXPayUtil.generateNonceStr());
        xp.setAreaId(a.getId());
        xp.setAreaName(a.getAreaName());
        tradeService.insert(xp);
        return "/basicinfo/test/jTestView.jsp";
    }

    // 增加考勤
    @RequestMapping("/basicinfo/test/addadddd.action")
    public String addadddd() {

        return "/basicinfo/test/jTestView.jsp";
    }
}
