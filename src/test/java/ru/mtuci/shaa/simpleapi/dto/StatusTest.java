package ru.mtuci.shaa.simpleapi.dto;

import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void StatusTestString() {
        Status status = null;
        try {
            status = new Status();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        assertNotNull( status );
        assertNotNull( status.getHostAddress() );
        assertNotNull( status.getHostName() );


    }

}