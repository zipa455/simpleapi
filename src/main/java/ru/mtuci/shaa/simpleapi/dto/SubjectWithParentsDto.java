package ru.mtuci.shaa.simpleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectWithParentsDto {
    private Long id;
    private String name;
    private Integer populating;
    private List<String> parentNames;
    private String type;
}
