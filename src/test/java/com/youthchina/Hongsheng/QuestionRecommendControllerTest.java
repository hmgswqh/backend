package com.youthchina.Hongsheng;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hongshengzhang on 2/26/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
@WebAppConfiguration
public class QuestionRecommendControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void getRecommandQuestionTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/questions")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"users\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"title\":\"第一个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"answers\":[{\"id\":1,\"body\":null,\"is_anonymous\":false,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"company_id\":null},{\"id\":2,\"body\":null,\"is_anonymous\":false,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"company_id\":null},{\"id\":3,\"body\":null,\"is_anonymous\":false,\"creator\":{\"id\":2,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"company_id\":null},{\"id\":4,\"body\":null,\"is_anonymous\":false,\"creator\":{\"id\":3,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"company_id\":null}],\"rela_type\":1,\"rela_id\":3,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 1 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 1 but 42\",\"resourceIdList\":[]}},{\"id\":2,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"title\":\"第二个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-05T13:32:40.000+0000\",\"modified_at\":\"2018-12-05T13:32:40.000+0000\",\"answers\":[{\"id\":5,\"body\":null,\"is_anonymous\":false,\"creator\":{\"id\":4,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"company_id\":null}],\"rela_type\":3,\"rela_id\":1,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 2 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 2 but 42\",\"resourceIdList\":[]}},{\"id\":3,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"title\":\"第三个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-06T13:32:40.000+0000\",\"modified_at\":\"2018-12-06T13:32:40.000+0000\",\"answers\":[],\"rela_type\":3,\"rela_id\":3,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 3 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 3 but 42\",\"resourceIdList\":[]}},{\"id\":4,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"title\":\"第四个问题\",\"is_anonymous\":false,\"create_at\":\"2018-12-06T14:32:40.000+0000\",\"modified_at\":\"2018-12-06T14:32:40.000+0000\",\"answers\":[],\"rela_type\":2,\"rela_id\":2,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 4 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 4 but 42\",\"resourceIdList\":[]}},{\"id\":5,\"creator\":null,\"title\":\"第一个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"answers\":[],\"rela_type\":null,\"rela_id\":null,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 1 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 1 but 42\",\"resourceIdList\":[]}},{\"id\":6,\"creator\":null,\"title\":\"第二个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-05T13:32:40.000+0000\",\"modified_at\":\"2018-12-05T13:32:40.000+0000\",\"answers\":[],\"rela_type\":null,\"rela_id\":null,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 2 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 2 but 42\",\"resourceIdList\":[]}},{\"id\":7,\"creator\":null,\"title\":\"第三个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-06T13:32:40.000+0000\",\"modified_at\":\"2018-12-06T13:32:40.000+0000\",\"answers\":[],\"rela_type\":null,\"rela_id\":null,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 3 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 3 but 42\",\"resourceIdList\":[]}},{\"id\":8,\"creator\":null,\"title\":\"第四个问题\",\"is_anonymous\":false,\"create_at\":\"2018-12-06T14:32:40.000+0000\",\"modified_at\":\"2018-12-06T14:32:40.000+0000\",\"answers\":[],\"rela_type\":null,\"rela_id\":null,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 4 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 4 but 42\",\"resourceIdList\":[]}},{\"id\":9,\"creator\":null,\"title\":\"第一个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"answers\":[],\"rela_type\":null,\"rela_id\":null,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 1 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 1 but 42\",\"resourceIdList\":[]}},{\"id\":10,\"creator\":null,\"title\":\"第二个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-05T13:32:40.000+0000\",\"modified_at\":\"2018-12-05T13:32:40.000+0000\",\"answers\":[],\"rela_type\":null,\"rela_id\":null,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 2 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 2 but 42\",\"resourceIdList\":[]}}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
