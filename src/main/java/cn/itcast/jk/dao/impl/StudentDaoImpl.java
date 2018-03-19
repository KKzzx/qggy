package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.StudentDao;
import cn.itcast.jk.domain.Student;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    public StudentDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.StudentMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
