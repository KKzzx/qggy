package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.AttendanceDao;
import cn.itcast.jk.domain.Attendance;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.AttendanceService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Resource
    AttendanceDao attendanceDao;

    @Override
    public List<Attendance> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Attendance> find(Map<?, ?> paraMap) {
        return attendanceDao.find(paraMap);
    }

    public Attendance get(Serializable id) {
        return attendanceDao.get(id);
    }

    public void insert(Attendance attendance) {
        attendance.setId(OrderUtil.getOrderNo()); //设置UUID
        attendanceDao.insert(attendance);
    }

    public void update(Attendance attendance) {
        attendanceDao.update(attendance);
    }

    public void deleteById(Serializable id) {
        attendanceDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        attendanceDao.delete(ids);
    }

    public List<Attendance> getAttendanceList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return attendanceDao.find(paraMap);
    }

}
