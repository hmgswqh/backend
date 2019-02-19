package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;


public interface BriefReviewService extends DomainCRUDService<BriefReview, Integer> {
    /**
     * add a new brief review
     * @param entity the entity should contains review_content, rela_id, rela_type and user_id
     * @return target
     */
    @Override
    BriefReview add(BriefReview entity);

    @Override
    BriefReview get(Integer id) throws NotFoundException;

    @Override
    List<BriefReview> get(List<Integer> id) throws NotFoundException;

    @Override
    BriefReview update(BriefReview briefReview) throws NotFoundException;

    @Override
    void delete(Integer id) throws NotFoundException;

    /**
     * get all the brief reviews which user has posted
     * @param user_id id of user
     * @return return List<BriefReview>
     */
    List<BriefReview> getUserReview(Integer user_id);

    /**
     * get all the brief reviews which user has upvoted
     * @param user_id id of user
     * @return return List<BriefReview>
     */
    List<BriefReview> getUserUpvoteReview(Integer user_id);

    Evaluate doEvaluate(Integer user_id, Integer review_id, Integer evaluate_type) throws NotFoundException;

    Integer getReviewAgreement(Integer review_id) throws NotFoundException;

    Comment addComment(Comment comment, Integer review_id);

    Comment getComment(Integer comment_id) throws NotFoundException;

    void deleteComment(Integer comment_id) throws NotFoundException;

    Comment updateComment(Comment comment) throws NotFoundException;

    List<Comment> getAllCommentsOfReview(Integer review_id) throws NotFoundException;

    Evaluate evaluateComment(Integer user_id, Integer comment_id, Integer evaluate_type) throws NotFoundException;

   Integer countCommentAgreement(Integer comment_id) throws NotFoundException;

    Discuss addDiscuss(Discuss discuss, Integer comment_id);

    Discuss getDiscuss(Integer discuss_id) throws NotFoundException;

    void deleteDiscuss(Integer discuss_id) throws NotFoundException;

    Discuss updateDiscuss(Discuss discuss) throws NotFoundException;

    List<Discuss> getAllDiscussesOfComment(Integer comment_id) throws NotFoundException;

    //评价讨论
    Evaluate EvaluateDiscuss(Integer user_id, Integer discuss_id, Integer evaluate_type) throws NotFoundException;

    Evaluate getDiscussEvaluate(Integer evaluate_id) throws NotFoundException;
}
