package com.binggou.sms.mission.core.send.impl.bg_getui;

/**
 * 个推账号的参数
 */
public class Constants {
    // 首先定义一些常量, 修改成开发者平台获得的值
    public static  String APPID = "fBhJrCEKxCABik16Xpwmw";////N0dAbPWGvj9G7OAt09DL03";
    public static  String APPKEY = "jHdQSuHFS090GccfEI47D";//"qyYE2pqXztAPCCZzGa1WR4";
    public static  String MASTERSECRET = "nVL0C5cvBo7c0Sx8yODxH7";//"joULnAd4sG8rQe8zNp3Bf7";
    public static  String URL = "http://sdk.open.api.igexin.com/serviceex";

    public static  Integer DEFAULT_TRANSMISSIONTYPE = 2;

    public static  String LOG_URL= "http://p1.binggou.com/images/app_push_icon.png";

    public static String getAPPID() {
        return APPID;
    }

    public static void setAPPID(String APPID) {
        Constants.APPID = APPID;
    }

    public static String getAPPKEY() {
        return APPKEY;
    }

    public static void setAPPKEY(String APPKEY) {
        Constants.APPKEY = APPKEY;
    }

    public static String getMASTERSECRET() {
        return MASTERSECRET;
    }

    public static void setMASTERSECRET(String MASTERSECRET) {
        Constants.MASTERSECRET = MASTERSECRET;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Constants.URL = URL;
    }

    public static Integer getDefaultTransmissiontype() {
        return DEFAULT_TRANSMISSIONTYPE;
    }

    public static void setDefaultTransmissiontype(Integer defaultTransmissiontype) {
        DEFAULT_TRANSMISSIONTYPE = defaultTransmissiontype;
    }

    public static String getLogUrl() {
        return LOG_URL;
    }

    public static void setLogUrl(String logUrl) {
        LOG_URL = logUrl;
    }
}
