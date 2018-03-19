package cn.itcast.jk.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Advertisement;
import cn.itcast.jk.vo.AdvVO;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月9日
 */
public interface AdvDao extends BaseDao<Advertisement> {


    public void insertList(List<Advertisement> list);

    public AdvVO view(Map<String, String> paraMap);


    public void toupisMain(Advertisement advertisement);
}
