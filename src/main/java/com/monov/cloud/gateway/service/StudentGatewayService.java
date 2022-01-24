package com.monov.cloud.gateway.service;

import com.monov.commons.dto.ItemIdsDTO;
import com.monov.commons.dto.StudentDTO;
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
public class StudentGatewayService {

    @Value("${student-service.url}")
    private String studentServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        ParameterizedTypeReference<List<StudentDTO>> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(studentServiceUrl,HttpMethod.GET,null,responseType);
    }

    public ResponseEntity<StudentDTO> saveStudent(StudentDTO studentDTO) {
        ParameterizedTypeReference<StudentDTO> responseType = new ParameterizedTypeReference<>() {};
        HttpEntity<StudentDTO> entity = new HttpEntity<>(studentDTO);

        return restTemplate.exchange(studentServiceUrl,HttpMethod.POST,entity,responseType);
    }


    public ResponseEntity<StudentDTO> findStudentById(Long studentId) {
        ParameterizedTypeReference<StudentDTO> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(String.format("%s/%d", studentServiceUrl,studentId),
                HttpMethod.GET,null,responseType);
    }

    public ResponseEntity<List<StudentDTO>> findStudentsByIds(ItemIdsDTO itemIds) {
        ParameterizedTypeReference<List<StudentDTO>> responseType = new ParameterizedTypeReference<>() {};
        HttpEntity<ItemIdsDTO> entity = new HttpEntity<>(itemIds);

        return restTemplate.exchange(String.format("%s/ids", studentServiceUrl),HttpMethod.POST,entity,responseType);
    }

}
