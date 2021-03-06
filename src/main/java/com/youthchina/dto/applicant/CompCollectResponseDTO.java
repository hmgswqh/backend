package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.dto.ResponseDTO;

/**
 * @program: youthchina
 * @description: 公司收藏DTO
 * @author: Qinghong Wang
 * @create: 2019-02-26 11:31
 **/
public class CompCollectResponseDTO implements ResponseDTO<CompCollect> {
    private Integer id;
    private OrganizationDTO organization;

    public CompCollectResponseDTO(CompCollect compCollect) {
        if(compCollect!=null){
            this.id = compCollect.getCollect_id();
            if(compCollect.getCompany()!=null){
                this.organization = new OrganizationDTO(compCollect.getCompany());
            }

        }

    }

    public CompCollectResponseDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }


    @Override
    public void convertToDTO(CompCollect compCollect) {
        this.id = compCollect.getCollect_id();
        this.organization = new OrganizationDTO(compCollect.getCompany());
    }
}
