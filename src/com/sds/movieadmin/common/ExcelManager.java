package com.sds.movieadmin.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.sds.movieadmin.domain.Movie;

@Component
public class ExcelManager {

	//서버에 저장된 엑셀파일을 접근해 읽어 자바의 List 형태로 반환
	public List getMovieData(String excelPath) {
		List<Movie> movieList = new ArrayList<Movie>();

		try {
			XSSFWorkbook book = new XSSFWorkbook(excelPath);
			XSSFSheet sheet= book.getSheetAt(0);
			
			//row 수만큼 반복문 돌려 row가져오기
			Iterator it=sheet.iterator();
			//가장 위 row는 제목 행이므로 반복문에서 제외
			it.next();//하나 넘김
			
			//hasnext()메서드는 다음 위치가 존재할때 true
			while(it.hasNext()) {//hasNext 가 true 라서 다음 row 있으면
				XSSFRow row = (XSSFRow)it.next(); //다음 row에 진입
				XSSFCell codeCell = row.getCell(0);//영화코드
				XSSFCell nameCell = row.getCell(1);//영화이름
				XSSFCell urlCell = row.getCell(2);//이미지URL
				
				System.out.println((int)codeCell.getNumericCellValue()+","+nameCell.getStringCellValue()+","+urlCell.getStringCellValue());
//				엑셀 row 한건은 movie 한 객체에 담기
				Movie movie = new Movie();
				movie.setMovieCd(Integer.toString((int)codeCell.getNumericCellValue()));//영화코드
				movie.setUrl(urlCell.getStringCellValue());
				movieList.add(movie);//리스트에 DTO 넣기
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return movieList;
	}
	
}
