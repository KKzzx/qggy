package cn.itcast.jk.dao;

import java.util.Map;

import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface SysUserDao extends BaseDao<SysUser> {
    public void updateState(Map<?, ?> map); // 修改状态

    public SysUserVO view(String id); // 查询某个课程

    public SysUser getBynamepass(Map<?, ?> map); // 登录
}
