package com.monov.cloud.gateway.service;

import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.ItemIds;
import com.monov.commons.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class GatewayService {

    @Autowired
    StudentGatewayService studentGatewayService;

    @Autowired
    CourseGatewayService courseGatewayService;

    public ResponseEntity<CourseDTO> addStudentToCourse(Long courseId, Long studentId) {
        // Check if student exists before attempting to assign to course
        studentGatewayService.findStudentById(studentId);
        return courseGatewayService.addStudentToCourse(courseId,studentId);
    }

    public ResponseEntity<List<StudentDTO>> findStudentsByCourseId(Long courseId) {
        // Check if course exists first
        courseGatewayService.findCourseById(courseId);
        ItemIds studentIds = new ItemIds(courseGatewayService.findStudentIdsByCourseId(courseId).getBody());
        return studentGatewayService.findStudentsByIds(studentIds);
    }
}
