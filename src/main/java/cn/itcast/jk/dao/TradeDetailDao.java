package cn.itcast.jk.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.CouStu;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.vo.PerCourseVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface TradeDetailDao extends BaseDao<TradeDetail> {
    public void updateState(Map<?, ?> map); // 修改状态

    public List<PerCourseVO> getpersonal(Map<?, ?> map); // 修改状态

    public void updatePhoneandname(TradeDetail tradedetail);

    public List<CouStu> coustu(String areaId); // 查询课程对应的未开班学生人数
}
