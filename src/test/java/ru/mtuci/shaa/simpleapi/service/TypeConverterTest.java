package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtuci.shaa.simpleapi.dto.TypeDto;
import ru.mtuci.shaa.simpleapi.model.Type;

import javax.xml.bind.ValidationException;

import static org.junit.jupiter.api.Assertions.*;


class TypeConverterTest {


    private final TypeConverter typeConverter = new TypeConverter();

    @Test
    void fromTypeToTypeDto() {
        TypeDto typeDto = typeConverter.fromTypeToTypeDto( null );
        assertNull( typeDto );
        typeDto = typeConverter.fromTypeToTypeDto( new Type("Name") );
        assertEquals( "Name", typeDto.getName());
    }

    @Test
    void formTypeDtoToType() {
        Throwable throwable = assertThrows( ValidationException.class, () -> typeConverter.formTypeDtoToType( null ));
        assertNotNull( throwable.getMessage() );
        assertEquals("type is null!", throwable.getMessage() );

        TypeDto typeDto = new TypeDto( "" );
        Throwable throwable1 = assertThrows( ValidationException.class, () -> typeConverter.formTypeDtoToType( typeDto ));
        assertNotNull( throwable1.getMessage() );
        assertEquals("type name is empty!", throwable1.getMessage() );

        typeDto.setName( "null");
        try{
            Type typeActual = typeConverter.formTypeDtoToType( typeDto );
            assertEquals( "null", typeActual.getName() );
        } catch (ValidationException e) {
            Assert.fail(e.getMessage());
        }


    }
}