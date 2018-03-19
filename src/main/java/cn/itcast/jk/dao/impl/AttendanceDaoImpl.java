package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.AttendanceDao;
import cn.itcast.jk.domain.Attendance;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class AttendanceDaoImpl extends BaseDaoImpl<Attendance> implements AttendanceDao {
    public AttendanceDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.AttendanceMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
