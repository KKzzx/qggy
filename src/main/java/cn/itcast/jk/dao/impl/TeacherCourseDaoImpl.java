package cn.itcast.jk.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.TeacherCourseDao;
import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.vo.ClassTradetail;
import cn.itcast.jk.vo.TeacherCourseVO;


/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class TeacherCourseDaoImpl extends BaseDaoImpl<TeacherCourse> implements TeacherCourseDao {
    public TeacherCourseDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.TeacherCourseMapper");
    }

    @Override
    public List<TeacherCourseVO> view(Map<?, ?> paraMap) {
        // TODO Auto-generated method stub
        return super.getSqlSession().selectList(super.getNs() + ".view", paraMap);

    }

    @Override
    public List<ClassTradetail> getpersonal(Map<?, ?> paraMap) {
        // TODO Auto-generated method stub
        return super.getSqlSession().selectList(super.getNs() + ".getpersonal", paraMap);

    }

    @Override
    public ClassTradetail getclassStudent(Map<String, String> paramap) {
        // TODO Auto-generated method stub
        return super.getSqlSession().selectOne(super.getNs() + ".getclassStudent", paramap);

    }


}
