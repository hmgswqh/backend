package com.youthchina.dto.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComRichText;

public class RichTextResponseDTO {

    private String braftEditorRaw;
    private String previewText;
    private Integer compiletype;

    public RichTextResponseDTO() {

    }

    public RichTextResponseDTO(ComRichText comRichText) {
        if(comRichText != null){
            this.braftEditorRaw = comRichText.getJsonContent();
            this.previewText = comRichText.getTextContent();
            this.compiletype = comRichText.getCompileType();
        }
    }

    public void setBraftEditorRaw(String braftEditorRaw) {
        this.braftEditorRaw = braftEditorRaw;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getBraftEditorRaw() {
        return this.braftEditorRaw;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setCompiletype(Integer compiletype){ this.compiletype = compiletype; }

    public Integer getCompiletype(){ return compiletype; }
}
