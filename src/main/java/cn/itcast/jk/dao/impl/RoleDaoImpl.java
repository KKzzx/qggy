package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.RoleDao;
import cn.itcast.jk.domain.Role;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.RoleMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
