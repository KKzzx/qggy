package cn.itcast.jk.domain;

/**
 * 类别
 *
 * @author Administrator
 */
public class Area {
    private String id; // 将主键都映射成id
    private String areaName;
    private String areaCode;
    private Integer parentId;
    private String parentName;
    private String firstParent;
    private String secondParent;
    private Integer areaLevel;
    private String areaRemark;
    private String areaState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaRemark() {
        return areaRemark;
    }

    public void setAreaRemark(String areaRemark) {
        this.areaRemark = areaRemark;
    }

    public String getAreaState() {
        return areaState;
    }

    public void setAreaState(String areaState) {
        this.areaState = areaState;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getFirstParent() {
        return firstParent;
    }

    public void setFirstParent(String firstParent) {
        this.firstParent = firstParent;
    }

    public String getSecondParent() {
        return secondParent;
    }

    public void setSecondParent(String secondParent) {
        this.secondParent = secondParent;
    }

    @Override
    public String toString() {
        return "Area [id=" + id + ", areaName=" + areaName + ", areaCode=" + areaCode + ", parentId=" + parentId
                + ", parentName=" + parentName + ", firstParent=" + firstParent + ", secondParent=" + secondParent
                + ", areaLevel=" + areaLevel + ", areaRemark=" + areaRemark + ", areaState=" + areaState + "]";
    }

}
