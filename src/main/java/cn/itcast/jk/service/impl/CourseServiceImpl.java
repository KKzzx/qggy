package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.CourseDao;
import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.CourseAdv;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.CourseService;
import cn.itcast.jk.vo.CourseVO;
import cn.itcast.jk.vo.CourseadvVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    CourseDao courseDao;

    @Override
    public List<Course> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Course> find(Map<?, ?> paraMap) {
        return courseDao.find(paraMap);
    }

    public Course get(Serializable id) {
        return courseDao.get(id);
    }

    public void insert(Course course) {

        courseDao.insert(course);
    }

    public void update(Course course) {
        courseDao.update(course);
    }

    public void deleteById(Serializable id) {
        courseDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        courseDao.delete(ids);
    }


    public List<Course> getCourseList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别
        courseDao.find(paraMap);
        return null;
    }

    @Override
    public List<CourseVO> view(Map<?, ?> map) {

        return courseDao.view(map);
    }

    @Override
    public void addAdv(List<CourseAdv> courseAdvs) {
        courseDao.addAdv(courseAdvs);
    }

    @Override
    public CourseadvVO see(Map<?, ?> paraMap) {
        return courseDao.see(paraMap);
    }

    @Override
    public void deleteAdv(Map<String, String> paramap) {
        // TODO Auto-generated method stub
        courseDao.deleteAdv(paramap);

    }

    @Override
    public void upstate(Course course) {
        // TODO Auto-generated method stub
        courseDao.upstate(course);

    }

    @Override
    public void uprecommend(Map<String, Integer> paramap) {
        // TODO Auto-generated method stub
        courseDao.uprecommend(paramap);

    }

}
