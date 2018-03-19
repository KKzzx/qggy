package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.AreaDao;
import cn.itcast.jk.domain.Area;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao {
    public AreaDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.AreaMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }

    @Override
    public String queryCodeByLevel(Area area) {
        return super.getSqlSession().selectOne(super.getNs() + ".queryCodeByLevel", area);
    }

    @Override
    public String queryCodeById(Integer parentId) {
        return super.getSqlSession().selectOne(super.getNs() + ".queryCodeById", parentId);
    }
}
