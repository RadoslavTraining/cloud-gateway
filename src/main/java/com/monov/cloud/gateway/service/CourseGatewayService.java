package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.Course;
import com.monov.cloud.gateway.dto.StudentOrCourseIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CourseGatewayService {
    public static final String COURSE_SERVICE_URL = "http://localhost:9002/courses";


    @Autowired
    RestTemplate restTemplate;

    public List<Course> findAllCourses() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(COURSE_SERVICE_URL,
                 Course[].class)));

    }

    public Course saveCourse(Course course) {
        return restTemplate.postForObject(COURSE_SERVICE_URL,course,Course.class);
    }

    public Course findById(Long courseId) {
        return restTemplate.getForObject(String.format("%s/%d",COURSE_SERVICE_URL,courseId),Course.class);

    }

    public List<Course> findByIds(StudentOrCourseIds courseIds) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/ids/", COURSE_SERVICE_URL),
                courseIds,
                Course[].class)));
    }


    public Course addStudentToCourse(Long courseId, Long studentId) {
        return restTemplate.postForObject(String.format("%s/%d/%d",COURSE_SERVICE_URL,courseId,studentId),null,
                Course.class);
    }
}
