package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Student;

public class ClasssVO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6201344894481930702L;
    private String id;
    private String className;
    private String classMonitor;
    private int classCishu;
    private String classTime;
    private int classNumber;
    private String classAddress;
    private String areaId;
    private String areaName;
    private String teacherId;
    private String teacherName;
    private int classstate;
    private List<Student> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getClassCishu() {
        return classCishu;
    }

    public void setClassCishu(int classCishu) {
        this.classCishu = classCishu;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassstate() {
        return classstate;
    }

    public void setClassstate(int classstate) {
        this.classstate = classstate;
    }

}
