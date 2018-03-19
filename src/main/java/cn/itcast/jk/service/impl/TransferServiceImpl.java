package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.TransferDao;
import cn.itcast.jk.domain.Transfer;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.TransferService;
import cn.itcast.util.OrderUtil;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Service
public class TransferServiceImpl implements TransferService {
    @Resource
    TransferDao transferDao;

    public List<Transfer> findPage(Page<?> page) {
        return null;
    }

    public List<Transfer> find(Map<?, ?> paraMap) {
        return transferDao.find(paraMap);
    }

    public Transfer get(Serializable id) {
        return transferDao.get(id);
    }

    public void insert(Transfer transfer) {
        if (transfer.getId() == null) {
            transfer.setId(OrderUtil.getOrderNo()); // 设置UUID
        }
        transferDao.insert(transfer);
    }

    public void update(Transfer rank) {
        transferDao.update(rank);
    }

}
