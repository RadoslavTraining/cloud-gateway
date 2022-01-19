package com.monov.cloud.gateway.dto;

import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithStudentDTO {

    private CourseDTO course;
    private StudentDTO student;

}
