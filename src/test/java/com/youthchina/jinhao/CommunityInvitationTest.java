package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.CommunityInvitationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:test.xml"})
public class CommunityInvitationTest {
    @Resource
    CommunityInvitationMapper communityInvitationMapper;
//
//    @Test
//    public void addAndCheck(){
//        communityInvitationMapper.add(1,1,5,3);
//        Assert.assertNotNull(communityInvitationMapper.checkIfInvitationExist(1,1,5,3));
//    }
//
    @Test
    public void get(){
//        CommunityInvitation communityInvitation = communityInvitationMapper.get(1);

    }
}
