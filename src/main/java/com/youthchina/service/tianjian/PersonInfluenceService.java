package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.PersonInfluence;
import com.youthchina.exception.zhongyang.NotFoundException;

public interface PersonInfluenceService {
    public int savePersonInfluence(PersonInfluence personInfluence);

    public int updatePersonInfluence(PersonInfluence personInfluence) throws NotFoundException;

    public PersonInfluence getPersonInfluence(Integer user_id) throws NotFoundException;
}
