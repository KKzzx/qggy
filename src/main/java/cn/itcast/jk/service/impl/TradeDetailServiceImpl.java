package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.TradeDetailDao;
import cn.itcast.jk.domain.CouStu;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.TradeDetailService;
import cn.itcast.jk.vo.PerCourseVO;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class TradeDetailServiceImpl implements TradeDetailService {
    @Resource
    TradeDetailDao tradeDetailDao;

    @Override
    public List<TradeDetail> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<TradeDetail> find(Map<?, ?> paraMap) {
        return tradeDetailDao.find(paraMap);
    }

    public TradeDetail get(Serializable id) {
        return tradeDetailDao.get(id);
    }

    public void insert(TradeDetail tradeDetail) {
        // tradeDetail.setId(UUID.randomUUID().toString()); //设置UUID
        System.out.println(OrderUtil.getOrderNo());
        // tradeDetail.setAreaState("1"); // 默认启用状态
        tradeDetailDao.insert(tradeDetail);
    }

    public void update(TradeDetail tradeDetail) {
        tradeDetailDao.update(tradeDetail);
    }

    public void deleteById(Serializable id) {
        tradeDetailDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        tradeDetailDao.delete(ids);
    }

    public List<TradeDetail> getTradeDetailList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("classState", 0); // 只查询未开班

        return tradeDetailDao.find(paraMap);
    }

    @Override
    public void updateClass(String[] ids, String classId, int state) {
        for (String id : ids) {
            TradeDetail t = new TradeDetail();
            t.setId(id);
            t.setClassId(classId);
            t.setClassState(1);
            tradeDetailDao.update(t);
        }
    }

    @Override
    public void updateState(Map<?, ?> paraMap) {
        tradeDetailDao.updateState(paraMap);
    }

    @Override
    public List<PerCourseVO> getpersonal(Map<?, ?> paraMap) {
        return tradeDetailDao.getpersonal(paraMap);
    }

    @Override
    public void updatePhoneandname(TradeDetail tradedetail) {

        tradeDetailDao.updatePhoneandname(tradedetail);

    }

    @Override
    public List<CouStu> coustu(String areaId) {
        return tradeDetailDao.coustu(areaId);
    }

}
