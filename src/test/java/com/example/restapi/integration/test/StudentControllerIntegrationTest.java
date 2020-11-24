package com.example.restapi.integration.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.restapi.RestapiApplication;
import com.example.student.model.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses"),
				HttpMethod.GET, entity, String.class);

		String expected = "[\r\n" + 
				"    \"Maths\",\r\n" + 
				"    \"Science\",\r\n" + 
				"    \"English\"\r\n" + 
				"]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	//POST call 
	@Test
	public void addCourse() {

		Course course = new Course();
		course.setId(1);
		course.setName("Java");

		HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/students/Student1/courses/"));

	}
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
}
