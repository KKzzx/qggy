package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.RankDao;
import cn.itcast.jk.domain.Rank;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class RankDaoImpl extends BaseDaoImpl<Rank> implements RankDao {
    public RankDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.RankMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
