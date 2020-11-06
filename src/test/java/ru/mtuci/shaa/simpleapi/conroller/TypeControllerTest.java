package ru.mtuci.shaa.simpleapi.conroller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import ru.mtuci.shaa.simpleapi.dto.TypeDto;
import ru.mtuci.shaa.simpleapi.model.Type;
import ru.mtuci.shaa.simpleapi.service.DefaultSubjectService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class TypeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TypeRepository subjectService;

    @Test
    void getById() throws Exception {
        Type subject = new Type("Name"  );
        given( subjectService.findById( 1L )).willReturn(java.util.Optional.of(subject));

        mvc.perform(
                get("/api/v1/type/byId?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$.name", is( subject.getName() )));
        verify( subjectService, VerificationModeFactory.times(1)).findById(1L);
        reset(subjectService);
    }

    @Test
    void getAll() throws Exception {
        Type type = new Type("Name"  );
        List<Type> types = Collections.singletonList(type);
        given( subjectService.findAll( ) ).willReturn( types );

        mvc.perform(
                get("/api/v1/type/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$[0].name", is( type.getName() )));
        verify( subjectService, VerificationModeFactory.times(1)).findAll();
        reset(subjectService);
    }

    @Test
    void saveNewSubject() throws Exception {
        Type type = new Type("Parent"  );
        given( subjectService.findById( 1L )).willReturn(java.util.Optional.of(type));

        TypeDto subject = new TypeDto("Name"  );
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mvc.perform(
                post("/api/v1/type/add")
                        .contentType(MediaType.APPLICATION_JSON).content("    \"name\": \"City\""))
                .andExpect( status().isOk());
        verify( subjectService, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(subjectService);
    }

    @Test
    void deleteSubject() throws Exception {
        Type type = new Type("Name"  );
        given( subjectService.save(type)).willReturn( type  );
        mvc.perform(
                delete("/api/v1/type/del/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk());
        verify( subjectService, VerificationModeFactory.times(1)).deleteById(1L);
        reset(subjectService);
    }
}