package ru.mtuci.shaa.simpleapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectWithPaentsDto {
    private Long id;
    private String Name;
    private Integer populating;
    private List<String> parentNames;
    private Long type;
}
