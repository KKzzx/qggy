package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.WithdrawDao;
import cn.itcast.jk.domain.Withdraw;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.WithdrawService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class WithdrawServiceImpl implements WithdrawService {
    @Resource
    WithdrawDao withdrawDao;

    @Override
    public List<Withdraw> findPage(Page<?> page) {
        return null;
    }

    @Override
    public List<Withdraw> find(Map<?, ?> paraMap) {
        return withdrawDao.find(paraMap);
    }

    public Withdraw get(Serializable id) {
        return withdrawDao.get(id);
    }

    public void insert(Withdraw withdraw) {
        withdraw.setId(OrderUtil.getOrderNo()); //设置UUID
        withdrawDao.insert(withdraw);
    }

    public void update(Withdraw withdraw) {
        withdrawDao.update(withdraw);
    }

    public void deleteById(Serializable id) {
        withdrawDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        withdrawDao.delete(ids);
    }

}
