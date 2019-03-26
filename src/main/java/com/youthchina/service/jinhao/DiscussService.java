package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface DiscussService extends DomainCRUDService<Discuss, Integer> {
    /**
     * get all the discusses of a comment
     * @param id id of the comment
     * @return a list of comment
     * @throws NotFoundException
     */
    List<Discuss> getDiscusses(Integer id) throws NotFoundException;

    void isDiscussExist(Integer id) throws NotFoundException;

    void deleteAllDiscussOfComment(Integer id);
}
