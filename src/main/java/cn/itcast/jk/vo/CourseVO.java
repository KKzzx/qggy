package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Course;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月11日
 */
public class CourseVO implements Serializable {
    private List<Course> courses; // 货物的集合
    private String id; // 将主键都映射成id
    private String categoryName;
    private String categoryRemark;
    private int categorySort;

    public String getCategoryRemark() {
        return categoryRemark;
    }

    public void setCategoryRemark(String categoryRemark) {
        this.categoryRemark = categoryRemark;
    }

    public String getCategoryState() {
        return categoryState;
    }

    public void setCategoryState(String categoryState) {
        this.categoryState = categoryState;
    }

    private String categoryState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

}
