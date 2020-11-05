package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.model.Subject;

import javax.xml.bind.ValidationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class DefaultSubjectService  {
    private final SubjectConverter subjectConverter;
    private final SubjectRepository subjectRepository;


    protected void validate(SubjectDto subject) throws ValidationException {
        if( isNull(subject) ) { throw new ValidationException("Object Subject is null"); }
        if( isNull(subject.getName()) || subject.getName().isEmpty() ) {
            throw new ValidationException("Object Subject, Name is empty");
        }
        if( isNull(subject.getPopulating())  ) {
            throw new ValidationException("Object Subject, Populating is empty");
        }
        if( isNull(subject.getParent())  ) {
            throw new ValidationException("Object Subject, Parent is empty");
        }
    }


    public List<SubjectDto> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map( subjectConverter::fromSubjectToSubjectDto )
                .collect( Collectors.toList() );
    }


    public SubjectWithParentsDto findById(Long id) {
        Optional<Subject> subject = subjectRepository.findById( id );
        return subject.map(subjectConverter::fromSubjectToSubjectWithParentsDto).orElse(null);
    }

    public SubjectDto save( SubjectDto subjectDto ) throws ValidationException {
        validate( subjectDto );
        Subject s = subjectConverter.fromSubjectDtoToSubject( subjectDto );
        Subject saveSubject = subjectRepository.save( s );
        return subjectConverter.fromSubjectToSubjectDto( saveSubject );
    }

    public void deleteSubject(Long id ) {
        subjectRepository.deleteById( id );
    }


}
