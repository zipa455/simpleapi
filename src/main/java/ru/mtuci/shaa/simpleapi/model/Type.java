package ru.mtuci.shaa.simpleapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "type" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Type extends AbstractBaseEntity{

    @Column
    private String name;

}