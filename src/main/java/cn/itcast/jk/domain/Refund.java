package cn.itcast.jk.domain;

import java.io.Serializable;

public class Refund implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8919002579541381425L;
    private String id;
    private String tradeId;
    private String tradeDetailId;

    private String refundUserId;
    private String refundUserName;
    private String refundUserOpenid;
    private double money;
    private int state;
    private String refundReason;
    private java.util.Date applyTime;
    private java.util.Date dealTime;
    private String dealUserId;
    private String dealUserName;
    private String dealOpenId;
    private String areaId;
    private String areaName;

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

    public String getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(String refundUserId) {
        this.refundUserId = refundUserId;
    }

    public String getRefundUserName() {
        return refundUserName;
    }

    public void setRefundUserName(String refundUserName) {
        this.refundUserName = refundUserName;
    }

    public String getRefundUserOpenid() {
        return refundUserOpenid;
    }

    public void setRefundUserOpenid(String refundUserOpenid) {
        this.refundUserOpenid = refundUserOpenid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    /**
     * 0待退款1已审核2驳回
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public java.util.Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
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

    public String getTradeDetailId() {
        return tradeDetailId;
    }

    public void setTradeDetailId(String tradeDetailId) {
        this.tradeDetailId = tradeDetailId;
    }

}
