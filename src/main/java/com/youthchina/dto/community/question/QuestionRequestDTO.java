package com.youthchina.dto.community.question;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;

/**
 * Created by hongshengzhang on 2/23/19.
 */
public class QuestionRequestDTO implements RequestDTO<Question>, QuestionDTO {
    String title;
    RichTextRequestDTO body;
    boolean is_anonymous;
    Integer rela_type;
    Integer rela_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RichTextRequestDTO getBody() {
        return body;
    }

    public void setBody(RichTextRequestDTO body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }

    @Override
    public Question convertToDomain() {
        return new Question(this);
    }
}
