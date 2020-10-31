package ru.mtuci.shaa.simpleapi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.shaa.simpleapi.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
