package com.youthchina.dto.Applicant;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.dto.ContactDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: youthchina
 * @description: 申请者信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:40
 **/
public class ApplicantResponseDTO {
    private Integer id;
    private String name;
    private String avatarUrl;
    private Boolean isInJob;
    private String currentCompanyName;
    private List<String> skills;
    private List<EducationResponseDTO> educations;
    private ContactDTO contactDTO;
//    private List<String> emails;
//    private List<String> phonenumbers;
    private List<WorkResponseDTO> experiences;
    private List<ProjectResponseDTO> projects;
    private List<ExtracurricularResponseDTO> extracurriculars;
    private List<CertificateResponseDTO> certificates;//todo: fixme

    public ApplicantResponseDTO() {
    }

    public ApplicantResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUsername();
        this.avatarUrl=student.getAvatarUrl();
        this.isInJob=student.getIsInJob();
        this.currentCompanyName=student.getCurrentCompanyName();
        this.skills=new ArrayList<>();
        for(LabelInfo labelInfo:student.getLabelInfos()){
            String label_chn=labelInfo.getLabel_chn();
            this.skills.add(label_chn);
        }
        this.educations = new ArrayList<>(student.getEducationInfos().size());
        for (EducationInfo educationInfo : student.getEducationInfos()) {
            this.educations.add(new EducationResponseDTO(educationInfo));
        }
        //contactDTO添加
        this.contactDTO=new ContactDTO(student.getEmail(),student.getPhonenumber());
//        this.emails = new ArrayList<>();
//        this.emails.add(student.getEmail());
//        this.phonenumbers = new ArrayList<>();
//        this.phonenumbers.add(student.getPhonenumber());
        this.experiences = new ArrayList<>(student.getWorks().size());
        for (Work work : student.getWorks()) {
            this.experiences.add(new WorkResponseDTO(work));
        }
        this.projects = new ArrayList<>(student.getProjects().size());
        for (Project project : student.getProjects()) {
            this.projects.add(new ProjectResponseDTO(project));
        }
        this.extracurriculars = new ArrayList<>(student.getActivities().size());
        for (Activity activity : student.getActivities()) {
            this.extracurriculars.add(new ExtracurricularResponseDTO(activity));
        }
        List<CertificateResponseDTO> certificates = new ArrayList<>(student.getCertificates().size());
        for(Certificate certificate: student.getCertificates()){
            certificates.add(new CertificateResponseDTO(certificate));
        }
        this.setCertificates(certificates);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getIsInJob() {
        return isInJob;
    }

    public void setIsInJob(Boolean inJob) {
        isInJob = inJob;
    }

    public ContactDTO getContactDTO() {
        return contactDTO;
    }

    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
    }

    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<EducationResponseDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationResponseDTO> educations) {
        this.educations = educations;
    }

//    public List<String> getEmails() {
//        return emails;
//    }
//
//    public void setEmails(List<String> emails) {
//        this.emails = emails;
//    }
//
//    public List<String> getPhonenumbers() {
//        return phonenumbers;
//    }
//
//    public void setPhonenumbers(List<String> phonenumbers) {
//        this.phonenumbers = phonenumbers;
//    }

    public List<WorkResponseDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkResponseDTO> experiences) {
        this.experiences = experiences;
    }

    public List<ProjectResponseDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectResponseDTO> projects) {
        this.projects = projects;
    }

    public List<ExtracurricularResponseDTO> getExtracurriculars() {
        return extracurriculars;
    }

    public void setExtracurriculars(List<ExtracurricularResponseDTO> extracurriculars) {
        this.extracurriculars = extracurriculars;
    }

    public List<CertificateResponseDTO> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateResponseDTO> certificates) {
        this.certificates = certificates;
    }


}
