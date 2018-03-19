package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.Category;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface CategoryDao extends BaseDao<Category> {
    public void updateState(Map<?, ?> map); // 修改状态
}
