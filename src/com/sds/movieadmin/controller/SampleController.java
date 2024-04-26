package com.sds.movieadmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieAPIServiceImplService;
import kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieInfoResult;

@RestController
public class SampleController {
	// 발급키
			String key = "0173122db3da4fb149dade4d437aa368";
			
			// KOBIS 오픈 API Rest Client를 통해 호출
		    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
		    
		    ObjectMapper mapper = new ObjectMapper();
	
	//샘플1 영화목록
	@GetMapping(value="/movie/movielist", produces="application/json;charset=UTF-8")
	public String sample1() {
	    // 파라메터 설정
		String curPage = "";					//현재페이지
		String itemPerPage = "";		//결과row수
		String movieNm = "";						//영화명
		String directorNm = "";				//감독명
		String openStartDt = "";			//개봉연도 시작조건 ( YYYY )
		String openEndDt = "";				//개봉연도 끝조건 ( YYYY )	
		String prdtStartYear = "";	//제작연도 시작조건 ( YYYY )
		String prdtEndYear = "";			//제작연도 끝조건    ( YYYY )
		String repNationCd = "";			//대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
		String[] movieTypeCdArr =null;	//영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)
		
		
		//불러오기 테스트
		
	    
	    String movieCdResponse=null;	
		try {
		    movieCdResponse = service.getMovieList(true, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//request.setAttribute("nationCd",nationCd);		
		return movieCdResponse;
	}
	
	//샘플2 국가정보
	@GetMapping(value="/movie/nation", produces="application/json;charset=UTF-8")
	public String sample2() {
		String nationCdResponse = null;
		
		try {
			nationCdResponse = service.getComCodeList(true,"2204");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nationCdResponse;
	}
	
	//샘플3 영화 타입?
	@GetMapping(value="/movie/type", produces="application/json;charset=UTF-8")
	public String sample3() {
		String movieTypeCdResponse = null;
		
		try {
			movieTypeCdResponse = service.getComCodeList(true,"2201");
		} catch (OpenAPIFault e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return movieTypeCdResponse;
	}
	
	//샘플 4 영화1편 조회 /movie/222013 이런식 조회
	@GetMapping(value="/movie/{moviecd}", produces="application/json;charset=UTF-8")
	public MovieInfoResult sample4(@PathVariable("moviecd")String moviecd) {
		MovieInfoResult movieInfoResult = null;
		
		try {
			movieInfoResult = new MovieAPIServiceImplService().getMovieAPIServiceImplPort().searchMovieInfo(key, moviecd);
		} catch (kr.or.kobis.kobisopenapi.consumer.soap.movie.OpenAPIFault e) {
			e.printStackTrace();
		}
		
		return movieInfoResult;
	}
	
}	