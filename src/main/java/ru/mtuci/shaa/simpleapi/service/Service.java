package ru.mtuci.shaa.simpleapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.shaa.simpleapi.model.Subject;

import javax.xml.bind.ValidationException;
import java.util.List;

@AllArgsConstructor
public abstract class Service<T> {
    protected final JpaRepository<T, Long> repository;

    abstract protected void validate( T obj ) throws ValidationException;

    public T save(T object) throws ValidationException {
        validate( object );
        T saveObject = this.repository.save( object );
        return saveObject;
    }

    public void delete(Long id) {
        this.repository.deleteById( id );
    }

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public T findById(Long id) {
        return this.repository.findById(id).get();
    }
}
