package com.sds.movieadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

@Controller
public class MovieController {
	
	@Autowired
	private String key; //스프링 설정파일에 등록한 String 형 빈을 자동주입받음
	
	KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
	
	//영화진흥위원회 제공 모든 영화 가져오기
	@GetMapping("/movie/api/list")
	public ModelAndView getApiList() {
		
		
		 // 파라메터 설정
		String curPage = "";					//현재페이지
		String itemPerPage = "";			//결과row수
		String movieNm = "";					//영화명
		String directorNm = "";				//감독명
		String openStartDt = "";				//개봉연도 시작조건 ( YYYY )
		String openEndDt = "";				//개봉연도 끝조건 ( YYYY )	
		String prdtStartYear = "";			//제작연도 시작조건 ( YYYY )
		String prdtEndYear = "";			//제작연도 끝조건    ( YYYY )
		String repNationCd = "";				//대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
		String[] movieTypeCdArr =null;	//영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)
		
		 String movieCdResponse=null;	
			try {
			    movieCdResponse = service.getMovieList(true, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//request.setAttribute("nationCd",nationCd);		
			return null;
	}

}
