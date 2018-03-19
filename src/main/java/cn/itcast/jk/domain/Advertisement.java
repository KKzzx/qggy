package cn.itcast.jk.domain;

import java.io.Serializable;


/**
 * @author jy
 * @time 下午9:01:46
 */
public class Advertisement implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4403122711263029415L;
    private int advertiseId;
    private int adcategory;
    private String advertiseCover;
    private String adverurl;//更新时使用
    private String advertiseUrl;
    private Integer isMain;
    private String content;
    private Integer adstate;
    private String areaId;
    private String areaName;
    private double price;
    private java.util.Date releaseTime;
    private String releaseUserId;
    private String releaseName;
    private String releaseOpenId;
    private String fCheckId;
    private String fCheckUserName;
    private String fCheckOpenId;
    private java.util.Date fCheckTime;

    private String zCheckId;
    private String zCheckUserName;
    private String zCheckOpenId;
    private java.util.Date zCheckTime;

    /**
     * @return the advertiseId
     */
    public int getAdvertiseId() {
        return advertiseId;
    }

    /**
     * @param advertiseId the advertiseId to set
     */
    public void setAdvertiseId(int advertiseId) {
        this.advertiseId = advertiseId;
    }

    /**
     * @return the categoryId
     */


    /**
     * @param categoryId
     *            the categoryId to set
     */


    /**
     * @return the advertiseCover
     */
    public String getAdvertiseCover() {
        return advertiseCover;
    }

    /**
     * @param advertiseCover the advertiseCover to set
     */
    public void setAdvertiseCover(String advertiseCover) {
        this.advertiseCover = advertiseCover;
    }

    /**
     * @return the advertiseUrl
     */
    public String getAdvertiseUrl() {
        return advertiseUrl;
    }

    /**
     * @param advertiseUrl the advertiseUrl to set
     */
    public void setAdvertiseUrl(String advertiseUrl) {
        this.advertiseUrl = advertiseUrl;
    }

    /**
     * @return the isAdmin
     */
    public Integer getIsMain() {
        return isMain;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the state
     */


    /**
     * @param state
     *            the state to set
     */


    /**
     * @return the areaId
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * @param areaId the areaId to set
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the releaseTime
     */
    public java.util.Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * @param releaseTime the releaseTime to set
     */
    public void setReleaseTime(java.util.Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * @return the releaseUserId
     */
    public String getReleaseUserId() {
        return releaseUserId;
    }

    /**
     * @param releaseUserId the releaseUserId to set
     */
    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    /**
     * @return the releaseName
     */
    public String getReleaseName() {
        return releaseName;
    }

    /**
     * @param releaseName the releaseName to set
     */
    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    /**
     * @return the releaseOpenId
     */
    public String getReleaseOpenId() {
        return releaseOpenId;
    }

    /**
     * @param releaseOpenId the releaseOpenId to set
     */
    public void setReleaseOpenId(String releaseOpenId) {
        this.releaseOpenId = releaseOpenId;
    }


    /**
     * @return the fCheckId
     */
    public String getfCheckId() {
        return fCheckId;
    }

    /**
     * @param fCheckId the fCheckId to set
     */
    public void setfCheckId(String fCheckId) {
        this.fCheckId = fCheckId;
    }

    /**
     * @return the fCheckUserName
     */
    public String getfCheckUserName() {
        return fCheckUserName;
    }

    /**
     * @param fCheckUserName the fCheckUserName to set
     */
    public void setfCheckUserName(String fCheckUserName) {
        this.fCheckUserName = fCheckUserName;
    }

    /**
     * @return the fCheckOpenId
     */
    public String getfCheckOpenId() {
        return fCheckOpenId;
    }

    /**
     * @param fCheckOpenId the fCheckOpenId to set
     */
    public void setfCheckOpenId(String fCheckOpenId) {
        this.fCheckOpenId = fCheckOpenId;
    }

    /**
     * @return the fCheckTime
     */
    public java.util.Date getfCheckTime() {
        return fCheckTime;
    }

    /**
     * @param fCheckTime the fCheckTime to set
     */
    public void setfCheckTime(java.util.Date fCheckTime) {
        this.fCheckTime = fCheckTime;
    }

    /**
     * @return the zCheckId
     */
    public String getzCheckId() {
        return zCheckId;
    }

    /**
     * @param zCheckId the zCheckId to set
     */
    public void setzCheckId(String zCheckId) {
        this.zCheckId = zCheckId;
    }

    /**
     * @return the zCheckUserName
     */
    public String getzCheckUserName() {
        return zCheckUserName;
    }

    /**
     * @param zCheckUserName the zCheckUserName to set
     */
    public void setzCheckUserName(String zCheckUserName) {
        this.zCheckUserName = zCheckUserName;
    }

    /**
     * @return the zCheckOpenId
     */
    public String getzCheckOpenId() {
        return zCheckOpenId;
    }

    /**
     * @param zCheckOpenId the zCheckOpenId to set
     */
    public void setzCheckOpenId(String zCheckOpenId) {
        this.zCheckOpenId = zCheckOpenId;
    }

    /**
     * @return the zCheckTime
     */
    public java.util.Date getzCheckTime() {
        return zCheckTime;
    }

    /**
     * @param zCheckTime the zCheckTime to set
     */
    public void setzCheckTime(java.util.Date zCheckTime) {
        this.zCheckTime = zCheckTime;
    }

	/*
     * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */


    public String getAdverurl() {
        return adverurl;
    }

    public void setAdverurl(String adverurl) {
        this.adverurl = adverurl;
    }

    public Integer getAdstate() {
        return adstate;
    }

    public void setAdstate(Integer adstate) {
        this.adstate = adstate;
    }


    public int getAdcategory() {
        return adcategory;
    }

    public void setAdcategory(int adcategory) {
        this.adcategory = adcategory;
    }


}
