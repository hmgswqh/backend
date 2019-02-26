package com.youthchina.controller.Hongsheng;

import com.youthchina.controller.zhongyang.DomainCRUDController;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.JobResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.SimpleJobDTO;
import com.youthchina.dto.StatusDTO;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.jinhao.communityQA.JobRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/home/**")
public class HomeController extends DomainCRUDController<SimpleJobDTO, Job, Integer> {
    private JobRecommendService jobRecommendService;
    private String url;

    @Autowired
    public HomeController(JobRecommendService jobRecommendService, @Value("${web.url.prefix}") String prefix){
        this.jobRecommendService = jobRecommendService;
        this.url = prefix + "/home";
    }
    @Override
    protected DomainCRUDService<Job, Integer> getService() {
        return this.jobRecommendService;
    }

    @Override
    protected SimpleJobDTO DomainToDto(Job domain) {
        return new SimpleJobDTO(domain);
    }

    @Override
    protected Job DtoToDomain(SimpleJobDTO simpleJobDTO) {
        return new Job(simpleJobDTO);
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @GetMapping("/new")
    public ResponseEntity<?> getNewJobs(){
        List<Job> jobList = jobRecommendService.getJobForYou();
        List<JobResponseDTO> jobResponseDTOS = new ArrayList<>();
        for(Job job : jobList){
            jobResponseDTOS.add(new JobResponseDTO(job));
        }
        HashMap<String, List<JobResponseDTO>> res = new HashMap<>();
        res.put("jobList", jobResponseDTOS);
        if(jobList.size() == 0){
            return ResponseEntity.ok(new Response(res, new StatusDTO(400,"fail")));
        }else {
            return ResponseEntity.ok(new Response(res, new StatusDTO(200,"success")));
        }
    }
    @GetMapping("/hot")
    public ResponseEntity<?> getHotJobs(){
        List<Job> jobList = jobRecommendService.getJobForYou();
        List<JobResponseDTO> jobResponseDTOS = new ArrayList<>();
        for(Job job : jobList){
            jobResponseDTOS.add(new JobResponseDTO(job));
        }
        HashMap<String, List<JobResponseDTO>> res = new HashMap<>();
        res.put("jobList", jobResponseDTOS);
        if(jobList.size() == 0){
            return ResponseEntity.ok(new Response(res, new StatusDTO(400,"fail")));
        }else {
            return ResponseEntity.ok(new Response(res, new StatusDTO(200,"success")));
        }
    }
}
