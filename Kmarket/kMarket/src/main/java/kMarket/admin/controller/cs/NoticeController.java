package kMarket.admin.controller.cs;

import kMarket.admin.dto.ArticleResponseDto;
import kMarket.admin.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "adminNotice",value = "/admin/cs/notice/list.do")
public class NoticeController extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    private static final ArticleService articleService = ArticleService.getInstance();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs_notice_list.jsp");
        String menu1 = req.getParameter("menu1");
        String pg = req.getParameter("pg");
        if(pg == null){
            pg = "1";
        }
        if (menu1 == null) {
            menu1 = "전체";
        }
        Integer page = Integer.parseInt(pg);

        int currentPage = articleService.getCurrentPage(pg);
        int total = articleService.selectCountTotal("notice", menu1);
        int lastPageNum = articleService.getLastPageNum(total);
        int[] result = articleService.getPageGroupNum(currentPage, lastPageNum);
        int pageStartNum = articleService.getPageStartNum(total, currentPage);
        int start = articleService.getStartNum(currentPage);

        List<ArticleResponseDto> pagedArticles = null;
        try {
            pagedArticles = articleService.getPagedArticles("notice", menu1, start);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("selected All");
        req.setAttribute("articles",pagedArticles);
        req.setAttribute("selectedValue",menu1);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum+1);
        dispatcher.forward(req,resp);

//        if (menu1 != null && !menu1.equals("전체")) {
//            logger.info("menu1 = {}", menu1);
//            try {
//                //List<ArticleResponseDto> filterdNotices = articleService.getNoticesByMenu1(menu1);
//                List<ArticleResponseDto> pagedArticles = articleService.getPagedArticles("notice", "전체", Integer.parseInt(pg));
//                logger.info("selected All");
//                req.setAttribute("articles",pagedArticles);
//                req.setAttribute("selectedValue",menu1);
//                dispatcher.forward(req,resp);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            try {
//                List<ArticleResponseDto> articles = articleService.getArticlesByCate("notice");
//                //req.setAttribute("articles", articles);
//                List<ArticleResponseDto> pagedArticles = articleService.getPagedArticles("notice", "전체", Integer.parseInt(pg));
//                logger.info("selected All");
//                req.setAttribute("articles",pagedArticles);
//                req.setAttribute("selectedValue","전체");
//                dispatcher.forward(req, resp);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
