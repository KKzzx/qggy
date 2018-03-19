package cn.itcast.qg.wxpay;

import java.util.List;

import cn.itcast.jk.domain.Student;

public class JsApiPay {

    private String prepayid;
    private List<Student> studentlist;
    /**
     * 内部订单号
     */
    private String outtradeno;
    private double countmoney;
    private double weixinmoney;
    private double xianjin;


    /**
     * 用户ID
     */


    /**
     * 课程ID
     */
    private int courseid;

    /**
     * 订购数量
     */
    private int amount;
    /**
     * 订购单价
     */
    private double price;
    /**
     * 标价金额
     */
    private double totalfee;
    /**
     * 支付交易订单号，支付宝的
     */
    private String transactionid;
    /**
     * 交易主题
     */
    private String body;
    /**
     * 支付设备
     */
    private String deviceinfo;
    /**
     * 支付类型，网页、二维码等
     */
    private String tradetype;
    /**
     * 交易银行
     */
    private String banktype;
    /**
     * 交易币种
     */
    private String feetype;
    /**
     * 现金支付金额
     */
    private double cashfee;
    /**
     * 支付完成时间
     */
    private String timeend;
    private String remark;

    public double getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(double totalfee) {
        this.totalfee = totalfee;
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getFeetype() {
        return feetype;
    }

    public void setFeetype(String feetype) {
        this.feetype = feetype;
    }

    public double getCashfee() {
        return cashfee;
    }

    public void setCashfee(double cashfee) {
        this.cashfee = cashfee;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public List<Student> getStudentlist() {
        return studentlist;
    }

    public void setStudentlist(List<Student> studentlist) {
        this.studentlist = studentlist;
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

    public double getXianjin() {
        return xianjin;
    }

    public void setXianjin(double xianjin) {
        this.xianjin = xianjin;
    }

}
