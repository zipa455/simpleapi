package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mtuci.shaa.simpleapi.dto.TypeDto;
import ru.mtuci.shaa.simpleapi.model.Type;

import javax.xml.bind.ValidationException;


@Component
@AllArgsConstructor
public class TypeConverter {

    public TypeDto fromTypeToTypeDto( Type type ) {
        if( type == null ) {
            return  null;
        }
        return new TypeDto( type.getName() );
    }

    public  Type formTypeDtoToType( TypeDto typeDto ) throws ValidationException {
        if( typeDto == null ) {
            throw new ValidationException( "type is null!" );
        }
        if( typeDto.getName() == null || typeDto.getName().isEmpty() ) {
            throw new ValidationException( "type name is empty!" );
        }
        return new Type( typeDto.getName() );
    }

}
