package cn.itcast.jk.domain;

/**
 * @author jy
 * @time 下午6:20:16
 */
public class CourseAdv {
    private Integer courseAdvertiseId;
    private Integer courseId;
    private Integer advertiseId;
    private String remark;
    private Integer state;

    /**
     * @return the courseAdvertiseId
     */
    public Integer getCourseAdvertiseId() {
        return courseAdvertiseId;
    }

    /**
     * @param courseAdvertiseId the courseAdvertiseId to set
     */
    public void setCourseAdvertiseId(Integer courseAdvertiseId) {
        this.courseAdvertiseId = courseAdvertiseId;
    }

    /**
     * @return the courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the advertiseId
     */
    public Integer getAdvertiseId() {
        return advertiseId;
    }

    /**
     * @param advertiseId the advertiseId to set
     */
    public void setAdvertiseId(Integer advertiseId) {
        this.advertiseId = advertiseId;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Integer state) {
        this.state = state;
    }

}
