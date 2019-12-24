package com.binggou.sms.mission.core.send.impl.bg_getui;

import com.gexin.fastjson.JSON;
import com.gexin.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by XYZ on 16/12/26.
 */
public class GetTuiService {

    public static void main(String[] args) {
        String CID = "4ae44c884a826ac7844a9fb450746344";  //android  //"03b6a4bf4991f55285f3010bce71dd41";//
//        String CID = "ca76e74df2db5629b5303486b1530118b576f6ec4bc49c79d50715a383c2f3be";  //IOS
        String tc = "{'key0':'value1','key1':'value2','key2':'value3'}";
        Notification note = new Notification();
        note.setTitle("测试通知");
        note.setText("立即广播的消息Android消息推送测试！");
        note.setTransmissionContent("{'key0':'key0va测试lue','key1':'测试','key2':'value3'}");
//        PushResult pr = new GetTuiService().pushMessageToSingleUser(CID, note, Constants.URL, 1);  //android
//        PushResult pr = new GetTuiService().pushTransmissionToSingleUserForAndroid(CID, "12211立即广播的消息Android消息推送测试！", 1, 1);?
//        PushResult pr = new GetTuiService().pushTransmissionToSingleUserForIOS("601cc5590f06314e0cededb34dcc9fc4","{\"key0\":\"key0va测试lue\",\"key1\":\"测试\",\"key2\":\"value3\"}" , "f11fffllllllllffg测试IOS 扩展参数", 1,null);
        //System.out.println(JSON.toJSON(pr));
        PushResult pr = new GetTuiService().pushTransmissionToSingleUserForIOS("278b978734f3e6bf39158fb3d6298d07", "{\"tag\":1,\"sid\":100000543}", "IOS自定义参数推送测试", 1, null);
        System.out.println(JSON.toJSON(pr));
    }

    /**
     * 向单个用户推送透传消息,所有单个用户的推送建议用这个接口 android
     * @param CID 个推ID
     * @param transmissionContent 推送的消息内容，格式为JSON串
     * @param transmissionType 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
     * @param offlineExpireTime 离线时间，单位为小时
     * @param offlineExpireTime 离线时间，单位为小时
     * @return PushResult
     */
    @SuppressWarnings("finally")
    public PushResult pushTransmissionToSingleUserForAndroid(String CID, String transmissionContent , Integer transmissionType, Integer offlineExpireTime) {

        String host =null;
        IGtPush push = new IGtPush(host, Constants.APPKEY, Constants.MASTERSECRET);
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(Constants.APPID);
        template.setAppkey(Constants.APPKEY);
        template.setTransmissionContent(transmissionContent);
        template.setTransmissionType(transmissionType);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime((offlineExpireTime == null || offlineExpireTime == 0 ) ? 24 * 1000 * 3600 : offlineExpireTime * 1000 * 3600);
        message.setData(template);
        message.setPushNetWorkType(0); // 可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();
        target.setAppId(Constants.APPID);
        target.setClientId(CID);
        return pushAction(push, message, target);

    }
    /**
     * 向单个用户推送透传消息,所有单个用户的推送建议用这个接口 ios
     * @param CID 个推ID
     * @param transmissionContent 推送时的扩展参数，格式为JSON串，{"key0":"value1","key1":"value2","key2":"value3"}
     * @param transmissionType 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
     * @param offlineExpireTime 离线时间，单位为小时
     * @return PushResult
     */
    @SuppressWarnings("finally")
    public PushResult pushTransmissionToSingleUserForIOS(String CID, String transmissionContent ,String pushinfo, Integer transmissionType,  Integer offlineExpireTime) {

        String host =null;
        System.out.println("URL = " + Constants.URL);
        System.out.println("APPID = "  + Constants.APPID);
        System.out.println("APPKey = " + Constants.APPKEY);
        System.out.println("MasterSecret = " + Constants.MASTERSECRET);
        IGtPush push = new IGtPush(Constants.URL, Constants.APPKEY, Constants.MASTERSECRET);

        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(Constants.APPID);
        template.setAppkey(Constants.APPKEY);
        //在 transmissionContent 这个属性值里面， 真接设置成为json 字符串，如 {“key1”:"value1","key2":"value2","key3":"value3"} ， 字符串里面不能用任何的转义字符
        template.setTransmissionContent(transmissionContent);
        template.setTransmissionType(transmissionType);//收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动

        //ios的通知
        APNPayload payload = new APNPayload();
        payload.setAutoBadge("+1");
        payload.setContentAvailable(transmissionType);//收到消息是否立即启动应用： 1为立即启动，2则广播等待客户端自启动
        payload.setSound("default");
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(pushinfo));
        //转换扩展参数的格式：
        JSONObject json = JSON.parseObject(transmissionContent);
        if(json != null && json.size() > 0 ){
//            int len = json.size();
            //System.out.println("len = " + len);
            Set keys = json.keySet();
            if(keys != null && !keys.isEmpty()){
                for(Iterator it = keys.iterator(); it.hasNext(); ){
                    String _key = (String)it.next();
                    System.out.println(_key + " = " + json.getString(_key));
                    payload.addCustomMsg(_key, json.getString(_key));
                }
            }
        }
        //字典模式使用下者
        //payload.setAlertMsg(getDictionaryAlertMsg());
        template.setAPNInfo(payload);


        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime((offlineExpireTime == null || offlineExpireTime == 0 ) ? 24 * 1000 * 3600 : offlineExpireTime * 1000 * 3600);
        message.setData(template);
        message.setPushNetWorkType(0); // 可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();
        target.setAppId(Constants.APPID);
        target.setClientId(CID);
        return pushAction(push, message, target);

    }

    public static TransmissionTemplate getTemplate() {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(Constants.APPID);
        template.setAppkey(Constants.APPKEY);
        template.setTransmissionContent("透传内容");
        template.setTransmissionType(1);
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
        //字典模式使用下者
        payload.setAlertMsg(getDictionaryAlertMsg());
        template.setAPNInfo(payload);
        return template;
    }

    //字典模式使用当前方法
    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody("body");
        alertMsg.setActionLocKey("ActionLockey");
        alertMsg.setLocKey("LocKey");
        alertMsg.addLocArg("loc-args");
        alertMsg.setLaunchImage("launch-image");
        // IOS8.2以上版本支持
        alertMsg.setTitle("Title");
        alertMsg.setTitleLocKey("TitleLocKey");
        alertMsg.addTitleLocArg("TitleLocArg");
        return alertMsg;
    }

    /**
     * APNS 消息推送方法
     */
    public PushResult pushApnsMessageToSingleUser(String host, String CID){
        IGtPush push = new IGtPush(host, Constants.APPKEY, Constants.MASTERSECRET);
        SingleMessage smge = new SingleMessage();
//        APNPayload apnload = new APNPayload();
//        APNPayload.AlertMsg alertMsg = new APNPayload.SimpleAlertMsg("IOS 测试");
//        apnload.setAlertMsg(alertMsg);
//        TransmissionTemplate temp = new TransmissionTemplate();
////        ApnPayload payload = new ApnPayload();
//        temp.setP
        smge.setData(getTemplate());
        IPushResult ret = push.pushAPNMessageToSingle(Constants.APPID, CID, smge);
        PushResult pushResult = new PushResult();
        String taskId = (String) ret.getResponse().get("taskId");
        String result = (String) ret.getResponse().get("result");
        String status = (String) ret.getResponse().get("status");
        if(result != null&&!"".equals(result))
            pushResult.setResult(result);
        if(taskId != null&&!"".equals(taskId))
            pushResult.setTaskId(taskId);
        if(status != null&&!"".equals(taskId))
            pushResult.setStatus(status);
        return pushResult;
    }

    /**
     * 推送通知给单个安卓用户
     * @param CID
     * @param notification
     * @param host
     * @return
     */
    @SuppressWarnings({ "finally"})
    public PushResult pushMessageToSingleUser(String CID, Notification notification, String host, Integer offlineExpireTime) {
        IGtPush push = new IGtPush(host, Constants.APPKEY, Constants.MASTERSECRET);
        NotificationTemplate template = new NotificationTemplate();

        template.setAppId(Constants.APPID);
        template.setAppkey(Constants.APPKEY);
        template.setTitle(notification.getTitle());
        template.setText(notification.getText());
        if(notification.getLogo() != null&&!"".equals(notification.getLogo()))
            template.setLogo(notification.getLogo());
        else{
            template.setLogo(Constants.LOG_URL);
        }
        if(notification.getIsRing() != null&&!"".equals(notification.getIsRing()))
            template.setIsRing(notification.getIsRing());
        if(notification.getIsVibrate() != null&&!"".equals(notification.getIsVibrate()))
            template.setIsVibrate(notification.getIsVibrate());
        if(notification.getIsClearable() != null&&!"".equals(notification.getIsClearable()))
            template.setIsClearable(notification.getIsClearable());

        if(notification.getTransmissionType() != null&&!"".equals(notification.getTransmissionType()))
            template.setTransmissionType(1);
        else{
            template.setTransmissionType(1);
        }
        if(notification.getTransmissionContent() != null&&!"".equals(notification.getTransmissionContent()))
            template.setTransmissionContent(notification.getTransmissionContent());
        if(notification.getLogoUrl() != null&&!"".equals(notification.getLogoUrl()))
            template.setLogoUrl(notification.getLogoUrl());
        else{
            template.setLogoUrl(Constants.LOG_URL);
        }

        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime((offlineExpireTime == null || offlineExpireTime == 0 ) ? 24 * 3600 * 1000 : offlineExpireTime * 1000 * 3600);
        message.setData(template);
        message.setPushNetWorkType(0); // 可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();
        target.setAppId(Constants.APPID);
        target.setClientId(CID);
        return pushAction(push, message, target);

    }

    private PushResult pushAction(IGtPush push, SingleMessage message, Target target){
        IPushResult ret = null;
        PushResult pushResult = new PushResult();
        try {
            ret = push.pushMessageToSingle(message, target);

        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());

        }
        finally{
            String taskId = (String) ret.getResponse().get("taskId");
            String result = (String) ret.getResponse().get("result");
            String status = (String) ret.getResponse().get("status");
            if(result != null&&!"".equals(result))
                pushResult.setResult(result);
            if(taskId != null&&!"".equals(taskId))
                pushResult.setTaskId(taskId);
            if(status != null&&!"".equals(taskId))
                pushResult.setStatus(status);
            return pushResult;
        }
    }
}
