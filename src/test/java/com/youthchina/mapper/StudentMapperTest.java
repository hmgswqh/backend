package com.youthchina.mapper;

import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.domain.qingyang.Degree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: V-0.1
 * @description: 对于studentmapper进行测试
 * @author: Qinghong Wang
 * @create: 2019-01-01 21:43
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentMapperTest {
    @Autowired
    ApplicantMapper applicantMapper;

    @Test
    public void testGetEducations() {
        List<EducationInfo> educationInfos = applicantMapper.getEducations(10);
        if (educationInfos != null) {
            System.out.print("测试成功");
        }

    }

    @Test
    public void testGetWorks() {
        List<Work> works = applicantMapper.getWorks(10);
        Assert.assertNotNull(works);
        System.out.print(works.get(0).getWork_company());

    }

    @Test
    public void testGetActivities() {
        List<Activity> activities = applicantMapper.getActivities(1);
        System.out.print(activities.get(0).getAct_detail());

    }

    @Test
    public void testGetProjects() {
        List<Project> projects = applicantMapper.getProjects(10);
        Assert.assertNotNull(projects);
        System.out.print(projects.get(0).getDeliver_pub_insti());

    }

    @Test
    public void testGetCertificates() {
        List<Certificate> certificates = applicantMapper.getCertificates(10);
        Assert.assertNotNull(certificates);
        System.out.print(certificates.get(0).getCertificate_insti());

    }

//    @Test
//    public void testGetAdvantageSkills(){
//        List<AdvantageLabel> advantageLabels=applicantMapper.getAdvantageLabels(10);
//        System.out.print("1");
//    }

//    @Test
//    public void testGetStudentInfo() {
//        Student student = applicantMapper.getStudentInfo(10);
//        System.out.print(student.getLabelInfos().get(0).getLabel_chn());
//
//    }


    @Test
    public void testAddApply() {
        JobApply jobApply = new JobApply();
        jobApply.setJob_cv_send(1);
        jobApply.setJob_apply_status("success");
        jobApply.setStu_id(1);
        jobApply.setJob_id(1);
        jobApply.setDocu_local_id("111");

        Integer key = applicantMapper.addApply(jobApply);
        if (key != 0) {
            System.out.print("测试成功");
        }
        System.out.print(applicantMapper.getJobApplies(1).get(0).getJob_apply_time());


    }

    @Test
    public void testGetJobApplies() {
        List<JobApply> jobApplies = applicantMapper.getJobApplies(1);


    }

    @Test
    public void testGetUserInfo() {
        UserInfo userInfo = applicantMapper.getUserInfo(1);
        Assert.assertNotNull(userInfo);
        System.out.print(userInfo.getUser_pass());

    }

    @Test
    public void testDeleteJobCollect() {
        Integer key = applicantMapper.deleteJobCollect(1);
        if (key != 0) {
            System.out.print("测试成功");
        }
        System.out.print(key);

    }

    @Test
    public void testDeleteCompCollect() {
        Integer key = applicantMapper.deleteCompCollect(1);
        if (key != 0) {
            System.out.print("测试成功");
        }
        System.out.print(key);

    }

    @Test
    public void testGetJobCollect() {
        List<JobCollect> jobCollects = applicantMapper.getJobCollects(1);
        Assert.assertNotNull(jobCollects);
        System.out.print(jobCollects.get(0).getJob().getCvReceiMail());

    }

    @Test
    public void testGetCompCollect() {
        List<CompCollect> compCollects = applicantMapper.getCompCollects(10);
        Assert.assertNotNull(compCollects);
    }

    @Test
    public void testGetOneJobCollect() {
        JobCollect jobCollect = applicantMapper.getOneJobCollect(1,1);

    }

    @Test
    public void testGetOneCompCollect() {
        JobCollect jobCollect = applicantMapper.getOneJobCollect(1,1);
        Assert.assertNotNull(jobCollect);
        System.out.print(jobCollect.getJob_id());
    }

    @Test
    public void testAddJobCollect() {
        JobCollect jobCollect = new JobCollect();
        jobCollect.setJob_id(1);
        jobCollect.setStu_id(1);
        jobCollect.setIs_delete(0);
        Integer integer = applicantMapper.addJobCollect(jobCollect);
        if (integer != 0) {
            System.out.print(integer);
        }


    }

//    @Test
//    public void testAddStuInfo() {
//        Student student = new Student();
//        student.setCurrentCompanyName("google");
//        student.setIsInJob(true);
//        student.setId(1);
//
//        Integer integer = applicantMapper.insertStuInfo(student);
//        if (integer != 0) {
//            System.out.print(integer);
//        }
//
//    }

    @Test
    public void testAddEduInfo() {
        EducationInfo educationInfo = new EducationInfo();
        Degree degree = new Degree();
        University university=new University();
        university.setUnivers_id(10001);
        degree.setDegreeNum(1);
        educationInfo.setDegree(degree);
        Major major=new Major();
        major.setMajor_code("10101");
        educationInfo.setMajor(major);
        educationInfo.setEdu_college("cssa");
        educationInfo.setEdu_gpa((float) 3.3);
        educationInfo.setEdu_start(new Date());
        educationInfo.setEdu_end(new Date());
        educationInfo.setStu_id(1);
        educationInfo.setIs_delete(0);
        educationInfo.setUniversity(university);

        Integer integer = applicantMapper.insertEduInfo(educationInfo);
        if (integer != 0) {
            System.out.print(integer);
        }

    }


    @Test
    public void testAddSubCertificate() {
        Certificate certificate = new Certificate();
        certificate.setCertificate_name("计算机证书");
        certificate.setCertificate_insti("sa");
        certificate.setCertificate_grant_date(new java.sql.Date(1));
        certificate.setCertificate_expir_date(new java.sql.Date(1));
        certificate.setStu_id(1);
        Country country=new Country();
        country.setCountryAbbre("CHN");
        certificate.setInsti_country(country);

        Integer integer = applicantMapper.insertStuCertificate(certificate);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuProject() {
        Project project = new Project();
        project.setProj_name("1");
        project.setProj_role("1");
        project.setProj_start_time(new Date());
        project.setProj_end_time(new Date());
        project.setProj_deliver("1");
        project.setDeliver_publish(1);
        project.setDeliver_pub_insti("1");
        project.setStu_id(1);
        project.setIs_delete(0);
        Integer integer = applicantMapper.insertStuProject(project);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuWork() {
        Work work = new Work();
        Location location = new Location();
        work.setWork_company("1");
        work.setLocation(location);
        work.getLocation().setRegionNum(1);
        work.setWork_position("1");
        work.setWork_sector("1");
        work.setWork_start_time(new Date());
        work.setWork_end_time(new Date());
        work.setWork_duty("1");
        work.setWork_nature(1);
        work.setStu_id(1);
        work.setIs_delete(0);
        Integer integer = applicantMapper.insertStuWork(work);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuActivity() {
        Activity activity = new Activity();
        activity.setAct_name("1");
        activity.setAct_organization("1");
        activity.setAct_role("1");
        activity.setAct_start_time(new Date());
        activity.setAct_end_time(new Date());
        activity.setAct_detail("1");
        activity.setStu_id(1);
        activity.setIs_delete(0);
        Integer integer = applicantMapper.insertStuActivity(activity);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testGetOneJobApply() {
        JobApply jobApply = applicantMapper.getOneJobApply(1, 10);

    }

    @Test
    public void testUpdateUser() {
        Student student = new Student();
        student.setAvatarUrl("www.baidu.com");
        student.setId(1);
        Integer integer = applicantMapper.updateUserInfo(student);
        System.out.print(integer);
    }

//    @Test
//    public void testAllSkills() {
//        List<LabelInfo> labelInfos = applicantMapper.getAllSkills();
//        Assert.assertNotNull(labelInfos);
//
//    }


}
