package ru.mtuci.shaa.simpleapi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.shaa.simpleapi.model.Type;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Type> findByName(String type);
}