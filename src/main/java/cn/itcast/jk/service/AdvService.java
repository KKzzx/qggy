package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.AdvVO;


/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface AdvService {
    public List<Advertisement> findPage(Page<?> page); // 分页查询

    public List<Advertisement> find(Map<?, ?> paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合


    public Advertisement get(Serializable id); // 只查询一个，常用于修改

    public void insertList(List<Advertisement> list); // 插入，用实体作为参数

    public void update(Advertisement adv); // 修改，用实体作为参数

    public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

    public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID


    public void insert(Advertisement advertisement);

    public AdvVO view(Map<String, String> pMap);//获取某个广告以及下面的课程

    public void toupisMain(Advertisement advertisement);//修改首页展示
}
