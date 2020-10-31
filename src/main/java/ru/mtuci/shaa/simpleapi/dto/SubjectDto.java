package ru.mtuci.shaa.simpleapi.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SubjectDto {
    private Long id;
    private String name;
    private Integer populating;
    private Long parent;
    private Long type;
}

