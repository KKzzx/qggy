package cn.itcast.jk.domain;

import java.io.Serializable;

/**
 * 类别
 *
 * @author Administrator
 */
public class Rank implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3255416351468736243L;
    private String id; // 将主键都映射成id
    private String rankName;
    private String rankRemark;
    private String rankState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRankRemark() {
        return rankRemark;
    }

    public void setRankRemark(String rankRemark) {
        this.rankRemark = rankRemark;
    }

    public String getRankState() {
        return rankState;
    }

    public void setRankState(String rankState) {
        this.rankState = rankState;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

}
