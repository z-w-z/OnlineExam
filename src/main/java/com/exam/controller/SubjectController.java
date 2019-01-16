package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.service.SubjectService;

@Controller
@RequestMapping("subject")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	

}
