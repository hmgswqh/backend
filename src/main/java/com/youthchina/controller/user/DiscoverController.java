package com.youthchina.controller.user;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.service.recommendation.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianjian on 2019/5/22.
 */
@RestController
@RequestMapping("${web.url.prefix}/discovery/**")
public class DiscoverController {
    @Autowired
    private RecommendServiceImpl recommendService;

    @GetMapping("/users")
    public ResponseEntity getRecommendUsers( @AuthenticationPrincipal User user, PageRequest pageRequest){
        List<User> users  = recommendService.getRecommendUser(user.getId());
        List<UserDTO> userDTOS = new ArrayList<>();
        Iterator iterator = users.iterator();
        while (iterator.hasNext()){
            UserDTO userDTO = new UserDTO((User)iterator.next());
            userDTOS.add(userDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, userDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/companies")
    public ResponseEntity getRecommendCompanies( @AuthenticationPrincipal User user, PageRequest pageRequest){
        List<Company> companies  = recommendService.getRecommendCompany(user.getId());
        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();
        Iterator iterator = companies.iterator();
        while (iterator.hasNext()){
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO((Company) iterator.next());
            companyResponseDTOS.add(companyResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, companyResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/articles")
    public ResponseEntity getRecommendArticles( @AuthenticationPrincipal User user, PageRequest pageRequest){
        List<ComEssay> comEssays  = recommendService.getRecommendEssay(user.getId());
        List<EssayResponseDTO> essayResponseDTOS = new ArrayList<>();
        Iterator iterator = comEssays.iterator();
        while (iterator.hasNext()){
            EssayResponseDTO essayResponseDTO = new EssayResponseDTO((ComEssay) iterator.next());
            essayResponseDTOS.add(essayResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, essayResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/questions")
    public ResponseEntity getRecommendQuestions( @AuthenticationPrincipal User user, PageRequest pageRequest){
        List<Question> questionList  = recommendService.getRecommendQuestion(user.getId());
        List<QuestionResponseDTO> questionResponseDTOArrayList = new ArrayList<>();
        Iterator iterator = questionList.iterator();
        while (iterator.hasNext()){
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO((Question) iterator.next());
            questionResponseDTOArrayList.add(questionResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, questionResponseDTOArrayList);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/jobs")
    public ResponseEntity getRecommendJobs( @AuthenticationPrincipal User user, PageRequest pageRequest){
        List<Job> jobs  = recommendService.getRecommendJob(user.getId());
        List<JobResponseDTO> jobResponseDTOS = new ArrayList<>();
        Iterator iterator = jobs.iterator();
        while (iterator.hasNext()){
            JobResponseDTO jobResponseDTO = new JobResponseDTO((Job) iterator.next());
            jobResponseDTOS.add(jobResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, jobResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/editorials")
    public ResponseEntity getRecommendEditorials( @AuthenticationPrincipal User user, PageRequest pageRequest){
        List<BriefReview> briefReviews  = recommendService.getRecommendBriefReview(user.getId());
        List<BriefReviewResponseDTO> briefReviewResponseDTOS = new ArrayList<>();
        Iterator iterator = briefReviews.iterator();
        while (iterator.hasNext()){
            BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO((BriefReview) iterator.next());
            briefReviewResponseDTOS.add(briefReviewResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, briefReviewResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

}
