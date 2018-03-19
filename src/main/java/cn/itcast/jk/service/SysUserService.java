package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.SysUserVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface SysUserService {
    public List<SysUser> findPage(Page<?> page); // 分页查询

    public List<SysUser> find(Map<?, ?> paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

    public SysUser get(Serializable id); // 只查询一个，常用于修改

    public void insert(SysUser SysUser); // 插入，用实体作为参数

    public void update(SysUser SysUser); // 修改，用实体作为参数

    public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

    public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

    public void start(Serializable[] ids); // 启用

    public void stop(Serializable[] ids); // 停用


    public SysUserVO view(String id);


}
