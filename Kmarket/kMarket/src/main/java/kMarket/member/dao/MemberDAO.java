package kMarket.member.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.member.db.DBHelper;
import kMarket.member.db.SQL;
import kMarket.member.dto.MemberDTO;

public class MemberDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD 메소드 정의
	//회원가입 (구매자)
	public void insertMember(MemberDTO dto) {
		
		try {
			logger.info("MemberDAO insertMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_MEMBER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setInt(4, dto.getGender());
			psmt.setString(5, dto.getHp());
			psmt.setString(6, dto.getEmail());
			psmt.setInt(7, dto.getType());
			psmt.setString(8, dto.getZip());
			psmt.setString(9, dto.getAddr1());
			psmt.setString(10, dto.getAddr2());
			psmt.setString(11, dto.getRegip());
			psmt.executeUpdate(); //쿼리문 실행
			close();
			
			logger.info("MemberDAO insertMember...2");
		} catch (Exception e) {
			logger.error("MemberDAO insertMember error : " + e.getMessage());
		}
	}
	
	//회원가입 (판매자)
	public void insertMemberSeller(MemberDTO dto) {
		
		try {
			logger.info("MemberDAO insertMemberSeller...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_MEMBER_SELLER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getCompany());
			psmt.setString(4, dto.getCeo());
			psmt.setString(5, dto.getBizRegNum()); //사업자등록번호
			psmt.setString(6, dto.getComRegNum()); //통신판매신고번호
			psmt.setString(7, dto.getTel());
			psmt.setString(8, dto.getFax());
			psmt.setString(9, dto.getEmail());
			psmt.setString(10, dto.getZip());
			psmt.setString(11, dto.getAddr1());
			psmt.setString(12, dto.getAddr2());
			psmt.setString(13, dto.getManager()); //담당자 이름
			psmt.setString(14, dto.getManagerHp()); //담당자 휴대폰 번호
			psmt.setString(15, dto.getRegip());
			
			psmt.executeUpdate(); //쿼리문 실행

			close();
			
			logger.info("MemberDAO insertMemberSeller...2");
		} catch (Exception e) {
			logger.error("MemberDAO insertMemberSeller error : " + e.getMessage());
		}
	}
	
	//로그인
	public MemberDTO selectMember(String uid, String pass) {
		
		MemberDTO dto = null;
		
		try {
			logger.info("MemberDAO selectMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				logger.debug("insertStart");
				dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setHp(rs.getString(5));
				dto.setEmail(rs.getString(6));
				dto.setType(rs.getInt(7));
				dto.setPoint(rs.getInt(8));
				dto.setLevel(rs.getInt(9));
				dto.setZip(rs.getString(10));
				dto.setAddr1(rs.getString(11));
				dto.setAddr2(rs.getString(12));
				dto.setRegip(rs.getString(13));
				dto.setRdate(rs.getString(14));
				dto.setWdate(rs.getString(15));
			}
			close();
			logger.info("MemberDAO selectMember...2");
		} catch (Exception e) {
			logger.error("MemberDAO selectMember error : " + e.getMessage());
		}
		
		return dto;
	}
	
	//아이디 중복 검사
	public int selectCountUid(String uid) {
		
		int result = 0;
		
		try {
			logger.info("MemberDAO selectCountUid...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
			logger.info("MemberDAO selectCountUid...2");

		} catch (Exception e) {
			logger.error("MemberDAO selectCountUid error : " + e.getMessage());
		}
		
		return result;
	}
	
	//이메일 중복 검사
	public int selectCountEmail(String email) {
		
		int result = 0;
		
		try {
			logger.info("MemberDAO selectCountEmail...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
			logger.info("MemberDAO selectCountEmail...2");

		} catch (Exception e) {
			logger.error("MemberDAO selectCountEmail error : " + e.getMessage());
		}
		
		return result;
	}
	
	//휴대폰번호 중복 검사
	public int selectCountHp(String hp) {
		
		int result = 0;
		
		try {
			logger.info("MemberDAO selectCountHp...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
			logger.info("MemberDAO selectCountHp...2");

		} catch (Exception e) {
			logger.error("MemberDAO selectCountHp error : " + e.getMessage());
		}
		
		return result;
	}
	
	public void selectMembers() {}
	public void updateMember() {}
	public void deleteMember() {}
}