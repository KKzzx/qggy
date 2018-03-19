package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ClasssDao;
import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.vo.ClasssVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class ClasssDaoImpl extends BaseDaoImpl<Classs> implements ClasssDao {
    public ClasssDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.ClasssMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }

    @Override
    public ClasssVO view(Map<?, ?> map) {
        return super.getSqlSession().selectOne(super.getNs() + ".view", map);
    }

}
