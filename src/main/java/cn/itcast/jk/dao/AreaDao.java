package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Area;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface AreaDao extends BaseDao<Area> {
    public void updateState(Map<?, ?> map); // 修改状态

    public String queryCodeByLevel(Area area);

    public String queryCodeById(Integer parentId);
}
