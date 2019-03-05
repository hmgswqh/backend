package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.CompanyResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.UserDTO;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.EssayRecommendServiceImplement;
import com.youthchina.service.qingyang.CompanyCURDService;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/discovery")
public class EssayRecommendController {
    @Autowired
    private EssayRecommendServiceImplement essayRecommendServiceImplement;
    @Autowired
    private EssayServiceImpl essayServiceimpl;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyCURDService companyCURDService;

    @GetMapping("/articles")
    public ResponseEntity getRecommandEssay() throws NotFoundException {
        System.out.println("11111");
        List<ComEssay> essayList = essayRecommendServiceImplement.getEssayForYou();
        List<EssayDTO> resultList = new ArrayList<>();
        for(ComEssay essay : essayList) {
            EssayDTO essayDTO = new EssayDTO(essay);
            ComAuthorEssayMap comAuthorEssayMap = essayServiceimpl.getEssayAuthor(essay.getEssay_id());

            User user = userService.get(comAuthorEssayMap.getUser_id());

            if(comAuthorEssayMap.getRela_type()==2){
                Company company = companyCURDService.get(comAuthorEssayMap.getRela_id());
                essayDTO.setCompany(new CompanyResponseDTO(company));
            }
            essayDTO.setAuthor(new UserDTO(user));

            resultList.add(essayDTO);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("users", resultList);
        System.out.println("11111");

        if (resultList!=null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400,"fail")));
    }
}
