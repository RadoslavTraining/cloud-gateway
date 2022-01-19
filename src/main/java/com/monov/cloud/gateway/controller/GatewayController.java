package com.monov.cloud.gateway.controller;

import com.monov.cloud.gateway.service.CourseGatewayService;
import com.monov.cloud.gateway.service.GatewayService;
import com.monov.cloud.gateway.service.StudentGatewayService;
import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<CourseDTO> getAllCourses() {
        log.info("Inside getAllCourses method in GatewayController");
        return  courseGatewayService.findAllCourses();
    }

    @PostMapping("/courses")
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO) {
        log.info("Inside saveCourse method in GatewayController");
        return courseGatewayService.saveCourse(courseDTO);
    }

    @GetMapping("/courses/{id}")
    public CourseDTO findCourseById(@PathVariable("id") Long courseId) {
        log.info("Inside findCourseById method in GatewayController");
        return courseGatewayService.findCourseById(courseId);
    }

    // 1st requirement
    @GetMapping("/courses/students/{studentId}")
    public List<CourseDTO> findCoursesByStudentId(@PathVariable(name = "studentId") Long studentId) {
        return courseGatewayService.findCoursesByStudentId(studentId);
    }

    // 3rd requirement
    @PostMapping("/courses/{courseId}/{studentId}")
    public CourseDTO addStudentToCourse(@PathVariable("courseId") Long courseId,
                                        @PathVariable("studentId") Long studentId) {
        log.info("Inside addStudentToCourse method in GatewayController");
        return gatewayService.addStudentToCourse(courseId,studentId);
    }

    @GetMapping("/students")
    public List<StudentDTO> findAllStudents() {
        log.info("Inside findAllStudents method of StudentController ");
        return studentGatewayService.findAllStudents();
    }

    @PostMapping("/students")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        log.info("Inside saveStudent method of StudentController ");
        return studentGatewayService.saveStudent(studentDTO);
    }

    @GetMapping("/students/{id}")
    public StudentDTO findStudentById(@PathVariable("id") Long studentId) {
        log.info("Inside findStudentById method of StudentController ");
        return studentGatewayService.findStudentById(studentId);
    }

    // 2nd requirement
    @GetMapping("/students/courses/{courseId}")
    public List<StudentDTO> findStudentsByCourseId(@PathVariable(name = "courseId") Long courseId) {
        return gatewayService.findStudentsByCourseId(courseId);
    }

}
