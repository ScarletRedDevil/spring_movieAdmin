package com.sds.movieadmin.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

@Controller
public class MainController {
	
	//관리자 메인 요청 처리
	@GetMapping("/")
	public String getMain() {
		
		
		System.out.println("메인요청");
		
		return "admin/index";
	}

}
