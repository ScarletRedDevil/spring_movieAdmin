package com.sds.movieadmin.model.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.AdminException;

@Repository
public class MybatisAdminDAO implements AdminDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Admin admin)throws AdminException {
		int result = sqlSessionTemplate.insert("Admin.insert", admin);
		
		if(result<1) {
			throw new AdminException("관리자 등록 실패");
		}
	}

	@Override
	public Admin loginCheck(Admin admin) throws AdminException{
		Admin dto = sqlSessionTemplate.selectOne("Admin.loginCheck",admin);
		
		//일치하는 데이터가 없다면 dto는 null이므로 예외발생시키기
		if(dto==null) {
			throw new AdminException("로그인 정보가 올바르지 않음");
		}
		
		return dto;
	}
	
	

}
