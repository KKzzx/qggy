package cn.itcast.jk.vo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.TradeDetail;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月11日
 */
public class TradeVO implements Serializable {
    private List<TradeDetail> tradedetails;
    private String id;
    private int category;
    private double totalFee;
    private String payUserId;
    private String payUserName;
    private String payUserOpenid;
    private java.util.Date payTime;
    private String state;
    private String bankType;
    private int cashFee;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
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

    public List<TradeDetail> getTradedetails() {
        return tradedetails;
    }

    public void setTradedetails(List<TradeDetail> tradedetails) {
        this.tradedetails = tradedetails;
    }

    public double getWeixinmoney() {
        return weixinmoney;
    }

    public void setWeixinmoney(double weixinmoney) {
        this.weixinmoney = weixinmoney;
    }

    public double getCountmoney() {
        return countmoney;
    }

    public void setCountmoney(double countmoney) {
        this.countmoney = countmoney;
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
