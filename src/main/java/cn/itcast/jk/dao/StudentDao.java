package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Student;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface StudentDao extends BaseDao<Student> {
    public void updateState(Map<?, ?> map); // 修改状态
}
