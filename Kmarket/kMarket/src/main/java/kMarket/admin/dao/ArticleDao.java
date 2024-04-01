package kMarket.admin.dao;

import kMarket.admin.db.SqlArticle;
import kMarket.admin.dto.ArticleRequestDto;
import kMarket.admin.dto.ArticleResponseDto;
import kMarket.admin.request.ArticleUpdateRequest;
import kMarket.cs.db.DBhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends DBhelper {
    private static ArticleDao INSTANCE = new ArticleDao();

    public static ArticleDao getInstance() {
        return INSTANCE;
    }
    private ArticleDao() {};
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int insertArticle(ArticleRequestDto dto) {

        int no = 0;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Transaction 시작

            stmt = conn.createStatement();
            psmt = conn.prepareStatement(SqlArticle.INSERT_ARTICLE);
            psmt.setString(1, dto.getCate());
            psmt.setString(2,dto.getMenu1());
            psmt.setString(3, dto.getMenu2());
            psmt.setString(4, dto.getTitle());
            psmt.setString(5, dto.getContent());
            psmt.executeUpdate();
            rs = stmt.executeQuery(SqlArticle.SELECT_MAX_NO);
            conn.commit(); // 작업확정

            if(rs.next()) {
                no = rs.getInt(1);
            }
            close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return no;
    }

    public List<ArticleResponseDto> selectArticles(String cate) throws SQLException {
        List<ArticleResponseDto> articles = new ArrayList<>();

        try{
            conn = getConnection();
            psmt = conn.prepareStatement(SqlArticle.SELECT_ARTICLES);
            psmt.setString(1, cate);
            rs = psmt.executeQuery();

            while (rs.next()) {
                ArticleResponseDto dto = new ArticleResponseDto();
                dto.setcNo((long) rs.getInt(1));
                dto.setCate(rs.getString(2));
                dto.setMenu1(rs.getString(3));
                dto.setMenu2(rs.getString(4));
                dto.setTitle(rs.getString(5));
                dto.setContent(rs.getString(6));

                articles.add(dto);
            }
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("[ArticleDao]length of result = {}",articles.size());
        return articles;
    }

    public List<ArticleResponseDto> selectPagedArticles(String cate, String menu1,int start) throws SQLException {
        List<ArticleResponseDto> articles = new ArrayList<>();

        conn = getConnection();
        if (menu1.equals("전체")) {
            psmt = conn.prepareStatement(SqlArticle.SELECT_PAGED_ARTICLES_ALL);
            psmt.setString(1,cate);
            psmt.setInt(2, start);
            rs = psmt.executeQuery();

            while (rs.next()) {
                ArticleResponseDto dto = new ArticleResponseDto();
                dto.setcNo((long) rs.getInt(1));
                dto.setCate(rs.getString(2));
                dto.setMenu1(rs.getString(3));
                dto.setMenu2(rs.getString(4));
                dto.setTitle(rs.getString(5));
                dto.setContent(rs.getString(6));
                dto.setRdate(rs.getString(7));
                dto.setHit(rs.getInt(8));

                articles.add(dto);
            }
            return articles;
        }
        psmt = conn.prepareStatement(SqlArticle.SELECT_PAGED_ARTICLES);
        psmt.setString(1,cate);
        psmt.setString(2, menu1);
        psmt.setInt(3, start);
        rs = psmt.executeQuery();

        while (rs.next()) {
            ArticleResponseDto dto = new ArticleResponseDto();
            dto.setcNo((long) rs.getInt(1));
            dto.setCate(rs.getString(2));
            dto.setMenu1(rs.getString(3));
            dto.setMenu2(rs.getString(4));
            dto.setTitle(rs.getString(5));
            dto.setContent(rs.getString(6));
            dto.setRdate(rs.getString(7));
            dto.setHit(rs.getInt(8));

            articles.add(dto);
        }
        return articles;
    }

    public ArticleResponseDto selectArticle(Integer cNo) throws SQLException {
        ArticleResponseDto response = new ArticleResponseDto();
        conn = getConnection();
        psmt = conn.prepareStatement(SqlArticle.SELECT_ARTICLE);
        psmt.setInt(1,cNo);
        rs = psmt.executeQuery();

        while (rs.next()) {
            response.setcNo((long) rs.getInt(1));
            response.setCate(rs.getString(2));
            response.setMenu1(rs.getString(3));
            response.setMenu2(rs.getString(4));
            response.setTitle(rs.getString(5));
            response.setContent(rs.getString(6));
        }
        return response;
    }

    public boolean updateArticle(ArticleUpdateRequest dto) throws SQLException {
        ArticleResponseDto result = new ArticleResponseDto();
        conn = getConnection();
        psmt = conn.prepareStatement(SqlArticle.UPDATE_ARTICLE);
        psmt.setString(1, dto.getCate());
        psmt.setString(2, dto.getMenu1());
        psmt.setString(3, dto.getMenu2());
        psmt.setString(4, dto.getTitle());
        psmt.setString(5, dto.getContent());
        psmt.setInt(6, dto.getcNo());

        int rowNum = psmt.executeUpdate();
        if(rowNum == 0) return false;
        return true;
    }

    public void deleteArticle(Integer articleId) throws SQLException {
        conn = getConnection();
        psmt = conn.prepareStatement(SqlArticle.DELETE_ARTICLE);
        psmt.setInt(1, articleId);
        psmt.executeUpdate();
    }
    // notice select
    public List<ArticleResponseDto> selectNoticesByMenu1(String menu1) throws SQLException {
        List<ArticleResponseDto> notices = new ArrayList<>();
        conn = getConnection();
        psmt = conn.prepareStatement(SqlArticle.SELECT_NOTICES_BY_MENU1);
        psmt.setString(1,menu1);
        rs = psmt.executeQuery();

        while (rs.next()) {
            ArticleResponseDto notice = new ArticleResponseDto();

            notice.setcNo((long) rs.getInt(1));
            notice.setCate(rs.getString(2));
            notice.setMenu1(rs.getString(3));
            notice.setMenu2(rs.getString(4));
            notice.setTitle(rs.getString(5));
            notice.setContent(rs.getString(6));

            notices.add(notice);
        }
        close();
        return notices;
    }

    public int selectCountTotal(String cate, String menu1) {
        int total = 0;

        try {
            conn = getConnection();

            if(menu1.equals("전체")) {
                psmt=conn.prepareStatement(SqlArticle.SELECT_COUNT_TOTAL_ALL);
                psmt.setString(1, cate);
            }else {
                psmt =conn.prepareStatement(SqlArticle.SELECT_COUNT_TOTAL);
                psmt.setString(1, cate);
                psmt.setString(2, menu1);
            }

            rs=psmt.executeQuery();

            if(rs.next()) {
                total = rs.getInt(1);
            }
            close();
        } catch (Exception e) {
            logger.error("selectCountTotal : "+e.getMessage());
        }

        return total;
    }

    public List<ArticleResponseDto> selectTenFaq(String menu1, String menu2) throws SQLException {
        List<ArticleResponseDto> faqs = new ArrayList<>();
        conn = getConnection();
        psmt = conn.prepareStatement(SqlArticle.SELECT_FAQS_BY_MENU1_AND_MENU2);
        psmt.setString(1,menu1);
        psmt.setString(2, menu2);
        rs = psmt.executeQuery();

        while (rs.next()) {
            ArticleResponseDto notice = new ArticleResponseDto();

            notice.setcNo((long) rs.getInt(1));
            notice.setCate(rs.getString(2));
            notice.setMenu1(rs.getString(3));
            notice.setMenu2(rs.getString(4));
            notice.setTitle(rs.getString(5));
            notice.setContent(rs.getString(6));
            notice.setHit(rs.getInt(7));
            notice.setRdate(rs.getString(8));

            faqs.add(notice);
        }
        close();
        return faqs;
    }
}
