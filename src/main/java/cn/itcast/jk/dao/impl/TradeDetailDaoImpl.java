package cn.itcast.jk.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.TradeDetailDao;
import cn.itcast.jk.domain.CouStu;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.vo.PerCourseVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
@Repository
public class TradeDetailDaoImpl extends BaseDaoImpl<TradeDetail> implements TradeDetailDao {
    public TradeDetailDaoImpl() {
        // 设置命名空间
        super.setNs("cn.itcast.jk.mapper.TradeDetailMapper");
    }

    public void updateState(Map<?, ?> map) {
        super.getSqlSession().update(super.getNs() + ".updateState", map);
    }

    @Override
    public List<PerCourseVO> getpersonal(Map<?, ?> map) {
        return super.getSqlSession().selectList(super.getNs() + ".getpersonal", map);
    }

    @Override
    public void updatePhoneandname(TradeDetail tradedetail) {

        super.getSqlSession().update(super.getNs() + ".updatephoneandname", tradedetail);
    }

    @Override
    public List<CouStu> coustu(String areaId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("areaId", areaId);
        return super.getSqlSession().selectList(super.getNs() + ".coustu", map);
    }
}
