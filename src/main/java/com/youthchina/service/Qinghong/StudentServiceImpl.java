package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: youthchina
 * @description: 学生实体Service层实现
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:11
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ApplicantMapper applicantMapper;

    @Autowired
    private JobMapper jobMapper;


    /**
     * @Description: 通过user_id获得学生的所有信息，如何该id为空，则抛出异常
     * @Param: [id] use_id
     * @return: com.youthchina.domain.Qinghong.Student
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    @Override
    public Student get(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            Student student = applicantMapper.getStudentInfo(id);
            return student;
        }
    }

    @Override
    public List<Student> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Student update(Student student) throws NotFoundException {
        return null;
    }

    @Override
    public Student add(Student entity) {
        return null;
    }

    /**
     * @Description: 通过user_id找到该用户的所有基本注册信息
     * @Param: [id]
     * @return: com.youthchina.domain.Qinghong.UserInfo
     * @Author: Qinghong Wang
     * @Date: 2018/12/20
     */
    public UserInfo getContacts(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else return userInfo;
    }

    /**
     * @Description: 通过user_id找到所有该id下所有的教育信息
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.EducationInfo>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<EducationInfo> getEducations(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            List<EducationInfo> educationInfos = applicantMapper.getEducations(id);
            return educationInfos;
        }
    }

    /**
     * @Description: 通过user_id找到该id下所有的工作经历
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Work>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<Work> getWorks(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            List<Work> works = applicantMapper.getWorks(id);
            return works;
        }

    }

    @Override
    public Student addStudent(Student student) {
        return null;
    }

    /**
     * @Description: 通过user_id找到该id下的所有课外活动经历
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Activity>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<Activity> getActivities(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            List<Activity> activities = applicantMapper.getActivities(id);
            return activities;
        }

    }

    /**
     * @Description: 通过user_id找到该id下所有的认证信息
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Certificate>
     * @Author: Qinghong Wang
     * @Date: 2018/12/22
     */
    public List<Certificate> getCertificates(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            List<Certificate> certificates = applicantMapper.getCertificates(id);
            return certificates;
        }
    }

    /**
     * @Description: 通过user_id找到该id下所有的项目经历
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Project>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<Project> getProjects(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            List<Project> projects = applicantMapper.getProjects(id);
            return projects;
        }

    }
    /**
    * @Description: 传入一个student对象，对学生的信息进行添加
    * @Param: [student]
    * @return: java.lang.Integer
    * @Author: Qinghong Wang
    * @Date: 2019/2/17
    */

    @Override
    public Integer addStudentInfo(Student student) throws NotFoundException {
        applicantMapper.insertStuInfo(student);
        for(EducationInfo educationInfo:student.getEducationInfos()){
            applicantMapper.insertEduInfo(educationInfo);
        }
        applicantMapper.insertSubInfo(student.getSubInfo());
        for(Project project:student.getProjects()){
            applicantMapper.insertStuProject(project);
        }
        for(Work work:student.getWorks()){
            applicantMapper.insertStuWork(work);
        }
        for (Activity activity:student.getActivities()){
            applicantMapper.insertStuActivity(activity);
        }
        for(Certificate certificate:student.getCertificates()){
            applicantMapper.insertStuCertificate(certificate);
        }
        return 0;
    }

    /**
     * @Description: 通过job_id和user_id来将申请的职位信息加入申请表中
     * @Param: [job_id, user_id]
     * @return: com.youthchina.domain.Qinghong.JobApply
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public Integer jobApply(Integer job_id,Integer user_id) throws NotFoundException {
        Job job = jobMapper.selectJobByJobId(job_id);
        if (job == null) {
            throw new NotFoundException(404, 404, "不能找到该job_id下的职位信息");
        } else {
            Date time = job.getJobEndTime();
            if (time.before(new Date())) {
                throw new NotFoundException(404, 404, "不能申请该职位因为申请时间已过");
            } else {
                JobApply jobApply=new JobApply();
                jobApply.setStu_id(applicantMapper.getStudentInfo(user_id).getStu_id());
                jobApply.setJob_id(job_id);
                jobApply.setJob_cv_send(1);
                jobApply.setJob_apply_status("已申请");
                Integer integer=applicantMapper.addApply(jobApply);
                return integer;
            }
        }
    }

    /**
     * @Description: 通过user_id找到该id下所有申请职位的信息
     * @Param: [user_id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.JobApply>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<JobApply> getJobApplies(Integer user_id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(user_id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "不能找到该user_id");
        } else {
            List<JobApply> jobApplies = applicantMapper.getJobApplies(user_id);
            return jobApplies;
        }
    }

    /**
    * @Description: 通过user_id找到该id下所有的职位收藏信息
    * @Param: [user_id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.JobCollect>
    * @Author: Qinghong Wang
    * @Date: 2019/1/9
    */
    public List<JobCollect> getJobCollect(Integer user_id) throws NotFoundException{
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new NotFoundException(404,404,"不能找到该user_id");
        }else {
            List<JobCollect> jobCollects=applicantMapper.getJobCollects(user_id);
            return jobCollects;
        }
    }

    /**
    * @Description: 通过user_id找到该id下所有的公司收藏信息
    * @Param: [user_id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.CompCollect>
    * @Author: Qinghong Wang
    * @Date: 2019/1/9
    */
    public List<CompCollect> getCompCollect(Integer user_id) throws NotFoundException{
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new NotFoundException(404,404,"不能找到该user_id");
        }else {
            List<CompCollect> compCollects=applicantMapper.getCompCollects(user_id);
            return compCollects;
        }
    }

    /**
    * @Description: 通过job_id user_id添加一个职位收藏
    * @Param: [job_id, user_id]
    * @return: java.lang.Integer
    * @Author: Qinghong Wang
    * @Date: 2019/1/9
    */
    public Integer addJobCollection(Integer job_id,Integer user_id) throws NotFoundException{
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new  NotFoundException(404,404,"不能找到该user_id");
        }else{
            JobCollect jobCollect=applicantMapper.getOneJobCollect(job_id);
            if(jobCollect!=null){
                throw new NotFoundException(404,404,"不能收藏该职位，因为已经收藏");
            }else{
                Job job=jobMapper.selectJobByJobId(job_id);
                if(job==null){
                    throw new NotFoundException(404,404,"不能收藏该职位，因为该职位已经被删除");
                }else {
                    JobCollect jobCollect1=new JobCollect();
                    jobCollect1.setStu_id(applicantMapper.getStudentInfo(user_id).getStu_id());
                    jobCollect1.setJob_id(job_id);
                    jobCollect1.setIs_delete(0);
                    Integer integer=applicantMapper.addJobCollect(jobCollect1);
                    return integer;

                }
            }
        }

    }

    /**
     * @Description: 通过collect_id删除收藏的信息，通过假删除实现
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: Qinghong Wang
     * @Date: 2018/12/21
     */


    public Integer deleteCollect(Integer id) throws NotFoundException {
        Integer num1 = applicantMapper.deleteJobCollect(id);
        Integer num2 = applicantMapper.deleteCompCollect(id);
        if (num1 == 0 && num2 == 0) {
            throw new NotFoundException(404, 404, "没有删除任何一条收藏信息");
        } else return num1 + num2;
    }
    /**
    * @Description: 通过collect_id删除职位收藏
    * @Param: [id]
    * @return: java.lang.Integer
    * @Author: Qinghong Wang
    * @Date: 2019/2/16
    */

    public Integer deleteJobCollect(Integer id) throws NotFoundException{
        Integer num=applicantMapper.deleteJobCollect(id);
        return num;
    }
    
    /** 
    * @Description: 通过collect_id删除公司收藏
    * @Param: [id] 
    * @return: java.lang.Integer 
    * @Author: Qinghong Wang 
    * @Date: 2019/2/16 
    */

    @Override
    public Integer deleteCompCollect(Integer id) throws NotFoundException {
        Integer num=applicantMapper.deleteCompCollect(id);
        return num;
    }

    /** 
    * @Description: 通过company_id和user_id添加公司收藏信息
    * @Param: [company_id, user_id] 
    * @return: java.lang.Integer 
    * @Author: Qinghong Wang 
    * @Date: 2019/2/16 
    */

    @Override
    public Integer addCompCollect(Integer company_id, Integer user_id) throws NotFoundException {
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new  NotFoundException(404,404,"不能找到该user_id");
        }else {
            CompCollect compCollect=new CompCollect();
            compCollect.setCompany_id(company_id);
            compCollect.setStu_id(applicantMapper.getStudentInfo(user_id).getStu_id());
            compCollect.setIs_delete(0);
            Integer integer=applicantMapper.addCompCollect(compCollect);
            return integer;
        }

    }


}
