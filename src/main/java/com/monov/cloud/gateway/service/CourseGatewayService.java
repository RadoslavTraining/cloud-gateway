package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.CourseDTO;
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

    public List<CourseDTO> findAllCourses() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(courseServiceUrl,
                 CourseDTO[].class)));

    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return restTemplate.postForObject(courseServiceUrl, courseDTO, CourseDTO.class);
    }

    public CourseDTO findCourseById(Long courseId) {
        return restTemplate.getForObject(String.format("%s/%d", courseServiceUrl,courseId), CourseDTO.class);

    }

    public CourseDTO addStudentToCourse(Long courseId, Long studentId) {
        return restTemplate.postForObject(String.format("%s/%d/%d", courseServiceUrl,courseId,studentId),null,
                CourseDTO.class);
    }

    public List<CourseDTO> findCoursesByStudentId(Long studentId) {
        CourseSearchRequest request = new CourseSearchRequest(studentId);
        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/students",
                courseServiceUrl), request, CourseDTO[].class)));
    }

    public List<Long> findStudentIdsByCourseId(Long courseId) {
        return  Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(String.format("%s/students/%d", courseServiceUrl, courseId),
                Long[].class)));
    }
}
