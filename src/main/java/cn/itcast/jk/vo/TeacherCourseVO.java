package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Course;
import cn.itcast.jk.domain.TeacherCourse;

public class TeacherCourseVO extends TeacherCourse implements Serializable {

    private String courseCover;
    private String courseAbstract;
    private double coursePrice;
    private String courseContent;
    private String courseRemark;
    private int openNumber;
    private int sortNum;
    private int state;

    private String releaseUserId;
    private String releaseName;
    private String releaseOpenId;
    private java.util.Date releaseTime;

    private String areaId;
    private String areaName;

    private String categoryId;
    private String categoryName;

    private String rankId;
    private String rankName;
    private String recommend;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    private String fCheckId;
    private String fCheckUserName;
    private String fCheckOpenId;
    private java.util.Date fCheckTime;

    private String zCheckId;
    private String zCheckUserName;
    private String zCheckOpenId;
    private java.util.Date zCheckTime;

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getReleaseOpenId() {
        return releaseOpenId;
    }

    public void setReleaseOpenId(String releaseOpenId) {
        this.releaseOpenId = releaseOpenId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getfCheckUserName() {
        return fCheckUserName;
    }

    public void setfCheckUserName(String fCheckUserName) {
        this.fCheckUserName = fCheckUserName;
    }

    public String getfCheckOpenId() {
        return fCheckOpenId;
    }

    public void setfCheckOpenId(String fCheckOpenId) {
        this.fCheckOpenId = fCheckOpenId;
    }

    public String getzCheckUserName() {
        return zCheckUserName;
    }

    public void setzCheckUserName(String zCheckUserName) {
        this.zCheckUserName = zCheckUserName;
    }

    public String getzCheckOpenId() {
        return zCheckOpenId;
    }

    public void setzCheckOpenId(String zCheckOpenId) {
        this.zCheckOpenId = zCheckOpenId;
    }


    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseAbstract() {
        return courseAbstract;
    }

    public void setCourseAbstract(String courseAbstract) {
        this.courseAbstract = courseAbstract;
    }

    public double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public int getOpenNumber() {
        return openNumber;
    }

    public void setOpenNumber(int openNumber) {
        this.openNumber = openNumber;
    }

    public String getCourseRemark() {
        return courseRemark;
    }

    public void setCourseRemark(String courseRemark) {
        this.courseRemark = courseRemark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getfCheckId() {
        return fCheckId;
    }


    public String getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRankId() {
        return rankId;
    }

    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    public void setfCheckId(String fCheckId) {
        this.fCheckId = fCheckId;
    }

    public String getzCheckId() {
        return zCheckId;
    }

    public void setzCheckId(String zCheckId) {
        this.zCheckId = zCheckId;
    }

    public java.util.Date getzCheckTime() {
        return zCheckTime;
    }

    public void setzCheckTime(java.util.Date zCheckTime) {
        this.zCheckTime = zCheckTime;
    }

    public java.util.Date getfCheckTime() {
        return fCheckTime;
    }

    public void setfCheckTime(java.util.Date fCheckTime) {
        this.fCheckTime = fCheckTime;
    }

    public java.util.Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(java.util.Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }


}
