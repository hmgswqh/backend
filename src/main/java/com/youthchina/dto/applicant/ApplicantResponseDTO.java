package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.dto.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: youthchina
 * @description: 申请者信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:40
 **/
public class ApplicantResponseDTO implements ResponseDTO<Student> {
    private Integer id;
    private String name;
    private String avatarUrl;
    private Boolean isInJob;
//    private OrganizationDTO currentCompany;
//    private List<String> skills;
//    private List<AdvantageLabelResponseDTO> skills;
    private List<EducationResponseDTO> educations;
//    private ContactDTO contacts;
    //    private List<String> emails;
//    private List<String> phonenumbers;
    private List<WorkResponseDTO> experiences;
    private List<ProjectResponseDTO> projects;
    private List<ExtracurricularResponseDTO> extracurriculars;
    private List<CertificateResponseDTO> certifications;//todo: fixme

    public ApplicantResponseDTO() {
    }

    public ApplicantResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUsername();
        this.avatarUrl = student.getAvatarUrl();
        this.isInJob = student.getIsInJob();
//        this.skills=new ArrayList<>();
//        for(AdvantageLabel advantageLabel:student.getAdvantageLabels()){
//            if(advantageLabel!=null){
//                this.skills.add(new AdvantageLabelResponseDTO(advantageLabel));
//            }
//        }
        this.educations = new ArrayList<>(student.getEducationInfos().size());

        for (EducationInfo educationInfo : student.getEducationInfos()) {
            if(educationInfo!=null){
                this.educations.add(new EducationResponseDTO(educationInfo));
            }

        }
        //contactDTO添加
//        this.contacts = new ContactDTO(student.getEmail(), student.getPhonenumber());
//        this.emails = new ArrayList<>();
//        this.emails.add(student.getEmail());
//        this.phonenumbers = new ArrayList<>();
//        this.phonenumbers.add(student.getPhonenumber());
        this.experiences = new ArrayList<>(student.getWorks().size());

        for (Work work : student.getWorks()) {
            if(work!=null){
                this.experiences.add(new WorkResponseDTO(work));
            }
        }

        this.projects = new ArrayList<>(student.getProjects().size());
        for (Project project : student.getProjects()) {
            if(project!=null){
                this.projects.add(new ProjectResponseDTO(project));
            }

        }
        this.extracurriculars = new ArrayList<>(student.getActivities().size());
        for (Activity activity : student.getActivities()) {
            if(activity!=null){
                this.extracurriculars.add(new ExtracurricularResponseDTO(activity));
            }

        }
        List<CertificateResponseDTO> certificates = new ArrayList<>(student.getCertificates().size());
        for (Certificate certificate : student.getCertificates()) {
            if(certificate!=null){
                certificates.add(new CertificateResponseDTO(certificate));
            }

        }
        this.setCertifications(certificates);
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

    public List<CertificateResponseDTO> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificateResponseDTO> certifications) {
        this.certifications = certifications;
    }


    @Override
    public void convertToDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUsername();
        this.avatarUrl = student.getAvatarUrl();
        this.isInJob = student.getIsInJob();
//        this.currentCompany = null;
//        this.skills = new ArrayList<>();
//        for (LabelInfo labelInfo : student.getLabelInfos()) {
//            String label_chn = labelInfo.getLabel_chn();
//            this.skills.add(label_chn);
//        }
        this.educations = new ArrayList<>(student.getEducationInfos().size());
        for (EducationInfo educationInfo : student.getEducationInfos()) {
            this.educations.add(new EducationResponseDTO(educationInfo));
        }
//        //contactDTO添加
//        this.contacts = new ContactDTO(student.getEmail(), student.getPhonenumber());
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
        for (Certificate certificate : student.getCertificates()) {
            certificates.add(new CertificateResponseDTO(certificate));
        }
        this.setCertifications(certificates);
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

    public Boolean getInJob() {
        return isInJob;
    }

    public void setInJob(Boolean inJob) {
        isInJob = inJob;
    }


}
