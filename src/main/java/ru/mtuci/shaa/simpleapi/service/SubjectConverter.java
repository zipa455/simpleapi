package ru.mtuci.shaa.simpleapi.service;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.model.Subject;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class SubjectConverter {
    private final SubjectRepository subjectRepository;
    private final TypeRepository typeRepository;

    public Subject fromSubjectDtoToSubject(SubjectDto subjectDto ) {
        Subject subject = new Subject();
        subject.setId( subjectDto.getId() );
        subject.setName( subjectDto.getName() );
        subject.setPopulating( subjectDto.getPopulating() );
        subject.setParent( subjectRepository.findById( subjectDto.getParent() ).orElse( null) );
        subject.setType( typeRepository.findByName( subjectDto.getType() ).orElse( null ) );
        return subject;
    }

    public SubjectDto fromSubjectToSubjectDto( Subject subject ) {
        return SubjectDto.builder()
                .id( subject.getId() )
                .name( subject.getName() )
                .populating( subject.getPopulating() )
                .parent( subject.getParentId() )
                .type( subject.getType() == null ? null : subject.getType().getName() )
                .build();
    }

    public SubjectWithParentsDto fromSubjectToSubjectWithParentsDto(Subject subject ) {
        SubjectWithParentsDto sbj = new SubjectWithParentsDto();
        sbj.setId( subject.getId() );
        sbj.setName( subject.getName() );
        if( subject.getType() != null ) {
            sbj.setType( subject.getType().getName() );
        } else  {
            sbj.setType( null );
        }

        sbj.setPopulating( subject.getPopulating() );
        ArrayList<String> names = new ArrayList<>();
        Subject parent = subject.getParent();
        while (parent!=null) {
            names.add( parent.getName() );
            parent = parent.getParent();
        }
        sbj.setParentNames(names);
        return sbj;
    }
}
