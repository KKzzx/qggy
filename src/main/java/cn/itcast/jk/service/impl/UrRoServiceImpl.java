package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.UrRoDao;
import cn.itcast.jk.domain.UrRo;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.UrRoService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class UrRoServiceImpl implements UrRoService {
    @Resource
    UrRoDao urRoDao;

    @Override
    public List<UrRo> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<UrRo> find(Map<?, ?> paraMap) {
        return urRoDao.find(paraMap);
    }

    public UrRo get(Serializable id) {
        return urRoDao.get(id);
    }

    public void insert(UrRo urRo) {
        urRo.setId(OrderUtil.getOrderNo()); //设置UUID
        urRoDao.insert(urRo);
    }

    public void update(UrRo urRo) {
        urRoDao.update(urRo);
    }

    public void deleteById(Serializable id) {
        urRoDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        urRoDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        urRoDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        urRoDao.updateState(map);
    }

    public List<UrRo> getUrRoList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return urRoDao.find(paraMap);
    }

}
