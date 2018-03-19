package cn.itcast.jk.domain;

/**
 * 类别
 *
 * @author Administrator
 */
public class Withdraw {
    private String id; // 将主键都映射成id
    private String userId;
    private String userName;
    private String userOpenId;
    private double money;
    private java.util.Date withdrawTime;
    private String areaId;
    private String areaName;
    private java.util.Date dealTime;
    private String dealUserId;
    private String dealUserName;
    private String dealOpenId;
    private String paymentNo;
    private String paymentTime;
    private int state;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public java.util.Date getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(java.util.Date withdrawTime) {
        this.withdrawTime = withdrawTime;
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

    public java.util.Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(java.util.Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public String getDealOpenId() {
        return dealOpenId;
    }

    public void setDealOpenId(String dealOpenId) {
        this.dealOpenId = dealOpenId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

}
