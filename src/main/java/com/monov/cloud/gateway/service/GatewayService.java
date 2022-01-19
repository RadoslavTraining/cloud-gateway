package com.monov.cloud.gateway.service;

import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.ItemIds;
import com.monov.commons.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class GatewayService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StudentGatewayService studentGatewayService;

    @Autowired
    CourseGatewayService courseGatewayService;

    public CourseDTO addStudentToCourse(Long courseId, Long studentId) {
        return courseGatewayService.addStudentToCourse(courseId,studentId);
    }

    public List<StudentDTO> findStudentsByCourseId(Long courseId) {
        ItemIds studentIds = new ItemIds(courseGatewayService.findStudentIdsByCourseId(courseId));
        return studentGatewayService.findStudentsByIds(studentIds);
    }
}
