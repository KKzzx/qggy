package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.CourseAdv;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.CourseVO;
import cn.itcast.jk.vo.CourseadvVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface CourseService {
    public List<Course> findPage(Page<?> page); // 分页查询

    public List<Course> find(Map<?, ?> paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

    public List<CourseVO> view(Map<?, ?> map);

    public Course get(Serializable id); // 只查询一个，常用于修改

    public void insert(Course Course); // 插入，用实体作为参数

    public void update(Course Course); // 修改，用实体作为参数

    public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

    public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID


    public CourseadvVO see(Map<?, ?> paraMap);

    public void addAdv(List<CourseAdv> courseAdvs); //课程加广告

    public void deleteAdv(Map<String, String> paramap);

    public void upstate(Course course);

    public void uprecommend(Map<String, Integer> paramap);
}
