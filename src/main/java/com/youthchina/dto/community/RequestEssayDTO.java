package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.RichTextDTO;

public class RequestEssayDTO {
    private Integer id;
    private String title;
    private Integer company_id;
    private boolean is_anonymous;
    private RichTextDTO richTextDTO;

    public RequestEssayDTO(ComEssay comEssay){
        this.id = comEssay.getEssay_id();
        this.title = comEssay.getEssay_title();
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssay.getEssay_body(), RichTextDTO.class);
            this.richTextDTO = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
    }
    public RequestEssayDTO(){}

    public RichTextDTO getRichTextDTO(){return richTextDTO;}

    public void setRichTextDTO(RichTextDTO richTextDTO){this.richTextDTO = richTextDTO;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
