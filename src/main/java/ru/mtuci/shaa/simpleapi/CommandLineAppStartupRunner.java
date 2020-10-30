package ru.mtuci.shaa.simpleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mtuci.shaa.simpleapi.dao.SubjectRepository;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final SubjectRepository subjectRepository;

    @Autowired
    public CommandLineAppStartupRunner(SubjectRepository cityRepository) {
        this.subjectRepository = cityRepository;
    }


    @Override
    public void run( String...args) throws Exception {
        System.out.println( subjectRepository.findById( 1L ).get() );
    }
}
