package kMarket.member.service;

import java.util.List;

import kMarket.member.dao.SignupDAO;
import kMarket.member.dto.SignupDTO;

public class SignupService {
	
	SignupDAO dao = new SignupDAO();
	
	public List<SignupDTO> selectTerms(String type) {
		return dao.selectTerms(type);
	}
}
