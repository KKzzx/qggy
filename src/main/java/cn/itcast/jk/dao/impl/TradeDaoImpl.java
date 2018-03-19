package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.TradeDao;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.vo.TradeVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class TradeDaoImpl extends BaseDaoImpl<Trade> implements TradeDao {
    public TradeDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.TradeMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }

    @Override
    public TradeVO view(String tradeId) {
        return super.getSqlSession().selectOne(super.getNs() + ".view",
                tradeId);
    }
}
