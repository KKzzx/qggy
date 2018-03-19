package cn.itcast.jk.domain;

public class Transfer {
    private String id;
    private String transferUserid;
    private String transferUsername;
    private String transferUseropenid;
    private double transferMoney;
    private double transferCommission;
    private double transferCash;
    private java.util.Date transferTime;
    private String areaId;
    private String areaName;

    public String getId() {
        return id;
    }

    public String getTransferUserid() {
        return transferUserid;
    }

    public String getTransferUsername() {
        return transferUsername;
    }

    public String getTransferUseropenid() {
        return transferUseropenid;
    }

    public double getTransferMoney() {
        return transferMoney;
    }

    public double getTransferCommission() {
        return transferCommission;
    }

    public double getTransferCash() {
        return transferCash;
    }

    public java.util.Date getTransferTime() {
        return transferTime;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTransferUserid(String transferUserid) {
        this.transferUserid = transferUserid;
    }

    public void setTransferUsername(String transferUsername) {
        this.transferUsername = transferUsername;
    }

    public void setTransferUseropenid(String transferUseropenid) {
        this.transferUseropenid = transferUseropenid;
    }

    public void setTransferMoney(double transferMoney) {
        this.transferMoney = transferMoney;
    }

    public void setTransferCommission(double transferCommission) {
        this.transferCommission = transferCommission;
    }

    public void setTransferCash(double transferCash) {
        this.transferCash = transferCash;
    }

    public void setTransferTime(java.util.Date transferTime) {
        this.transferTime = transferTime;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
