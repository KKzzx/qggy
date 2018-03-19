package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.TradeDao;
import cn.itcast.jk.domain.Trade;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.TradeService;
import cn.itcast.jk.vo.TradeVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class TradeServiceImpl implements TradeService {
    @Resource
    TradeDao tradeDao;

    @Override
    public List<Trade> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Trade> find(Map<?, ?> paraMap) {
        return tradeDao.find(paraMap);
    }

    public Trade get(Serializable id) {
        return tradeDao.get(id);
    }

    public void insert(Trade trade) {
        // trade.setId(UUID.randomUUID().toString()); //设置UUID
        trade.setState(0); // 默认启用状态
        tradeDao.insert(trade);
    }

    public void update(Trade trade) {
        tradeDao.update(trade);
    }

    public void deleteById(Serializable id) {
        tradeDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        tradeDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        tradeDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        tradeDao.updateState(map);
    }

    public List<Trade> getTradeList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别
        tradeDao.find(paraMap);
        return null;
    }

    @Override
    public TradeVO view(String tradeId) {

        return tradeDao.view(tradeId);
    }

    @Override
    public void updateState(Map<?, ?> paraMap) {
        tradeDao.updateState(paraMap);
    }

}
