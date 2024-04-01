package kMarket.member.db;

public class SQL {
	
	//Signup
	//약관 넣기
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms` where type IN(?,?,?,?)";
	
	public static final String SELECT_TERMS_BY_TYPE = "SELECT * FROM `km_member_terms` WHERE `type`=?";

	
	//Member
	//회원가입 (일반회원)
	public static final String INSERT_MEMBER = "INSERT INTO `km_member` SET "
											 + "uid=?,"
											 + "pass=SHA2(?, 256),"
											 + "name=?,"
											 + "gender=?,"
											 + "hp=?,"
											 + "email=?,"
											 + "type=?,"
											 + "zip=?,"
											 + "addr1=?,"
											 + "addr2=?,"
											 + "regip=?,"
											 + "rdate=NOW()";
	
	//회원가입 (판매회원)
	public static final String INSERT_MEMBER_SELLER = "INSERT INTO `km_member` SET "
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

	
	public static final String SELECT_MEMBER = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";

	//아이디 중복 확인
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=?";
	//이메일 중복 확인
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `km_member` WHERE `email`=?";
	//휴대폰번호 중복 확인
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `km_member` WHERE `hp`=?";
}
