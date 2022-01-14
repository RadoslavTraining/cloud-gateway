package com.monov.cloud.gateway.data;

import lombok.Data;

import java.util.List;


@Data
public class StudentOrCourseIds {
    private List<Long> ids;
}
