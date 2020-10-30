package ru.mtuci.shaa.simpleapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Access( AccessType.FIELD )
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private  Long id;

}
