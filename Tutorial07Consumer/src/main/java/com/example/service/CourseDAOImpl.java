package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.CourseDAO;
import com.example.model.CourseModel;

@Service
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public CourseModel selectCourse(String id) {
		CourseModel kurs = restTemplate.getForObject("http://localhost:8080/rest/course/view/"+id, CourseModel.class);
		return kurs;
	}

}
