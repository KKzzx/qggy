package cn.itcast.jk.domain;

import java.io.Serializable;

public class Trade implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7945592222346306992L;
    private String id;
    private int category;
    private double totalFee;
    private String payUserId;
    private String payUserName;
    private String payUserOpenid;
    private java.util.Date payTime;
    private int state;
    private String bankType;
    private double cashFee;
    private String deviceInfo;
    private String feeType;
    private String outTradeNo;
    private String timeEnd;
    private String tradeType;
    private String transactionId;
    private String areaId;
    private String areaName;
    private double countmoney;
    private double weixinmoney;
    private double fmoney;
    private double xianjinPay;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    public String getPayUserOpenid() {
        return payUserOpenid;
    }

    public void setPayUserOpenid(String payUserOpenid) {
        this.payUserOpenid = payUserOpenid;
    }

    public java.util.Date getPayTime() {
        return payTime;
    }

    public void setPayTime(java.util.Date payTime) {
        this.payTime = payTime;
    }

    public int getState() {
        return state;
    }

    /**
     * 0已支付1申请退款2驳回3部分退款4已退款5已完成不可更改
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public double getCashFee() {
        return cashFee;
    }

    public void setCashFee(double cashFee) {
        this.cashFee = cashFee;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public double getCountmoney() {
        return countmoney;
    }

    public void setCountmoney(double countmoney) {
        this.countmoney = countmoney;
    }

    public double getWeixinmoney() {
        return weixinmoney;
    }

    public void setWeixinmoney(double weixinmoney) {
        this.weixinmoney = weixinmoney;
    }

    public double getFmoney() {
        return fmoney;
    }

    public void setFmoney(double fmoney) {
        this.fmoney = fmoney;
    }

    public double getXianjinPay() {
        return xianjinPay;
    }

    public void setXianjinPay(double xianjinPay) {
        this.xianjinPay = xianjinPay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
