package kMarket.member.service;

import kMarket.member.dao.MemberDAO;
import kMarket.member.dto.MemberDTO;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();
	
	//회원가입(구매자)
	public void insertMember(MemberDTO dto) {
		dao.insertMember(dto);
	}
	
	//회원가입(판매자)
	public void insertMemberSeller(MemberDTO dto) {
		dao.insertMemberSeller(dto);
	}
	
	//로그인
	public MemberDTO selectMember(String uid, String pass) {
		return dao.selectMember(uid, pass);
	}
	
	//아이디 중복확인
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	//이메일 중복확인
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	//휴대폰 중복확인
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
}
