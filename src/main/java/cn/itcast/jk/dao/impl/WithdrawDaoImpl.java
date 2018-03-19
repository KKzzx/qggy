package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.WithdrawDao;
import cn.itcast.jk.domain.Withdraw;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class WithdrawDaoImpl extends BaseDaoImpl<Withdraw> implements
        WithdrawDao {
    public WithdrawDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.WithdrawMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
