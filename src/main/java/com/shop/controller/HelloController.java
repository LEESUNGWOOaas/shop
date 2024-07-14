package com.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.UserDto;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public UserDto hello() {
		UserDto userDto = new UserDto();
		userDto.setAge(20);
		userDto.setName("이성우");
		return userDto;
	}
	
}
