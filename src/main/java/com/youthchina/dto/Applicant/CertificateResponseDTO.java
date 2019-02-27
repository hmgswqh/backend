package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.Certificate;
import com.youthchina.dto.DurationDTO;

/**
 * @program: youthchina
 * @description: 证书信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:39
 **/
public class CertificateResponseDTO {
    private Integer id;
    private String name;
    private String authority;
    private DurationDTO duration;
    private String note;

    public CertificateResponseDTO() {
    }

    public CertificateResponseDTO(Certificate certificate) {
        this.id=certificate.getCertificate_id();
        this.name = certificate.getCertificate_name();
        this.authority = certificate.getCertificate_insti();
        this.duration = new DurationDTO(certificate.getCertificate_grant_date(), certificate.getCertificate_expir_date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public DurationDTO getDuration() {
        return duration;
    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
