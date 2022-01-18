package com.monov.cloud.gateway.service;

import com.monov.cloud.gateway.dto.ItemIds;
import com.monov.cloud.gateway.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class StudentGatewayService {

    public static final String STUDENT_SERVICE_URL = "http://localhost:9001/students";

    @Autowired
    private RestTemplate restTemplate;

    public List<Student> findAllStudents() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(STUDENT_SERVICE_URL, Student[].class)));
    }

    public Student saveStudent(Student student) {
        return restTemplate.postForObject(STUDENT_SERVICE_URL,student,Student.class);
    }


    public Student findStudentById(Long studentId) {
        return restTemplate.getForObject(String.format("%s/%d",STUDENT_SERVICE_URL,studentId), Student.class);

    }

    public List<Student> findStudentsByIds(ItemIds itemIds) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.postForObject(String.format("%s/ids", STUDENT_SERVICE_URL), itemIds,
                Student[].class)));
    }
//    public Student addStudentToCourse(Long courseId, Long studentId) {
//        return restTemplate.postForObject(String.format("%s/%d/%d",STUDENT_SERVICE_URL,studentId,courseId),null,
//                Student.class);
//    }
//
//    public List<Student> getStudentsByCourseId(Long courseId) {
//        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(String.format("%s/course/%d", STUDENT_SERVICE_URL, courseId),
//                Student[].class)));
//    }

//    public List<Course> getCoursesForStudentById(Long studentId) {
//
//        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(String.format("%s/%d/courses",
//                        STUDENT_SERVICE_URL, studentId),
//                Course[].class)));
//    }
}
