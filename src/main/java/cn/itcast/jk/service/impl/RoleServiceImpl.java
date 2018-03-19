package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.RoleDao;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.RoleService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleDao roleDao;

    @Override
    public List<Role> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Role> find(Map<?, ?> paraMap) {
        return roleDao.find(paraMap);
    }

    public Role get(Serializable id) {
        return roleDao.get(id);
    }

    public void insert(Role role) {
        // role.setId(UUID.randomUUID().toString()); //设置UUID
        role.setRoleState("1"); // 默认启用状态
        roleDao.insert(role);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public void deleteById(Serializable id) {
        roleDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        roleDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        roleDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        roleDao.updateState(map);
    }

    public List<Role> getRoleList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return roleDao.find(paraMap);
    }

}
