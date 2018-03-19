package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ClasssDao;
import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ClasssService;
import cn.itcast.jk.vo.ClasssVO;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class ClasssServiceImpl implements ClasssService {
    @Resource
    ClasssDao classsDao;

    @Override
    public List<Classs> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Classs> find(Map<?, ?> paraMap) {
        return classsDao.find(paraMap);
    }

    public Classs get(Serializable id) {
        return classsDao.get(id);
    }

    public void insert(Classs classs) {
        if (classs.getId() == null) {
            classs.setId(OrderUtil.getOrderNo()); // 设置UUID
        }
        // classs.setState("1"); // 默认启用状态
        classsDao.insert(classs);
    }

    public void update(Classs classs) {
        classsDao.update(classs);
    }

    public void deleteById(Serializable id) {
        classsDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        classsDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        classsDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        classsDao.updateState(map);
    }

    public List<Classs> getClasssList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别
        classsDao.find(paraMap);
        return null;
    }

    @Override
    public ClasssVO view(Map<?, ?> paraMap) {

        return classsDao.view(paraMap);
    }

}
