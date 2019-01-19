package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Hr;
import com.youthchina.domain.qingyang.Job;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
public class JobTest {

    @Autowired
    private JobMapper jobMapper;

    @Test
    public void testGetJob() {
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals("大疆", job.getCompany().getCompanyName());
    }


    @Test
    public void testGetJobByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Job> jobs = jobMapper.selectJobByJobIdList(ids);
        Assert.assertEquals(2, jobs.size());
        for (Job job : jobs) {
            if(job.getJobId() != 1 && job.getJobId()  != 2){
                Assert.fail();
            }
        }
    }

    @Test
    public void testJobInsert() {
        Job job = new Job();
        Hr hr = new Hr();
        hr.setHrId(1);
        job.setHr(hr);
        Company company = new Company();
        company.setCompanyId(1);
        job.setCompany(company);
        job.setJobName("全栈");
        job.setJobProfNum(1);
        job.setJobStartTime(Date.valueOf("2019-1-1"));
        job.setJobEndTime(Date.valueOf("2020-1-1"));
        job.setJobTime(1);
        job.setJobDescription("fullStack");
        job.setJobDuty("fullStack");
        job.setJobReq("fullStack");
        job.setJobLocation("北京");
        job.setJobHighlight("80K");
        job.setJobSalary("80K");
        job.setCvReceiMail("youth@china");
        job.setCvNameRule("rule");
        job.setJobActive(1);
        jobMapper.insertJob(job);
    }

    @Test
    public void testUpdateJob(){
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals("Beijing", job.getJobLocation());
        job.getCompany().setCompanyId(2);
        jobMapper.updateJob(job);
    }

    @Test
    public void testDeleteJob(){
        jobMapper.deleteJob(1);
    }
}