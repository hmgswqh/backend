package com.youthchina.service.community;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import com.youthchina.util.LoggedInUserUtil;
import com.youthchina.util.dictionary.AttentionTargetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Resource
    AnswerMapper answerMapper;

    @Resource
    RichTextService richTextService;

    @Resource
    QuestionService questionService;

    @Resource
    CommentService commentService;

    @Resource
    UserService userService;

    @Resource
    AttentionService attentionService;

    @Resource
    EvaluateService evaluateService;

    @Override
    public Integer countAnswersOfQuestion(Integer id) {
        return answerMapper.countAnswers(id);
    }

    @Override
    public List<Answer> getMyAnswers(Integer id) {
        List<Answer> answers = answerMapper.getMyAnswers(id);
        for (Answer answer : answers) {
            try {
                answer.setUser(userService.get(answer.getUser().getId()));
                answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
                answer.setAttentionCount(attentionService.countAttention(answer));
                answer.setEvaluateStatus(evaluateService.evaluateStatus(answer, id));
                answer.setUpvoteCount(evaluateService.countUpvote(answer));
                answer.setDownvoteCount(evaluateService.countDownvote(answer));
                answer.setAttention((attentionService.isAttention(AttentionTargetType.ANSWER, answer.getId(), id)));

            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(answer);
            answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
        }
        return answers;
    }

    @Override
    @Transactional
    public Answer get(Integer id) throws NotFoundException {
        Answer answer = answerMapper.get(id);
        if (answer == null) {
            throw new NotFoundException(4040, 404, "This answer does not exist!");//todo
        }
        answer.setUser(userService.get(answer.getUser().getId()));
        richTextService.getComRichText(answer);
        answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
        answer.setAttentionCount(attentionService.countAttention(answer));
        answer.setEvaluateStatus(evaluateService.evaluateStatus(answer, LoggedInUserUtil.currentUser().getId()));
        answer.setUpvoteCount(evaluateService.countUpvote(answer));
        answer.setDownvoteCount(evaluateService.countDownvote(answer));
        answer.setAttention((attentionService.isAttention(AttentionTargetType.ANSWER, answer.getId(), LoggedInUserUtil.currentUser().getId())));
        return answer;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer id, int start, int limit) {
        List<Answer> answers = answerMapper.getLimitedAnswers(id, start, limit);
        for (Answer answer : answers) {
            try {
                answer.setUser(userService.get(answer.getUser().getId()));
                answer.setAttentionCount(attentionService.countAttention(answer));
                answer.setEvaluateStatus(evaluateService.evaluateStatus(answer, LoggedInUserUtil.currentUser().getId()));
                answer.setUpvoteCount(evaluateService.countUpvote(answer));
                answer.setDownvoteCount(evaluateService.countDownvote(answer));
                answer.setAttention((attentionService.isAttention(AttentionTargetType.ANSWER, answer.getId(), LoggedInUserUtil.currentUser().getId())));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(answer);
        }

        return answers;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer questionId) {
        List<Answer> answers = answerMapper.getAnswers(questionId);
        for (Answer answer : answers) {
            try {
                answer.setUser(userService.get(answer.getUser().getId()));
                answer.setAttentionCount(attentionService.countAttention(answer));
                answer.setEvaluateStatus(evaluateService.evaluateStatus(answer, LoggedInUserUtil.currentUser().getId()));
                answer.setUpvoteCount(evaluateService.countUpvote(answer));
                answer.setDownvoteCount(evaluateService.countDownvote(answer));
                answer.setAttention((attentionService.isAttention(AttentionTargetType.ANSWER, answer.getId(), LoggedInUserUtil.currentUser().getId())));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(answer);
        }
        return answers;
    }

    @Override
    @Transactional
    public Answer add(Answer answer) throws NotFoundException {
        questionService.get(answer.getTargetId());
        richTextService.addComRichText(answer.getBody());
        answerMapper.add(answer);
        return get(answer.getId());
    }

    @Override
    @Transactional
    public Answer update(Answer answer) throws NotFoundException {
        get(answer.getId());
        answerMapper.update(answer);
        Answer answer1 = get(answer.getId());
        answer.getBody().setTextId(answer1.getBody().getTextId());
        richTextService.updateComRichText(answer.getBody());
        return get(answer.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        Answer answer = get(id);
        List<Comment> comments = commentService.getComments(answer);
        for (Comment comment : comments) {
            commentService.delete(comment.getId());
        }
        attentionService.cancel(answer);
        evaluateService.cancel(answer);
        answerMapper.delete(id);
    }


}
