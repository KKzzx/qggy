package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import cn.itcast.jk.dao.TeacherCourseDao;

import cn.itcast.jk.domain.TeacherCourse;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.TeacherCourseService;
import cn.itcast.jk.vo.ClassTradetail;
import cn.itcast.jk.vo.TeacherCourseVO;


/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    @Resource
    TeacherCourseDao teacherCoursedao;

    public List<TeacherCourse> findPage(Page<?> page) {
        return null;
    }

    public List<TeacherCourse> find(Map<?, ?> paraMap) {
        return teacherCoursedao.find(paraMap);
    }


    public void insert(TeacherCourse advertisement) {
        teacherCoursedao.insert(advertisement);
    }


    public void deleteById(Serializable id) {
        teacherCoursedao.deleteById(id);
    }

    @Override
    public List<TeacherCourseVO> view(Map<?, ?> paraMap) {
        // TODO Auto-generated method stub
        return teacherCoursedao.view(paraMap);
    }

    @Override
    public List<ClassTradetail> getpersonal(Map<?, ?> paraMap) {
        // TODO Auto-generated method stub
        return teacherCoursedao.getpersonal(paraMap);
    }

    @Override
    public ClassTradetail getclassStudent(Map<String, String> paramap) {
        // TODO Auto-generated method stub
        return teacherCoursedao.getclassStudent(paramap);
    }


}
