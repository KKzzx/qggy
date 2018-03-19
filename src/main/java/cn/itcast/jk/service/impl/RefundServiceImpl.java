package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.RefundDao;
import cn.itcast.jk.domain.Refund;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.RefundService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class RefundServiceImpl implements RefundService {
    @Resource
    RefundDao refundDao;

    @Override
    public List<Refund> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Refund> find(Map<?, ?> paraMap) {
        return refundDao.find(paraMap);
    }

    public Refund get(Serializable id) {
        return refundDao.get(id);
    }

    public void insert(Refund refund) {
        if (refund.getId() == null) {
            refund.setId(OrderUtil.getOrderNo()); // 设置UUID
        }
        // System.out.println(UUID.randomUUID().toString().length());
        refund.setState(0); // 默认启用状态
        refundDao.insert(refund);
    }

    public void update(Refund refund) {
        refundDao.update(refund);
    }

    public void deleteById(Serializable id) {
        refundDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        refundDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        refundDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        refundDao.updateState(map);
    }

    public List<Refund> getRefundList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return refundDao.find(paraMap);
    }

    @Override
    public void updateState(Map<?, ?> paraMap) {
        refundDao.updateState(paraMap);
    }

}
