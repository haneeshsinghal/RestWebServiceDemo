package com.example.rsws;

import com.example.rsws.models.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestWebServiceDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentControllerIntegrationTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/restws";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllStudents() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        System.out.println("RootURI: "+getRootUrl());
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students",
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetStudentById() {
        Student student = restTemplate.getForObject(getRootUrl() + "/students/1", Student.class);
        System.out.println(student.getFirstname());
        assertNotNull(student);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setEmail("admin@gmail.com");
        student.setFirstname("admin");
        student.setLastname("admin");
        student.setPhone("9999999999");

        ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/students", student, Student.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 3;
        Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        student.setFirstname("admin1");
        student.setLastname("admin2");

        restTemplate.put(getRootUrl() + "/employees/" + id, student);

        Student updatedStudent = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        assertNotNull(updatedStudent);
    }

    @Test
    public void testDeleteStudent() {
        int id = 2;
        //Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        //assertNotNull(student);

        //restTemplate.delete(getRootUrl() + "/students/" + id);

        try {
            Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
            assertNotNull(student);

            restTemplate.delete(getRootUrl() + "/students/" + id);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
