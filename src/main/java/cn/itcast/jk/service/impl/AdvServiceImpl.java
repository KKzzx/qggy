package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.AdvDao;
import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.AdvService;
import cn.itcast.jk.vo.AdvVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class AdvServiceImpl implements AdvService {
    @Resource
    AdvDao advDao;

    @Override
    public List<Advertisement> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Advertisement> find(Map<?, ?> paraMap) {
        return advDao.find(paraMap);
    }

    public Advertisement get(Serializable id) {
        return advDao.get(id);
    }

    public void insert(Advertisement advertisement) {
        advDao.insert(advertisement);
    }

    public void update(Advertisement advertisement) {
        advDao.update(advertisement);
    }

    public void deleteById(Serializable id) {
        advDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        advDao.delete(ids);
    }

    @Override
    public void insertList(List<Advertisement> list) {
        advDao.insertList(list);

    }

    @Override
    public AdvVO view(Map<String, String> paraMap) {
        return advDao.view(paraMap);
    }

    @Override
    public void toupisMain(Advertisement advertisement) {
        advDao.toupisMain(advertisement);

    }

}
