package ru.mtuci.shaa.simpleapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.model.Subject;
import ru.mtuci.shaa.simpleapi.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@AutoConfigureJsonTesters
@SpringBootTest( classes = SubjectConverter.class )
@AutoConfigureMockMvc
class SubjectConverterTest {



    @MockBean
    private SubjectRepository subjectRepository;

    @MockBean
    private TypeRepository typeRepository;

    static private SubjectConverter converter ;

    @BeforeEach
    void Setup() {
        converter = new SubjectConverter(subjectRepository,typeRepository);
    }

    @Test
    void fromSubjectDtoToSubjectTest() {
        given( subjectRepository.findById( 1L ) ).willReturn( Optional.of( new Subject("parent", 111, null, null ) ) );
        given( typeRepository.findByName("Type1" )).willReturn( Optional.of( new Type("Type1" ) ) );

        assertNotNull( converter  );
        SubjectDto subjectDto = new SubjectDto( null, "name", 111, 1L, "Type1" );
        System.out.println(subjectRepository.findById( 1L ));
        System.out.println(subjectRepository.findById( 1L ));
        Subject  subject = converter.fromSubjectDtoToSubject( subjectDto );
        Subject  subject1 = converter.fromSubjectDtoToSubject( new SubjectDto( null, "name", 111, null, "" ) );
        Subject  subject2 = converter.fromSubjectDtoToSubject( new SubjectDto( null, "name", 111, null, null ) );

        assertNotNull( subject1  );
        assertNotNull( subject2  );
        assertNull( subject1.getParent() );
        assertNull( subject2.getType() );
        assertNotNull( subject  );
        assertEquals( subjectDto.getName(), subject.getName()  );
        assertEquals( subjectDto.getPopulating(), subject.getPopulating()  );
        assertNotNull( subject.getParent() );
        assertEquals( "parent", subject.getParent().getName()  );
        assertNotNull( subject.getType() );
        assertEquals( "Type1", subject.getType().getName()  );
    }

    @Test
    void fromSubjectToSubjectDtoTest() {
        Subject parent1 =  new Subject("parent1", 111, null, null );
        parent1.setId( 1L );
        given( subjectRepository.findById( 1L ) ).willReturn( Optional.of( parent1 ) );
        Type type1 = new Type("Type1" );
        given( typeRepository.findByName("Type1" )).willReturn( Optional.of( type1 ) );

        Subject subject = new Subject(  "name", 222, parent1, type1 );
        SubjectDto subjectDto = converter.fromSubjectToSubjectDto( subject );
        SubjectDto subjectDto1 = converter.fromSubjectToSubjectDto( new Subject(  "name", 222, parent1, null ) );

        assertNotNull( subjectDto  );
        assertEquals( "name" , subjectDto.getName() );
        assertEquals( 222 , subjectDto.getPopulating() );
        assertNotNull( subjectDto.getParent() );
        assertEquals( 1L, subjectDto.getParent() );
        assertNotNull( subjectDto.getType() );
        assertEquals( "Type1", subjectDto.getType()  );
    }

    @Test
    void fromSubjectToSubjectWithParentsDtoTest() {
        Subject parent1 =  new Subject("parent1", 111, null, null );
        parent1.setId( 1L );
        Subject parent2 =  new Subject("parent2", 111, parent1, null );
        parent2.setId( 2L );
        given( subjectRepository.findById( 1L ) ).willReturn( Optional.of( parent1 ) );
        given( subjectRepository.findById( 2L ) ).willReturn( Optional.of( parent2 ) );
        Type type1 = new Type("Type1" );
        given( typeRepository.findByName("Type1" )).willReturn( Optional.of( type1 ) );

        SubjectWithParentsDto subject = converter.fromSubjectToSubjectWithParentsDto( new Subject(  "name", 222, parent2, type1 ) );
        SubjectWithParentsDto subject1 = converter.fromSubjectToSubjectWithParentsDto( new Subject(  "name", 222, parent2, null ) );

        assertNotNull( subject  );
        assertEquals( "name", subject.getName()  );
        assertEquals( 222, subject.getPopulating()  );
        assertNotNull( subject.getParentNames() );
        assertEquals( Arrays.asList("parent2", "parent1"), subject.getParentNames() );
        assertNotNull( subject.getType() );
        assertEquals( "Type1", subject.getType()  );
    }
}