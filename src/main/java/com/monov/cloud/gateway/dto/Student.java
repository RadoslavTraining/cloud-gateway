package com.monov.cloud.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long studentId;
    private String firstName;
    private String lastName;
    private List<Long> courseIds;

}
