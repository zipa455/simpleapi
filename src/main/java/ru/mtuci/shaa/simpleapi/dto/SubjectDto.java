package ru.mtuci.shaa.simpleapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubjectDto {
    private Long id;
    private String Name;
    private Integer populating;
    private Long parent;
    private Long type;
}

