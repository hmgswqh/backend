package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.dto.CompanyDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CompanyRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/company-for-you")
public class CompanyRecommendController {
    @Autowired
    public CompanyRecommendServiceImplement companyRecommendServiceImplement;

    @GetMapping("/pop")
    public ResponseEntity getRecommandPopCompany() throws NotFoundException {
        List<Company> companyList = companyRecommendServiceImplement.getPopCompanyForYou();
        List<CompanyDTO> resultList = new ArrayList<>();
        for(Company company : companyList) {
            resultList.add(new CompanyDTO(company));
        }

        if (resultList!=null)
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(400,"fail")));
    }

    @GetMapping("/new")
    public ResponseEntity getRecommandNewCompany() throws NotFoundException {
        List<Company> companyList = companyRecommendServiceImplement.getNewCompanyForYou();
        List<CompanyDTO> resultList = new ArrayList<>();
        for(Company company : companyList) {
            resultList.add(new CompanyDTO(company));
        }

        if (resultList!=null)
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(400,"fail")));
    }
}