package com.monov.cloud.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monov.cloud.gateway.CloudGatewayApplication;
import com.monov.commons.dto.CourseDTO;
import com.monov.commons.dto.CourseSearchRequestDTO;
import com.monov.commons.dto.StudentDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.monov.commons.utils.StringUtils.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CloudGatewayApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location = classpath:integration-test-application.yaml" })
public class GatewayControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Value("${student-service.url}")
    private String studentServiceUrl;

    @Value("${course-service.url}")
    private String courseServiceUrl;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getAllCourses() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(UUID.randomUUID().toString());
        courseDTO.setName("Bio");
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        courseDTOS.add(courseDTO);

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:9002/courses")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(courseDTOS))
                );

        mvc.perform(get("/courses"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveCourse() throws Exception{
    }

    @Test
    public void findCourseById() throws Exception{
    }

    @Test
    public void findCoursesByStudentId() throws Exception{
        final String studentId = UUID.randomUUID().toString();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentId);
        studentDTO.setFirstName("Jennifer");
        studentDTO.setLastName("Lawrence");

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(String.format("%s/%s", studentServiceUrl, studentId))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(studentDTO)));

        CourseSearchRequestDTO searchRequestDTO = new CourseSearchRequestDTO();
        searchRequestDTO.setStudentId(studentId);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Chemistry");
        courseDTO.setId(UUID.randomUUID().toString());
        List<CourseDTO> courseDTOS = new ArrayList<>();
        courseDTOS.add(courseDTO);



        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(String.format("%s/students",courseServiceUrl))))
                .andExpect(method(HttpMethod.POST))
                .andExpect(MockRestRequestMatchers.content().string(asJsonString(searchRequestDTO)))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(courseDTOS)));

        mvc.perform(get(String.format("/courses/students/%s",studentId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",
                        is(courseDTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",
                        is(courseDTO.getName())));
    }

    @Test
    public void addStudentToCourse() throws Exception{
    }

    @Test
    public void findAllStudents() throws Exception{
    }

    @Test
    public void saveStudent() throws Exception{
    }

    @Test
    public void findStudentById() throws Exception{
    }

    @Test
    public void findStudentsByCourseId() throws Exception{
    }
}