package ru.mtuci.shaa.simpleapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "type" )
@NoArgsConstructor
@AllArgsConstructor
public class Type extends AbstractBaseEntity{

    @Column
    private String name;

}