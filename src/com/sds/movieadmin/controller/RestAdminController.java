package com.sds.movieadmin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAdminController {

	@PostMapping("/admin")
	public String regist() {
		
		return null;
	}
}
