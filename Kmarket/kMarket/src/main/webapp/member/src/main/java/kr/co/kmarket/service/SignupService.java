package kr.co.kmarket.service;

import kr.co.kmarket.dao.SignupDAO;
import kr.co.kmarket.dto.SignupDTO;

public class SignupService {
	
	SignupDAO dao = new SignupDAO();
	
	public SignupDTO selectTerms() {
		return dao.selectTerms();
	}
}
