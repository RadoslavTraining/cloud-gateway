package com.monov.cloud.gateway.security;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

@PropertySource("classpath:application.yaml")
@PropertySource("classpath:application.properties")
@Service
public class RequestInterceptor implements ClientHttpRequestInterceptor {

    @Value("${student-service.url}")
    private String studentServiceUrl;

    @Value("${course-service.url}")
    private String courseServiceUrl;

    @Value("${student-service.username}")
    private String studentServiceUsername;

    @Value("${student-service.pass}")
    private String studentServicePass;

    @Value("${course-service.username}")
    private String courseServiceUsername;

    @Value("${course-service.pass}")
    private String courseServicePass;

//    @Value("${encpass}")
//    private String encpass;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (request.getURI().toURL().toString().startsWith(studentServiceUrl)) {
            request.getHeaders().add("Authorization", encodedAuthHeader(studentServiceUsername, studentServicePass));
        }

        if (request.getURI().toURL().toString().startsWith(courseServiceUrl)) {
            request.getHeaders().add("Authorization", encodedAuthHeader(courseServiceUsername, courseServicePass));
        }

        return execution.execute(request, body);
    }

    private String encodedAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(encodedAuth);
    }
}