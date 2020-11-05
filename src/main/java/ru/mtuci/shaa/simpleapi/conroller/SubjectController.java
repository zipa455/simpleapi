package ru.mtuci.shaa.simpleapi.conroller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.shaa.simpleapi.dto.SubjectDto;
import ru.mtuci.shaa.simpleapi.dto.SubjectWithParentsDto;
import ru.mtuci.shaa.simpleapi.service.DefaultSubjectService;

import javax.xml.bind.ValidationException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping( value = "/api/v1/subject", produces = MediaType.APPLICATION_JSON_VALUE )
@AllArgsConstructor
public class SubjectController {
    private final DefaultSubjectService defaultSubjectService;

    @GetMapping( "/byId")
    public SubjectWithParentsDto getAll(@RequestParam Long id)  {
        log.info( "get Subject by ID: " + id );
        return this.defaultSubjectService.findById( id );
    }

    @GetMapping("/all")
    public List<SubjectDto> getAll()  {
        log.info( "get Subject ALLL" );
        return this.defaultSubjectService.findAll();
    }

    @PostMapping("/add")
    public SubjectDto saveNewSubject( @RequestBody SubjectDto subject ) throws ValidationException {
        log.info( "post new Subject: " + subject );
        return this.defaultSubjectService.save( subject );
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        log.info( "delete subject by id: " + id );
        this.defaultSubjectService.deleteSubject( id );
        return  ResponseEntity.ok().build();
    }

}
