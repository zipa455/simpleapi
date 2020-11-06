package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.model.Subject;
import ru.mtuci.shaa.simpleapi.model.Type;

import javax.xml.bind.ValidationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Slf4j
@org.springframework.stereotype.Service
@AllArgsConstructor
public class DefaultSubjectService  {
    private final SubjectConverter subjectConverter;
    private final SubjectRepository subjectRepository;
    private final TypeRepository typeRepository;


    protected void validate(SubjectDto subjectDto) throws ValidationException {
        if( isNull(subjectDto) ) { throw new ValidationException("Object Subject is null"); }
        if( isNull(subjectDto.getName()) || subjectDto.getName().isEmpty() ) {
            throw new ValidationException("Object Subject, Name is empty");
        }
        if( isNull(subjectDto.getPopulating())  ) {
            throw new ValidationException("Object Subject, Populating is empty");
        }
        if( subjectDto.getParent() != null  ) {
            log.info( subjectRepository.findById(subjectDto.getParent()).toString() );
            if( subjectRepository.findById(subjectDto.getParent()).isEmpty() ) {
                throw new ValidationException("Object Subject, Parent is not found");
            }
        }
        if( subjectDto.getType() != null ) {
            if( subjectDto.getType().isEmpty() ) {
                throw new ValidationException("Object Subject, Type is empty");
            }
            if( typeRepository.findByName( subjectDto.getName() ).isEmpty() ) {
                throw new ValidationException("Object Subject, Type is not found");
            }
        }
        log.info("validate ok: " + subjectDto);
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
        log.info( "validate pass");
        Subject s = subjectConverter.fromSubjectDtoToSubject( subjectDto );
        Subject saveSubject = subjectRepository.save( s );
        return subjectConverter.fromSubjectToSubjectDto( saveSubject );
    }

    public void deleteSubject(Long id ) {
        subjectRepository.deleteById( id );
    }

    public SubjectDto setType( Long id, String typeId ) throws ValidationException  {
        Optional<Subject> subject = subjectRepository.findById( id );
        if( subject.isEmpty() ) {
            throw new ValidationException( "Subject not found" );
        }

        Optional<Type> type = typeRepository.findByName( typeId );
        if( type.isEmpty() ) {
            throw new ValidationException( "Type not found " );
        }

        subject.get().setType( type.get() );
        return subjectConverter.fromSubjectToSubjectDto( subject.get() );
    }


}
