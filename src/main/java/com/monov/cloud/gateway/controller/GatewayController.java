package com.monov.cloud.gateway.controller;

import com.monov.cloud.gateway.dto.CourseWithStudentDTO;
import com.monov.cloud.gateway.service.CourseGatewayService;
import com.monov.cloud.gateway.service.GatewayService;
import com.monov.cloud.gateway.service.StudentGatewayService;
import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class GatewayController {

    @Autowired
    private CourseGatewayService courseGatewayService;
    
    @Autowired
    private StudentGatewayService studentGatewayService;
    
    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        log.info("Inside getAllCourses method in GatewayController");
        return  courseGatewayService.findAllCourses();
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO) {
        log.info("Inside saveCourse method in GatewayController");
        return courseGatewayService.saveCourse(courseDTO);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> findCourseById(@PathVariable("id") String courseId) {
        log.info("Inside findCourseById method in GatewayController");
        return courseGatewayService.findCourseById(courseId);
    }

    @GetMapping("/courses/students/{studentId}")
    public ResponseEntity<List<CourseDTO>> findCoursesByStudentId(@PathVariable(name = "studentId") String studentId) {
        return gatewayService.findCoursesByStudentId(studentId);
    }

    @PostMapping("/courses/{courseId}/{studentId}")
    public ResponseEntity<CourseWithStudentDTO> addStudentToCourse(@PathVariable("courseId") String courseId,
                                                                   @PathVariable("studentId") String studentId) {
        log.info("Inside addStudentToCourse method in GatewayController");
        return gatewayService.addStudentToCourse(courseId,studentId);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        log.info("Inside findAllStudents method of StudentController ");
        return studentGatewayService.findAllStudents();
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        log.info("Inside saveStudent method of StudentController ");
        return studentGatewayService.saveStudent(studentDTO);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable("id") String studentId) {
        log.info("Inside findStudentById method of StudentController ");
        return studentGatewayService.findStudentById(studentId);
    }

    @GetMapping("/students/courses/{courseId}")
    public ResponseEntity<List<StudentDTO>> findStudentsByCourseId(@PathVariable(name = "courseId") String courseId) {
        return gatewayService.findStudentsByCourseId(courseId);
    }

    @GetMapping("/test/{str}")
    public String abc (@PathVariable(name = "str") String str) {
        String str2 = ("\"\"\"\"sadasda");
        return str2;
    }
}
