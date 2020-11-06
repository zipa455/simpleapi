package ru.mtuci.shaa.simpleapi.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.model.Subject;

import javax.xml.bind.ValidationException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@AutoConfigureJsonTesters
@SpringBootTest( classes = DefaultSubjectService.class )
class DefaultSubjectServiceTest {

    private static DefaultSubjectService service;

    @MockBean
    private SubjectConverter subjectConverter;

    @MockBean
    private SubjectRepository subjectRepository;

    @MockBean
    private TypeRepository typeRepository;

    @BeforeEach
    void Setup() {
        service = new DefaultSubjectService(subjectConverter,subjectRepository,typeRepository);
    }

    @Test
    void validateTestNull() {
        Throwable throwable = assertThrows( ValidationException.class, () -> service.validate( null ));
        assertNotNull( throwable.getMessage() );
        assertEquals("Object Subject is null", throwable.getMessage() );
    }

    @Test
    void validateTestName()  {
        SubjectDto subjectDto = new SubjectDto( 1L, null, 111, null, null );

        Throwable throwable = assertThrows( ValidationException.class, () -> service.validate( subjectDto ));
        assertNotNull( throwable.getMessage() );
        assertEquals("Object Subject, Name is empty", throwable.getMessage() );

        subjectDto.setName( "" );
        Throwable throwable1 = assertThrows( ValidationException.class, () -> service.validate( subjectDto ));
        assertNotNull( throwable1.getMessage() );
        assertEquals("Object Subject, Name is empty", throwable1.getMessage() );
    }

    @Test
    void validateTestPopulating() {
        SubjectDto subjectDto = new SubjectDto( 1L, "null", null, null, null );

        Throwable throwable = assertThrows( ValidationException.class, () -> service.validate( subjectDto ));
        assertNotNull( throwable.getMessage() );
        assertEquals("Object Subject, Populating is empty", throwable.getMessage() );
    }

    @Test
    void validateTestParent()  {
        SubjectDto subjectDto = new SubjectDto( 1L, "null", 111, 1L, null );

        Throwable throwable = assertThrows( ValidationException.class, () -> service.validate( subjectDto ));
        assertNotNull( throwable.getMessage() );
        assertEquals("Object Subject, Parent is not found", throwable.getMessage() );
    }

    @Test
    void validateTestType()  {
        SubjectDto subjectDto = new SubjectDto( 1L, "null", 111, null, "" );

        Throwable throwable = assertThrows( ValidationException.class, () -> service.validate( subjectDto ));
        assertNotNull( throwable.getMessage() );
        assertEquals("Object Subject, Type is empty", throwable.getMessage() );

        subjectDto.setType("null");
        Throwable throwable1 = assertThrows( ValidationException.class, () -> service.validate( subjectDto ));
        assertNotNull( throwable1.getMessage() );
        assertEquals("Object Subject, Type is not found", throwable1.getMessage() );
    }

    @Test
    void findAllTest() {
        Subject parent1 =  new Subject("parent1", 111, null, null );
        Subject parent2 =  new Subject("parent2", 111, parent1, null );
        List< Subject > subjects = new ArrayList<>();
        subjects.add( parent1 );
        subjects.add( parent2 );

        given( subjectRepository.findAll() ).willReturn( subjects );

        List<SubjectDto> subjectsDto = service.findAll();

        assertNotNull( subjectsDto );
        assertEquals( 2, subjectsDto.size() );
    }

    @Test
    void findById() {
        Subject parent1 =  new Subject("parent1", 111, null, null );
        parent1.setId( 1L );
        Subject parent2 =  new Subject("parent2", 111, parent1, null );
        parent1.setId( 2L );
        given( subjectRepository.findById( 1L ) ).willReturn(Optional.of(parent1));
        given( subjectRepository.findById( 2L ) ).willReturn(Optional.of(parent2));
        SubjectWithParentsDto subjectWithParentsDtoExpected = new SubjectWithParentsDto(1L,"parent2",111, Collections.singletonList("parent1"),null);
        given( subjectConverter.fromSubjectToSubjectWithParentsDto(Mockito.any())).willReturn(subjectWithParentsDtoExpected);

        SubjectWithParentsDto subjectWithParentsDto = service.findById( 2L );

        assertNotNull( subjectWithParentsDto );
        assertEquals( subjectWithParentsDtoExpected, subjectWithParentsDto );
    }

    @Test
    void save() throws ValidationException {
        Subject subject =  new Subject("parent1", 111, null, null );
        subject.setId( 1L );
        given( subjectRepository.save( Mockito.any() ) ).willReturn(subject);

        SubjectDto subjectDtoExtended = new SubjectDto( 1L, "parent1", 111, null, null);
        given( subjectConverter.fromSubjectToSubjectDto( Mockito.any()) ).willReturn( subjectDtoExtended );
        SubjectDto  subjectDto;
        subjectDto = service.save( subjectDtoExtended );

        assertNotNull( subjectDto );
        assertEquals( subjectDtoExtended, subjectDto );
    }

    @Test
    void deleteSubject() {
        try {
            service.deleteSubject( 1L );
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}