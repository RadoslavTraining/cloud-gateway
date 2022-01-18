package com.monov.cloud.gateway.controller;

import com.monov.cloud.gateway.dto.Course;
import com.monov.cloud.gateway.dto.Student;
import com.monov.cloud.gateway.service.CourseGatewayService;
import com.monov.cloud.gateway.service.GatewayService;
import com.monov.cloud.gateway.service.StudentGatewayService;
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
    public List<Course> getAllCourses() {
        log.info("Inside getAllCourses method in GatewayController");
        return  courseGatewayService.findAllCourses();
    }

    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        log.info("Inside saveCourse method in GatewayController");
        return courseGatewayService.saveCourse(course);
    }

    @GetMapping("/courses/{id}")
    public Course findCourseById(@PathVariable("id") Long courseId) {
        log.info("Inside findCourseById method in GatewayController");
        return courseGatewayService.findCourseById(courseId);
    }

    // 1st requirement
    @GetMapping("/courses/students/{studentId}")
    public List<Course> findCoursesByStudentId(@PathVariable(name = "studentId") Long studentId) {
        return courseGatewayService.findCoursesByStudentId(studentId);
    }

    // 3rd requirement
    @PostMapping("/courses/{courseId}/{studentId}")
    public Course addStudentToCourse(@PathVariable("courseId") Long courseId,
                                     @PathVariable("studentId") Long studentId) {
        log.info("Inside addStudentToCourse method in GatewayController");
        return gatewayService.addStudentToCourse(courseId,studentId);
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {
        log.info("Inside findAllStudents method of StudentController ");
        return studentGatewayService.findAllStudents();
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        log.info("Inside saveStudent method of StudentController ");
        return studentGatewayService.saveStudent(student);
    }

    @GetMapping("/students/{id}")
    public Student findStudentById(@PathVariable("id") Long studentId) {
        log.info("Inside findStudentById method of StudentController ");
        return studentGatewayService.findStudentById(studentId);
    }

    // 2nd requirement
    @GetMapping("/students/courses/{courseId}")
    public List<Student> findStudentsByCourseId(@PathVariable(name = "courseId") Long courseId) {
        return gatewayService.findStudentsByCourseId(courseId);
    }



//    @GetMapping("/students/{id}/courses")
//    public List<Course> getCoursesForStudentById(@PathVariable("id") Long id) {
//        log.info("Inside getCoursesForStudent method of StudentController ");
//        return gatewayService.getCoursesForStudentById(id);
//    }

//    @GetMapping("/courses/{id}/students")
//    public List<Student> getStudentsByCourseId(@PathVariable("id") Long courseId) {
////        return studentGatewayService.getStudentsByCourseId(courseId);
//    }

}
