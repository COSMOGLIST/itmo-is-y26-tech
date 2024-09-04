package ru.itmo;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmo.security.MyUser;
import ru.itmo.security.SecurityConfiguration;
import ru.itmo.services.CatService;
import ru.itmo.services.OwnerService;
import ru.itmo.services.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = SecurityConfiguration.class)
public class CatsSecurityTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CatService catService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    @Sql("mysql.sql")
    public void findCatTest() throws Exception {
        String result = mockMvc.perform(
                get("/cats/1").with(user(new MyUser(userService.getUserByName("bob")))))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals(
                "{" +
                        "id: 1, " +
                        "name: \"Kit\", " +
                        "breed: \"BENGAL\", " +
                        "color: \"BLACK\", " +
                        "ownerId: 1" +
                        "}",
                result, JSONCompareMode.LENIENT);
    }

    @Test
    @Sql("mysql.sql")
    public void findByColorCatsTest() throws Exception {
        String result = mockMvc.perform(get("/cats/cats/color?color=GRAY").with(user(new MyUser(userService.getUserByName("bob")))))
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals("[{id: 2, name: \"Kit2\", breed: \"BENGAL\", color: \"GRAY\", ownerId: 1}]", result, JSONCompareMode.LENIENT);
    }

    @Test
    @Sql("mysql.sql")
    public void findCatsUserControlTest() throws Exception {
        String result = mockMvc.perform(get("/cats/cats/breed?breed=BENGAL").with(user(new MyUser(userService.getUserByName("bobby")))))
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals("[{id: 4, name: \"Kit4\", breed: \"BENGAL\", color: \"BLACK\", ownerId: 2}]", result, JSONCompareMode.LENIENT);
    }
}