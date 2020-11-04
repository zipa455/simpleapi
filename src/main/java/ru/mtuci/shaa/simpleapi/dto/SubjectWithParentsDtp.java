package ru.mtuci.shaa.simpleapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectWithParentsDtp {
    private Long id;
    private String name;
    private Integer populating;
    private List<String> parentNames;
    private String type;
}
