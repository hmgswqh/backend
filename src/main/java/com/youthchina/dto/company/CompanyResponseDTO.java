package com.youthchina.dto.company;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.domain.qingyang.Logo;

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-24
 **/
public class CompanyResponseDTO implements CompanyDTOInterface{
    private Integer id;
    private String name;
    private String avatarUrl;
    private String location;
    private String website;
    private String note;
    private String nation;

    public CompanyResponseDTO(Company company) {
        this.id = company.getCompanyId();
        this.name = company.getCompanyName();
        List<Logo> logoList = company.getLogoList();
        if(logoList != null && logoList.size() > 0){
            this.avatarUrl = company.getLogoList().get(0).getDocuLocalId();
        }
        Location location = company.getLocation();
        if (location != null) {
            this.location = location.getRegionName();
        }
        Country country = company.getCountry();
        if (country != null) {
            this.nation = country.getCountryChn();
        }
        this.website = company.getCompanyWebsite();
        this.note = company.getCompanyIntroduc();

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
