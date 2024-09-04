package ru.itmo;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmo.dto.CatDto;
import ru.itmo.dto.OwnerDto;
import ru.itmo.services.CatService;
import ru.itmo.services.OwnerService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class CatsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CatService catService;
    @Autowired
    private OwnerService ownerService;

    @Test
    public void findCatTest() throws Exception {
        ownerService.creation(OwnerDto.builder().name("Vasya").birthDate(LocalDate.ofEpochDay(2022-01-01)).build());
        catService.creation(CatDto.builder().name("Kit").birthDate(LocalDate.ofEpochDay(2022-01-01)).color("BLACK").breed("RUSSIAN_BLUE").ownerId(1).build());
        String result = mockMvc.perform(get("/cats/1"))
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals("{id: 1, name: \"Kit\", breed: \"RUSSIAN_BLUE\", color: \"BLACK\", ownerId: 1}", result, JSONCompareMode.LENIENT);
    }

    @Test
    public void findByBreedCatsTest() throws Exception {
        ownerService.creation(OwnerDto.builder().name("Vasya").birthDate(LocalDate.ofEpochDay(2022-01-01)).build());
        catService.creation(CatDto.builder().name("Kit").birthDate(LocalDate.ofEpochDay(2022-01-01)).color("BLACK").breed("RUSSIAN_BLUE").ownerId(1).build());
        catService.creation(CatDto.builder().name("Kit2").birthDate(LocalDate.ofEpochDay(2022-01-01)).color("GRAY").breed("BENGAL").ownerId(1).build());
        catService.creation(CatDto.builder().name("Kit3").birthDate(LocalDate.ofEpochDay(2022-01-01)).color("BLACK").breed("RUSSIAN_BLUE").ownerId(1).build());
        String result = mockMvc.perform(get("/cats/cats/color?color=GRAY"))
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals("[{id: 2, name: \"Kit2\", breed: \"BENGAL\", color: \"GRAY\", ownerId: 1}]", result, JSONCompareMode.LENIENT);
    }
}