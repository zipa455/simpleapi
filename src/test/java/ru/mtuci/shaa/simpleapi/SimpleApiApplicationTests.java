package ru.mtuci.shaa.simpleapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleapiApplicationTests {

    @Test
    void contextLoads() {
        Integer i = 1;
        assertNotNull( i );
    }


}
