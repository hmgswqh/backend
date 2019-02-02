package com.youthchina.controller.Qinghong;

import com.youthchina.domain.Qinghong.MailResult;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.UserDTO;
import com.youthchina.service.Qinghong.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: youthchina
 * @description: 对于邮件发送系统controller层的实现
 * @author: Qinghong Wang
 * @create: 2019-02-02 13:17
 **/
@RestController
public class MailController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailService mailService;

    @RequestMapping(value ="/registerEmail",method = RequestMethod.GET)
    public MailResult sendSimpleMail(@RequestBody UserDTO userDTO){
        MailResult mailResult=new MailResult();
        Map<String,Object> valueMap=new HashMap<>();
        valueMap.put("to",userDTO.getEmail());
        valueMap.put("subject","注册邮箱");
        valueMap.put("username",userDTO.getUsername());
        valueMap.put("email",userDTO.getEmail());
        if(StringUtils.isEmpty(userDTO.getEmail())||!userDTO.getEmail().contains("@")){
            mailResult.setResCode("01");
            mailResult.setRspMsg("邮件不存在或邮件格式不正确");
        }

        try{
            mailService.sendUserRegisterEmail(valueMap);

        }catch (Exception e){
            mailResult.setResCode("02");
            mailResult.setRspMsg("邮件发送异常");
            logger.error("邮件发送异常",e);
        }
        return mailResult;
    }

}
