package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.SysUserDao;
import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.SysUserService;
import cn.itcast.jk.vo.SysUserVO;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserDao sysUserDao;

    @Override
    public List<SysUser> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<SysUser> find(Map<?, ?> paraMap) {
        return sysUserDao.find(paraMap);
    }

    public SysUser get(Serializable id) {
        return sysUserDao.get(id);
    }

    public void insert(SysUser sysuser) {
        sysuser.setId(OrderUtil.getOrderNo()); //设置UUID
        sysuser.setState(0); // 默认启用状态
        sysUserDao.insert(sysuser);
    }

    public void update(SysUser sysuser) {
        sysUserDao.update(sysuser);
    }

    public void deleteById(Serializable id) {
        sysUserDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        sysUserDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        sysUserDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        sysUserDao.updateState(map);
    }

    @Override
    public SysUserVO view(String id) {
        return sysUserDao.view(id);
    }


}
