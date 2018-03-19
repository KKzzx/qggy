package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.RankDao;
import cn.itcast.jk.domain.Rank;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.RankService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class RankServiceImpl implements RankService {
    @Resource
    RankDao rankDao;

    @Override
    public List<Rank> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Rank> find(Map<?, ?> paraMap) {
        return rankDao.find(paraMap);
    }

    public Rank get(Serializable id) {
        return rankDao.get(id);
    }

    public void insert(Rank rank) {
        rank.setId(OrderUtil.getOrderNo()); //设置UUID
        rank.setRankState("1"); // 默认启用状态
        rankDao.insert(rank);
    }

    public void update(Rank rank) {
        rankDao.update(rank);
    }

    public void deleteById(Serializable id) {
        rankDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        rankDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        rankDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        rankDao.updateState(map);
    }

    public List<Rank> getRankList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return rankDao.find(paraMap);
    }

}
