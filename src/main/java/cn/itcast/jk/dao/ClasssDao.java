package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Classs;
import cn.itcast.jk.vo.ClasssVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface ClasssDao extends BaseDao<Classs> {
    public void updateState(Map<?, ?> map); // 修改状态

    public ClasssVO view(Map<?, ?> map); // 查询某个课程

}
