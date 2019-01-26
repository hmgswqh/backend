package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyVerification;
import com.youthchina.domain.qingyang.Industry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface CompanyMapper {

    Company selectCompany(Integer id);

    List<Company> selectCompanyByIdList(List<Integer> id);

    Integer deleteCompany(Integer id);

    Integer updateCompany(Company company);

    Integer insertCompany(Company entity);

    Industry selectIndustry(Integer id);

    List<Industry> selectIndustryByIdList(List<Integer> ids);

    Integer deleteIndustry(Integer id);

    Integer updateIndustry(Industry industry);

    Integer insertIndustry(Industry entity);
    
    CompanyVerification selectCompanyVerification(Integer id);

    List<CompanyVerification> selectCompanyVerificationByIdList(List<Integer> id);

    Integer deleteCompanyVerification(Integer id);

    Integer updateCompanyVerification(CompanyVerification companyVerification);

    Integer insertCompanyVerification(CompanyVerification entity);

    void deleteCompanyInd(Integer id);

    Integer insertCompanyInd(List<Industry> industries);
}

