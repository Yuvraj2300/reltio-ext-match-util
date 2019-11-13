package com.example.filedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/")
public class ViewController {
/*
	@GetMapping("/")
	public String	getIndex() {
		return "index";
	}*/
	
	@GetMapping("/summary")
	public String getSummary() {
		try {
			return "summary";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

}
