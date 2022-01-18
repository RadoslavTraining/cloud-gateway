package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.ItemIds;
import com.monov.cloud.gateway.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class StudentGatewayService {

    @Value("${studentservice.url}")
    private String studentServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<StudentDTO> findAllStudents() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(studentServiceUrl, StudentDTO[].class)));
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        return restTemplate.postForObject(studentServiceUrl, studentDTO, StudentDTO.class);
    }


    public StudentDTO findStudentById(Long studentId) {
        return restTemplate.getForObject(String.format("%s/%d", studentServiceUrl,studentId), StudentDTO.class);

    }

    public List<StudentDTO> findStudentsByIds(ItemIds itemIds) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/ids", studentServiceUrl), itemIds,
                StudentDTO[].class)));
    }

}
