package com.sds.movieadmin.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieadmin.common.EncryptionManager;
import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private EncryptionManager encryptionManager;
	
	public void regist(Admin admin) throws AdminException {
		//암호화 처리
		String pass = admin.getAdmin_pwd();//게터로 가져와서
		pass = encryptionManager.getConvertedData(pass); //암호화해서
		admin.setAdmin_pwd(pass);//암호화시킨 비밀번호를 다시 대입
		
		adminDAO.insert(admin);
	}

	public Admin loginCheck(Admin admin) throws AdminException{
		//평문 비밀번호를 가져와 암호화하여 admin 에 set
		admin.setAdmin_pwd(encryptionManager.getConvertedData(admin.getAdmin_pwd()));
		
		Admin dto = adminDAO.loginCheck(admin);
		//dto라 이름주는 이유는 admin이라고 하면 기존 admin과 충돌 가능성 있어서 
		
		return dto;
	}
	

}
