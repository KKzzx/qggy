package cn.itcast.jk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.CourseDao;
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
@Repository
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
    public CourseDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.CourseMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }

    @Override
    public List<CourseVO> view(Map<?, ?> map) {
        return super.getSqlSession().selectList(super.getNs() + ".view", map);
    }

    @Override
    public void addAdv(List<CourseAdv> courseAdvs) {
        super.getSqlSession().insert(super.getNs() + ".addAdv", courseAdvs);
    }

    @Override
    public CourseadvVO see(Map<?, ?> paraMap) {
        return super.getSqlSession().selectOne(super.getNs() + ".see", paraMap);
    }

    @Override
    public void deleteAdv(Map<String, String> paramap) {
        super.getSqlSession().delete(super.getNs() + ".deleteAdv", paramap);

    }

    @Override
    public void upstate(Course course) {
        // TODO Auto-generated method stub
        super.getSqlSession().update(super.getNs() + ".upstate", course);

    }

    @Override
    public void uprecommend(Map<String, Integer> paramap) {
        // TODO Auto-generated method stub
        super.getSqlSession().update(super.getNs() + ".uprecommend", paramap);
    }
}
