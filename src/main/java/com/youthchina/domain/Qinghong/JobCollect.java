package com.youthchina.domain.Qinghong;

import java.util.Date;

/**
 * @program: youthchina
 * @description: 职位收藏实体
 * @author: Qinghong Wang
 * @create: 2018-11-29 16:59
 **/
public class JobCollect {
    private Integer collect_id;
    private Integer job_id;
    private Date job_coll_time;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Date getJob_coll_time() {
        return job_coll_time;
    }

    public void setJob_coll_time(Date job_coll_time) {
        this.job_coll_time = job_coll_time;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Date is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}