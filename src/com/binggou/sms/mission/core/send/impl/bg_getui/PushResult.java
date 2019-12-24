package com.binggou.sms.mission.core.send.impl.bg_getui;

import java.io.Serializable;

/**
 * Created by XYZ on 16/12/26.
 */
public class PushResult implements Serializable {
    private static final long serialVersionUID = 1L;

    //推送任务ID  成功之后的返回值  OSS-0418_b9fc80e0788340f1d4418d27481924ad
    String taskId;
    //推送结果 成功之后的返回值ok
    String result;
    //推送状态 成功之后的返回值  successed_online
    String status;
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
