package ru.mtuci.shaa.simpleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SubjectDto {
    private Long id;
    private String name;
    private Integer populating;
    private Long parent;
    private String type;
}

