package com.binggou.sms.mission.core.send.impl.bg_getui;

import java.io.Serializable;

/**
 * Created by XYZ on 16/12/26.
 */
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 长度：40中/英字符
     * 必填：是
     * 默认值：
     * 说明:通知标题
     */
    private String title;

    /**
     * 长度：600中/英字符
     * 必填：是
     * 默认值：
     * 说明:通知内容
     */
    private String text;

    /**
     * 长度：40中/英字符
     * 必填：是
     * 默认值：
     * 说明:通知的图标名称，包含后缀名（需要在客户端开发时嵌入），如“push.png”
     */
    private String logo;

    /**
     * 长度：
     * 必填：否
     * 默认值：True
     * 说明:收到通知是否响铃：true响铃，false不响铃。默认响铃
     */
    private Boolean isRing;

    /**
     * 长度：
     * 必填：否
     * 默认值：true
     * 说明:收到通知是否振动：true振动，false不振动。默认振动
     */
    private Boolean isVibrate;

    /**
     * 长度：
     * 必填：否
     * 默认值：True
     * 说明:通知是否可清除：true可清除，false不可清除。默认可清除
     */
    private Boolean isClearable;


    /**
     * 长度：
     * 必填：是
     * 默认值：
     * 说明:收到消息是否立即启动应用：1为立即启动，2则广播等待客户端自启动
     */
    private Integer transmissionType;


    /**
     * 长度：
     * 必填：是
     * 默认值：
     * 说明:透传内容，不支持转义字符
     */
    private String transmissionContent;
    /**
     * 长度：
     * 必填：是
     * 默认值：
     * 说明:透传内容，不支持转义字符
     */
    private String logoUrl;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public Boolean getIsRing() {
        return isRing;
    }
    public void setIsRing(Boolean isRing) {
        this.isRing = isRing;
    }
    public Boolean getIsVibrate() {
        return isVibrate;
    }
    public void setIsVibrate(Boolean isVibrate) {
        this.isVibrate = isVibrate;
    }
    public Boolean getIsClearable() {
        return isClearable;
    }
    public void setIsClearable(Boolean isClearable) {
        this.isClearable = isClearable;
    }
    public Integer getTransmissionType() {
        return transmissionType;
    }
    public void setTransmissionType(Integer transmissionType) {
        this.transmissionType = transmissionType;
    }
    public String getTransmissionContent() {
        return transmissionContent;
    }
    public void setTransmissionContent(String transmissionContent) {
        this.transmissionContent = transmissionContent;
    }
    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
