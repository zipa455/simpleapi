package ru.mtuci.shaa.simpleapi.conroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.model.Subject;
import ru.mtuci.shaa.simpleapi.model.Type;
import ru.mtuci.shaa.simpleapi.service.DefaultSubjectService;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private TypeRepository typeRepository;


    @Test
    void getTest() throws Exception {
        SubjectDto subject = new SubjectDto(1L,"Name", 111, null, null  );
        List<SubjectDto> subjects = Collections.singletonList(subject);
        given( subjectService.findAll( ) ).willReturn(subjects  );

        mvc.perform(
                get("/api/v1/subject/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$[0].name", is( subject.getName() )));
        verify( subjectService, VerificationModeFactory.times(1)).findAll();
        reset(subjectService);
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
        verify( subjectService, VerificationModeFactory.times(1)).findById(1L);
        reset(subjectService);
    }

    @Test
    void saveNewSubject() throws Exception {
        SubjectWithParentsDto subjectParent = new SubjectWithParentsDto(1L,"Parent", 111, null, null  );
        given( subjectService.findById( 1L )).willReturn( subjectParent );

        SubjectDto subject = new SubjectDto(2L,"Name", 111, 333L, ""  );
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mvc.perform(
                post("/api/v1/subject/add")
                        .contentType(MediaType.APPLICATION_JSON).content(ow.writeValueAsString(subject)))
                .andExpect( status().isOk());
        verify( subjectService, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(subjectService);
    }

    @Test
    void setTypeSubject() throws Exception {
        SubjectWithParentsDto subjectParent = new SubjectWithParentsDto(1L,"Parent", 111, null, null  );
        given( subjectService.findById( 1L )).willReturn( subjectParent );
        given( typeRepository.findByName("NameType " )).willReturn(java.util.Optional.of(new Type("NameType")));

        SubjectDto subject = new SubjectDto(2L,"Name", 111, 333L, ""  );
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mvc.perform(
                post("/api/v1/subject/setType/1")
                        .contentType(MediaType.APPLICATION_JSON).content("NameType"))
                .andExpect( status().isOk());
        verify( subjectService, VerificationModeFactory.times(1)).setType(1L, "NameType");
        reset(subjectService);
    }

    @Test
    void deleteSubject() throws Exception {
        SubjectDto subject = new SubjectDto(1L,"Name", 111, 3L, null  );
        given( subjectService.save(subject)).willReturn( subject  );
        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mvc.perform(
                delete("/api/v1/subject/del/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk());
        verify( subjectService, VerificationModeFactory.times(1)).deleteSubject(1L);
        reset(subjectService);
    }
}