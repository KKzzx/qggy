package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.domain.Course;

public class AdvVO extends Advertisement implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1633837476409162897L;
    private List<Course> courses; // 广告的集合

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


}
