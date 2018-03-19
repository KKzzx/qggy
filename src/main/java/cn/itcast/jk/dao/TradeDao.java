package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.vo.TradeVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface TradeDao extends BaseDao<Trade> {
    public void updateState(Map<?, ?> map); // 修改状态

    public TradeVO view(String tradeId); // 查询某个合同
}
