package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDtp;
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
        subject.setType( subjectDto.getType() );
        return subject;
    }

    public SubjectDto fromSubjectToSubjectDto( Subject subject ) {
        return SubjectDto.builder()
                .id( subject.getId() )
                .name( subject.getName() )
                .populating( subject.getPopulating() )
                .parent( subject.getParentId() )
                .type( subject.getType() )
                .build();
    }

    public SubjectWithParentsDtp fromSubjectToSubjectWithParentsDto(Subject subject ) {
        SubjectWithParentsDtp sbj = new SubjectWithParentsDtp();
        sbj.setId( subject.getId() );
        sbj.setName( subject.getName() );
        sbj.setType( subject.getType() );
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
