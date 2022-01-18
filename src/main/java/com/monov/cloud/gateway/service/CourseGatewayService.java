package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.Course;
import com.monov.cloud.gateway.dto.CourseSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CourseGatewayService {
    @Value("${courseservice.url}")
    private String courseServiceUrl;


    @Autowired
    RestTemplate restTemplate;

    public List<Course> findAllCourses() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(courseServiceUrl,
                 Course[].class)));

    }

    public Course saveCourse(Course course) {
        return restTemplate.postForObject(courseServiceUrl,course,Course.class);
    }

    public Course findCourseById(Long courseId) {
        return restTemplate.getForObject(String.format("%s/%d", courseServiceUrl,courseId),Course.class);

    }

    public Course addStudentToCourse(Long courseId, Long studentId) {
        return restTemplate.postForObject(String.format("%s/%d/%d", courseServiceUrl,courseId,studentId),null,
                Course.class);
    }

    public List<Course> findCoursesByStudentId(Long studentId) {
        CourseSearchRequest request = new CourseSearchRequest(studentId);
        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/students",
                courseServiceUrl), request, Course[].class)));
    }

    public List<Long> findStudentIdsByCourseId(Long courseId) {
        return  Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(String.format("%s/students/%d", courseServiceUrl, courseId),
                Long[].class)));
    }
}
