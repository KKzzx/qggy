package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.RefundDao;
import cn.itcast.jk.domain.Refund;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class RefundDaoImpl extends BaseDaoImpl<Refund> implements RefundDao {
    public RefundDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.RefundMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }
}
