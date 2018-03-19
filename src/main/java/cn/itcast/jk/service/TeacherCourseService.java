package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.ClassTradetail;
import cn.itcast.jk.vo.ClasssVO;
import cn.itcast.jk.vo.PerCourseVO;
import cn.itcast.jk.vo.TeacherCourseVO;


/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface TeacherCourseService {
    public List<TeacherCourse> findPage(Page<?> page); // 分页查询

    public List<TeacherCourse> find(Map<?, ?> paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

    public List<TeacherCourseVO> view(Map<?, ?> paraMap);


    public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

    public List<ClassTradetail> getpersonal(Map<?, ?> paraMap);

    public void insert(TeacherCourse advertisement);

    public ClassTradetail getclassStudent(Map<String, String> paramap);
}
