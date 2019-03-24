package com.youthchina.controller.Hongsheng;


import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.toBeDeleted.JobRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongshengzhang on 2/23/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/job-for-you/**")
public class JobForYouController {
    private JobRecommendServiceImplement jobRecommendServiceImplement;

    @Autowired
    public JobForYouController(JobRecommendServiceImplement jobRecommendServiceImplement) {
        this.jobRecommendServiceImplement = jobRecommendServiceImplement;
    }

    @GetMapping({"/intern", "/general", "/campus"})
    public ResponseEntity getRecommandInternJobs() throws NotFoundException {
        List<Job> jobList = jobRecommendServiceImplement.getInternForYou();
        List<JobResponseDTO> resultList = new ArrayList<>();
        for (Job job : jobList) {
            resultList.add(new JobResponseDTO(job));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("jobList", resultList);

        return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
    }

}
