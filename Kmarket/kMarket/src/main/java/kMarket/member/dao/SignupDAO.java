package kMarket.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.member.db.DBHelper;
import kMarket.member.db.SQL;
import kMarket.member.dto.SignupDTO;

public class SignupDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD 메소드 정의
	public List<SignupDTO> selectTerms(String type) {
		
		
		List<SignupDTO> results = new ArrayList<>();

		try {
			logger.info("SignupDAO selectTerms...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_TERMS);
			
			if(type.equals("normal")) {
				psmt.setString(1, "1");
				psmt.setString(2, "3");
				psmt.setString(3, "4");
				psmt.setString(4, "5");
			}else {
				psmt.setString(1, "2");
				psmt.setString(2, "3");
				psmt.setString(3, "4");
				psmt.setString(4, "5");			
			}
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				SignupDTO dto = new SignupDTO();
				dto.setContent(rs.getString(1));
				dto.setType(rs.getString(2));
				dto.setTitle(rs.getString(3));
				results.add(dto);
			}
			logger.info("SignupDAO selectTerms...2");
		} catch (Exception e) {
			logger.error("SignupDAO selectTerms error : " + e.getMessage());
		}
		
		return results;
		
	}
}
