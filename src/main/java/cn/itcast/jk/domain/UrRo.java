package cn.itcast.jk.domain;

import java.io.Serializable;

/**
 * 类别
 *
 * @author Administrator
 */
public class UrRo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6545787142382580499L;
    private String id; // 将主键都映射成id
    private String suserId;
    private String sroleId;
    private String sroleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuserId() {
        return suserId;
    }

    public void setSuserId(String suserId) {
        this.suserId = suserId;
    }

    public String getSroleId() {
        return sroleId;
    }

    public void setSroleId(String sroleId) {
        this.sroleId = sroleId;
    }

    public String getSroleName() {
        return sroleName;
    }

    public void setSroleName(String sroleName) {
        this.sroleName = sroleName;
    }


}
