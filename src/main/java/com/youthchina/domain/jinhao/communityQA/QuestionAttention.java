package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class QuestionAttention {
    private Integer atten_id;
    private String user_id;
    private Timestamp atten_time;
    private Integer atten_cancel;
    private Timestamp atten_cancel_time;

    public Integer getAtten_cancel() {
        return atten_cancel;
    }

    public void setAtten_cancel(Integer atten_cancel) {
        this.atten_cancel = atten_cancel;
    }

    public Timestamp getAtten_cancel_time() {
        return atten_cancel_time;
    }

    public void setAtten_cancel_time(Timestamp atten_cancel_time) {
        this.atten_cancel_time = atten_cancel_time;
    }

    public Integer getAtten_id() {
        return atten_id;
    }

    public void setAtten_id(Integer atten_id) {
        this.atten_id = atten_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Timestamp getAtten_time() {
        return atten_time;
    }

    public void setAtten_time(Timestamp atten_time) {
        this.atten_time = atten_time;
    }
}
