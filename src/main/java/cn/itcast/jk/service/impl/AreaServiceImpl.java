package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.AreaDao;
import cn.itcast.jk.domain.Area;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.AreaService;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    AreaDao areaDao;

    @Override
    public List<Area> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Area> find(Map<?, ?> paraMap) {
        return areaDao.find(paraMap);
    }

    public Area get(Serializable id) {
        return areaDao.get(id);
    }

    public void insert(Area area) {
        // area.setId(UUID.randomUUID().toString()); //设置UUID
        area.setAreaState("1"); // 默认启用状态
        areaDao.insert(area);
    }

    public void update(Area area) {
        areaDao.update(area);
    }

    public void deleteById(Serializable id) {
        areaDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        areaDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1); // 1启用
        map.put("ids", ids);

        areaDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 0); // 0停用
        map.put("ids", ids);

        areaDao.updateState(map);
    }

    public List<Area> getAreaList() {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("state", 1); // 1启用，代表只查询启用的类别

        return areaDao.find(paraMap);
    }


    @Override
    public String queryCodeByLevel(Area area) {
        return areaDao.queryCodeByLevel(area);

    }

    @Override
    public String queryCodeById(Integer parentId) {
        return areaDao.queryCodeById(parentId);
    }

}
