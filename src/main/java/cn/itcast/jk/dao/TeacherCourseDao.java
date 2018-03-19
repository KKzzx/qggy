package cn.itcast.jk.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.vo.AdvVO;
import cn.itcast.jk.vo.ClassTradetail;
import cn.itcast.jk.vo.TeacherCourseVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface TeacherCourseDao extends BaseDao<TeacherCourse> {

    List<TeacherCourseVO> view(Map<?, ?> paraMap);

    List<ClassTradetail> getpersonal(Map<?, ?> paraMap);

    ClassTradetail getclassStudent(Map<String, String> paramap);


}
