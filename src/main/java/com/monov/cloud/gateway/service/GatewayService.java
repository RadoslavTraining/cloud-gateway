package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.CourseWithStudentDTO;
import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.ItemIdsDTO;
import com.monov.commons.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GatewayService {

    @Autowired
    StudentGatewayService studentGatewayService;

    @Autowired
    CourseGatewayService courseGatewayService;

    public ResponseEntity<CourseWithStudentDTO> addStudentToCourse(String courseId, String studentId) {
        StudentDTO studentDTO = studentGatewayService.findStudentById(studentId).getBody();
        CourseDTO courseDTO = courseGatewayService.addStudentToCourse(courseId,studentId).getBody();

        return new ResponseEntity<>(new CourseWithStudentDTO(courseDTO, studentDTO),HttpStatus.OK);
    }

    public ResponseEntity<List<StudentDTO>> findStudentsByCourseId(String courseId) {
        courseGatewayService.findCourseById(courseId);
        ItemIdsDTO studentIds = new ItemIdsDTO(courseGatewayService.findStudentIdsByCourseId(courseId).getBody());

        return studentGatewayService.findStudentsByIds(studentIds);
    }

    public ResponseEntity<List<CourseDTO>> findCoursesByStudentId(String studentId) {
        studentGatewayService.findStudentById(studentId);
        return courseGatewayService.findCoursesByStudentId(studentId);
    }
}
