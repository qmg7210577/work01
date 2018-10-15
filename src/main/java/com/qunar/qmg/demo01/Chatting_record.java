package com.qunar.qmg.demo01;

import java.util.Date;

/**
 * Created by menggao.qi on 2018/10/15.
 */
public class Chatting_record {

    //发言人信息
    private UserInfo userInfo;
    //发言时间
    private Date createTime;
    //聊天内容
    private String content;


    public Chatting_record(UserInfo userInfo, Date createTime, String content) {
        this.userInfo = userInfo;
        this.createTime = createTime;
        this.content = content;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Chatting_record{" +
                "userInfo=" + userInfo +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
