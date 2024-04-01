package kr.co.kmarket.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.SignupDTO;

public class SignupDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD 메소드 정의
	public SignupDTO selectTerms() {
		
		SignupDTO dto = new SignupDTO();

		try {
			logger.info("SignupDAO selectTerms...1");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()) {
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
				dto.setFinance(rs.getString(3));
				dto.setLocation(rs.getString(4));
				dto.setTax(rs.getString(5));
			}
			
			logger.info("SignupDAO selectTerms...2");
		} catch (Exception e) {
			logger.error("SignupDAO selectTerms error : " + e.getMessage());
		}
		
		return dto;
		
	}
}
