package com.youthchina.others;

import com.youthchina.domain.zhongyang.Gender;
import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.util.DomainToDTOConverterFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhongyangwu on 3/21/19.
 */
@Transactional
public class DomainToDTOConverterFactoryTest {

    @Test
    public void getConverter() {
        DomainToDTOConverterFactory domainToDTOConverterFactory = new DomainToDTOConverterFactory();
        Converter<Object, UserDTO> converter = domainToDTOConverterFactory.getConverter(UserDTO.class);
        User user = new User();
        user.setRole(Role.APPLICANT);
        user.setPassword("123456");
        user.setId(1);
        user.setEmail("test@test.com");
        user.setGender(Gender.MALE);
        user.setPhonenumber("2022922222");
        UserDTO dto = converter.convert(user);
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getEmail(), user.getEmail());
    }
}