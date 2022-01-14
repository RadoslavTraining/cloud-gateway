package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.Course;
import com.monov.cloud.gateway.dto.Student;
import com.monov.cloud.gateway.dto.StudentOrCourseIds;
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

    public Course   addStudentToCourse(Long courseId, Long studentId) {
        studentGatewayService.addStudentToCourse(courseId,studentId);
        return courseGatewayService.addStudentToCourse(courseId,studentId);
    }

    public List<Course> getCoursesForStudentById(Long id) {
        Student student = studentGatewayService.findStudentById(id);
        StudentOrCourseIds courseIds = new StudentOrCourseIds(student.getCourseIds());
        return courseGatewayService.findByIds(courseIds);
    }
}
