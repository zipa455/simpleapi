package ru.mtuci.shaa.simpleapi.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectDtoTest {


    @Test
    void getId() {
        SubjectDto dto = new SubjectDto( 1L,"name", 1, 1L, null );
        assertNotNull( dto );
    }


}