package kMarket.cs.db;

public class SQL {
	
	//Index Selector
	
	public static final String INDEX_SELECT_NOTICE="select* from `km_csArticle` "
											+ "where `cate`='notice' order by rdate desc "
											+ "limit 0,5";
	
	public static final String INDEX_SELECT_QNA="select* from `km_csArticle` "
											+ "where `cate`='qna' order by rdate desc "
											+ "limit 0,5";
	
	//Article
	
	public static final String INSERT_ARTICLE= "INSERT INTO `km_csArticle` SET `cate`=?, "
																	+ "`menu1`=?, "
																	+ "`menu2`=?, "
																	+ "`writer`=?, "
																	+ "`title`=?, "
																	+ "`content`=?, "
																	+ "`regip`=?, "
																	+ "`file`=?, "
																	+ "`rdate`=NOW()";
	
	public final static String SELECT_MAX_NO = "SELECT MAX(`cNo`) From `km_csArticle`";
	
	public final static String SELECT_ARTICELS ="SELECT * FROM `km_csArticle` where cate=? AND menu1=? order by rdate desc LIMIT ?, 10 ";
	public final static String SELECT_ARTICELS_ALL ="SELECT * FROM `km_csArticle` where cate=? order by rdate desc limit ?, 10";
	public final static String SELECT_ARTICELS_FAQ ="SELECT * FROM `km_csArticle` where cate=? AND menu1=?" ;
	
	public final static String SELECT_ADMIN_ARTICLES="SELECT * FROM `km_csArticle` where cate=? and "
													+ "menu1=? order by rdate desc limit ?, 10";
	
	public final static String SELECT_ADMIN_MENU2_ARTICLES="SELECT * FROM `km_csArticle` where cate=? and "
														+ "menu1=? AND menu2 = ? "
														+ "order by rdate desc limit ?, 10";

	
	public final static String SELECT_ARTICLE ="SELECT * FROM `km_csArticle` where cNo=?";
	
	public final static String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `km_csArticle` where `parent`=0 and `cate`=? and `menu1`=? ";
	public final static String SELECT_COUNT_TOTAL_ALL = "SELECT COUNT(*) FROM `km_csArticle` where `parent`=0 and `cate`=? ";
	
	public final static String INSERT_CONTENT=" insert into `km_csArticle` set parent=?, menu1=?, menu2=?, content=?,  rdate=NOW() ";
	
	
	//댓글
	public final static String SELECT_REPLY="select * from `km_csArticle` where parent=?";
	public final static String COUNT_COMMENT_PLUS="UPDATE `km_csArticle` set `comment`=`comment`+1 where `cNo`=? ";
	
	//ADMIN
	
	public final static String SELECT_MENU2_COUNT_TOTAL = "SELECT COUNT(*) FROM `km_csArticle` "
														+ "where `parent`=0 and `cate`=? and `menu1`=? and menu2=?";
	public final static String SELECT_MENU2_COUNT_TOTAL_ALL = "SELECT COUNT(*) FROM `km_csArticle` "
															+ "where `parent`=0 and `cate`=? and menu1=?";
	
	public final static String DELETE_ARTICLE = "delete from `km_csArticle` where cNo=? or parent=?";
	
	//File
		public static final String INSERT_FILE = "INSERT INTO `File` set "
												+"`ano`=? , "
												+"`ofile`=? , "
												+"`sfile`=? , "
												+"`rdate`=NOW() ";
		public static final String SELECT_FILE ="SELECT * FROM `File` where fno=?";
		
		public static final String SELECT_FILE_SNAMES="select `sfile` from `File` where `ano`=? ";
		
		public static final String UPDATE_FILE ="UPDATE `File` set " 
												+"ofile=?, "
												+"sfile=?, "
												+"rdate=NOW() "
												+"WHERE fno=? ";
		
		public static final String DELETE_FILE = "DELETE from `File` where ano =? ";
}
