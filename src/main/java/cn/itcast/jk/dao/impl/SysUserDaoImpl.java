package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.SysUserDao;
import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao {
    public SysUserDaoImpl() {
        // 设置命名空间SystemUserMapper
        super.setNs("cn.itcast.jk.mapper.SysUserMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }

    @Override
    public SysUserVO view(String id) {
        return super.getSqlSession().selectOne(super.getNs() + ".view", id);
    }

    @Override
    public SysUser getBynamepass(Map<?, ?> map) {
        System.out.println("inin");
        return super.getSqlSession().selectOne(super.getNs() + ".getBynamepass", map);
    }

}
