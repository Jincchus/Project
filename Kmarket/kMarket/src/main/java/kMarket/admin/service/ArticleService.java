package kMarket.admin.service;

import kMarket.admin.dao.ArticleDao;
import kMarket.admin.dto.ArticleRequestDto;
import kMarket.admin.dto.ArticleResponseDto;
import kMarket.admin.request.ArticleUpdateRequest;

import java.sql.SQLException;
import java.util.List;

public class ArticleService {
//    singleton
    private static ArticleService INSTANCE = new ArticleService();
    private ArticleService() {} //생성자에 접근 x
    public static ArticleService getInstance() {
        return INSTANCE;
    }
    private static ArticleDao articleDao = ArticleDao.getInstance();
    private static ArticleDao dao = ArticleDao.getInstance();

    ///
    public int insertArticle(ArticleRequestDto dto) {
        return dao.insertArticle(dto);
    }

    public List<ArticleResponseDto> getArticlesByCate(String cate) throws SQLException {
        return articleDao.selectArticles(cate);
    }
    public List<ArticleResponseDto> getPagedArticles(String cate, String menu1,int start) throws SQLException {
        return articleDao.selectPagedArticles(cate, menu1, start);
    }
    public ArticleResponseDto getOneArticle(Integer cNo) throws SQLException {
        return articleDao.selectArticle(cNo);
    }

    public boolean updateArticle(ArticleUpdateRequest dto) throws SQLException {
        return articleDao.updateArticle(dto);
    }
    public void deleteArticle(Integer articleId) throws SQLException {
        articleDao.deleteArticle(articleId);
    }

    // notices service
    public List<ArticleResponseDto> getNoticesByMenu1(String menu1) throws SQLException {
        return articleDao.selectNoticesByMenu1(menu1);
    }

    // faq service
    public List<ArticleResponseDto> getTenFaq(String menu1, String menu2) throws SQLException {
        return articleDao.selectTenFaq(menu1, menu2);
    }


    //paging
    public int getPageStartNum(int total, int currentPage) {
        int start = (currentPage -1)*10;
        return total - start;

    }
    //현재 페이지 번호
    public int getCurrentPage(String pg) {
        int currentPage =1;

        if(pg != null) {
            currentPage = Integer.parseInt(pg);
        }

        return currentPage;
    }

    //페이지 마지막 번호
    public int getLastPageNum(int total) {
        int lastPageNum = 0;

        if(total % 10==0) {
            lastPageNum = total/10;
        }else {
            lastPageNum = total/10+1;
        }
        return lastPageNum;
    }

    //Limit 시작번호
    public int getStartNum(int currentPage) {
        return (currentPage-1)*10;
    }

    //전체 게시물 조회
    public int selectCountTotal(String cate, String menu1) {
        return dao.selectCountTotal(cate,menu1);
    }

    //페이지 그룹
    public int[] getPageGroupNum(int currentPage, int lastPageNum){

        int currentPageGroup = (int)Math.ceil(currentPage/10.0);
        int pageGroupStart = (currentPageGroup -1)*10+1;
        int pageGroupEnd = currentPageGroup * 10;

        if(pageGroupEnd > lastPageNum) {
            pageGroupEnd = lastPageNum;
        }
        int[] result = {pageGroupStart,pageGroupEnd};
        return result;

    }


}
