package cn.itcast.jk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.AdvDao;
import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.vo.AdvVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class AdvDaoImpl extends BaseDaoImpl<Advertisement> implements AdvDao {
    public AdvDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.AdvMapper");
    }


    @Override
    public AdvVO view(Map<String, String> paramap) {
        return super.getSqlSession().selectOne(super.getNs() + ".view", paramap);
    }

    @Override
    public void insertList(List<Advertisement> list) {
        super.getSqlSession().selectList(super.getNs() + ".insertList", list);
    }

    @Override
    public void toupisMain(Advertisement advertisement) {
        // TODO Auto-generated method stub
        super.getSqlSession().update(super.getNs() + ".toupisMain", advertisement);

    }


}
