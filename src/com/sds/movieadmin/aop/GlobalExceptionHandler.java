package com.sds.movieadmin.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sds.movieadmin.exception.UnAuthorizedException;

//하위컨트롤러를 포함해 어플리케잇 ㅕㄴ의 전반적인 영역에 걸쳐 발생하는 예외를 아래의
//어노테이션에만 명시하면 이 클래스에서 모두 처리 가능. 따라서 예외처리를 여기저기 파편화시키지 않고
//하나의 공통 클래스에서 관리하려면 @ControllerAdvice를 이용할 수 있음.

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UnAuthorizedException.class)
	public String handle(UnAuthorizedException e) {
		
		return "admin/error/result";
	}
}
