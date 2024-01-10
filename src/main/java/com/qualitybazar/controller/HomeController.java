package com.qualitybazar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qualitybazar.responce.ApiResponse;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public ApiResponse welcome()
	{
		return new ApiResponse("welcome to backend",null,true);
	}
	
	
	

}
