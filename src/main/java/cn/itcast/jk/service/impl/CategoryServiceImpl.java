package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.CategoryDao;
import cn.itcast.jk.domain.Category;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.CategoryService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryDao categoryDao;

    @Override
    public List<Category> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Category> find(Map<?, ?> paraMap) {
        return categoryDao.find(paraMap);
    }

    public Category get(Serializable id) {
        return categoryDao.get(id);
    }

    public void insert(Category category) {
        category.setId(OrderUtil.getOrderNo());        //设置UUID
        category.setCategoryState("1");                                //默认启用状态
        categoryDao.insert(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void deleteById(Serializable id) {
        categoryDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        categoryDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1);                //1启用
        map.put("ids", ids);

        categoryDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0);                //0停用
        map.put("ids", ids);

        categoryDao.updateState(map);
    }


    public List<Category> getCategoryList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1);            //1启用，代表只查询启用的类别

        return categoryDao.find(paraMap);
    }

}
