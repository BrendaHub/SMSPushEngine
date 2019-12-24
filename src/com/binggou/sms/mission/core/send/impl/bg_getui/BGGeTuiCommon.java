package com.binggou.sms.mission.core.send.impl.bg_getui;

import com.binggou.mission.Task;
import com.binggou.mission.common.DesUtil;
import com.binggou.sms.mission.core.send.SendMessage;
import com.binggou.sms.mission.core.send.impl.bg_sms.BGSMSCommon;
import com.gexin.fastjson.JSON;
import com.gexin.fastjson.JSONObject;
import com.huawei.insa2.util.Args;
import com.huawei.insa2.util.Cfg;

import java.io.IOException;
import java.util.Date;

/**
 * 云并购2.0 个推消息推送服务商接口实现类
 * 主要完成APP的消息推送服务
 * Created by XYZ on 16/12/16.
 */
public class BGGeTuiCommon implements SendMessage {
    //发送短信实例
    private static BGGeTuiCommon instance=null;
    private static String APPID = "N0dAbPWGvj9G7OAt09DL03";
    private static String APPKEY = "qyYE2pqXztAPCCZzGa1WR4";
    private static String MASTERSECRET = "joULnAd4sG8rQe8zNp3Bf7";
    private static String URL = "http://sdk.open.api.igexin.com/serviceex";
    private static String LOG_URL = "";

    synchronized public static BGGeTuiCommon getInstance(String config) {
        if(instance==null){
            if(config == null) {
                System.exit(1);
            }
            instance = new BGGeTuiCommon(config);
        }
        return instance;
    }

    //带通道编号的构适函数
    private BGGeTuiCommon(String config){
        //密钥
        String secretkey = "bg2.0!@#$%#^@^";
        Args args = null;
        try {
            args = new Cfg("SMProxy.xml", false).getArgs("Channel" + config);
        } catch (IOException e) {
//            e.printStackTrace();
            try {
                args = new Cfg("bin/SMProxy.xml", false).getArgs("Channel" + config);
            } catch (IOException e1) {}
        }
        //初始化参数
        this.APPID = args.get("getui-app-id", "");
        this.APPKEY = args.get("getui-app-key", "");
        this.MASTERSECRET = args.get("getui-mastersecret", "");
        this.URL = args.get("getui-url", "");
        this.LOG_URL = args.get("LOG-URL", "");

        //System.out.println(" 获取参数如下：    ");
        //System.out.println(" AppID = " + this.APPID);
        //System.out.println(" appkey = " + this.APPKEY);
        //System.out.println("MASTERSECRET =   " + this.MASTERSECRET);
        //System.out.println(" URL = " + this.URL);
        //System.out.println(" LOG_URL = " + this.LOG_URL);

        Constants.setAPPID(this.APPID);
        Constants.setAPPKEY(this.APPKEY);
        Constants.setMASTERSECRET(this.MASTERSECRET);
        Constants.setURL(this.URL);
        Constants.setLogUrl(this.LOG_URL);
    }

    /**
     * 发送方法
     * @param task
     * @return
     */
    public Task sendMessage(Task task){
        //System.out.println("################################################################################################################");
        String title = (String)task.getAttribValue("title");//推送信息标题
        String message = (String)task.getAttribValue("content");//推送信息内容
        String exParam = (String)task.getAttribValue("extParam");//推送信息的扩展参数
        if(exParam != null && !"".equals(exParam)){
            try{
               JSONObject json =  JSON.parseObject(exParam);
            }catch(Exception e){
                //转换成json对象异常， 返回。
                task.addAttrib("SUBMIT", "bg_getui_tojsonerror");
                task.addAttrib("ERR_MSG", "扩展参数是非法的json串,请检查扩展参数格式");
                task.addAttrib("pushId",new Integer(1000000));
                return task;
            }
        }
        String pushType = (String)task.getAttribValue("pushType");//推送的方式，  0：通知 / 1：透传
        String pushOs = (String)task.getAttribValue("pushOs");//推送的平台  0：ios/ 1：android
        String badgeNum = (String)task.getAttribValue("badgeNum");//设置IOS角标

        PushResult pr = null;
        //根据不同平台的推送平台，选择不同的推送服务
        if(!"".equals(pushOs) && pushOs != null){
            if(Integer.parseInt(pushOs) == 0){//ios
                //初始化个推对象
                GetTuiService getTuiService = new GetTuiService();
//                .pushTransmissionToSingleUserForIOS("601cc5590f06314e0cededb34dcc9fc4","{\"key0\":1,\"key1\":3,\"key2\":\"value3\"}" , "f11fffllllllllffg测试IOS 扩展参数", 1,null);
                pr = getTuiService.pushTransmissionToSingleUserForIOS(task.getMobileTo(), exParam, message, 1, null);

            }else if(Integer.parseInt(pushOs) == 1){//android
                //初始化个推对象
                GetTuiService getTuiService = new GetTuiService();
                //在android推送分支里， 需要判断是通知还是透传类型的动作
                if(Integer.parseInt(pushType) == 0){//通知
                    Notification note = new Notification();
                    note.setTitle(title);
                    note.setText(message);
                    note.setTransmissionContent(exParam);
                    pr = new GetTuiService().pushMessageToSingleUser(task.getMobileTo(), note, Constants.URL, null);  //android
                }else if(Integer.parseInt(pushType) == 1){//透传
                    //纯透传的消息， 只发送扩展参数部分内容,
                    //参数说明：CID ；扩展参数 ； 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动 ； 离线保留时长
                    pr = new GetTuiService().pushTransmissionToSingleUserForAndroid(task.getMobileTo(), exParam, 1, null);
                }
            }

            if(pr != null){
                if("ok".equals(pr.getResult()) && pr.getStatus().indexOf("success") != -1) {
                    //成功
                    String result_code = pr.getTaskId();
                    task.addAttrib("SUBMIT", "bg_getui_success");
                    task.addAttrib("ERR_MSG", "个推成功");
                    task.addAttrib("pushId",result_code);
                    return task;
                }else{
                    //失败
                    String result_code = pr.getTaskId();
                    task.addAttrib("SUBMIT", "bg_getui_fail");
                    task.addAttrib("ERR_MSG", "个推失败");
                    task.addAttrib("pushId",result_code);
                    return task;
                }
            }else{
                //失败
                String result_code = pr.getTaskId();
                task.addAttrib("SUBMIT", "bg_getui_fail");
                task.addAttrib("ERR_MSG", "推送返回结果异常");
                task.addAttrib("pushId", "");
                return task;
            }
        }else{
            //失败
            String result_code = pr.getTaskId();
            task.addAttrib("SUBMIT", "bg_getui_fail");
            task.addAttrib("ERR_MSG", "推送记录没有标识ios或android");
            task.addAttrib("pushId",result_code);
            return task;
        }
    }
}
