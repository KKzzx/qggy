package cn.itcast.jk.domain;

import java.io.Serializable;

public class Attendance implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 745189831007029138L;
    private String id;
    private String userId;
    private String userOpenid;
    private String userName;
    private String classId;
    private String className;
    private java.util.Date saocodeTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public java.util.Date getSaocodeTime() {
        return saocodeTime;
    }

    public void setSaocodeTime(java.util.Date saocodeTime) {
        this.saocodeTime = saocodeTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
