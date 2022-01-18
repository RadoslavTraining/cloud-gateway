package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.Course;
import com.monov.cloud.gateway.dto.ItemIds;
import com.monov.cloud.gateway.dto.Student;
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
        return courseGatewayService.addStudentToCourse(courseId,studentId);
    }

    public List<Student> findStudentsByCourseId(Long courseId) {
        ItemIds studentIds = new ItemIds(courseGatewayService.findStudentIdsByCourseId(courseId));
        return studentGatewayService.findStudentsByIds(studentIds);
    }
}
