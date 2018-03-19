package cn.itcast.jk.domain;

import java.io.Serializable;

public class TradeDetail implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1804684768010286798L;
    private String id;
    private String tradeId;
    private String userId;
    private String userName;
    private String courseId;
    private String courseName;

    private int tradeState;
    private String classId;
    private int classState;
    private String courseCover;
    private String areaId;
    private String areaName;
    private double classprice;
    private String userPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTradeState() {
        return tradeState;
    }

    /**
     * 0已支付1申请退款2驳回3已退款4已完成
     *
     * @param tradeState
     */
    public void setTradeState(int tradeState) {
        this.tradeState = tradeState;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getClassState() {
        return classState;
    }

    public void setClassState(int classState) {
        this.classState = classState;
    }

    public double getClassprice() {
        return classprice;
    }

    public void setClassprice(double classprice) {
        this.classprice = classprice;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

}
