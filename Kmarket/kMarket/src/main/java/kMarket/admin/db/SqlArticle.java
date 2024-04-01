package kMarket.admin.db;

public class SqlArticle {
    //article
    public static final String INSERT_ARTICLE = "insert into `km_csArticle` set `cate`=?, `menu1`=?, `menu2`=?,`title`=?,`content`=?, `rdate`=NOW()";
    public static final String SELECT_MAX_NO = "select MAX(`cNo`) from `km_csArticle`";
    //TODO :paging 처리 해주어야함
    public static final String SELECT_ARTICLES = "select cNo, cate, menu1, menu2, title,content from `km_csArticle` where cate=?";
    public static final String SELECT_PAGED_ARTICLES = "select cNo, cate, menu1, menu2, title,content,rdate,hit from `km_csArticle` where cate=? and menu1=? order by `cNo` desc limit ?, 10";
    public static final String SELECT_PAGED_ARTICLES_ALL = "select cNo, cate, menu1, menu2, title,content,rdate,hit from `km_csArticle` where cate=? order by `cNo` desc limit ?, 10";
    public static final String SELECT_NOTICES_BY_MENU1 = "select cNo, cate, menu1, menu2, title, content from `km_csArticle` where cate='notice' and menu1=?";
    public static final String SELECT_ARTICLE = "select cNo,cate,menu1,menu2,title,content from `km_csArticle` where cNo=?";
    public static final String UPDATE_ARTICLE = "update `km_csArticle` set cate=?,menu1=?,menu2=?,title=?,content=? where cNo=?";
    public static final String DELETE_ARTICLE = "delete from `km_csArticle` where cNo=?";

    public static final String SELECT_COUNT_TOTAL_ALL = "SELECT COUNT(*) FROM `km_csArticle` where `parent`=0 and `cate`=? ";
    public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `km_csArticle` where `parent`=0 and `cate`=? and `menu1`=? ";

    // faq section
    public static final String SELECT_FAQS_BY_MENU1_AND_MENU2 = "SELECT cNo, cate,menu1,menu2,title,content,hit,rdate from `km_csArticle` where cate='faq' and `menu1`=? and `menu2`=? order by `cNo` desc limit 10";

}
