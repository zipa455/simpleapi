package ru.mtuci.shaa.simpleapi.conroller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.service.DefaultSubjectService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DefaultSubjectService subjectService;


    @Test
    void getTest() throws Exception {
        SubjectDto subject = new SubjectDto(1L,"Name", 111, null, null  );
        List<SubjectDto> subjects = Arrays.asList( subject );
        given( subjectService.findAll( ) ).willReturn(subjects  );

        mvc.perform(
                get("/api/v1/subject/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$[0].name", is( subject.getName() )));

    }

    @Test
    void testGet() throws Exception {
        SubjectWithParentsDto subject = new SubjectWithParentsDto(1L,"Name", 111, null, null  );
        given( subjectService.findById( 1L )).willReturn( subject  );

        mvc.perform(
                get("/api/v1/subject/byId?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$.name", is( subject.getName() )));

    }

    @Test
    void saveNewSubject() {
    }

    @Test
    void deleteSubject() {
    }
}