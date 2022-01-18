package com.monov.cloud.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long courseId;
    private String name;
    private List<Long> studentIds;

}
