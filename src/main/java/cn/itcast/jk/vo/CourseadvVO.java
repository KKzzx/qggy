package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.domain.Course;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月11日
 */
public class CourseadvVO extends Course implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7090208152583710317L;
    private List<Advertisement> advertisements; // 广告的集合

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setCourses(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

}
