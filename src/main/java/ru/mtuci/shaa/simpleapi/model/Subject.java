package ru.mtuci.shaa.simpleapi.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "subject" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    //@ManyToOne
    //@JoinColumn( name="type", referencedColumnName = "id" )
    //private Type type;
    private Long type;
}
