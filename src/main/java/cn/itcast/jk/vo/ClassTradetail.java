package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.TradeDetail;

public class ClassTradetail implements Serializable {


    private String classId;


    private int classstate;//班级表

    private String areaId;
    private String areaName;
    private String teacherId;
    private String teacherName;
    private String className;
    private int classCishu;
    private String classMonitor;
    private String classTime;
    private int classNumber;
    private String classAddress;
    private List<TradeDetail> tradeDetails;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getClassstate() {
        return classstate;
    }

    public void setClassstate(int classstate) {
        this.classstate = classstate;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassCishu() {
        return classCishu;
    }

    public void setClassCishu(int classCishu) {
        this.classCishu = classCishu;
    }

    public String getClassMonitor() {
        return classMonitor;
    }

    public void setClassMonitor(String classMonitor) {
        this.classMonitor = classMonitor;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassAddress() {
        return classAddress;
    }

    public void setClassAddress(String classAddress) {
        this.classAddress = classAddress;
    }

    public List<TradeDetail> getTradeDetails() {
        return tradeDetails;
    }

    public void setTradeDetails(List<TradeDetail> tradeDetails) {
        this.tradeDetails = tradeDetails;
    }


}
