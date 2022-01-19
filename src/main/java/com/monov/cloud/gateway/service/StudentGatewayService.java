package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.exception.ItemNotFoundException;
import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.ItemIds;
import com.monov.commons.dto.StudentDTO;
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
public class StudentGatewayService {

    @Value("${studentservice.url}")
    private String studentServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        ParameterizedTypeReference<List<StudentDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(studentServiceUrl,HttpMethod.GET,null,responseType);

//        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(studentServiceUrl, StudentDTO[].class)));
    }

    public ResponseEntity<StudentDTO> saveStudent(StudentDTO studentDTO) {
        ParameterizedTypeReference<StudentDTO> responseType = new ParameterizedTypeReference<>() {};
        HttpEntity<StudentDTO> entity = new HttpEntity<>(studentDTO);

        return restTemplate.exchange(studentServiceUrl,HttpMethod.POST,entity,responseType);
//        return restTemplate.postForObject(studentServiceUrl, studentDTO, StudentDTO.class);
    }


    public ResponseEntity<StudentDTO> findStudentById(Long studentId) {
//        StudentDTO studentDTO = restTemplate.getForObject(String.format("%s/%d", studentServiceUrl,studentId),
//                StudentDTO.class);
//        if(studentDTO != null) {
//            return studentDTO;
//        }
//        throw new ItemNotFoundException();

        ParameterizedTypeReference<StudentDTO> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(String.format("%s/%d", studentServiceUrl,studentId),
                HttpMethod.GET,null,responseType);

    }

    public ResponseEntity<List<StudentDTO>> findStudentsByIds(ItemIds itemIds) {
        ParameterizedTypeReference<List<StudentDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        HttpEntity<ItemIds> entity = new HttpEntity<>(itemIds);

        return restTemplate.exchange(String.format("%s/ids", studentServiceUrl),HttpMethod.POST,entity,responseType);
//        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/ids", studentServiceUrl), itemIds,
//                StudentDTO[].class)));
    }

}
