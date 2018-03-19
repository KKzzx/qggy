package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.CouStu;
import cn.itcast.jk.domain.TradeDetail;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.PerCourseVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface TradeDetailService {
    public List<TradeDetail> findPage(Page<?> page); // 分页查询

    public List<TradeDetail> find(Map<?, ?> paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

    public TradeDetail get(Serializable id); // 只查询一个，常用于修改

    public void insert(TradeDetail tradeDetail); // 插入，用实体作为参数

    public void updateState(Map<?, ?> paraMap); // 修改状态，用实体作为参数

    public void update(TradeDetail tradeDetail); // 修改，用实体作为参数

    public void updateClass(String[] ids, String classId, int state); // 修改，用实体作为参数

    public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

    public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

    public List<TradeDetail> getTradeDetailList(); // 获取类别列表

    public List<PerCourseVO> getpersonal(Map<?, ?> paraMap);


    /**
     * 查询课程对应的未开班学生人数
     *
     * @param areaId
     * @return
     */
    public List<CouStu> coustu(String areaId); // 查询课程对应的未开班学生人数

    public void updatePhoneandname(TradeDetail tradedetail);
}
