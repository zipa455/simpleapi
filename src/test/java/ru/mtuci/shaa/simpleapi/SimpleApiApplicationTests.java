package ru.mtuci.shaa.simpleapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtuci.shaa.simpleapi.conroller.StatusController;
import ru.mtuci.shaa.simpleapi.conroller.SubjectController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleapiApplicationTests {

    @Autowired
    StatusController statusController;

    @Autowired
    SubjectController  subjectController;

    @Test
    void contextLoads() {
        assertThat(statusController).isNotNull();
        assertThat(subjectController).isNotNull();
    }


}
