package com.youthchina.controller;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.service.jinhao.AnswerServiceImpl;
import com.youthchina.service.jinhao.QuestionServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.JwtService;
import com.youthchina.util.AuthGenerator;
import com.youthchina.util.JwtAuthenticationProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author: Qingyang Zhao
 * @create: 2019-04-10
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class UserControllerGetMyTest {
    @Autowired
    WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    MockMvc mvc;

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    JwtService jwtService;
    @Autowired
    QuestionServiceImpl questionService;
    @Autowired
    AnswerServiceImpl answerService;
    @Autowired
    EssayServiceImpl essayService;

    private AuthGenerator authGenerator = new AuthGenerator();

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void getMy() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                get(this.urlPrefix + "/users/" + id + "/my")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        //.andExpect(content().json("", false))
        ;
    }

    @Test
    public void getMyQuestionServiceTest() throws Exception {
        Integer id = 1;
        List<Question> questionList = questionService.getMyQuestion(id);
        Assert.assertEquals(49, questionList.size());
    }

    @Test
    public void getMyAnswerServiceTest() throws Exception {
        Integer id = 1;
        List<Answer> answerList = answerService.getMyAnswers(id);
        Assert.assertEquals(0, answerList.size());

        id = 5;
        answerList = answerService.getMyAnswers(id);
        Assert.assertEquals(13, answerList.size());

    }

    @Test
    public void getMyEssayServiceTest() throws Exception {
        Integer id = 1;
        List<ComEssay> comEssayList = essayService.getAllEssayByUserId(id);
        Assert.assertEquals(6, comEssayList.size());
//        ComEssay comEssay1 = comEssayList.get(0);
//        EssayResponseDTO essayResponseDTO = new EssayResponseDTO();
//        essayResponseDTO.convertToDTO(comEssay1);
    }

    @Test
    public void getMyNone() throws Exception {
        Integer id = 2;
        this.mvc.perform(
                get(this.urlPrefix + "/users/" + id + "/my")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4030,\"reason\":\"Cannot access\"}}", false))
        ;
    }
}