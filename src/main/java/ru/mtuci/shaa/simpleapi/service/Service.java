package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.bind.ValidationException;
import java.util.List;

@AllArgsConstructor
public abstract class Service<T> {
    protected final JpaRepository<T, Long> repository;

    protected abstract void validate( T obj ) throws ValidationException;

    public T save(T object) throws ValidationException {
        validate( object );
        return this.repository.save( object );
    }

    public void delete(Long id) {
        this.repository.deleteById( id );
    }

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public T findById(Long id) {

        return this.repository.findById(id).orElse(null);
    }
}
