package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.PersonInfluenceMapper;
import com.youthchina.domain.tianjian.PersonInfluence;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PersonInfluenceServiceImpl")
public class PersonInfluenceServiceImpl implements PersonInfluenceService {
    @Autowired
    PersonInfluenceMapper personInfluenceMapper;
    @Override
    public int savePersonInfluence(PersonInfluence personInfluence) {
        return personInfluenceMapper.savePersonInfluence(personInfluence);
    }


    @Override
    public int updatePersonInfluence(PersonInfluence personInfluence) throws NotFoundException {
        PersonInfluence comEssay = personInfluenceMapper.getPersonInfluence(personInfluence.getUser_id());
        if(comEssay==null){
            throw new NotFoundException(404,404,"this personInfluence is not exist");
        }
        return personInfluenceMapper.updatePersonInfluence(personInfluence);
    }

    @Override
    public PersonInfluence getPersonInfluence(Integer user_id) throws NotFoundException {
        PersonInfluence comEssay = personInfluenceMapper.getPersonInfluence(user_id);
        if(comEssay==null){
            throw new NotFoundException(404,404,"this personInfluence is not exist");
        }
       return personInfluenceMapper.getPersonInfluence(user_id);
    }
}
