package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.youthchina.domain.zhongyang.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.youthchina.domain.qingyang.Industry;
import com.youthchina.dao.qingyang.CompanyMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:industry.xml"})
public class IndustryTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void testGetIndustry() {
        Industry industry = companyMapper.selectIndustry(2);
        Assert.assertEquals("chn", industry.getIndChn());
    }


    @Test
    public void testIndustryInsert() {
        Industry industry = new Industry();
        industry.setIndChn("招");
        industry.setIndEng("r4");
        industry.setIndLevel(1);
        industry.setIndNum(666);
        industry.setIndParentNum(3);
        industry.setStartDate(Timestamp.valueOf("2012-12-12 12:12:12"));
        companyMapper.insertIndustry(industry);
    }

    @Test
    public void testGetIndustryByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Industry> industries = companyMapper.selectIndustryByIdList(ids);
        Assert.assertEquals(2, industries.size());
        for (Industry industry : industries) {
            if(industry.getIndNum() != 1 && industry.getIndNum()  != 2){
                Assert.fail();
            }
        }
    }

    @Test
    public void testUpdateIndustry(){
        Industry industry = new Industry();
        industry.setIndChn("upc");
        industry.setIndEng("upe");
        industry.setIndLevel(1);
        industry.setIndNum(2);
        industry.setIndParentNum(3);
        industry.setStartDate(Timestamp.valueOf("2012-12-12 12:12:59"));
        Integer id = companyMapper.updateIndustry(industry);
        Industry newInd = companyMapper.selectIndustry(id);
        //Assert.assertEquals("upc", newInd.getIndChn());
    }












}
