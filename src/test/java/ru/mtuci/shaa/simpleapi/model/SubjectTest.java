package ru.mtuci.shaa.simpleapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {

    @Test
    void getParentId() {
        Subject subject = new Subject();
        Subject parent = new Subject();
        parent.setId( 1L );
        subject.setParent( parent );
        assertEquals( 1L, subject.getParentId() );

        parent.setId( null );
        assertNull(subject.getParentId());

        subject.setParent( null );
        assertNull(subject.getParentId());
    }
}