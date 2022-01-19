package com.monov.cloud.gateway.service;

import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.CourseSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<CourseDTO>> findAllCourses() {
        ParameterizedTypeReference<List<CourseDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(String.format("%s", courseServiceUrl),
                HttpMethod.GET,null,responseType);
    }

    public ResponseEntity<CourseDTO> saveCourse(CourseDTO courseDTO) {
        ParameterizedTypeReference<CourseDTO> responseType = new ParameterizedTypeReference<>() {
        };
        HttpEntity<CourseDTO> request = new HttpEntity<>(courseDTO);
        return restTemplate.exchange(String.format("%s", courseServiceUrl),
                HttpMethod.POST,request,responseType);
//        return restTemplate.postForObject(courseServiceUrl, courseDTO, CourseDTO.class);
    }

    public ResponseEntity<CourseDTO> findCourseById(Long courseId) {
        ParameterizedTypeReference<CourseDTO> responseType = new ParameterizedTypeReference<>() {
        };

        return restTemplate.exchange(String.format("%s/%d", courseServiceUrl,courseId),
                HttpMethod.GET,null,responseType);
    }
// TUKA RABOTI GORE
    public ResponseEntity<CourseDTO> addStudentToCourse(Long courseId, Long studentId) {
//        return restTemplate.postForObject(String.format("%s/%d/%d", courseServiceUrl,courseId,studentId),null,
//                CourseDTO.class);

        ParameterizedTypeReference<CourseDTO> responseType = new ParameterizedTypeReference<>() {
        };

        return restTemplate.exchange(String.format("%s/%d/%d", courseServiceUrl,courseId,studentId),
                HttpMethod.POST,null,responseType);

    }

    public ResponseEntity<List<CourseDTO>> findCoursesByStudentId(Long studentId) {
        ParameterizedTypeReference<List<CourseDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        HttpEntity<Long> entity = new HttpEntity<>(studentId);
        return restTemplate.exchange(String.format("%s/students",
                courseServiceUrl),HttpMethod.POST,entity,responseType);
//        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/students",
//                courseServiceUrl), request, CourseDTO[].class)));
    }

    public ResponseEntity<List<Long>> findStudentIdsByCourseId(Long courseId) {
        ParameterizedTypeReference<List<Long>> responseType = new ParameterizedTypeReference<>() {
        };
        HttpEntity<Long> entity = new HttpEntity<>(courseId);
        return restTemplate.exchange(String.format("%s/students/%d", courseServiceUrl, courseId),HttpMethod.GET,
                entity,responseType);
//        return  Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(String.format("%s/students/%d", courseServiceUrl, courseId),
//                Long[].class)));
    }
}
