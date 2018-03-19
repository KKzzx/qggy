package cn.itcast.jk.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.CourseAdv;
import cn.itcast.jk.vo.CourseVO;
import cn.itcast.jk.vo.CourseadvVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface CourseDao extends BaseDao<Course> {
    public void updateState(Map<?, ?> map); // 修改状态

    public List<CourseVO> view(Map<?, ?> map); // 查询某个课程

    public void addAdv(List<CourseAdv> courseAdvs);

    public CourseadvVO see(Map<?, ?> paraMap);

    public void deleteAdv(Map<String, String> paramap);

    public void upstate(Course course);

    public void uprecommend(Map<String, Integer> paramap);
}
