package ru.mtuci.shaa.simpleapi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.shaa.simpleapi.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}