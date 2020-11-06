package ru.mtuci.shaa.simpleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TypeDto {
    private String name;
}
