package ru.mtuci.shaa.simpleapi.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "subject" )
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends AbstractBaseEntity{

    @Column
    private String name;
    @Column
    private Integer populating;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name="parent" )
    private Subject parent;

    public Long getParentId(){
        if( this.parent != null ) {
            return this.parent.getId();
        }
        else {
            return null;
        }
    }

    @Column
    private Long type;
}
