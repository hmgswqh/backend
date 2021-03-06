package com.youthchina.domain.Qinghong;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.applicant.*;
import com.youthchina.util.HasId;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: youthchina
 * @description: 学生实体，包含所有学生信息
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:08
 **/
public class Student extends User implements HasId<Integer> {
    private Integer stu_id;
    private Boolean isInJob;
    private String currentCompanyName;
    private List<EducationInfo> educationInfos;
    private SubInfo subInfo;
    private List<Project> projects;
    private List<Work> works;
    private List<Activity> activities;
    private List<JobCollect> jobCollects;
    private List<CompCollect> compCollects;
    private List<AdvantageLabel> advantageLabels;
    private List<PreferJob> preferJobs;
    private List<PreferIndustry> preferIndustries;
    private List<PreferCity> preferCities;
    private PreferSalary preferSalary;
    private IntroductionVideo introductionVideo;
    private List<Noti> notis;
    private List<JobApply> jobApplies;
    private List<Certificate> certificates;
    private List<LabelInfo> labelInfos;

    public Student(ApplicantRequestDTO applicantRequestDTO) {
        this.isInJob= applicantRequestDTO.getIsInJob();
//        this.setUsername(applicantRequestDTO.getName());
        this.setAvatarUrl(applicantRequestDTO.getAvatarUrl());
        this.currentCompanyName= applicantRequestDTO.getCurrentCompanyId().toString();
        //对于教育信息的转化
        List<EducationInfo> educationInfos=new ArrayList<>();
        for(EducationRequestDTO educationRequestDTO : applicantRequestDTO.getEducations()){
            EducationInfo educationInfo=new EducationInfo(educationRequestDTO);
            educationInfos.add(educationInfo);
        }
        this.educationInfos=educationInfos;
        //目前联系人问题
        this.setEmail(applicantRequestDTO.getContacts().getEmails().get(0));
        this.setPhonenumber(applicantRequestDTO.getContacts().getPhonenumbers().get(0));
        //对于职位的转化
        List<Work> works=new ArrayList<>();
        for(WorkRequestDTO workRequestDTO : applicantRequestDTO.getExperiences()){
            Work work=new Work(workRequestDTO);
            works.add(work);
        }
        this.works=works;
        //对于项目的转化
        List<Project> projects=new ArrayList<>();
        for(ProjectRequestDTO projectRequestDTO : applicantRequestDTO.getProjects()){
            Project project=new Project(projectRequestDTO);
            projects.add(project);
        }
        this.projects=projects;
        //对于组织活动的转化
        List<Activity> activities=new ArrayList<>();
        for(ExtracurricularRequestDTO extracurricularRequestDTO : applicantRequestDTO.getExtracurriculars()){
            Activity activity=new Activity(extracurricularRequestDTO);
            activities.add(activity);
        }
        this.activities=activities;

        //对于技能证书的转化
        List<Certificate> certificates=new ArrayList<>();
        for(CertificateRequestDTO certificateRequestDTO : applicantRequestDTO.getCertifications()){
            Certificate certificate=new Certificate(certificateRequestDTO);
            certificates.add(certificate);
        }
        this.certificates=certificates;
        //公司信息的设置

//        Company company=new Company();
//        this.company=company;
//        company.setCompanyId(applicantRequestDTO.getId());

        //优势标签的设置
        List<LabelInfo> labelInfos=new ArrayList<>();
        for(String s: applicantRequestDTO.getSkills()){
            LabelInfo labelInfo=new LabelInfo();
            labelInfo.setLabel_code(s);
            labelInfos.add(labelInfo);
        }
        this.labelInfos=labelInfos;
    }

    public Student(){}


    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }


    public Boolean getIsInJob() {
        return isInJob;
    }

    public void setIsInJob(Boolean inJob) {
        isInJob = inJob;
    }

    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
    }

    public List<EducationInfo> getEducationInfos() {
        return educationInfos;
    }

    public void setEducationInfos(List<EducationInfo> educationInfos) {
        this.educationInfos = educationInfos;
    }

    public SubInfo getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(SubInfo subInfo) {
        this.subInfo = subInfo;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<JobCollect> getJobCollects() {
        return jobCollects;
    }

    public void setJobCollects(List<JobCollect> jobCollects) {
        this.jobCollects = jobCollects;
    }

    public List<CompCollect> getCompCollects() {
        return compCollects;
    }

    public void setCompCollects(List<CompCollect> compCollects) {
        this.compCollects = compCollects;
    }

    public List<AdvantageLabel> getAdvantageLabels() {
        return advantageLabels;
    }

    public void setAdvantageLabels(List<AdvantageLabel> advantageLabels) {
        this.advantageLabels = advantageLabels;
    }

    public List<PreferJob> getPreferJobs() {
        return preferJobs;
    }

    public void setPreferJobs(List<PreferJob> preferJobs) {
        this.preferJobs = preferJobs;
    }

    public List<PreferIndustry> getPreferIndustries() {
        return preferIndustries;
    }

    public void setPreferIndustries(List<PreferIndustry> preferIndustries) {
        this.preferIndustries = preferIndustries;
    }

    public List<PreferCity> getPreferCities() {
        return preferCities;
    }

    public void setPreferCities(List<PreferCity> preferCities) {
        this.preferCities = preferCities;
    }

    public PreferSalary getPreferSalary() {
        return preferSalary;
    }

    public void setPreferSalary(PreferSalary preferSalary) {
        this.preferSalary = preferSalary;
    }

    public IntroductionVideo getIntroductionVideo() {
        return introductionVideo;
    }

    public void setIntroductionVideo(IntroductionVideo introductionVideo) {
        this.introductionVideo = introductionVideo;
    }

    public List<Noti> getNotis() {
        return notis;
    }

    public void setNotis(List<Noti> notis) {
        this.notis = notis;
    }

    public List<JobApply> getJobApplies() {
        return jobApplies;
    }

    public void setJobApplies(List<JobApply> jobApplies) {
        this.jobApplies = jobApplies;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<LabelInfo> getLabelInfos() {
        return labelInfos;
    }

    public void setLabelInfos(List<LabelInfo> labelInfos) {
        this.labelInfos = labelInfos;
    }

}
