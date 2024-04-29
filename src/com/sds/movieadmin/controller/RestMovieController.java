package com.sds.movieadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.model.movie.MovieApiService;
import com.sds.movieadmin.model.movie.MovieService;

@RestController
public class RestMovieController {
	
	@Autowired
	private MovieApiService movieApiService;
	
	@Autowired
	private MovieService movieService;
	
	//영화 검색 (국가 및 영화타입을 파라미터로 받는다)
	@GetMapping("/search/movie")
	public List getList(Movie movie) {
//		System.out.println("국가 코드 = "+movie.getRepNationCd());
//		System.out.println("영화유형은 "+movie.getMovieTypeCdArr()[0]);
		
		List movieList = movieApiService.getMovieList(movie);
		
		return movieList;
	}
	
	@PostMapping("/movie")
	public ResponseEntity regist(Movie movie) {
		System.out.println("영화코드"+movie.getMovieCd());
		System.out.println("이미지주소"+movie.getUrl());
		
		//3단계
		movieService.regist(movie);
		
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();//200응답
		
		return entity;
	}
	@ExceptionHandler(MovieException.class)
	public ResponseEntity handle(MovieException e) {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		
		return entity;
	}
}






