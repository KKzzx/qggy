package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.TransferDao;
import cn.itcast.jk.domain.Transfer;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class TransferDaoImpl extends BaseDaoImpl<Transfer> implements
        TransferDao {
    public TransferDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.TransferMapper");
    }
}
