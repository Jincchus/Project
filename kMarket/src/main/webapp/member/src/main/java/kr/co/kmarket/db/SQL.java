package kr.co.kmarket.db;

public class SQL {
	
	//Signup
	//약관 넣기
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	//Member
	//회원가입 (일반회원)
	public static final String INSERT_MEMBER = "INSERT INTO `Member` SET "
											 + "uid=?,"
											 + "pass=SHA2(?, 256),"
											 + "name=?,"
											 + "gender=?,"
											 + "hp=?,"
											 + "email=?,"
											 + "zip=?,"
											 + "addr1=?,"
											 + "addr2=?,"
											 + "regip=?,"
											 + "rdate=NOW()";
	
	//회원가입 (판매회원)
	public static final String INSERT_MEMBER_SELLER = "INSERT INTO `Member` SET "
													 + "uid=?,"
													 + "pass=SHA2(?, 256),"
													 + "company=?,"
													 + "ceo=?,"
													 + "bizRegNum=?,"
													 + "comRegNum=?,"
													 + "tel=?,"
													 + "fax=?,"
													 + "email=?,"
													 + "zip=?,"
													 + "addr1=?,"
													 + "addr2=?,"
													 + "manager=?,"
													 + "managerHp=?,"
													 + "regip=?,"
													 + "rdate=NOW()";

	
	public static final String SELECT_MEMBER = "SELECT * FROM `Member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";

	//아이디 중복 확인
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `Member` WHERE `uid`=?";
	//이메일 중복 확인
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `Member` WHERE `email`=?";
	//휴대폰번호 중복 확인
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `Member` WHERE `hp`=?";
}
