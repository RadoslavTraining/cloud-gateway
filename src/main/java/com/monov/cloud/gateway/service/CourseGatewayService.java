package com.monov.cloud.gateway.service;

import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.CourseSearchRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseGatewayService {
    @Value("${course-service.url}")
    private String courseServiceUrl;


    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<List<CourseDTO>> findAllCourses() {
        ParameterizedTypeReference<List<CourseDTO>> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(String.format("%s", courseServiceUrl),
                HttpMethod.GET,null,responseType);
    }

    public ResponseEntity<CourseDTO> saveCourse(CourseDTO courseDTO) {
        ParameterizedTypeReference<CourseDTO> responseType = new ParameterizedTypeReference<>() {};
        HttpEntity<CourseDTO> request = new HttpEntity<>(courseDTO);

        return restTemplate.exchange(String.format("%s", courseServiceUrl),
                HttpMethod.POST,request,responseType);
    }

    public ResponseEntity<CourseDTO> findCourseById(String courseId) {
        ParameterizedTypeReference<CourseDTO> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(String.format("%s/%s", courseServiceUrl,courseId),
                HttpMethod.GET,null,responseType);
    }

    public ResponseEntity<CourseDTO> addStudentToCourse(String courseId, String studentId) {
        ParameterizedTypeReference<CourseDTO> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(String.format("%s/%s/%s", courseServiceUrl,courseId,studentId),
                HttpMethod.POST,null,responseType);
    }

    public ResponseEntity<List<CourseDTO>> findCoursesByStudentId(String studentId) {
        ParameterizedTypeReference<List<CourseDTO>> responseType = new ParameterizedTypeReference<>() {};
        CourseSearchRequestDTO searchRequestDTO = new CourseSearchRequestDTO();
        searchRequestDTO.setStudentId(studentId);
        HttpEntity<CourseSearchRequestDTO> entity = new HttpEntity<>(searchRequestDTO);

        return restTemplate.exchange(String.format("%s/students",
                courseServiceUrl),HttpMethod.POST,entity,responseType);
    }

    public ResponseEntity<List<String>> findStudentIdsByCourseId(String courseId) {
        ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<>() {};
        HttpEntity<String> entity = new HttpEntity<>(courseId);

        return restTemplate.exchange(String.format("%s/students/%s", courseServiceUrl, courseId),
                HttpMethod.GET,
                entity,responseType);
    }

}
