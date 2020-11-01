package ru.mtuci.shaa.simpleapi.model;


import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {

    @Test
    void getParentIdNormal() {
        Subject subject = new Subject();
        Subject parent = new Subject();
        subject.setParent( parent );
        parent.setId( 1L );

        assertEquals( 1L, subject.getParentId() );
    }

    @Test
    void getParentId_parent_null() {
        Subject subject = new Subject();
        assertNull(subject.getParentId());
    }

    @Test
    void getParentId_parentID_null() {
        Subject subject = new Subject();
        Subject parent = new Subject();
        parent.setId( null );
        subject.setParent( parent );
        assertNull(subject.getParentId());
    }
}