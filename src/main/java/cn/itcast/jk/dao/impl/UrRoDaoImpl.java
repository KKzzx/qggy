package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.UrRoDao;
import cn.itcast.jk.domain.UrRo;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class UrRoDaoImpl extends BaseDaoImpl<UrRo> implements UrRoDao {
    public UrRoDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.UrRoMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
