package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.StudentDao;
import cn.itcast.jk.domain.Student;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.StudentService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentDao studentDao;

    @Override
    public List<Student> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Student> find(Map<?, ?> paraMap) {
        return studentDao.find(paraMap);
    }

    // 使用openid查询
    public Student get(Serializable id) {
        return studentDao.get(id);
    }

    public void insert(Student student) {
        student.setId(OrderUtil.getOrderNo()); // 设置UUID

        studentDao.insert(student);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void deleteById(Serializable id) {
        studentDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        studentDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        studentDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        studentDao.updateState(map);
    }

    public List<Student> getStudentList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return studentDao.find(paraMap);
    }

}
