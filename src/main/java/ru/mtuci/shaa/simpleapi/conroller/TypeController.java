package ru.mtuci.shaa.simpleapi.conroller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.shaa.simpleapi.dao.TypeRepository;
import ru.mtuci.shaa.simpleapi.dto.TypeDto;
import ru.mtuci.shaa.simpleapi.model.Type;
import ru.mtuci.shaa.simpleapi.service.TypeConverter;

import javax.xml.bind.ValidationException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping( value = "/api/v1/type", produces = MediaType.APPLICATION_JSON_VALUE )
@AllArgsConstructor
public class TypeController {

    private final TypeRepository typeRepository;
    private final  TypeConverter typeConverter;

    @GetMapping( "/byId")
    public TypeDto getById(@RequestParam Long id)  {
        log.info( "get Subject by ID: " + id );
        return typeConverter.fromTypeToTypeDto( this.typeRepository.findById( id ).orElse(null) );
    }

    @GetMapping("/all")
    public List<Type> getAll()  {
        log.info( "get Subject ALLL" );
        return this.typeRepository.findAll();
    }

    @PostMapping("/add")
    public TypeDto saveNewSubject( @RequestBody TypeDto type ) throws ValidationException {
        log.info( "post new Subject: " + type );
        return typeConverter.fromTypeToTypeDto( this.typeRepository.save( typeConverter.formTypeDtoToType( type ) ) );
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        log.info( "delete subject by id: " + id );
        this.typeRepository.deleteById( id );
        return  ResponseEntity.ok().build();
    }
}
