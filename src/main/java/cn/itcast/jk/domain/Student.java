package cn.itcast.jk.domain;

import java.io.Serializable;

public class Student implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5213076144693895520L;
    private String id;
    private String userName;
    private String phoneNumber;
    private String email;
    private String company;
    private int sex;
    private String adress;
    private String weiXin;
    private int marryd;
    private String photoPath;
    private String userOpenid;
    private String areaId;
    private String areaName;
    private double frozenAssets;
    private double availableAssets;
    private int state;
    private double xianjin;
    private String likes;
    private String zishu;


    public String getShenFen() {
        return shenFen;
    }

    public void setShenFen(String shenFen) {
        this.shenFen = shenFen;
    }

    private String shenFen;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public int getMarryd() {
        return marryd;
    }

    public void setMarryd(int marryd) {
        this.marryd = marryd;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(double frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public double getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(double availableAssets) {
        this.availableAssets = availableAssets;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getXianjin() {
        return xianjin;
    }

    public void setXianjin(double xianjin) {
        this.xianjin = xianjin;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getZishu() {
        return zishu;
    }

    public void setZishu(String zishu) {
        this.zishu = zishu;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", sex=" + sex +
                ", adress='" + adress + '\'' +
                ", weiXin='" + weiXin + '\'' +
                ", marryd=" + marryd +
                ", photoPath='" + photoPath + '\'' +
                ", userOpenid='" + userOpenid + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", frozenAssets=" + frozenAssets +
                ", availableAssets=" + availableAssets +
                ", state=" + state +
                ", xianjin=" + xianjin +
                ", likes='" + likes + '\'' +
                ", zishu='" + zishu + '\'' +
                ", shenFen='" + shenFen + '\'' +
                '}';
    }
}
