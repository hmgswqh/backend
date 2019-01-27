package com.youthchina.domain.qingyang;

import com.youthchina.domain.Qinghong.Location;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Job {
    /*主键, 职位ID (JOB_INFO)*/
    private Integer jobId;

    private String  jobName;
    private String  jobProfCode;
    private Date    jobStartTime;
    private Date    jobEndTime;
    private Integer jobType;
    private String  jobDescription;
    private String  jobDuty;
    private String  jobHighlight;

/*
create table JOB_INFO
(
	JOB_ID int auto_increment comment '职位ID'
		primary key,
	JOB_NAME varchar(200) not null comment '职位名称',
	JOB_PROF_CODE varchar(20) not null comment '职位类别编号',
	JOB_START_TIME date not null comment '职位起始时间',
	JOB_END_TIME date not null comment '职位截止时间',
	JOB_TYPE int not null comment '职位性质',
	JOB_DESCRIPTION varchar(200) not null comment '职位描述',
	JOB_DUTY varchar(200) null comment '职责描述',
	JOB_HIGHLIGHT varchar(200) null comment '职位亮点',
	JOB_SALARY_FLOOR int null comment '职位薪资下限',
	JOB_SALARY_CAP int null comment '职位薪资上限',
	JOB_LINK varchar(500) null comment '职位链接',
	CV_RECEI_MAIL varchar(200) not null comment '简历接收邮箱',
	CV_NAME_RULE varchar(200) null comment '简历命名规则',
	JOB_ACTIVE int not null comment '职位状态',
	HR_ID int not null comment '招聘者ID',
	COMPANY_ID int not null comment '企业ID',
	IS_DELETE int default 0 null comment '是否删除',
	IS_DELETE_TIME timestamp null comment '删除时间',
	constraint JOB_COMPANY_ID
		foreign key (COMPANY_ID) references COMPANY_INFO (company_id),
	constraint JOB_HR_ID
		foreign key (HR_ID) references HR_INFO (hr_id)
)
comment '职位基本信息表';


*/


    private Integer jobSalaryFloor;
    private Integer jobSalaryCap;
    private String  jobLink;
    private String  cvReceiMail;
    private String  cvNameRule;
    private Integer jobActive;
    private List<Location> jobLocationList;
    private List<Degree> jobReqList;
    private List<Industry> industries;
    private Profession profession;


    private Integer isDelete;
    private Timestamp isDeleteTime;

    private Company company;
    private Hr hr;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Hr getHr() {
        return hr;
    }


    public void setHr(Hr hr) {
        this.hr = hr;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobProfCode() {
        return jobProfCode;
    }

    public void setJobProfCode(String jobProfCode) {
        this.jobProfCode = jobProfCode;
    }

    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public List<Degree> getJobReqList() {
        return jobReqList;
    }

    public void setJobReqList(List<Degree> jobReqList) {
        this.jobReqList = jobReqList;
    }

    public List<Location> getJobLocationList() {
        return jobLocationList;
    }

    public void setJobLocationList(List<Location> jobLocationList) {
        this.jobLocationList = jobLocationList;
    }

    public String getJobHighlight() {
        return jobHighlight;
    }

    public void setJobHighlight(String jobHighlight) {
        this.jobHighlight = jobHighlight;
    }

    public Integer getJobSalaryFloor() {
        return jobSalaryFloor;
    }

    public void setJobSalaryFloor(Integer jobSalaryFloor) {
        this.jobSalaryFloor = jobSalaryFloor;
    }

    public Integer getJobSalaryCap() {
        return jobSalaryCap;
    }

    public void setJobSalaryCap(Integer jobSalaryCap) {
        this.jobSalaryCap = jobSalaryCap;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public String getCvReceiMail() {
        return cvReceiMail;
    }

    public void setCvReceiMail(String cvReceiMail) {
        this.cvReceiMail = cvReceiMail;
    }

    public String getCvNameRule() {
        return cvNameRule;
    }

    public void setCvNameRule(String cvNameRule) {
        this.cvNameRule = cvNameRule;
    }

    public Integer getJobActive() {
        return jobActive;
    }

    public void setJobActive(Integer jobActive) {
        this.jobActive = jobActive;
    }

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getIsDeleteTime() {
        return isDeleteTime;
    }

    public void setIsDeleteTime(Timestamp isDeleteTime) {
        this.isDeleteTime = isDeleteTime;
    }
}